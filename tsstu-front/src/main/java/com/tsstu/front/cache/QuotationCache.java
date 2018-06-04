package com.tsstu.front.cache;

import org.springframework.stereotype.Component;

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
