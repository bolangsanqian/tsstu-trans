package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 会员提现订单
 * @author： admin
 * @date： 2017-05-12
 */
public class MemberWithdrawOrder extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 提现订单id
	private Integer id;
	
	// 提现流水号
	private String flow_no;
	
	// 会员编号
	private Integer member_id;
	
	// 提现金额
	private BigDecimal amount;
	
	// 手续费
	private BigDecimal fee;
	
	// 银行编号
	private String bank_no;
	
	// 省份（冗余字段）
	private Integer province_code;
	
	// 城市（冗余字段）
	private Integer city_code;
	
	// 银行支行（冗余字段）
	private String bank_branch;
	
	// 银行卡号
	private String bankcard_no;
	
	// 姓名
	private String real_name;
	
	// 身份证
	private String identity;
	
	// 申请时间
	private Date apply_time;
	
	// 提现状态
	private Integer status;
	
	// 审批标识（0：非审批单，1：审批单）
	private Integer review_sign;
	
	// 审批人
	private Integer review_id;
	
	// 审批时间
	private Date review_time;
	
	// 审批备注
	private String review_remark;
	
	// 审批状态
	private Integer review_status;
	
	// 付款方式
	private Integer pay_method;
	
	// 第三方单号
	private String third_flow_no;
	
	// 付款人
	private Integer pay_id;
	
	// 付款时间
	private Date pay_time;
	
	// 付款备注
	private String pay_remark;
	
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
	
	public String getBank_no() {
		return this.bank_no;
	}
	
	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}
	
	public Integer getProvince_code() {
		return this.province_code;
	}
	
	public void setProvince_code(Integer province_code) {
		this.province_code = province_code;
	}
	
	public Integer getCity_code() {
		return this.city_code;
	}
	
	public void setCity_code(Integer city_code) {
		this.city_code = city_code;
	}
	
	public String getBank_branch() {
		return this.bank_branch;
	}
	
	public void setBank_branch(String bank_branch) {
		this.bank_branch = bank_branch;
	}
	
	public String getBankcard_no() {
		return this.bankcard_no;
	}
	
	public void setBankcard_no(String bankcard_no) {
		this.bankcard_no = bankcard_no;
	}
	
	public String getReal_name() {
		return this.real_name;
	}
	
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	public String getIdentity() {
		return this.identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public Date getApply_time() {
		return this.apply_time;
	}
	
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getReview_sign() {
		return this.review_sign;
	}
	
	public void setReview_sign(Integer review_sign) {
		this.review_sign = review_sign;
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
	
	public String getReview_remark() {
		return this.review_remark;
	}
	
	public void setReview_remark(String review_remark) {
		this.review_remark = review_remark;
	}
	
	public Integer getReview_status() {
		return this.review_status;
	}
	
	public void setReview_status(Integer review_status) {
		this.review_status = review_status;
	}
	
	public Integer getPay_method() {
		return this.pay_method;
	}
	
	public void setPay_method(Integer pay_method) {
		this.pay_method = pay_method;
	}
	
	public String getThird_flow_no() {
		return this.third_flow_no;
	}
	
	public void setThird_flow_no(String third_flow_no) {
		this.third_flow_no = third_flow_no;
	}
	
	public String getPay_remark() {
		return this.pay_remark;
	}
	
	public void setPay_remark(String pay_remark) {
		this.pay_remark = pay_remark;
	}

	public Integer getPay_id() {
		return pay_id;
	}

	public void setPay_id(Integer pay_id) {
		this.pay_id = pay_id;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	
}