package com.tsstu.console.dao;

import java.util.List;

import com.tsstu.console.model.Permission;

/**
 * 权限 Dao 接口
 * @author liwei
 * @since 2017年4月25日16:02:13
 **/
public interface PermissionMapper extends BaseMapper<Permission> {

	/**
	 * 根据角色ID获取角色所拥有的权限列表
	 * @param roleId 角色id
	 * @return
	 */
	List<Permission> getListByRoleId(Integer roleId);

	/**
	 * 根据用户Id查询权限列表
	 * @param userId 用户id
	 * @return
	 */
	List<Permission> getListByUserId(Integer userId);

}