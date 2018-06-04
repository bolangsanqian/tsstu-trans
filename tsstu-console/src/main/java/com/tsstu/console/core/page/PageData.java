package com.tsstu.console.core.page;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;


public class PageData extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private HttpServletRequest request;
	
	public PageData() {
		
	}
	
	public PageData(HttpServletRequest request) {
		this.request = request;
		this.initData(request);
	}

	public String getString(String key) {
		Object obj = this.get(key);
		String value = null;
		if(null != obj) {
			value = obj.toString();
		}
		return value;
	}
	
	public String getStringNotNull(String key) {
		String value = this.getString(key);
		return value == null ? "" : value;
	} 
	
	public Integer getInteger(String key) {
		Object obj = this.get(key);
		Integer value = null;
		if(null != obj) {
			value = new Integer(obj.toString());
		}
		return value;
	}
	
	public Integer getIntegerNotNull(String key) {
		Object obj = this.get(key);
		Integer value = 0;
		if(null != obj) {
			value = Integer.valueOf(obj.toString());
		}
		return value;
	}
	
	public int getInt(String key) {
		Object obj = this.get(key);
		int value = 0;
		if(null != obj) {
			value = Integer.valueOf(obj.toString());
		}
		return value;
	}
	
	/**
	 * 初始化请求数据
	 * @param request
	 */
	public void initData(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		Set<String> keys = map.keySet();
		Iterator<String> its = keys.iterator();
		while(its.hasNext()) {
			String key = its.next();
			String[] obj_arr = map.get(key);
			Object value = obj_arr;
			if (null != obj_arr && obj_arr.length == 1) {
				value = obj_arr[0];
				//日期查询条件处理
				if (null != value && key.endsWith("_time")) {
					if (value.toString().indexOf("~") > 0) {
						String a [] = value.toString().split("~");
						this.put(key + "_begin", a[0] + " 00:00:00");
						this.put(key + "_end", a[1] + " 23:59:59");
					}
				}
			}
			this.put(key, value);
		}
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
