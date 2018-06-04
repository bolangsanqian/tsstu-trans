package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 交易日管理
 * @author： admin
 * @date： 2017-05-02
 */
public class TradingDay extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 交易日id
	private Integer id;
	
	// 产品分类
	private Integer product_category_id;
	
	// 星期
	private Integer week;
	
	// 开盘时间
	private String opening_time;
	
	// 收盘时间
	private String closing_time;
	
	// 收盘日期
	private Integer closing_day;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getProduct_category_id() {
		return this.product_category_id;
	}
	
	public void setProduct_category_id(Integer product_category_id) {
		this.product_category_id = product_category_id;
	}
	
	public Integer getWeek() {
		return this.week;
	}
	
	public void setWeek(Integer week) {
		this.week = week;
	}
	
	public String getOpening_time() {
		return this.opening_time;
	}
	
	public void setOpening_time(String opening_time) {
		this.opening_time = opening_time;
	}
	
	public String getClosing_time() {
		return this.closing_time;
	}
	
	public void setClosing_time(String closing_time) {
		this.closing_time = closing_time;
	}
	
	public Integer getClosing_day() {
		return this.closing_day;
	}
	
	public void setClosing_day(Integer closing_day) {
		this.closing_day = closing_day;
	}
	
}