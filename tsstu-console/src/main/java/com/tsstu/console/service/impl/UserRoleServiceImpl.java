package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.UserRoleMapper;
import com.tsstu.console.model.UserRole;
import com.tsstu.console.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

	@Autowired
    private UserRoleMapper userRoleMapper;

	@Override
	public BaseMapper<UserRole> getDao() {
		return userRoleMapper;
	}
}
