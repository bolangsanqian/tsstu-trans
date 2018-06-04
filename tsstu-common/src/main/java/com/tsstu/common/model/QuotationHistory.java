package com.tsstu.common.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 历史行情
 * @author： admin
 * @date： 2017-05-13
 */
public class QuotationHistory extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 行情id
	private Integer id;
	
	// 开盘价
	private BigDecimal open;
	
	// 最低价
	private BigDecimal low;
	
	// 最高价
	private BigDecimal high;
	
	// 收盘价
	private BigDecimal close;
	
	// 时间戳
	private Integer ctm;
	
	// 开盘时间
	private Date firstdt;
	
	// 收盘时间
	private Date lastdt;
	
	// 创建时间
	private Date ct;
	
	// 行情代码
	private String code;
	
	// 分钟
	private Integer minute;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal getOpen() {
		return this.open;
	}
	
	public void setOpen(BigDecimal open) {
		this.open = open;
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
	
	public BigDecimal getClose() {
		return this.close;
	}
	
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	
	public Integer getCtm() {
		return this.ctm;
	}
	
	public void setCtm(Integer ctm) {
		this.ctm = ctm;
	}
	
	public Date getFirstdt() {
		return this.firstdt;
	}
	
	public void setFirstdt(Date firstdt) {
		this.firstdt = firstdt;
	}
	
	public Date getLastdt() {
		return this.lastdt;
	}
	
	public void setLastdt(Date lastdt) {
		this.lastdt = lastdt;
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

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
}