package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 客户管理
 * @author： admin
 * @date： 2017-05-03
 */
public class Customer extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 客户id
	private Integer id;
	
	// 客户编号
	private String cust_no;
	
	// 手机号码
	private String mobile;
	
	// 密码
	private String password;
	
	// 余额
	private BigDecimal balance;
	
	// 冻结资金
	private BigDecimal frozen_money;
	
	// 昵称
	private String nick_name;
	
	// 微信openid
	private String wx_openid;
	
	// 头像地址
	private String head_url;
	
	// 邮箱
	private String email;
	
	// 性别
	private Integer sex;
	
	// 生日
	private Date birthday;
	
	// 是否为经纪人
	private Integer is_borker;
	
	// 所属经纪人id
	private Integer broker_id;
	
	// 层级编号
	private String h_number;
	
	// 层级等级
	private Integer h_level;
	
	// 运营中心id
	private Integer operation_member_id;
	
	// 所属会员id
	private Integer member_id;
	
	// 代理会员id
	private Integer agent_member_id;
	
	// 上一级id
	private Integer pid;
	
	// 状态
	private Integer status;
	
	// 是否已激活
	private Integer active_status;
	
	// 是否容许充值
	private Integer recharge_status;
	
	// 是否容许提现
	private Integer withdraw_status;
	
	// 是否容许登录
	private Integer login_status;
	
	// 是否容许交易
	private Integer trade_status;
	
	// 注册日期
	private Date register_time;
	
	// 注册ip地址
	private String register_ip;
	
	// 最后登录时间
	private Date last_login_time;
	
	// 最后登录ip地址
	private String last_login_ip;
	
	// 销户前手机号码
	private String org_mobile;
	
	// 销户前微信openid
	private String org_wx_openid;
	
	// 分享码
	private String share_code;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCust_no() {
		return this.cust_no;
	}
	
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public BigDecimal getBalance() {
		return this.balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public BigDecimal getFrozen_money() {
		return this.frozen_money;
	}
	
	public void setFrozen_money(BigDecimal frozen_money) {
		this.frozen_money = frozen_money;
	}
	
	public String getNick_name() {
		return this.nick_name;
	}
	
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	public String getWx_openid() {
		return this.wx_openid;
	}
	
	public void setWx_openid(String wx_openid) {
		this.wx_openid = wx_openid;
	}
	
	public String getHead_url() {
		return this.head_url;
	}
	
	public void setHead_url(String head_url) {
		this.head_url = head_url;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getSex() {
		return this.sex;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Integer getIs_borker() {
		return this.is_borker;
	}
	
	public void setIs_borker(Integer is_borker) {
		this.is_borker = is_borker;
	}
	
	public Integer getBroker_id() {
		return this.broker_id;
	}
	
	public void setBroker_id(Integer broker_id) {
		this.broker_id = broker_id;
	}
	
	public String getH_number() {
		return this.h_number;
	}
	
	public void setH_number(String h_number) {
		this.h_number = h_number;
	}
	
	public Integer getH_level() {
		return this.h_level;
	}
	
	public void setH_level(Integer h_level) {
		this.h_level = h_level;
	}
	
	public Integer getOperation_member_id() {
		return this.operation_member_id;
	}
	
	public void setOperation_member_id(Integer operation_member_id) {
		this.operation_member_id = operation_member_id;
	}
	
	public Integer getMember_id() {
		return this.member_id;
	}
	
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	
	public Integer getAgent_member_id() {
		return this.agent_member_id;
	}
	
	public void setAgent_member_id(Integer agent_member_id) {
		this.agent_member_id = agent_member_id;
	}
	
	public Integer getPid() {
		return this.pid;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getActive_status() {
		return this.active_status;
	}
	
	public void setActive_status(Integer active_status) {
		this.active_status = active_status;
	}
	
	public Integer getRecharge_status() {
		return this.recharge_status;
	}
	
	public void setRecharge_status(Integer recharge_status) {
		this.recharge_status = recharge_status;
	}
	
	public Integer getWithdraw_status() {
		return this.withdraw_status;
	}
	
	public void setWithdraw_status(Integer withdraw_status) {
		this.withdraw_status = withdraw_status;
	}
	
	public Integer getLogin_status() {
		return this.login_status;
	}
	
	public void setLogin_status(Integer login_status) {
		this.login_status = login_status;
	}
	
	public Integer getTrade_status() {
		return this.trade_status;
	}
	
	public void setTrade_status(Integer trade_status) {
		this.trade_status = trade_status;
	}
	
	public Date getRegister_time() {
		return this.register_time;
	}
	
	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}
	
	public String getRegister_ip() {
		return this.register_ip;
	}
	
	public void setRegister_ip(String register_ip) {
		this.register_ip = register_ip;
	}
	
	public Date getLast_login_time() {
		return this.last_login_time;
	}
	
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	
	public String getLast_login_ip() {
		return this.last_login_ip;
	}
	
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	
	public String getOrg_mobile() {
		return this.org_mobile;
	}
	
	public void setOrg_mobile(String org_mobile) {
		this.org_mobile = org_mobile;
	}
	
	public String getOrg_wx_openid() {
		return this.org_wx_openid;
	}
	
	public void setOrg_wx_openid(String org_wx_openid) {
		this.org_wx_openid = org_wx_openid;
	}
	
	public String getShare_code() {
		return this.share_code;
	}
	
	public void setShare_code(String share_code) {
		this.share_code = share_code;
	}
	
}