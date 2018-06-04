package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 菜单管理(微信)
 * @author： admin
 * @date： 2017-05-13
 */
public class WechatMenu extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 菜单id
	private Integer id;
	
	// 父菜单id
	private Integer pid;
	
	// 名称
	private String name;
	
	// 菜单类型
	private Integer menu_type;
	
	// 关键字
	private String keyword;
	
	// 跳转地址
	private String url;
	
	// 排序
	private Integer sort;
	
	// 状态
	private Integer status;
	
	// 创建时间
	private Date create_time;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPid() {
		return this.pid;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getMenu_type() {
		return this.menu_type;
	}
	
	public void setMenu_type(Integer menu_type) {
		this.menu_type = menu_type;
	}
	
	public String getKeyword() {
		return this.keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
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
	
}