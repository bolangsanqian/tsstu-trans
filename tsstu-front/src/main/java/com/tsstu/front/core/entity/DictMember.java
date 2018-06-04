package com.tsstu.front.core.entity;

import java.io.Serializable;

public class DictMember implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer operation_member_id;
	
	private Integer member_id;
	
	private Integer agent_member_id;
	
	private String item_name;
	
	private String item_value;

	public Integer getOperation_member_id() {
		return operation_member_id;
	}

	public void setOperation_member_id(Integer operation_member_id) {
		this.operation_member_id = operation_member_id;
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

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_value() {
		return item_value;
	}

	public void setItem_value(String item_value) {
		this.item_value = item_value;
	}
	
}
