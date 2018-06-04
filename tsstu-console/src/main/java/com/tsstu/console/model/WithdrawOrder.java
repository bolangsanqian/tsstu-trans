package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 提现订单(客户)
 * @author： admin
 * @date： 2017-05-12
 */
public class WithdrawOrder extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 提现订单id
	private Integer id;
	
	// 提现流水号
	private String flow_no;
	
	// 客户表_客户id
	private Integer customer_id;
	
	// 提现金额
	private BigDecimal amount;
	
	// 手续费
	private BigDecimal fee;
	
	// 申请时间
	private Date apply_time;
	
	// 提现状态
	private Integer status;
	
	// 审批标识
	private Integer review_sign;
	
	// 审批人
	private Integer review_id;
	
	// 审批时间
	private Date review_time;
	
	// 审批备注
	private String review_remark;
	
	// 审批状态
	private Integer review_status;
	
	// 自动到账
	private Integer auto_arrival;
	
	// 提现方式
	private Integer withdraw_method;
	
	// 第三方单号
	private String third_flow_no;
	
	// 付款人
	private Integer pay_id;
	
	// 付款时间
	private Date pay_time;
	
	// 付款备注
	private String pay_remark;
	
	// 运营中心
	private Integer operation_member_id;
	
	// 所属会员
	private Integer member_id;
	
	// 代理会员
	private Integer agent_member_id;
	
	// 提现openid
	private String withdraw_openid;
	
	// 公众号应用ID
	private String wx_appid;
	
	// 微信支付商户号
	private String wxpay_mch_id;
	
	// 微信支付密钥
	private String wxpay_api_key;
	
	// 微信支付证书路径
	private String wxpay_cert_path;
	
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
	
	public Integer getCustomer_id() {
		return this.customer_id;
	}
	
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
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
	
	public Integer getAuto_arrival() {
		return this.auto_arrival;
	}
	
	public void setAuto_arrival(Integer auto_arrival) {
		this.auto_arrival = auto_arrival;
	}
	
	public Integer getWithdraw_method() {
		return this.withdraw_method;
	}
	
	public void setWithdraw_method(Integer withdraw_method) {
		this.withdraw_method = withdraw_method;
	}
	
	public String getThird_flow_no() {
		return this.third_flow_no;
	}
	
	public void setThird_flow_no(String third_flow_no) {
		this.third_flow_no = third_flow_no;
	}
	
	public Integer getPay_id() {
		return this.pay_id;
	}
	
	public void setPay_id(Integer pay_id) {
		this.pay_id = pay_id;
	}
	
	public Date getPay_time() {
		return this.pay_time;
	}
	
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	
	public String getPay_remark() {
		return this.pay_remark;
	}
	
	public void setPay_remark(String pay_remark) {
		this.pay_remark = pay_remark;
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
	
	public String getWithdraw_openid() {
		return this.withdraw_openid;
	}
	
	public void setWithdraw_openid(String withdraw_openid) {
		this.withdraw_openid = withdraw_openid;
	}
	
	public String getWx_appid() {
		return this.wx_appid;
	}
	
	public void setWx_appid(String wx_appid) {
		this.wx_appid = wx_appid;
	}
	
	public String getWxpay_mch_id() {
		return this.wxpay_mch_id;
	}
	
	public void setWxpay_mch_id(String wxpay_mch_id) {
		this.wxpay_mch_id = wxpay_mch_id;
	}
	
	public String getWxpay_api_key() {
		return this.wxpay_api_key;
	}
	
	public void setWxpay_api_key(String wxpay_api_key) {
		this.wxpay_api_key = wxpay_api_key;
	}
	
	public String getWxpay_cert_path() {
		return this.wxpay_cert_path;
	}
	
	public void setWxpay_cert_path(String wxpay_cert_path) {
		this.wxpay_cert_path = wxpay_cert_path;
	}
	
}