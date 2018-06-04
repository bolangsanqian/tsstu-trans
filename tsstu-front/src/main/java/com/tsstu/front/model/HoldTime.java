package com.tsstu.front.model;

import java.io.Serializable;
/**
 * 持单时间
 * @author： admin
 * @date： 2017-05-09
 */
public class HoldTime extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 持单时间id
	private Integer id;
	
	// 简单商品id
	private Integer cycle_product_id;
	
	// 持单时间
	private Integer hold_time;
	
	// 时间单位（M：分钟，H：小时）
	private String time_unit;
	
	// 潜在收益比例
	private Integer profit;
	
	// 时间段列表(09:00-10:00,11:00-12:00)
	private String time_list;
	
	// 金额列表(100,200,300,400)
	private String amount_list;
	
	// 排序
	private Integer sort;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCycle_product_id() {
		return this.cycle_product_id;
	}
	
	public void setCycle_product_id(Integer cycle_product_id) {
		this.cycle_product_id = cycle_product_id;
	}
	
	public Integer getHold_time() {
		return this.hold_time;
	}
	
	public void setHold_time(Integer hold_time) {
		this.hold_time = hold_time;
	}
	
	public String getTime_unit() {
		return this.time_unit;
	}
	
	public void setTime_unit(String time_unit) {
		this.time_unit = time_unit;
	}
	
	public Integer getProfit() {
		return this.profit;
	}
	
	public void setProfit(Integer profit) {
		this.profit = profit;
	}
	
	public String getTime_list() {
		return this.time_list;
	}
	
	public void setTime_list(String time_list) {
		this.time_list = time_list;
	}
	
	public String getAmount_list() {
		return this.amount_list;
	}
	
	public void setAmount_list(String amount_list) {
		this.amount_list = amount_list;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}