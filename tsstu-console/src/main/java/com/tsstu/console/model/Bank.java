package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 银行管理
 * @author： admin
 * @date： 2017-05-04
 */
public class Bank extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 银行id
	private Integer id;
	
	// 银行名称
	private String bank_name;
	
	// 银行编号
	private String bank_no;
	
	// 图标
	private String icon;
	
	// 用途
	private Integer purpose;
	
	// 状态
	private Integer status;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBank_name() {
		return this.bank_name;
	}
	
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	
	public String getBank_no() {
		return this.bank_no;
	}
	
	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}
	
	public String getIcon() {
		return this.icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getPurpose() {
		return this.purpose;
	}
	
	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}