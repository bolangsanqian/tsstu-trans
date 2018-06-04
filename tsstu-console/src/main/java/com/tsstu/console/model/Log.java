package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 系统日志
 * @author： admin
 * @date： 2016-08-08
 */
public class Log extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 系统日志id
	private Integer id;
	
	// 用户id
	private Integer user_id;
	
	// 日志类型
	private Integer operate_type;
	
	//用户姓名
	private String user_name;
	
	//用户所属分组
	private Integer user_group;
	
	// 资源名称
	private String name;
	
	// 资源路径
	private String url;
	
	// 请求参数
	private String param;
	
	// IP地址
	private String ip;
	
	// 操作时间
	private Date create_time;
	

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getParam() {
		return this.param;
	}
	
	public void setParam(String param) {
		this.param = param;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Date getCreate_time() {
		return this.create_time;
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getUser_group() {
		return user_group;
	}

	public void setUser_group(Integer user_group) {
		this.user_group = user_group;
	}

	public Integer getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(Integer operate_type) {
		this.operate_type = operate_type;
	}
	
}