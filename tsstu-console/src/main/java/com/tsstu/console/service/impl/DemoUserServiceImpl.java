package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.DemoUserMapper;
import com.tsstu.console.model.DemoUser;
import com.tsstu.console.service.DemoUserService;

@Service
public class DemoUserServiceImpl extends BaseServiceImpl<DemoUser> implements DemoUserService {

	@Autowired
    private DemoUserMapper demoUserMapper;

	@Override
	public BaseMapper<DemoUser> getDao() {
		return demoUserMapper	;
	}
}
