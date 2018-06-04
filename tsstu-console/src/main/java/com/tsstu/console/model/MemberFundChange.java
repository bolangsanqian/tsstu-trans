package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 加减币管理(会员)
 * @author： admin
 * @date： 2017-05-12
 */
public class MemberFundChange extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 会员资金调整id
	private Integer id;
	
	// 加减币流水号
	private String flow_no;
	
	// 所属会员
	private Integer member_id;
	
	// 变动类型
	private Integer change_type;
	
	// 变动金额
	private BigDecimal amount;
	
	// 申请人
	private Integer apply_id;
	
	// 审批原因
	private String apply_reason;
	
	// 申请时间
	private Date apply_time;
	
	// 审核人
	private Integer review_id;
	
	// 审核时间
	private Date review_time;
	
	// 状态
	private Integer status;
	
	// 审核备注
	private String review_remark;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFlow_no() {
		return this.flow_no;
	}
	
	public void setFlow_no(String flow_no) {
		this.flow_no = flow_no;
	}
	
	public Integer getMember_id() {
		return this.member_id;
	}
	
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	
	public Integer getChange_type() {
		return this.change_type;
	}
	
	public void setChange_type(Integer change_type) {
		this.change_type = change_type;
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Integer getApply_id() {
		return this.apply_id;
	}
	
	public void setApply_id(Integer apply_id) {
		this.apply_id = apply_id;
	}
	
	public String getApply_reason() {
		return this.apply_reason;
	}
	
	public void setApply_reason(String apply_reason) {
		this.apply_reason = apply_reason;
	}
	
	public Date getApply_time() {
		return this.apply_time;
	}
	
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	
	public Integer getReview_id() {
		return this.review_id;
	}
	
	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}
	
	public Date getReview_time() {
		return this.review_time;
	}
	
	public void setReview_time(Date review_time) {
		this.review_time = review_time;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getReview_remark() {
		return this.review_remark;
	}
	
	public void setReview_remark(String review_remark) {
		this.review_remark = review_remark;
	}
	
}