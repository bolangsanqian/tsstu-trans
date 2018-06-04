package com.tsstu.console.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tsstu.common.model.Model;
/**
 * 代理会员
 * @author： admin
 * @date： 2017-05-04
 */
public class AgentMember extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 代理会员id
	private Integer id;
	
	// 登录用户id
	private Integer user_id;
	
	// 会员编号
	private Integer member_id;
	
	// 代理会员名称
	private String name;
	
	// 公司名称
	private String company_name;
	
	// 公司负责人
	private String company_leader;
	
	// 手机号码
	private String mobile;
	
	// 证件类型
	private Integer identity_type;
	
	// 证件编号
	private String identity;
	
	// 分红比例
	private BigDecimal psition_percent;
	
	// 状态
	private Integer status;
	
	// 邀请码
	private String invite_code;
	
	// 公众号二维码
	private String wx_qrcode_url;
	
	// 所属机构会员id
	private Integer operation_member_id;
	
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
	
	public Integer getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public Integer getMember_id() {
		return this.member_id;
	}
	
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompany_name() {
		return this.company_name;
	}
	
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	public String getCompany_leader() {
		return this.company_leader;
	}
	
	public void setCompany_leader(String company_leader) {
		this.company_leader = company_leader;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getIdentity_type() {
		return this.identity_type;
	}
	
	public void setIdentity_type(Integer identity_type) {
		this.identity_type = identity_type;
	}
	
	public String getIdentity() {
		return this.identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public BigDecimal getPsition_percent() {
		return this.psition_percent;
	}
	
	public void setPsition_percent(BigDecimal psition_percent) {
		this.psition_percent = psition_percent;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getInvite_code() {
		return this.invite_code;
	}
	
	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}
	
	public String getWx_qrcode_url() {
		return this.wx_qrcode_url;
	}
	
	public void setWx_qrcode_url(String wx_qrcode_url) {
		this.wx_qrcode_url = wx_qrcode_url;
	}
	
	public Integer getOperation_member_id() {
		return this.operation_member_id;
	}
	
	public void setOperation_member_id(Integer operation_member_id) {
		this.operation_member_id = operation_member_id;
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