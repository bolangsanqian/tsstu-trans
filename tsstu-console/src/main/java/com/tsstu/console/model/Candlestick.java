package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * K线管理
 * @author： admin
 * @date： 2017-05-13
 */
public class Candlestick extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 任务id
	private Integer id;
	
	// 名称
	private String name;
	
	// 周期(分钟)
	private Integer minute;
	
	// 状态
	private Integer status;
	
	// 是否在前端显示
	private Integer is_show;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getMinute() {
		return this.minute;
	}
	
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getIs_show() {
		return this.is_show;
	}
	
	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
	
}