package com.tsstu.front.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 产品分类
 * @author： admin
 * @date： 2017-05-01
 */
public class ProductCategory extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 产品分类id
	private Integer id;
	
	// 产品代码
	private String code;
	
	// 分类名称
	private String name;
	
	// 状态
	private Integer status;
	
	// 图标
	private String icon_url;
	
	// 汇率类型
	private String rate_type;
	
	// 汇率
	private BigDecimal rate;
	
	// 排序
	private Integer sort;
	
	// 创建时间
	private Date create_time;
	
	// 数据源代码
	private String datasource_code;
	
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
	
	public String getIcon_url() {
		return this.icon_url;
	}
	
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	
	public String getRate_type() {
		return this.rate_type;
	}
	
	public void setRate_type(String rate_type) {
		this.rate_type = rate_type;
	}
	
	public BigDecimal getRate() {
		return this.rate;
	}
	
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Date getCreate_time() {
		return this.create_time;
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getDatasource_code() {
		return datasource_code;
	}

	public void setDatasource_code(String datasource_code) {
		this.datasource_code = datasource_code;
	}
	
}