package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 经纪人申请
 * @author： admin
 * @date： 2017-05-16
 */
public class BrokerApply extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 经纪人申请id
	private Integer id;
	
	// 客户id
	private Integer customer_id;
	
	// 用户名
	private String username;
	
	// 密码
	private String password;
	
	// 申请时间
	private Date apply_time;
	
	// 运营中心
	private Integer operation_member_id;
	
	// 所属会员
	private Integer member_id;
	
	// 代理会员
	private Integer agent_member_id;
	
	// 审核人
	private Integer review_by;
	
	// 审批人用户名
	private String review_by_name;
	
	// 审核时间
	private Date review_time;
	
	// 审批状态 
	private Integer review_status;
	
	// 审批备注
	private String review_remark;
	
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
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getApply_time() {
		return this.apply_time;
	}
	
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
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
	
	public Integer getReview_by() {
		return this.review_by;
	}
	
	public void setReview_by(Integer review_by) {
		this.review_by = review_by;
	}
	
	public Date getReview_time() {
		return this.review_time;
	}
	
	public void setReview_time(Date review_time) {
		this.review_time = review_time;
	}
	
	public Integer getReview_status() {
		return this.review_status;
	}
	
	public void setReview_status(Integer review_status) {
		this.review_status = review_status;
	}

	public String getReview_remark() {
		return review_remark;
	}

	public void setReview_remark(String review_remark) {
		this.review_remark = review_remark;
	}

	public String getReview_by_name() {
		return review_by_name;
	}

	public void setReview_by_name(String review_by_name) {
		this.review_by_name = review_by_name;
	}
	
}