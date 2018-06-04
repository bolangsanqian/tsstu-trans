package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 行情数据源
 * @author： admin
 * @date： 2017-05-13
 */
public class QuotationDatasource extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 数据源id
	private Integer id;
	
	// 代码
	private String code;
	
	// 名称
	private String name;
	
	// 状态（0：禁用，1：启用）
	private Integer status;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}