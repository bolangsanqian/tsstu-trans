package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 消息子项(微信)
 * @author： admin
 * @date： 2017-05-13
 */
public class WechatMsgItem extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 子项id
	private Integer id;
	
	// 消息id
	private Integer wechat_msg_id;
	
	// 标题
	private String title;
	
	// 描述
	private String description;
	
	// 图片地址
	private String image_url;
	
	// 跳转地址
	private String url;
	
	// 状态
	private Integer status;
	
	// 创建时间
	private Date create_time;
	
	// 备注
	private String remark;
	
	// 排序
	private Integer sort;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getWechat_msg_id() {
		return this.wechat_msg_id;
	}
	
	public void setWechat_msg_id(Integer wechat_msg_id) {
		this.wechat_msg_id = wechat_msg_id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage_url() {
		return this.image_url;
	}
	
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
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
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}