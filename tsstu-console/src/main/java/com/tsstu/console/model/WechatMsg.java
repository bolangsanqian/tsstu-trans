package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 消息管理
 * @author： admin
 * @date： 2017-05-13
 */
public class WechatMsg extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 消息id
	private Integer id;
	
	// 关键字
	private String keyword;
	
	// 匹配方式
	private Integer match_type;
	
	// 消息类型
	private Integer msg_type;
	
	// 内容
	private String content;
	
	// 状态(0：禁用，1：启用)
	private Integer status;
	
	// 创建时间
	private Date create_time;
	
	// 备注
	private String remark;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getKeyword() {
		return this.keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public Integer getMatch_type() {
		return this.match_type;
	}
	
	public void setMatch_type(Integer match_type) {
		this.match_type = match_type;
	}
	
	public Integer getMsg_type() {
		return this.msg_type;
	}
	
	public void setMsg_type(Integer msg_type) {
		this.msg_type = msg_type;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
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
	
}