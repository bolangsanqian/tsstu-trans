package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 轮播图片
 * @author： admin
 * @date： 2017-05-13
 */
public class Banner extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 首页banner图id
	private Integer id;
	
	// 标题
	private String title;
	
	// 图片地址
	private String image_url;
	
	// 跳转地址
	private String jump_url;
	
	// 交易模式
	private Integer trade_mode;
	
	// 状态
	private Integer status;
	
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
	
	public String getJump_url() {
		return this.jump_url;
	}
	
	public void setJump_url(String jump_url) {
		this.jump_url = jump_url;
	}
	
	public Integer getTrade_mode() {
		return this.trade_mode;
	}
	
	public void setTrade_mode(Integer trade_mode) {
		this.trade_mode = trade_mode;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
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