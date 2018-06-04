package com.tsstu.console.service;

import java.util.List;

import com.tsstu.console.model.Permission;

public interface PermissionService extends BaseService<Permission> {

	/**
	 * 根据角色id加载权限列表
	 * @param roleId
	 */
	List<Permission> buildPermissionByRoleId(Integer roleId);

	/**
	 * 修改角色的权限
	 * @param ids 权限id
	 * @param roleId 角色id
	 * @return
	 */
	boolean updateRolePermission(Integer[] ids, Integer roleId);

	/**
	 * 根据用户id查询用户权限列表
	 * @param userId 用户id
	 * @return
	 */
	List<Permission> getListByUserId(Integer userId);

	/**
	 * 查询可用权限列表
	 * @return
	 */
	List<Permission> getEnableList();
}
