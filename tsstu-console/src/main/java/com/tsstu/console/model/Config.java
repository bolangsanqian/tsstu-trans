package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 系统参数
 * @author： admin
 * @date： 2016-08-29
 */
public class Config extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 系统参数id
	private Integer id;
	
	// 参数代码
	private String config_code;
	
	// 参数名称
	private String config_name;
	
	// 参数值
	private String config_value;

	// 参数值-文本
	private String config_value_text;
	
	// 分组
	private Integer config_group;
	
	// 参数单位
	private String config_unit;
	
	// 状态
	private Integer status;
	
	// 排序
	private Integer sort;
	
	// JSON编辑参数
	private String json_data;
	
	// 参数类型（0：文本，1：下拉框，3：图片，4：文本域）
	private int form_type;
	
	// 生效方式
	private Integer effective_way;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfig_code() {
		return config_code;
	}

	public void setConfig_code(String config_code) {
		this.config_code = config_code;
	}

	public String getConfig_name() {
		return config_name;
	}

	public void setConfig_name(String config_name) {
		this.config_name = config_name;
	}

	public String getConfig_value() {
		return config_value;
	}

	public void setConfig_value(String config_value) {
		this.config_value = config_value;
	}

	public String getConfig_value_text() {
		return config_value_text;
	}

	public void setConfig_value_text(String config_value_text) {
		this.config_value_text = config_value_text;
	}

	public Integer getConfig_group() {
		return config_group;
	}

	public void setConfig_group(Integer config_group) {
		this.config_group = config_group;
	}

	public String getConfig_unit() {
		return config_unit;
	}

	public void setConfig_unit(String config_unit) {
		this.config_unit = config_unit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJson_data() {
		return json_data;
	}

	public void setJson_data(String json_data) {
		this.json_data = json_data;
	}

	public Integer getEffective_way() {
		return effective_way;
	}

	public void setEffective_way(Integer effective_way) {
		this.effective_way = effective_way;
	}

	public int getForm_type() {
		return form_type;
	}

	public void setForm_type(int form_type) {
		this.form_type = form_type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}