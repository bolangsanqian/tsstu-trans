package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 图片管理
 * @author： admin
 * @date： 2017-05-13
 */
public class Image extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 首页banner图id
	private Integer id;
	
	// 说明
	private String title;
	
	// 图片地址
	private String image_url;
	
	// 图片分组
	private Integer image_group;
	
	// 排序
	private Integer sort;
	
	// 创建时间
	private Date create_time;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImage_url() {
		return this.image_url;
	}
	
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	public Integer getImage_group() {
		return this.image_group;
	}
	
	public void setImage_group(Integer image_group) {
		this.image_group = image_group;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Date getCreate_time() {
		return this.create_time;
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}