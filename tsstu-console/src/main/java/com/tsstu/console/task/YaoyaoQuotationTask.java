package com.tsstu.console.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tsstu.common.constants.Constants.HTTP_METHOD;
import com.tsstu.common.model.Quotation;
import com.tsstu.common.util.HttpRequestUtil;
import com.tsstu.console.model.ProductCategory;
import com.tsstu.console.service.ProductCategoryService;
import com.tsstu.console.service.QuotationService;
import com.tsstu.console.util.SpringUtils;

/**
 * http://11shuju.com/
 * 11网行情数据获取任务
 * @author liwei
 *
 */
public class YaoyaoQuotationTask {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static YaoyaoQuotationTask instance = new YaoyaoQuotationTask();
	
	private Timer timer;
	
	private static QuotationService quotationService = SpringUtils.getBean(QuotationService.class);
	
	private static ProductCategoryService productCategoryService = SpringUtils.getBean(ProductCategoryService.class);
	
	private static String REQ_URL = "http://www.11shuju.com/api/wc10/demo/jquotes.aspx";
	
	public static YaoyaoQuotationTask getInstance() {
		return instance;
	}
	
	public void start() {
		logger.info("###################启动行情获取任务###################");
		timer = new Timer(true);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					JSONObject json = HttpRequestUtil.httpRequest(REQ_URL, HTTP_METHOD.GET, null);
					if (json.getBooleanValue("success")) {
						JSONArray arr = json.getJSONArray("results");
						for (int i=0; i<arr.size(); i++) {
							JSONObject obj = arr.getJSONObject(i);
							String code = obj.getString("symbol").toUpperCase();
							ProductCategory category = productCategoryService.getOne("code", code);
							if (null != category) {
								Quotation quotation = new Quotation();
								quotation.setCode(code);
								//int r = new Random().nextInt(1000);
								int r = 0;
								quotation.setBid(obj.getBigDecimal("bid").add(new BigDecimal(r / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP)));
								//Date uptime = DateUtil.parse(obj.getString("uptime"), DateUtil.FORMAT_LONG);
								Date uptime = new Date();
								quotation.setDt(uptime);
								quotation.setCtm(Integer.valueOf(uptime.getTime() / 1000 + ""));
								quotation.setLow(obj.getBigDecimal("low"));
								quotation.setHigh(obj.getBigDecimal("high"));
								quotationService.saveQuotation(quotation, category);
							}
						}
					} else {
						String code = "USDJPY";
						ProductCategory category = productCategoryService.getOne("code", code);
						Quotation quotation = new Quotation();
						quotation.setCode(code);
						int r = new Random().nextInt(1000000);
						BigDecimal bid = new BigDecimal(r / 1000.0000).setScale(4, BigDecimal.ROUND_HALF_UP);
						quotation.setBid(bid);
						Date uptime = new Date();
						quotation.setDt(uptime);
						quotation.setCtm(Integer.valueOf(uptime.getTime() / 1000 + ""));
						quotation.setLow(bid);
						quotation.setHigh(bid);
						quotationService.saveQuotation(quotation, category);
					}
				} catch (Exception e) {
					logger.error("获取行情异常：{}", e);
				}
			}
		};
		timer.schedule(task, new Date(), 5000);
	}
	
	public void stop() {
		logger.info("###################停止行情获取任务###################");
		if(null != timer) {
			timer.cancel();
		}
	}
}
