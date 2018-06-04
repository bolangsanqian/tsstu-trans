package com.tsstu.console.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsstu.console.cache.ehcache.EhCache;
import com.tsstu.common.constants.Constants;
import com.tsstu.common.constants.Constants.CacheConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.console.core.entity.DictMember;
import com.tsstu.console.model.AgentMember;
import com.tsstu.console.model.Config;
import com.tsstu.console.model.HoldTime;
import com.tsstu.console.model.Member;
import com.tsstu.console.model.Permission;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.ConfigService;
import com.tsstu.console.service.HoldTimeService;
import com.tsstu.console.service.MemberService;

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
