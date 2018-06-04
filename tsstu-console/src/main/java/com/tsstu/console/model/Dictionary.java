package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.ArrayList;
import java.util.List;
/**
 * 数据字典
 * @author： admin
 * @date：2016-07-05
 */
public class Dictionary extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 字典id
	private Integer id;
	
	// 字典名字
	private String dict_name;
	
	// 字典代码
	private String dict_code;
	
	// 字典代码
	private String old_dict_code;
	
	// 描述
	private String remark;
	
	// 子项集合
	private List<DictionaryItem> itemList = new ArrayList<DictionaryItem>();

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDict_name() {
		return this.dict_name;
	}
	
	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}
	
	public String getDict_code() {
		return this.dict_code;
	}
	
	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOld_dict_code() {
		return old_dict_code;
	}

	public void setOld_dict_code(String old_dict_code) {
		this.old_dict_code = old_dict_code;
	}

	public List<DictionaryItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<DictionaryItem> itemList) {
		this.itemList = itemList;
	}
	
}