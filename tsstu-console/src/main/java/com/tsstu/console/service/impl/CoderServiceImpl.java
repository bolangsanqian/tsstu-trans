package com.tsstu.console.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.CoderMapper;
import com.tsstu.console.model.Coder;
import com.tsstu.console.model.CoderAttribute;
import com.tsstu.console.model.Columns;
import com.tsstu.console.service.CoderAttributeService;
import com.tsstu.console.service.CoderService;
import com.tsstu.console.service.ColumnsService;

@Service
public class CoderServiceImpl extends BaseServiceImpl<Coder> implements CoderService {

	@Autowired
    private CoderMapper coderMapper;
	
	@Autowired
    private CoderAttributeService CoderAttributeService;
	
	@Autowired
    private ColumnsService columnsService;

	@Override
	public BaseMapper<Coder> getDao() {
		return coderMapper	;
	}
	
	@Override
	@Transactional
	public int add(Coder model) {
		int effectRow = super.add(model);
		if (effectRow <= 0) {
			throw new ValidateException("添加失败");
		}
		List<Columns> list = columnsService.getList("table_name", model.getTable_name());
		int i = 1;
		for (Columns column : list) {
			CoderAttribute attr = new CoderAttribute();
			boolean isKey = null != column.getExtra() && !"".endsWith(column.getExtra()); 
			if (isKey) {
				attr.setIs_key(1);
				attr.setData_type("Integer");
				attr.setIs_input(0);
				attr.setIs_search(0);
			} else {
				attr.setIs_key(0);
			}
			if ("varchar".equals(column.getData_type()) || "text".equals(column.getData_type())) {
				attr.setData_type("VARCHAR");
				attr.setIs_input(1);
				attr.setIs_search(1);
				attr.setIs_show(1);
				attr.setElement_type("text");
				attr.setJava_type("String");
			} else if ("int".equals(column.getData_type()) || "tinyint".equals(column.getData_type())) {
				attr.setData_type("INTEGER");
				attr.setElement_type("select");
				if (!isKey) {
					attr.setIs_input(1);
					attr.setIs_search(1);
				}
				attr.setJava_type("Integer");
			} else if ("datetime".equals(column.getData_type())) {
				attr.setData_type("TIMESTAMP");
				attr.setJava_type("Date");
				attr.setElement_type("date");
			} else if ("decimal".equals(column.getData_type())) {
				attr.setData_type("NUMERIC");
				attr.setJava_type("BigDecimal");
				attr.setElement_type("text");
			} else {
				attr.setElement_type("empty");
			}
			attr.setName(column.getColumn_name());
			attr.setTitle(column.getColumn_comment());
			attr.setCreate_time(new Date());
			attr.setCoder_id(model.getId());
			attr.setSort(i++);
			CoderAttributeService.add(attr);
		}
		return effectRow;
	}
	
	@Override
	@Transactional
	public int delete(int id) {
		int effectRow = super.delete(id);
		if (effectRow > 0) {
			CoderAttributeService.delete("coder_id", id);
		}
		return effectRow;
	}
	
	@Override
	@Transactional
	public int deleteBatch(Object[] ids) {
		int effectRow = super.deleteBatch(ids);
		if (effectRow > 0) {
			for (Object obj : ids) {
				int id = Integer.valueOf(obj.toString());
				CoderAttributeService.delete("coder_id", id);
			}
		}
		return effectRow;
	}
}
