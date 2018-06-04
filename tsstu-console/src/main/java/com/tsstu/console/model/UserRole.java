package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;

/**
 * 用户角色中间表
 * @author liwei
 * 2016年6月13日09:57:14
 */
public class UserRole extends Model implements Serializable {
	
	private static final long serialVersionUID = -2788171085797063233L;

	private Integer id;

    private Integer role_id;

    private Integer user_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}



}