package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
/**
 * 数据库表字段
 * @author： admin
 * @date： 2017-05-04
 */
public class Columns extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 表名
	private String table_name;
	
	// 字段名
	private String column_name;
	
	// 字段备注
	private String column_comment;
	
	// 数据类型
	private String data_type;
	
	// 自增
	private String extra;
	
	public String getColumn_name() {
		return this.column_name;
	}
	
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	
	public String getColumn_comment() {
		return this.column_comment;
	}
	
	public void setColumn_comment(String column_comment) {
		this.column_comment = column_comment;
	}
	
	public String getData_type() {
		return this.data_type;
	}
	
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	
	public String getExtra() {
		return this.extra;
	}
	
	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	
}