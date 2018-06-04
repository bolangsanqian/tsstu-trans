package com.tsstu.console.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tsstu.console.core.entity.Menu;
import com.tsstu.console.model.Permission;
import com.tsstu.console.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Override
	public List<Menu> buildMenuTree(List<Permission> permissionList) {
		return buildMenu(0, permissionList);
	}
	
	/**
	 * 递归构建左侧菜单
	 * @param menuId 父菜单id
	 * @param menuList 菜单集合
	 * @return
	 */
	private List<Menu> buildMenu(int menuId, List<Permission> permissionList) {
		List<Menu> items = new ArrayList<Menu>();
		for (Permission p : permissionList) {
			if (p.getPermission_type() < 2 && p.getPid() == menuId) {
				Menu menu = new Menu();
				menu.setId(p.getId());
				menu.setName(p.getName());
				menu.setUrl(p.getUrl());
				menu.setIcon(p.getIcon());
				menu.setSort(p.getSort());
				List<Menu> subMenuList = this.buildMenu(p.getId(), permissionList);
				menu.setList(subMenuList);
				items.add(menu);
			}
		}
		Collections.sort(items);
		return items;
	}

}
