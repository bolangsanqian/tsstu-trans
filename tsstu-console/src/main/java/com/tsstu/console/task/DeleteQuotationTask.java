package com.tsstu.console.task;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tsstu.common.constants.Constants;
import com.tsstu.console.model.Candlestick;
import com.tsstu.console.model.ProductCategory;
import com.tsstu.console.service.CandlestickService;
import com.tsstu.console.service.ProductCategoryService;
import com.tsstu.console.service.QuotationHistoryService;
import com.tsstu.console.service.QuotationService;
import com.tsstu.console.util.SpringUtils;
/**
 * 定时清除行情记录任务
 * @author liwei
 *
 */
public class DeleteQuotationTask {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static DeleteQuotationTask instance = new DeleteQuotationTask();
	
	private Timer timer;
	
	private static QuotationService quotationService = SpringUtils.getBean(QuotationService.class);
	
	private static QuotationHistoryService quotationHistoryService = SpringUtils.getBean(QuotationHistoryService.class);
	
	private static CandlestickService candlestickService = SpringUtils.getBean(CandlestickService.class);
	
	private static ProductCategoryService productCategoryService = SpringUtils.getBean(ProductCategoryService.class);
	
	private static final int QUOTATION_KEEP_COUNT = 100000;
	
	private static final int QUOTATION_HISTORY_KEEP_COUNT = 1440;
	
	public static DeleteQuotationTask getInstance() {
		return instance;
	}
	
	public void start() {
		logger.info("###################启动清除行情记录任务###################");
		timer = new Timer(true);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					List<ProductCategory> categoryList = productCategoryService.getList("status", Constants.STATUS_ENABLE);
					List<Candlestick> candlestickList = candlestickService.getList("status", Constants.STATUS_ENABLE);
					if (null != categoryList && null != candlestickList) {
						for (ProductCategory category : categoryList) {
							String code = category.getCode();
							int row = quotationService.deleteQuotation(code, QUOTATION_KEEP_COUNT);
							logger.info("删除行情记录：hq_{}_quotation: {}", code, row);
							for (Candlestick stick : candlestickList) {
								Integer minute = stick.getMinute();
								row = quotationHistoryService.deleteQuotationHistory(code, minute, QUOTATION_HISTORY_KEEP_COUNT);
								logger.info("删除行情历史记录：hq_{}_history_m{}: {}", code, minute, row);
							}
						}
					}
				} catch (Exception e) {
					logger.error("清除行情记录异常：{}", e);
				}
			}
		};
		timer.schedule(task, new Date(), 1000 * 60 * 30);
	}
	
	public void stop() {
		logger.info("###################停止清除行情记录任务###################");
		if(null != timer) {
			timer.cancel();
		}
	}
}
