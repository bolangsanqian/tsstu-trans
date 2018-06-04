package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 任务管理
 * @author： admin
 * @date： 2017-05-13
 */
public class Task extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 任务id
	private Integer id;
	
	// 任务代码
	private String code;
	
	// 任务标题
	private String title;
	
	// 状态
	private Integer status;
	
	// 自动启动
	private Integer auto_startup;
	
	// 可删除？
	private String allow_del;
	
	// 排序
	private Integer sort;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getAuto_startup() {
		return this.auto_startup;
	}
	
	public void setAuto_startup(Integer auto_startup) {
		this.auto_startup = auto_startup;
	}
	
	public String getAllow_del() {
		return this.allow_del;
	}
	
	public void setAllow_del(String allow_del) {
		this.allow_del = allow_del;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}