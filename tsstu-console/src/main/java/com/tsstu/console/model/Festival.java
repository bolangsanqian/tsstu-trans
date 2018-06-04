package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 节假日
 * @author： admin
 * @date： 2017-05-13
 */
public class Festival extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 节假日id
	private Integer id;
	
	// 名称
	private String name;
	
	// 开始时间
	private Date begin_time;
	
	// 结束时间
	private Date end_time;
	
	// 描述
	private String remark;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBegin_time() {
		return this.begin_time;
	}
	
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	
	public Date getEnd_time() {
		return this.end_time;
	}
	
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}