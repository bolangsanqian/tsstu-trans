package com.tsstu.console.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsstu.common.constants.Constants;
import com.tsstu.common.model.Quotation;
import com.tsstu.common.model.QuotationHistory;
import com.tsstu.common.util.CommonUtils;
import com.tsstu.console.model.Candlestick;
import com.tsstu.console.model.ProductCategory;
import com.tsstu.console.service.CandlestickService;
import com.tsstu.console.service.ProductCategoryService;
import com.tsstu.console.service.QuotationHistoryService;
import com.tsstu.console.service.QuotationService;
import com.tsstu.console.util.SpringUtils;

@SuppressWarnings("all")
@Component
public class QuotationCache extends BaseCache {

	private static final String CACHE_NAME = "quotation";
	
	/**
	 * 保存到缓存
	 * @param cacheName 缓存名称
	 * @param key 
	 * @param value
	 */
	public void put(String key, Object value) {
		ehCacheManager.put(CACHE_NAME, key, value);
	}
	
	/**
	 * 从缓存获取值
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return ehCacheManager.getValue(CACHE_NAME, key);
	}
	
	/**
	 * 从缓存中移除
	 * @param cacheName
	 * @param key
	 */
	public void remove(String key) {
		ehCacheManager.remove(CACHE_NAME, key);
	}
}
