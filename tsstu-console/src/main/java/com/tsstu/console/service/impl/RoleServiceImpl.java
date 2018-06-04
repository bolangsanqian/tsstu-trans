package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.RoleMapper;
import com.tsstu.console.model.Role;
import com.tsstu.console.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Autowired
    private RoleMapper roleMapper;

	@Override
	public BaseMapper<Role> getDao() {
		return roleMapper;
	}
}
