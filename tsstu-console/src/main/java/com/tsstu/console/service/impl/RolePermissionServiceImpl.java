package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.RolePermissionMapper;
import com.tsstu.console.model.RolePermission;
import com.tsstu.console.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {

	@Autowired
    private RolePermissionMapper rolePermissionMapper;

	@Override
	public BaseMapper<RolePermission> getDao() {
		return rolePermissionMapper;
	}
}
