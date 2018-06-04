package com.tsstu.common.model;

public abstract class Model {
	// 客户编号
	private String cust_no;
	
	// 手机号码
	private String mobile;
	
	// 昵称
	private String nick_name;
	
	// 真实信息
	private String real_name;
	
	// 运营中心id
	private Integer operation_member_id;
	
	// 运营中心id
	private Integer exchange_member_id;
	
	// 所属会员id
	private Integer member_id;
	
	// 代理会员id
	private Integer agent_member_id;

	public String getCust_no() {
		return cust_no;
	}

	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public Integer getOperation_member_id() {
		return operation_member_id;
	}

	public void setOperation_member_id(Integer operation_member_id) {
		this.operation_member_id = operation_member_id;
	}

	public Integer getExchange_member_id() {
		return exchange_member_id;
	}

	public void setExchange_member_id(Integer exchange_member_id) {
		this.exchange_member_id = exchange_member_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Integer getAgent_member_id() {
		return agent_member_id;
	}

	public void setAgent_member_id(Integer agent_member_id) {
		this.agent_member_id = agent_member_id;
	}

}
