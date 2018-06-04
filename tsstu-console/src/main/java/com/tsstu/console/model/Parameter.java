package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 系统参数
 * @author： admin
 * @date： 2017-05-01
 */
public class Parameter extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 系统参数id
	private String id;
	
	// 参数代码
	private Integer param_code;
	
	// 参数值
	private Integer param_value;
	
	// 参数名称
	private Integer param_name;
	
	// 分组
	private String param_group;
	
	// 参数单位
	private Integer param_unit;
	
	// 状态
	private String status;
	
	// JSON编辑参数
	private Integer json_data;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getParam_code() {
		return this.param_code;
	}
	
	public void setParam_code(Integer param_code) {
		this.param_code = param_code;
	}
	
	public Integer getParam_value() {
		return this.param_value;
	}
	
	public void setParam_value(Integer param_value) {
		this.param_value = param_value;
	}
	
	public Integer getParam_name() {
		return this.param_name;
	}
	
	public void setParam_name(Integer param_name) {
		this.param_name = param_name;
	}
	
	public String getParam_group() {
		return this.param_group;
	}
	
	public void setParam_group(String param_group) {
		this.param_group = param_group;
	}
	
	public Integer getParam_unit() {
		return this.param_unit;
	}
	
	public void setParam_unit(Integer param_unit) {
		this.param_unit = param_unit;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getJson_data() {
		return this.json_data;
	}
	
	public void setJson_data(Integer json_data) {
		this.json_data = json_data;
	}
	
}