package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 角色组
 * @author liwei
 * 2016年6月1日12:53:36
 */
public class Group extends Model implements Serializable {
	private static final long serialVersionUID = -8977653392424872917L;

	private Integer id;

    private String name;

    private Integer sort;

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}