package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 周期订单
 * @author： admin
 * @date： 2017-05-04
 */
public class CycleOrder extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 订单id
	private Integer id;
	
	// 订单编号
	private String flow_no;
	
	// 周期产品id
	private Integer cycle_product_id;
	
	// 客户id
	private Integer customer_id;
	
	// 产品代码
	private String product_code;
	
	// 产品名称
	private String product_name;
	
	// 建仓价格
	private BigDecimal open_price;
	
	// 平仓价格
	private BigDecimal close_price;
	
	// 做单方向
	private Integer direction;
	
	// 金额
	private BigDecimal amount;
	
	// 盈亏金额
	private BigDecimal yk_amount;
	
	// 手续费
	private BigDecimal fee;
	
	// 持单时间
	private Integer hold_time;
	
	// 时间单位
	private String time_unit;
	
	// 潜在收益比例
	private Integer profit;
	
	// 建仓时间
	private Date open_time;
	
	// 平仓时间
	private Date close_time;
	
	// 订单状态
	private Integer status;
	
	// 结算类型
	private Integer settlement_type;
	
	// 结算时间
	private Date settlement_time;
	
	// 所属会员ID
	private Integer member_id;
	
	// 平仓时间戳
	private Integer close_timestamp;
	
	// 出手交易手续费方
	private Integer out_fee_part;
	
	// 运营中心
	private Integer operation_member_id;
	
	// 代理会员
	private Integer agent_member_id;
	
	// 建仓时间戳
	private Integer open_timestamp;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFlow_no() {
		return this.flow_no;
	}
	
	public void setFlow_no(String flow_no) {
		this.flow_no = flow_no;
	}
	
	public Integer getCycle_product_id() {
		return this.cycle_product_id;
	}
	
	public void setCycle_product_id(Integer cycle_product_id) {
		this.cycle_product_id = cycle_product_id;
	}
	
	public Integer getCustomer_id() {
		return this.customer_id;
	}
	
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getProduct_code() {
		return this.product_code;
	}
	
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	
	public String getProduct_name() {
		return this.product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public BigDecimal getOpen_price() {
		return this.open_price;
	}
	
	public void setOpen_price(BigDecimal open_price) {
		this.open_price = open_price;
	}
	
	public BigDecimal getClose_price() {
		return this.close_price;
	}
	
	public void setClose_price(BigDecimal close_price) {
		this.close_price = close_price;
	}
	
	public Integer getDirection() {
		return this.direction;
	}
	
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getYk_amount() {
		return this.yk_amount;
	}
	
	public void setYk_amount(BigDecimal yk_amount) {
		this.yk_amount = yk_amount;
	}
	
	public BigDecimal getFee() {
		return this.fee;
	}
	
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	public Integer getHold_time() {
		return this.hold_time;
	}
	
	public void setHold_time(Integer hold_time) {
		this.hold_time = hold_time;
	}
	
	public String getTime_unit() {
		return this.time_unit;
	}
	
	public void setTime_unit(String time_unit) {
		this.time_unit = time_unit;
	}
	
	public Integer getProfit() {
		return this.profit;
	}
	
	public void setProfit(Integer profit) {
		this.profit = profit;
	}
	
	public Date getOpen_time() {
		return this.open_time;
	}
	
	public void setOpen_time(Date open_time) {
		this.open_time = open_time;
	}
	
	public Date getClose_time() {
		return this.close_time;
	}
	
	public void setClose_time(Date close_time) {
		this.close_time = close_time;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getSettlement_type() {
		return this.settlement_type;
	}
	
	public void setSettlement_type(Integer settlement_type) {
		this.settlement_type = settlement_type;
	}
	
	public Date getSettlement_time() {
		return this.settlement_time;
	}
	
	public void setSettlement_time(Date settlement_time) {
		this.settlement_time = settlement_time;
	}
	
	public Integer getMember_id() {
		return this.member_id;
	}
	
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	
	public Integer getClose_timestamp() {
		return this.close_timestamp;
	}
	
	public void setClose_timestamp(Integer close_timestamp) {
		this.close_timestamp = close_timestamp;
	}
	
	public Integer getOut_fee_part() {
		return this.out_fee_part;
	}
	
	public void setOut_fee_part(Integer out_fee_part) {
		this.out_fee_part = out_fee_part;
	}
	
	public Integer getOperation_member_id() {
		return this.operation_member_id;
	}
	
	public void setOperation_member_id(Integer operation_member_id) {
		this.operation_member_id = operation_member_id;
	}
	
	public Integer getAgent_member_id() {
		return this.agent_member_id;
	}
	
	public void setAgent_member_id(Integer agent_member_id) {
		this.agent_member_id = agent_member_id;
	}
	
	public Integer getOpen_timestamp() {
		return this.open_timestamp;
	}
	
	public void setOpen_timestamp(Integer open_timestamp) {
		this.open_timestamp = open_timestamp;
	}

}