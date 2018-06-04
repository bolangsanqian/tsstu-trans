package com.tsstu.console.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.common.constants.Constants;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.PermissionMapper;
import com.tsstu.console.dao.RolePermissionMapper;
import com.tsstu.console.model.Permission;
import com.tsstu.console.model.RolePermission;
import com.tsstu.console.service.PermissionService;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

	@Autowired
    private PermissionMapper permissionMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public BaseMapper<Permission> getDao() {
		return permissionMapper	;
	}

	@Override
	public List<Permission> buildPermissionByRoleId(Integer roleId) {
		
		//加载可用权限列表
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		List<Permission> permissionList = permissionMapper.getList(map);
		
		//查询指定角色拥有的权限列表
		List<Permission> userPermissionList = permissionMapper.getListByRoleId(roleId);
		if (null != permissionList && null != userPermissionList) {
			for (Permission userPermission : userPermissionList) {
				for (Permission permission : permissionList) {
					if (userPermission.getId().intValue() == permission.getId().intValue()) {
						permission.setHasPermission(true);
						break;
					}
				}
			}
		}
		return permissionList;
	}

	@Override
	public boolean updateRolePermission(Integer[] ids, Integer roleId) {
		//删除原有权限
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_id", roleId);
		rolePermissionMapper.delete(map);
		
		//添加权限
		if (null != ids && ids.length > 0) {
			List<RolePermission> list = new ArrayList<RolePermission>();
			for (int i = 0; i < ids.length; i++) {
				RolePermission rp = new RolePermission(roleId, ids[i]);
				list.add(rp);
			}
			rolePermissionMapper.addBatch(list);
		}
		return true;
	}

	@Override
	public List<Permission> getListByUserId(Integer userId) {
		return permissionMapper.getListByUserId(userId);
	}

	@Override
	public List<Permission> getEnableList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.STATUS_ENABLE);
		return permissionMapper.getList(map);
	}
	
	@Override
	public int delete(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", id);
		int count = permissionMapper.getCount(map);
		if (count > 0) {
			throw new ValidateException("请先删除子菜单！");
		}
		return super.delete(id);
	}
}
