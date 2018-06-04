package com.tsstu.front.cache.ehcache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * EhCache工具类,主要包含取元素和插入元素。
 * 
 * @author xuyi
 *
 */
@Service
public class EhCache {
	
	@Autowired
	private CacheManager cacheManager;

	/**
	 * 根据缓存名字获得某个缓存
	 * 
	 * @param cacheName
	 * @return
	 */
	public Cache getCache(String cacheName) {
		return cacheManager.getCache(cacheName);
	}

	/**
	 * 根据缓存名字,元素的key值,获得缓存中对应的value值对象。
	 * 
	 * @param cacheName
	 * @param key
	 * @param isRemoveKey
	 * @return
	 */
	public Object getValue(String cacheName, Object key, boolean isRemoveKey) {
		Cache cache = getCache(cacheName);
		Element e = null;
		if (isRemoveKey) {
			e = cache.get(key);
		} else {
			e = cache.getQuiet(key);
		}
		if (e == null) {
			return null;
		}
		return e.getObjectValue();
	}

	/**
	 * 根据缓存名字,元素的key值,获得缓存中对应的value值对象。
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Object getValue(String cacheName, Object key) {
		return getValue(cacheName, key, false);
	}
	
	/**
	 * 静态的获取元素，不会产生update.
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Element getElementByQuite(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		return cache.getQuiet(key);
	}

	/**
	 * 动态的获取元素，会产生update.
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Element getElementByDynic(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		return cache.get(key);
	}

	/**
	 * 向某个缓存中添加元素
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void put(String cacheName, Object key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 移除某个缓存中的元素
	 * 
	 * @param cacheName
	 * @param key
	 */
	public void remove(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		if (cache != null) {
			cache.remove(key);
		}
	}

	/**
	 * 判断某个缓存是否包含某个元素
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public boolean contains(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		Element e = cache.get(key);
		if (e != null) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getKeys(String cacheName, Class<T> t) {
		Cache cache = getCache(cacheName);
		return (List<T>) cache.getKeys();
	}
}