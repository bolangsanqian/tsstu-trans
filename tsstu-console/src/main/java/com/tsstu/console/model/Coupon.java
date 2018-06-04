package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 优惠券
 * @author： admin
 * @date： 2017-05-13
 */
public class Coupon extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 优惠劵id
	private Integer id;
	
	// 名称
	private String name;
	
	// 金额
	private BigDecimal amount;
	
	// 最低使用金额
	private BigDecimal min_use_amount;
	
	// 优惠券类型
	private Integer coupon_type;
	
	// 状态
	private Integer status;
	
	// 创建人
	private Integer create_by;
	
	// 创建时间
	private Date create_time;
	
	// 备注
	private String remark;
	
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
	
	public BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getMin_use_amount() {
		return this.min_use_amount;
	}
	
	public void setMin_use_amount(BigDecimal min_use_amount) {
		this.min_use_amount = min_use_amount;
	}
	
	public Integer getCoupon_type() {
		return this.coupon_type;
	}
	
	public void setCoupon_type(Integer coupon_type) {
		this.coupon_type = coupon_type;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getCreate_by() {
		return this.create_by;
	}
	
	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}
	
	public Date getCreate_time() {
		return this.create_time;
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}