package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;

public class Permission extends Model implements Serializable, Comparable<Permission>  {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Integer status;

    private Integer pid;

    private String url;

    private Integer permission_type;
    
    private String permission_sign;

    private Integer sort;

    private String icon;
    
    private String remark;
    
    private boolean hasPermission;
    
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPermission_type() {
		return permission_type;
	}

	public void setPermission_type(Integer permission_type) {
		this.permission_type = permission_type;
	}

	public String getPermission_sign() {
		return permission_sign;
	}

	public void setPermission_sign(String permission_sign) {
		this.permission_sign = permission_sign;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isHasPermission() {
		return hasPermission;
	}

	public void setHasPermission(boolean hasPermission) {
		this.hasPermission = hasPermission;
	}

	@Override
	public int compareTo(Permission m) {
		int rs = 0;
		if ((this.sort.intValue() > m.getSort().intValue()) || (this.sort.intValue() == m.getSort().intValue() && this.id.intValue() > m.getId().intValue())) {
			rs = 1;
		}  else {
			rs = -1;
		}
		return rs;
	}
}