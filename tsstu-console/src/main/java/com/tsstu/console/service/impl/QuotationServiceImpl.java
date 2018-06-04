package com.tsstu.console.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.common.constants.Constants;
import com.tsstu.common.model.Quotation;
import com.tsstu.common.model.QuotationHistory;
import com.tsstu.common.util.CommonUtils;
import com.tsstu.common.util.DateUtil;
import com.tsstu.console.cache.QuotationCache;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.QuotationMapper;
import com.tsstu.console.dao.TradingDayMapper;
import com.tsstu.console.model.Candlestick;
import com.tsstu.console.model.ProductCategory;
import com.tsstu.console.model.TradingDay;
import com.tsstu.console.service.CandlestickService;
import com.tsstu.console.service.ProductCategoryService;
import com.tsstu.console.service.QuotationHistoryService;
import com.tsstu.console.service.QuotationService;
import com.tsstu.console.util.SpringUtils;

/**
 * 实时行情业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class QuotationServiceImpl extends BaseServiceImpl<Quotation> implements QuotationService {

    @Autowired
    private QuotationMapper quotationMapper;
    
    @Autowired
    private TradingDayMapper tradingDayMapper;
    
    @Autowired
    private QuotationCache quotationCache;
    
    @Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private QuotationHistoryService quotationHistoryService;
	
    @Override
	public BaseMapper<Quotation> getDao() {
		return quotationMapper;
	}
    
    @Override
	public void saveQuotation(Quotation quotation, ProductCategory goodsCategory) {
		//行情代码
		String code = quotation.getCode();
		
		//获取当天星期几
		long hq_time = quotation.getCtm() * 1000L;
		String hq_time_str = DateUtil.format(new Date(hq_time), "HH:mm:ss");
		Date trading_date = null;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(hq_time);
		TradingDay tradingDay = getTradingDay(goodsCategory.getId(), cal, false);
		boolean isEnableTrans = tradingDay == null ? false : true;   //当前交易日是否可交易
		boolean enable = false;
		//获取当前交易日的开盘收盘时间
		if (isEnableTrans) {
			trading_date = new Date(hq_time);
			if (hq_time_str.compareToIgnoreCase(tradingDay.getOpening_time()) >= 0) {
				enable = true;
			}
		}
		
		if (!enable) {
			TradingDay preTradingDay = getTradingDay(goodsCategory.getId(), cal, true);
			boolean isEnablePreTrans = preTradingDay == null ? false : true; //上一个交易日是否可交易
			//上个交易日为跨日，且行情时间小于等于上个交易日的收盘时间，则设置开盘收盘时间为上个交易的开盘收盘时间
			if (isEnablePreTrans && preTradingDay.getClosing_day() == 1) {
				trading_date = DateUtil.addDay(new Date(hq_time), -1);
				if (hq_time_str.compareToIgnoreCase(preTradingDay.getClosing_time()) <= 0) {
					enable = true;
				}
			}
		}
		
		//非交易时间内
		if (!enable) {
			quotationCache.remove(code);
			return;
		}
		
		//前一条行情数据
		Quotation preQuotation = (Quotation)quotationCache.get(code);
		
		//缓存交易日
		String tradingDay_date_str = (String)quotationCache.get("tradingDay_date_str_" + code);
		String tradingDay_date_str_tmp = DateUtil.format(trading_date, DateUtil.FORMAT_SHORT);
		if (!tradingDay_date_str_tmp.equals(tradingDay_date_str)) {
			preQuotation = null;
			quotationCache.put("tradingDay_date_str_" + code, tradingDay_date_str_tmp);
			quotationCache.remove(code);
		}
		//重置最低、最高价
		if (preQuotation != null) {
			//最高价
			if (quotation.getBid().compareTo(preQuotation.getHigh()) == -1) {
				quotation.setHigh(preQuotation.getHigh());
			}
			//最低价
			if (quotation.getBid().compareTo(preQuotation.getLow()) == 1 ) { 
				quotation.setLow(preQuotation.getLow());
			}
		} 
		
		//判断是否为实时保存行情数据，如果为true，则实时保存行情数据,否则保存到行情堆栈中，待定时保存
		quotation.setCt(new Date());
		quotationMapper.add(quotation);
		
		quotationCache.put("realtime_" + code, quotation);
		saveQuotationByM(quotation);
	}
    
    /**
	 * 获取指定时间对应的交易日
	 * @param goods_category_id
	 * @param cal
	 * @return
	 */
	private TradingDay getTradingDay(int goods_category_id, Calendar cal, boolean preWeekDay) {
		if (preWeekDay) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		int day_week= cal.get(Calendar.DAY_OF_WEEK) - 1;
		day_week = day_week == 0 ? 7 : day_week;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product_category_id", goods_category_id);
		map.put("week", day_week);
		map.put("status", 1);
		TradingDay tradingDay = tradingDayMapper.getOne(map);
		return tradingDay;
	}
	
	/**
	 * 保存k线图到redis
	 * @param quotation
	 */
	@SuppressWarnings("unchecked")
	public void saveQuotationByM(Quotation quotation) {
		String code = quotation.getCode();
		List<Candlestick> candlestickJobList = SpringUtils.getBean(CandlestickService.class).getList("status", Constants.STATUS_ENABLE);
		QuotationHistory history = null;
		String key = null;
		for (Candlestick job : candlestickJobList) {
			int m = job.getMinute();
			int ctm = quotation.getCtm() / m / 60 * m * 60;
			if (m == 1440) {
				String tradingDay_date_str = (String)quotationCache.get("tradingDay_date_str_" + quotation.getCode());
				ctm =  (int)(DateUtil.parse(tradingDay_date_str + " 00:00:00", DateUtil.FORMAT_LONG).getTime() / 1000);
			}
			key = "history_" + code + "_" + m;
			List<QuotationHistory> historyList = (List<QuotationHistory>)quotationCache.get(key);
			if (null == historyList) {
				historyList = new ArrayList<QuotationHistory>();
			}
			if (historyList.size() == 0 || ctm > historyList.get(historyList.size() - 1).getCtm()) { //新增
				history = new QuotationHistory();
				history.setCtm(ctm);
				history.setCode(code);
				history.setOpen(quotation.getBid());
				history.setHigh(quotation.getBid());
				history.setLow(quotation.getBid());
				history.setClose(quotation.getBid());
				history.setFirstdt(quotation.getCt());
				history.setLastdt(quotation.getCt());
				history.setCt(quotation.getCt());
				history.setMinute(m);
				historyList.add(history);
				/*if (historyList.size() > 1) {
					int row = quotationHistoryService.add(historyList.get(historyList.size() - 2));
					logger.info("code: {}, minute: {}, row: {}", code, m, row);
				}*/
				quotationHistoryService.add(history);
			} else {  //更新
				history = historyList.get(historyList.size() - 1);
				BigDecimal newBid = quotation.getBid();
				if (quotation.getBid().floatValue() > history.getHigh().floatValue()) { //重置最高价
					history.setHigh(newBid);
				}
				if (quotation.getBid().floatValue() < history.getLow().floatValue()) { //重置最低价
					history.setLow(newBid);
				}
				history.setLastdt(quotation.getCt());  //重置最后更新时间
				history.setClose(newBid);			   //重置收盘价
				quotationHistoryService.update(history);
			}
			
			String now_key = "history_" + code + "_" + m + "_now";
			quotationCache.put(now_key, history);
			quotationCache.put(key, historyList);
		}
	}
	
	@Override
	public boolean initToCache() {
		try {
			List<ProductCategory> categoryList = productCategoryService.getEnableList();
			if (!CommonUtils.isNullOrEmpty(categoryList)) {
				for (ProductCategory category : categoryList) {
					String code = category.getCode();
					
					//实时行情数据
					Quotation quotation = this.getLastQuotation(code);
					if (null != quotation) {
						quotation.setCode(code);
						quotationCache.put("realtime_" + code, quotation);
					}
					
					//k线行情数据
					List<Candlestick> candlestickJobList = SpringUtils.getBean(CandlestickService.class).getList("status", Constants.STATUS_ENABLE);
					for (Candlestick job : candlestickJobList) {
						int minute = job.getMinute();
						List<QuotationHistory> historyList = new ArrayList<QuotationHistory>();
						List<QuotationHistory> list = quotationHistoryService.getListByCodeAndMinute(code, minute, 60);
						if (null != list && list.size() > 0) {
							for (int i = list.size() - 1; i >= 0; i--) {
								QuotationHistory history = list.get(i);
								history.setCode(code);
								history.setMinute(minute);
								historyList.add(history);
							}
							String key = "history_" + code + "_" + minute;
							String key_now = "history_" + code + "_" + minute + "_now";
							quotationCache.put(key_now, list.get(list.size() - 1));
							quotationCache.put(key, historyList);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("初始化k线行情数据到缓存异常：{}", e);
		}
		return true;
	}

	@Override
	public int deleteQuotation(String code, int keepCount) {
		keepCount = keepCount - 1;
		return quotationMapper.deleteQuotation(code, keepCount);
	}

	@Override
	public Quotation getLastQuotation(String code) {
		return quotationMapper.getLastQuotation(code);
	}
}
