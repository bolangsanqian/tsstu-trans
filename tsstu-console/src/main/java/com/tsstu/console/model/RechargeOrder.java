package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 充值订单
 * @author： admin
 * @date： 2017-05-04
 */
public class RechargeOrder extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 充值订单id
	private Integer id;
	
	// 客户id
	private Integer customer_id;
	
	// 充值流水号
	private String flow_no;
	
	// 充值金额
	private BigDecimal amount;
	
	// 手续费
	private BigDecimal fee;
	
	// 支付方式
	private Integer payment_method;
	
	// 状态
	private Integer status;
	
	// 充值时间
	private Date recharge_time;
	
	// 支付单号
	private String pay_sn;
	
	// 支付回调时间
	private Date callback_time;
	
	// 备注
	private String remark;
	
	// 支付IP地址
	private String pay_ip;
	
	// 运营中心
	private Integer operation_member_id;
	
	// 所属微会员
	private Integer member_id;
	
	// 代理会员
	private Integer agent_member_id;
	
	// 充值时间戳
	private Integer recharge_timestamp;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCustomer_id() {
		return this.customer_id;
	}
	
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getFlow_no() {
		return this.flow_no;
	}
	
	public void setFlow_no(String flow_no) {
		this.flow_no = flow_no;
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getFee() {
		return this.fee;
	}
	
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	public Integer getPayment_method() {
		return this.payment_method;
	}
	
	public void setPayment_method(Integer payment_method) {
		this.payment_method = payment_method;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getRecharge_time() {
		return this.recharge_time;
	}
	
	public void setRecharge_time(Date recharge_time) {
		this.recharge_time = recharge_time;
	}
	
	public String getPay_sn() {
		return this.pay_sn;
	}
	
	public void setPay_sn(String pay_sn) {
		this.pay_sn = pay_sn;
	}
	
	public Date getCallback_time() {
		return this.callback_time;
	}
	
	public void setCallback_time(Date callback_time) {
		this.callback_time = callback_time;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getPay_ip() {
		return this.pay_ip;
	}
	
	public void setPay_ip(String pay_ip) {
		this.pay_ip = pay_ip;
	}
	
	public Integer getOperation_member_id() {
		return this.operation_member_id;
	}
	
	public void setOperation_member_id(Integer operation_member_id) {
		this.operation_member_id = operation_member_id;
	}
	
	public Integer getMember_id() {
		return this.member_id;
	}
	
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	
	public Integer getAgent_member_id() {
		return this.agent_member_id;
	}
	
	public void setAgent_member_id(Integer agent_member_id) {
		this.agent_member_id = agent_member_id;
	}
	
	public Integer getRecharge_timestamp() {
		return this.recharge_timestamp;
	}
	
	public void setRecharge_timestamp(Integer recharge_timestamp) {
		this.recharge_timestamp = recharge_timestamp;
	}
	
}