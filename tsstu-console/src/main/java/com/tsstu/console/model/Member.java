package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 会员管理
 * @author： admin
 * @date： 2017-05-03
 */
public class Member extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 会员编号
	private Integer id;
	
	// 名称
	private String name;
	
	// 公司名字
	private String company_name;
	
	// 公司领导
	private String company_leader;
	
	// 手机号码
	private String mobile;
	
	// 证件类型
	private Integer identity_type;
	
	// 证件号
	private String identity;
	
	// 余额
	private BigDecimal balance;
	
	// 冻结资金
	private BigDecimal frozen_money;
	
	// 保证金
	private BigDecimal deposit;
	
	// 信用额度
	private BigDecimal credit_money;
	
	// 状态
	private Integer status;
	
	// 创建时间
	private Date create_time;
	
	// 登录用户id
	private Integer user_id;
	
	// 微信二维码地址
	private String wx_qrcode_url;
	
	// 是否为默认会员
	private Integer is_default;
	
	// 会员邀请码
	private String invite_code;
	
	// 交易所id
	private Integer exchange_member_id;
	
	// 运营中心id
	private Integer operation_member_id;
	
	// 客户激活金额
	private BigDecimal customer_active_amount;
	
	// 交易所佣金
	private BigDecimal exchange_commission;
	
	// 运营中心佣金
	private BigDecimal operation_commission;
	
	// 会员佣金
	private BigDecimal member_commission;
	
	// 经纪人直接佣金
	private BigDecimal direct_commission;
	
	// 经济人间接佣金
	private BigDecimal indirect_commission;
	
	// 经纪人直接分红
	private BigDecimal direct_b_commission;
	
	// 经纪人间接分红
	private BigDecimal indirect_b_commission;
	
	// 会员类型
	private Integer member_type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_leader() {
		return company_leader;
	}

	public void setCompany_leader(String company_leader) {
		this.company_leader = company_leader;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getIdentity_type() {
		return identity_type;
	}

	public void setIdentity_type(Integer identity_type) {
		this.identity_type = identity_type;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getFrozen_money() {
		return frozen_money;
	}

	public void setFrozen_money(BigDecimal frozen_money) {
		this.frozen_money = frozen_money;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getCredit_money() {
		return credit_money;
	}

	public void setCredit_money(BigDecimal credit_money) {
		this.credit_money = credit_money;
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

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getWx_qrcode_url() {
		return wx_qrcode_url;
	}

	public void setWx_qrcode_url(String wx_qrcode_url) {
		this.wx_qrcode_url = wx_qrcode_url;
	}

	public Integer getIs_default() {
		return is_default;
	}

	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}

	public String getInvite_code() {
		return invite_code;
	}

	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}

	public Integer getExchange_member_id() {
		return exchange_member_id;
	}

	public void setExchange_member_id(Integer exchange_member_id) {
		this.exchange_member_id = exchange_member_id;
	}

	public Integer getOperation_member_id() {
		return operation_member_id;
	}

	public void setOperation_member_id(Integer operation_member_id) {
		this.operation_member_id = operation_member_id;
	}

	public BigDecimal getCustomer_active_amount() {
		return customer_active_amount;
	}

	public void setCustomer_active_amount(BigDecimal customer_active_amount) {
		this.customer_active_amount = customer_active_amount;
	}

	public BigDecimal getExchange_commission() {
		return exchange_commission;
	}

	public void setExchange_commission(BigDecimal exchange_commission) {
		this.exchange_commission = exchange_commission;
	}

	public BigDecimal getOperation_commission() {
		return operation_commission;
	}

	public void setOperation_commission(BigDecimal operation_commission) {
		this.operation_commission = operation_commission;
	}

	public BigDecimal getMember_commission() {
		return member_commission;
	}

	public void setMember_commission(BigDecimal member_commission) {
		this.member_commission = member_commission;
	}

	public BigDecimal getDirect_commission() {
		return direct_commission;
	}

	public void setDirect_commission(BigDecimal direct_commission) {
		this.direct_commission = direct_commission;
	}

	public BigDecimal getIndirect_commission() {
		return indirect_commission;
	}

	public void setIndirect_commission(BigDecimal indirect_commission) {
		this.indirect_commission = indirect_commission;
	}

	public BigDecimal getDirect_b_commission() {
		return direct_b_commission;
	}

	public void setDirect_b_commission(BigDecimal direct_b_commission) {
		this.direct_b_commission = direct_b_commission;
	}

	public BigDecimal getIndirect_b_commission() {
		return indirect_b_commission;
	}

	public void setIndirect_b_commission(BigDecimal indirect_b_commission) {
		this.indirect_b_commission = indirect_b_commission;
	}

	public Integer getMember_type() {
		return member_type;
	}

	public void setMember_type(Integer member_type) {
		this.member_type = member_type;
	}
	
}