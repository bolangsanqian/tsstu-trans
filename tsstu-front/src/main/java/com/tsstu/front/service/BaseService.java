package com.tsstu.front.service;

import java.util.List;
import java.util.Map;

import com.tsstu.front.core.page.Pager;

public interface BaseService<T> {
	
	// 添加
	int add(T model);
	
	int add(Map<String, Object> map);
	
	int addBatch(List<T> list);
	
	int addBatch(Object[] objs);
	
	// 删除
	int delete(int id);
	
	int delete(T model);
	
	int delete(Map<String, Object> map);
	
	int delete(String key, Object value);
	
	int deleteBatch(Object[] ids);
	
	// 修改
	int update(T model);
	
	int update(Map<String, Object> map);
	
	int update(String key, Object value);
	
	int update(String key, Object value, String key2, Object value2);
	
	// 查询列表
	List<T> getList();
	
	List<T> getList(T model);
	
	List<T> getList(Map<String, Object> map);
	
	List<T> getList(String key, Object value);
	
	// 分页查询
	List<T> getList(Pager<T> pager);
	
	List<T> getList(Pager<T> pager, T model);
	
	// 导出查询
	List<T> getExportList(Pager<T> pager);
	
	// 查询唯一
	T getOne();
	
	T getOne(int id);
	
	T getOne(T model);
	
	T getOne(Map<String, Object> map);
	
	T getOne(String key, Object value);
	
	// 获取行数
	Integer getCount();
	
	Integer getCount(T model);
	
	Integer getCount(Map<String, Object> map);
	
	Integer getCount(String key, Object value);
	
	// 是否存在
	boolean isExist();
	
	boolean isExist(int id);
	
	boolean isExist(T model);
	
	boolean isExist(Map<String, Object> map);
	
	boolean isExist(String key, Object value);
	
	boolean isExist(String key, Object value, String key2, Object value2);
	
	//统计
	Map<String, Object> getStatistics();
	
	Map<String, Object> getStatistics(int id);
	
	Map<String, Object> getStatistics(T model);
	
	Map<String, Object> getStatistics(Map<String, Object> map);
	
	Map<String, Object> getStatistics(String key, Object value);
	
}
