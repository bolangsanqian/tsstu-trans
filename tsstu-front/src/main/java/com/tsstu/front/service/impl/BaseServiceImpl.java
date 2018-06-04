package com.tsstu.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageRowBounds;
import com.tsstu.front.core.page.Pager;
import com.tsstu.front.dao.BaseMapper;
import com.tsstu.front.service.BaseService;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public abstract BaseMapper<T> getDao();
	
	public int add(T model) {
		return getDao().add(model);
	}

	public int add(Map<String, Object> map) {
		return getDao().add(map);
	}
	
	public int addBatch(List<T> list) {
		return getDao().addBatch(list);
	}

	public int addBatch(Object[] objs) {
		return getDao().addBatch(objs);
	}

	public int delete(int id) {
		return getDao().delete(id);
	}

	public int delete(T model) {
		return getDao().delete(model);
	}

	public int delete(Map<String, Object> map) {
		return getDao().delete(map);
	}

	public int delete(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return this.delete(map);
	}

	public int deleteBatch(Object[] ids) {
		return getDao().deleteBatch(ids);
	}

	public int update(T model) {
		return getDao().update(model);
	}

	public int update(Map<String, Object> map) {
		return getDao().update(map);
	}

	public int update(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return this.update(map);
	}
	
	public int update(String key, Object value, String key2, Object value2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		map.put(key2, value2);
		return this.update(map);
	}

	public List<T> getList() {
		return getDao().getList();
	}

	public List<T> getList(T model) {
		return getDao().getList(model);
	}

	public List<T> getList(Map<String, Object> map) {
		return getDao().getList(map);
	}

	public List<T> getList(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value.toString());
		return this.getList(map);
	}

	public T getOne() {
		return getDao().getOne();
	}

	public T getOne(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return this.getOne(map);
	}

	public T getOne(T model) {
		return getDao().getOne(model);
	}

	public T getOne(Map<String, Object> map) {
		return getDao().getOne(map);
	}

	public T getOne(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return this.getOne(map);
	}

	public Integer getCount() {
		return getDao().getCount();
	}

	public Integer getCount(T model) {
		return getDao().getCount(model);
	}

	public Integer getCount(Map<String, Object> map) {
		return getDao().getCount(map);
	}

	public Integer getCount(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return this.getCount(map);
	}

	public boolean isExist() {
		return getDao().isExist();
	}

	public boolean isExist(int id) {
		return getDao().isExist(id);
	}

	public boolean isExist(T model) {
		return getDao().isExist(model);
	}

	public boolean isExist(Map<String, Object> map) {
		return getDao().isExist(map);
	}

	public boolean isExist(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return this.isExist(map);
	}
	
	public boolean isExist(String key, Object value, String key2, Object value2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		map.put(key2, value2);
		return this.isExist(map);
	}

	public Map<String, Object> getStatistics() {
		return getDao().getStatistics();
	}

	public Map<String, Object> getStatistics(int id) {
		return getDao().getStatistics(id);
	}

	public Map<String, Object> getStatistics(T model) {
		return getDao().getStatistics(model);
	}

	public Map<String, Object> getStatistics(Map<String, Object> map) {
		return getDao().getStatistics(map);
	}

	public Map<String, Object> getStatistics(String key, Object value) {
		return getDao().getStatistics(key, value);
	}

	public List<T> getList(Pager<T> pager) {
		PageRowBounds rowBounds = pager.getRowBounds();
		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String, Object> map = pager.getCondition();
		if (null != map) {
			for (String key : map.keySet()) {
				condition.put(key, map.get(key).toString());
			}
		}
		List<T> list = getDao().getList(rowBounds, condition);
		pager.initPageAfter(list);
		return list;
	}
	
	@Override
	public List<T> getList(Pager<T> pager, T model) {
		List<T> list = this.getList(pager);
		pager.setModel(model);
		return list;
	}
	
	public List<T> getExportList(Pager<T> pager) {
		pager.initPageBefore();
		pager.initExportQuery();
		return this.getList(pager);
	}
	
}
