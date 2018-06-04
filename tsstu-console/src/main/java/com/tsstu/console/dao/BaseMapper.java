package com.tsstu.console.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageRowBounds;
/**
 * Mapper基类
 * @author liwei
 * 日期：2017年4月14日10:47:22
 * @param <T>
 */
public interface BaseMapper<T> {

	/****** 删除相关方法  ******/
	/**
	 * 根据model添加
	 * @param model
	 * @return
	 */
	int add(T model);
	
	/**
	 * 根据map添加
	 * @param map
	 * @return
	 */
	int add(Map<String, Object> map);
	
	/**
	 * 批量添加
	 * @param list
	 * @return
	 */
	int addBatch(List<T> list);
	
	/**
	 * 批量添加
	 * @param objs
	 * @return
	 */
	int addBatch(Object[] objs);
	
	/****** 删除相关方法  ******/
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	int delete(int id);
	
	/**
	 * 根据model删除
	 * @param model
	 * @return
	 */
	int delete(T model);
	
	/**
	 * 根据map删除
	 * @param map
	 * @return
	 */
	int delete(Map<String, Object> map);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteBatch(Object[] ids);
	
	/****** 修改相关方法  ******/
	/**
	 * 根据model修改
	 * @param model
	 * @return
	 */
	int update(T model);
	
	/**
	 * 根据map修改
	 * @param map
	 * @return
	 */
	
	int update(Map<String, Object> map);
	
	/****** 查询相关方法  ******/
	/**
	 * 列表查询
	 * @return
	 */
	List<T> getList();
	
	/**
	 * 根据model查询列表
	 * @param model
	 * @return
	 */
	List<T> getList(T model);
	
	/**
	 * 根据map查询列表
	 * @param map
	 * @return
	 */
	List<T> getList(Map<String, Object> map);
	
	/****** 分页查询相关方法  ******/
	/**
	 * 根据分页对象、map查询
	 * @param rowBounds
	 * @param condition
	 * @return
	 */
	List<T> getList(PageRowBounds rowBounds, Map<String, Object> condition);
	
	/****** 唯一查询相关方法  ******/
	/**
	 * 唯一查询
	 * @return
	 */
	T getOne();
	
	/**
	 * 根据id唯一查询
	 * @param id
	 * @return
	 */
	T getOne(int id);
	
	/**
	 * 根据model唯一查询
	 * @param model
	 * @return
	 */
	T getOne(T model);
	
	/**
	 * 根据map唯一查询
	 * @param map
	 * @return
	 */
	T getOne(Map<String, Object> map);
	
	/****** 获取行情相关方法  ******/
	/**
	 * 获取行数
	 * @return
	 */
	Integer getCount();
	
	/**
	 * 根据model获取行数
	 * @param model
	 * @return
	 */
	Integer getCount(T model);
	
	/**
	 * 根据map获取行数
	 * @param map
	 * @return
	 */
	Integer getCount(Map<String, Object> map);
	
	/****** 判断是否存在相关方法  ******/
	/**
	 * 判断是否存在
	 * @return
	 */
	boolean isExist();
	
	/**
	 * 根据id判断是否存在
	 * @param id
	 * @return
	 */
	boolean isExist(int id);
	
	/**
	 * 根据model判断是否存在
	 * @param model
	 * @return
	 */
	boolean isExist(T model);
	
	/**
	 * 根据map判断是否存在
	 * @param map
	 * @return
	 */
	boolean isExist(Map<String, Object> map);
	
	/****** 统计相关方法  ******/
	/**
	 * 统计
	 * @return
	 */
	Map<String, Object> getStatistics();
	
	/**
	 * 根据id统计
	 * @param id
	 * @return
	 */
	Map<String, Object> getStatistics(int id);
	
	/**
	 * 根据model统计
	 * @param model
	 * @return
	 */
	Map<String, Object> getStatistics(T model);
	
	/**
	 * 根据map统计
	 * @param map
	 * @return
	 */
	Map<String, Object> getStatistics(Map<String, Object> map);
	
	/**
	 * 根据key、value统计
	 * @param key
	 * @param value
	 * @return
	 */
	Map<String, Object> getStatistics(String key, Object value);
	
}
