package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.CoderAttributeMapper;
import com.tsstu.console.model.CoderAttribute;
import com.tsstu.console.service.CoderAttributeService;

@Service
public class CoderAttributeServiceImpl extends BaseServiceImpl<CoderAttribute> implements CoderAttributeService {

	@Autowired
    private CoderAttributeMapper coderAttributeMapper;

	@Override
	public BaseMapper<CoderAttribute> getDao() {
		return coderAttributeMapper	;
	}
}
