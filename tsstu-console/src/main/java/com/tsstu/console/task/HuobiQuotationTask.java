package com.tsstu.console.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.tsstu.common.constants.Constants.HTTP_METHOD;
import com.tsstu.common.model.Quotation;
import com.tsstu.common.util.HttpRequestUtil;
import com.tsstu.console.model.ProductCategory;
import com.tsstu.console.service.ProductCategoryService;
import com.tsstu.console.service.QuotationService;
import com.tsstu.console.util.SpringUtils;
/**
 * https://www.huobi.com/
 * 火币网行情数据获取任务
 * @author liwei
 *
 */
public class HuobiQuotationTask {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static HuobiQuotationTask instance = new HuobiQuotationTask();
	
	private Timer timer;
	
	private static QuotationService quotationService = SpringUtils.getBean(QuotationService.class);
	
	private static ProductCategoryService productCategoryService = SpringUtils.getBean(ProductCategoryService.class);
	
	//https://www.huobi.com/qt/staticmarket/ticker_btc_json.js?r=kbx9nf7akpm
	//https://www.huobi.com/qt/usdmarket/ticker_btc_json.js?r=eb2e0i9dlb2fn7b9
	//https://www.huobi.com/qt/staticmarket/ticker_ltc_json.js?r=m34k1wi0xgk3xr
	private static String btccny_URL = "https://www.huobi.com/qt/staticmarket/ticker_btc_json.js?r=";
	private static String btcusd_URL = "https://www.huobi.com/qt/usdmarket/ticker_btc_json.js?r=";
	private static String ltccny_URL = "https://www.huobi.com/qt/staticmarket/ticker_ltc_json.js?r=";
	
	public static HuobiQuotationTask getInstance() {
		return instance;
	}
	
	public void start() {
		logger.info("###################启动行情获取任务###################");
		timer = new Timer(true);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					//比特币人民币现货
					JSONObject btccny_json = HttpRequestUtil.httpRequest(btccny_URL + System.currentTimeMillis(), HTTP_METHOD.GET, null);
					saveQuotation(btccny_json, "BTCCNY");
					
					//比特币美元现货
					JSONObject btcusd_json = HttpRequestUtil.httpRequest(btcusd_URL + System.currentTimeMillis(), HTTP_METHOD.GET, null);
					saveQuotation(btcusd_json, "BTCUSD");
					
					//莱特币人民币现货
					JSONObject ltccny_json = HttpRequestUtil.httpRequest(ltccny_URL + System.currentTimeMillis(), HTTP_METHOD.GET, null);
					saveQuotation(ltccny_json, "LTCCNY");
					
				} catch (Exception e) {
					logger.error("获取行情异常：{}", e);
				}
			}
		};
		timer.schedule(task, new Date(), 1000);
	}
	
	public void stop() {
		logger.info("###################停止行情获取任务###################");
		if(null != timer) {
			timer.cancel();
		}
	}
	
	private void saveQuotation(JSONObject json, String code) {
		JSONObject obj = json.getJSONObject("ticker");
		ProductCategory category = productCategoryService.getOne("code", code);
		if (null != category) {
			Quotation quotation = new Quotation();
			quotation.setCode(code);
			//int r = new Random().nextInt(1000);
			int r = 0;
			quotation.setBid(obj.getBigDecimal("last").add(new BigDecimal(r / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP)));
			Date uptime = new Date();
			quotation.setDt(uptime);
			quotation.setCtm(Integer.valueOf(uptime.getTime() / 1000 + ""));
			quotation.setLow(obj.getBigDecimal("low"));
			quotation.setHigh(obj.getBigDecimal("high"));
			quotationService.saveQuotation(quotation, category);
		}
	}
}
