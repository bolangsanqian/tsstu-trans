package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 数据字典项
 * @author： admin
 * @date：2016-07-05
 */
public class DictionaryItem extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 数据字典项id
	private Integer id;
	
	// 数据字典代码
	private String dict_code;
	
	// 字典代码
	private String old_dict_code;
	
	// 项名称
	private String item_name;
	
	// 项值
	private String item_value;
	
	// 排序
	private Integer sort;
	

	public Integer getId() {
		return this.id;
	}
	
	public String getOld_dict_code() {
		return old_dict_code;
	}

	public void setOld_dict_code(String old_dict_code) {
		this.old_dict_code = old_dict_code;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDict_code() {
		return this.dict_code;
	}
	
	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}
	
	public String getItem_name() {
		return this.item_name;
	}
	
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	public String getItem_value() {
		return this.item_value;
	}
	
	public void setItem_value(String item_value) {
		this.item_value = item_value;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}