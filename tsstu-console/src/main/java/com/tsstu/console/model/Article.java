package com.tsstu.console.model;

import java.io.Serializable;
import java.util.Date;
import com.tsstu.common.model.Model;
/**
 * 文章管理
 * @author： admin
 * @date： 2017-05-13
 */
public class Article extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 公告id
	private Integer id;
	
	// 标题
	private String title;
	
	// 内容
	private String content;
	
	// 分组
	private Integer article_group;
	
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
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getArticle_group() {
		return this.article_group;
	}
	
	public void setArticle_group(Integer article_group) {
		this.article_group = article_group;
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