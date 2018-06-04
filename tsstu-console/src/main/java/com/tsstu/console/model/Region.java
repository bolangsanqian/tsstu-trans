package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 区域管理
 * @author： admin
 * @date： 2017-05-04
 */
public class Region extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 区域id
	private Integer id;
	
	// 区域代码
	private Integer code;
	
	// 区域名称
	private String name;
	
	// 父级代码
	private Integer parent_code;
	
	// 层级
	private Integer level;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getParent_code() {
		return this.parent_code;
	}
	
	public void setParent_code(Integer parent_code) {
		this.parent_code = parent_code;
	}
	
	public Integer getLevel() {
		return this.level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}