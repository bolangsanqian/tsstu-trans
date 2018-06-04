package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.util.List;
/**
 * 用户数据模型
 * @author liwei
 * 日期：2017年4月30日13:28:25
 */
public class User extends Model implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// 自增id
	private Integer id;

	// 用户名
    private String username;

    // 密码
    private String password;
    
    // 手机号码
    private String mobile;
    
    // 再次输入密码
    private String repassword;
    
    // 用户类型
    private Integer user_type;
    
    // 状态（0：启用，1：禁用）
    private Integer status;
    
    // 是否容许删除（0：不容许，1：容许）
    private Integer allow_del;
    
    // 创建人
    private Integer create_by;
    
    // 创建日期
    private Date create_time;
    
    // 最后登录日期
    private Date last_login_time;
    
    // 最后登录ip
    private String last_login_ip;
    
    // 拥有的角色列表
    private List<Role> roleList;
    
    // 角色名称
    private String role_name;
    
    // 创建人用户名
    private String create_by_username;
    
    public User() {
    	
	}
    
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public Integer getCreate_by() {
		return create_by;
	}

	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getCreate_by_username() {
		return create_by_username;
	}

	public void setCreate_by_username(String create_by_username) {
		this.create_by_username = create_by_username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAllow_del() {
		return allow_del;
	}

	public void setAllow_del(Integer allow_del) {
		this.allow_del = allow_del;
	}
    
}