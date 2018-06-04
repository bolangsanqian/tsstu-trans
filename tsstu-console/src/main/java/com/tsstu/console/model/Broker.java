package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 经纪人
 * @author： admin
 * @date： 2017-05-16
 */
public class Broker extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 经纪人id
	private Integer id;
	
	// 用户id
	private Integer user_id;
	
	// 客户id
	private Integer customer_id;
	
	// 邀请码
	private String invite_code;
	
	// 状态
	private Integer status;
	
	// 创建时间
	private Date create_time;
	
	// 登录用户名
	private String username;
	
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
	
	public Integer getCustomer_id() {
		return this.customer_id;
	}
	
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getInvite_code() {
		return this.invite_code;
	}
	
	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}