package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.ColumnsMapper;
import com.tsstu.console.model.Columns;
import com.tsstu.console.service.ColumnsService;

/**
 * 数据库表字段业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class ColumnsServiceImpl extends BaseServiceImpl<Columns> implements ColumnsService {

    @Autowired
    private ColumnsMapper columnsMapper;
    
    @Override
	public BaseMapper<Columns> getDao() {
		return columnsMapper;
	}
}
