package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.DemoMapper;
import com.tsstu.console.model.Demo;
import com.tsstu.console.service.DemoService;

@Service
public class DemoServiceImpl extends BaseServiceImpl<Demo> implements DemoService {

	@Autowired
    private DemoMapper demoMapper;

	@Override
	public BaseMapper<Demo> getDao() {
		return demoMapper;
	}
}
