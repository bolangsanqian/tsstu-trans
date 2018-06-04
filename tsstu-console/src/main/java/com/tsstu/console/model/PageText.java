package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 文案管理
 * @author： admin
 * @date： 2017-05-13
 */
public class PageText extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 页面文案id
	private Integer id;
	
	// 代码
	private String code;
	
	// 名称
	private String name;
	
	// 交易模式
	private Integer trade_mode;
	
	// 内容
	private String text;
	
	// 排序
	private Integer sort;
	
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
	
	public Integer getTrade_mode() {
		return this.trade_mode;
	}
	
	public void setTrade_mode(Integer trade_mode) {
		this.trade_mode = trade_mode;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}