package com.tsstu.front.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsstu.front.cache.ehcache.EhCache;

@SuppressWarnings("all")
@Component
public class BaseCache {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected EhCache ehCacheManager;

	/**
	 * 保存到缓存
	 * @param cacheName 缓存名称
	 * @param key 
	 * @param value
	 */
	public void put(String cacheName, String key, Object value) {
		ehCacheManager.put(cacheName, key, value);
	}
	
	/**
	 * 从缓存获取值
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Object get(String cacheName, String key) {
		return ehCacheManager.getValue(cacheName, key);
	}
	
	/**
	 * 从缓存中移除
	 * @param cacheName
	 * @param key
	 */
	public void remove(String cacheName, String key) {
		ehCacheManager.remove(cacheName, key);
	}
}
