package com.tsstu.front.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 产品管理(时限模式)
 * @author： admin
 * @date： 2017-05-02
 */
public class CycleProduct extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 商品id
	private Integer id;
	
	// 产品代码
	private String code;
	
	// 产品名称
	private String name;
	
	// 单位
	private String unit;
	
	// 产品分类
	private Integer product_category_id;
	
	// 最大持仓金额
	private BigDecimal max_hold_amount;
	
	// 最小建仓金额
	private BigDecimal min_create_amount;
	
	// 手续费公式
	private String fee_formula;
	
	// 状态
	private Integer status;
	
	// 排序
	private Integer sort;
	
	// 创建时间
	private Date create_time;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Integer getProduct_category_id() {
		return this.product_category_id;
	}
	
	public void setProduct_category_id(Integer product_category_id) {
		this.product_category_id = product_category_id;
	}
	
	public BigDecimal getMax_hold_amount() {
		return this.max_hold_amount;
	}
	
	public void setMax_hold_amount(BigDecimal max_hold_amount) {
		this.max_hold_amount = max_hold_amount;
	}
	
	public BigDecimal getMin_create_amount() {
		return this.min_create_amount;
	}
	
	public void setMin_create_amount(BigDecimal min_create_amount) {
		this.min_create_amount = min_create_amount;
	}
	
	public String getFee_formula() {
		return this.fee_formula;
	}
	
	public void setFee_formula(String fee_formula) {
		this.fee_formula = fee_formula;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Date getCreate_time() {
		return this.create_time;
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}