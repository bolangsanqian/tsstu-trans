package com.tsstu.front.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable, Comparable<Menu> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer pid;
	
	private String name;
	
	private String url;
	
	private String icon;
	
	private Integer sort;
	
	private List<Menu> list = new ArrayList<Menu>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Menu> getList() {
		return list;
	}

	public void setList(List<Menu> list) {
		this.list = list;
	}

	@Override
	public int compareTo(Menu menu) {
		int rs = 0;
		if ( this.getSort() > menu.getSort()) {
			rs = 1;
		} else {
			rs = -1;
		}
		return rs;
	}
}
