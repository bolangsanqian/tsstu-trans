package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;

public class RolePermission extends Model implements Serializable {
	
	private static final long serialVersionUID = -1022719641192211546L;

	private Integer id;

    private Integer role_id;

    private Integer permission_id;

	public RolePermission() {
		super();
	}

	public RolePermission(Integer role_id, Integer permission_id) {
		super();
		this.role_id = role_id;
		this.permission_id = permission_id;
	}

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

	public Integer getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}

}