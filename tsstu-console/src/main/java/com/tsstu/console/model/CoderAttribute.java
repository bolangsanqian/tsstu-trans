package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 代码生成
 * 创建人：liwei
 * 创建时间：${nowDate?string("yyyy-MM-dd")}
 *
 */
public class CoderAttribute extends Model implements Serializable {

	private static final long serialVersionUID = 1L;

	// 主键id
	private Integer id;
	
	// 代码id
	private Integer coder_id;
	
	// 属性名称
	private String name;
	
	// 属性备注
	private String title;
	
	// 数据类型(java)
	private String java_type;
		
	// 数据类型
	private String data_type;
	
	// 数据长度
	private Integer data_length;
	
	// 主键
	private Integer is_key;
	
	// 表单元素类型
	private String element_type;
	
	// 是否前台录入 
	private Integer is_input = 0;
	
	// 是否为查询条件
	private Integer is_search = 0;
	
	// 前台录入
	private Integer is_show = 0;
	
	// 排序字段
	private Integer is_sort = 0;
	
	// 排序
	private Integer sort;
	
	// 创建时间
	private Date create_time;
	
	// 字典代码
	private String dict_code;
	
	// 字典项
	private List<DictionaryItem> dictItemList = new ArrayList<DictionaryItem>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCoder_id() {
		return coder_id;
	}

	public void setCoder_id(Integer coder_id) {
		this.coder_id = coder_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJava_type() {
		return java_type;
	}

	public void setJava_type(String java_type) {
		this.java_type = java_type;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public Integer getData_length() {
		return data_length;
	}

	public void setData_length(Integer data_length) {
		this.data_length = data_length;
	}

	public Integer getIs_key() {
		return is_key;
	}

	public void setIs_key(Integer is_key) {
		this.is_key = is_key;
	}

	public String getElement_type() {
		return element_type;
	}

	public void setElement_type(String element_type) {
		this.element_type = element_type;
	}

	public Integer getIs_input() {
		return is_input;
	}

	public void setIs_input(Integer is_input) {
		this.is_input = is_input;
	}

	public Integer getIs_search() {
		return is_search;
	}

	public void setIs_search(Integer is_search) {
		this.is_search = is_search;
	}

	public Integer getIs_show() {
		return is_show;
	}

	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getDict_code() {
		return dict_code;
	}

	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}

	public List<DictionaryItem> getDictItemList() {
		return dictItemList;
	}

	public void setDictItemList(List<DictionaryItem> dictItemList) {
		this.dictItemList = dictItemList;
	}

	public Integer getIs_sort() {
		return is_sort;
	}

	public void setIs_sort(Integer is_sort) {
		this.is_sort = is_sort;
	}
}