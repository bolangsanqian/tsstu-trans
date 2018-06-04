package com.tsstu.console.model;

import java.io.Serializable;
import com.tsstu.common.model.Model;
import java.util.Date;
/**
 * 代码生成
 * @author liwei
 * @date 2016年6月21日11:16:52
 *
 */
public class Coder extends Model implements Serializable {

	private static final long serialVersionUID = -2024242760068305679L;
	
	// 代码生成id
	private Integer id;
	
	// 名称
	private String name;
	
	// 包名
	private String package_name;
	
	// 类名
	private String class_name;
	
	// 表名
	private String table_name;
	
	// 添加
	private Integer add_button;
	
	// 删除
	private Integer del_button;
	
	// 修改
	private Integer edit_button;
	
	// 查询
	private Integer query_button;
	
	// 导出
	private Integer export_button;
	
	// 导入
	private Integer import_button;
	
	// 权限
	private Integer permission;
	
	// 创建时间
	private Date create_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public Integer getAdd_button() {
		return add_button;
	}

	public void setAdd_button(Integer add_button) {
		this.add_button = add_button;
	}

	public Integer getDel_button() {
		return del_button;
	}

	public void setDel_button(Integer del_button) {
		this.del_button = del_button;
	}

	public Integer getEdit_button() {
		return edit_button;
	}

	public void setEdit_button(Integer edit_button) {
		this.edit_button = edit_button;
	}

	public Integer getQuery_button() {
		return query_button;
	}

	public void setQuery_button(Integer query_button) {
		this.query_button = query_button;
	}

	public Integer getExport_button() {
		return export_button;
	}

	public void setExport_button(Integer export_button) {
		this.export_button = export_button;
	}

	public Integer getImport_button() {
		return import_button;
	}

	public void setImport_button(Integer import_button) {
		this.import_button = import_button;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}