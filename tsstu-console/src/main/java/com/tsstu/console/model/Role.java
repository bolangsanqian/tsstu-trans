package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;

public class Role extends Model implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String name;

    private String remark;

    private Integer sort;
    
    private Integer status;
    
    private boolean hasRole;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isHasRole() {
		return hasRole;
	}

	public void setHasRole(boolean hasRole) {
		this.hasRole = hasRole;
	}


}