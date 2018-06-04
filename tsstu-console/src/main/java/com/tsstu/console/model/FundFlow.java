package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 资金流水
 * @author： admin
 * @date： 2017-05-04
 */
public class FundFlow extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 资金流水id
	private Integer id;
	
	// 顾客id
	private Integer customer_id;
	
	// 流水号
	private String flow_no;
	
	// 交易单号
	private String trans_no;
	
	// 金额
	private BigDecimal amount;
	
	// 交易类型
	private Integer change_type;
	
	// 可用余额
	private BigDecimal balance;
	
	// 占用资金
	private BigDecimal frozen_money;
	
	// 交易时间
	private Date trans_time;
	
	// 所属会员ID
	private Integer member_id;
	
	// 运营中心
	private Integer operation_member_id;
	
	// 代理会员
	private Integer agent_member_id;
	
	// 交易时间戳
	private Integer trans_timestamp;
	
	// 备注
	private String remark;
	
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
	
	public String getTrans_no() {
		return this.trans_no;
	}
	
	public void setTrans_no(String trans_no) {
		this.trans_no = trans_no;
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Integer getChange_type() {
		return this.change_type;
	}
	
	public void setChange_type(Integer change_type) {
		this.change_type = change_type;
	}
	
	public BigDecimal getBalance() {
		return this.balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public BigDecimal getFrozen_money() {
		return this.frozen_money;
	}
	
	public void setFrozen_money(BigDecimal frozen_money) {
		this.frozen_money = frozen_money;
	}
	
	public Date getTrans_time() {
		return this.trans_time;
	}
	
	public void setTrans_time(Date trans_time) {
		this.trans_time = trans_time;
	}
	
	public Integer getMember_id() {
		return this.member_id;
	}
	
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	
	public Integer getOperation_member_id() {
		return this.operation_member_id;
	}
	
	public void setOperation_member_id(Integer operation_member_id) {
		this.operation_member_id = operation_member_id;
	}
	
	public Integer getAgent_member_id() {
		return this.agent_member_id;
	}
	
	public void setAgent_member_id(Integer agent_member_id) {
		this.agent_member_id = agent_member_id;
	}
	
	public Integer getTrans_timestamp() {
		return this.trans_timestamp;
	}
	
	public void setTrans_timestamp(Integer trans_timestamp) {
		this.trans_timestamp = trans_timestamp;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}