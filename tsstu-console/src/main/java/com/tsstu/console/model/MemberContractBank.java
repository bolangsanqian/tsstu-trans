package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 签约管理(会员)
 * @author： admin
 * @date： 2017-05-12
 */
public class MemberContractBank extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 签约银行id
	private Integer id;
	
	// 会员编号
	private Integer member_id;
	
	// 真实姓名
	private String real_name;
	
	// 身份证
	private String identity;
	
	// 银行编号
	private String bank_no;
	
	// 银行卡号
	private String bankcard_no;
	
	// 省份
	private Integer province_code;
	
	// 城市
	private Integer city_code;
	
	// 银行支行
	private String bank_branch;
	
	// 状态
	private Integer status;
	
	// 创建时间
	private Date create_time;
	
	// 最后更新时间
	private Date last_update_time;
	
	// 审批人
	private Integer review_id;
	
	// 审批时间
	private Date review_time;
	
	// 审批备注
	private String review_remark;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getMember_id() {
		return this.member_id;
	}
	
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
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
	
	public String getBank_no() {
		return this.bank_no;
	}
	
	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}
	
	public String getBankcard_no() {
		return this.bankcard_no;
	}
	
	public void setBankcard_no(String bankcard_no) {
		this.bankcard_no = bankcard_no;
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
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getCreate_time() {
		return this.create_time;
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getLast_update_time() {
		return this.last_update_time;
	}
	
	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
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
	
}