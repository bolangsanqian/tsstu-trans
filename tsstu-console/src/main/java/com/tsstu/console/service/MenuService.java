package com.tsstu.console.service;

import java.util.List;

import com.tsstu.console.core.entity.Menu;
import com.tsstu.console.model.Permission;

public interface MenuService {

	List<Menu> buildMenuTree(List<Permission> permissionList);
	
}
