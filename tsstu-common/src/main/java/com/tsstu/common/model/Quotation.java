package com.tsstu.common.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 实时行情
 * @author： admin
 * @date： 2017-05-13
 */
public class Quotation extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 报价id
	private Integer id;
	
	// 当天开盘价
	private BigDecimal open;
	
	// 当前价
	private BigDecimal bid;
	
	// 最低价
	private BigDecimal low;
	
	// 最高价
	private BigDecimal high;
	
	// 报价时间戳
	private Integer ctm;
	
	// 报价时间
	private Date dt;
	
	// 创建时间
	private Date ct;
	
	// 分类代码
	private String code;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal getBid() {
		return this.bid;
	}
	
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}
	
	public BigDecimal getLow() {
		return this.low;
	}
	
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	
	public BigDecimal getHigh() {
		return this.high;
	}
	
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	
	public Integer getCtm() {
		return this.ctm;
	}
	
	public void setCtm(Integer ctm) {
		this.ctm = ctm;
	}
	
	public Date getDt() {
		return this.dt;
	}
	
	public void setDt(Date dt) {
		this.dt = dt;
	}
	
	public Date getCt() {
		return this.ct;
	}
	
	public void setCt(Date ct) {
		this.ct = ct;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	
}