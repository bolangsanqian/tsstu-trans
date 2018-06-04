package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 短信记录
 * @author： admin
 * @date： 2017-05-04
 */
public class Sms extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 短信记录id
	private Integer id;
	
	// 手机号码
	private String mobile;
	
	// 短信类型
	private Integer sms_type;
	
	// 短信内容
	private String sms_content;
	
	// 发送时间
	private Date send_time;
	
	// IP地址
	private String ip;
	
	// 备注
	private String remark;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getSms_type() {
		return this.sms_type;
	}
	
	public void setSms_type(Integer sms_type) {
		this.sms_type = sms_type;
	}
	
	public String getSms_content() {
		return this.sms_content;
	}
	
	public void setSms_content(String sms_content) {
		this.sms_content = sms_content;
	}
	
	public Date getSend_time() {
		return this.send_time;
	}
	
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}