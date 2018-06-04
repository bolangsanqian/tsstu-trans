package com.tsstu.console.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tsstu.console.model.DictionaryItem;

@SuppressWarnings("unchecked")
public class Detail {

	private String title;
	
	private String name;
	
	private Object value;
	
	// 数据类型(0：文本，1：金额，2：日期，3：图片)
	private Integer data_type = 0;
	
	private String dict_code = "";
	
	List<DictionaryItem> items = new ArrayList<DictionaryItem>();
	
	public Detail() {
		
	}

	public Detail(String title, Object value) {
		super();
		this.title = title;
		this.value = value;
	}
	
	public Detail(String title, Object value, Integer data_type) {
		super();
		this.title = title;
		this.value = value;
		this.data_type = data_type;
	}

	public Detail(String title, Object value, String dict_code) {
		super();
		this.title = title;
		this.value = value;
		this.dict_code = dict_code;
		this.dealDictCode();
	}
	
	public Detail(String title, Object value, List<DictionaryItem> items) {
		super();
		this.title = title;
		this.value = value;
		this.items = items;
	}

	public Detail(String title, String name, Object value, Integer data_type, String dict_code) {
		super();
		this.title = title;
		this.name = name;
		this.value = value;
		this.data_type = data_type;
		this.dict_code = dict_code;
		this.dealDictCode();
	}
	
	private void dealDictCode() {
		if (null != this.dict_code && !"".equals(this.dict_code)) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			items = (ArrayList<DictionaryItem>)request.getServletContext().getAttribute(this.dict_code);
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Integer getData_type() {
		return data_type;
	}

	public void setData_type(Integer data_type) {
		this.data_type = data_type;
	}

	public String getDict_code() {
		return dict_code;
	}

	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}

	public List<DictionaryItem> getItems() {
		return items;
	}

	public void setItems(List<DictionaryItem> items) {
		this.items = items;
	}

}
