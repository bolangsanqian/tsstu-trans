package com.tsstu.console.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.common.constants.Constants;
import com.tsstu.common.constants.Constants.UserConstants;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.UserMapper;
import com.tsstu.console.model.User;
import com.tsstu.console.model.UserRole;
import com.tsstu.console.service.UserRoleService;
import com.tsstu.console.service.UserService;
import com.tsstu.common.util.MD5;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
    private UserMapper userMapper;
	
	@Autowired
    private UserRoleService userRoleService;

	@Override
	public BaseMapper<User> getDao() {
		return userMapper;
	}

	@Override
	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	@Override
	public boolean checkPassword(String dbPassword, String password) {
		String md5password = this.md5password(password);
		if (dbPassword.equals(md5password)) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public int add(User user, Integer[] roleIds, Integer create_by) {
		//判断用户名是否已存在
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", user.getUsername());
		boolean isExist = userMapper.isExist(map);
		if (isExist) {
			throw new ValidateException("用户名已存在，请更换！");
		}
		
		//判断手机号码是否已存在
		map.clear();
		map.put("mobile", user.getMobile());
		isExist = userMapper.isExist(map);
		if (isExist) {
			throw new ValidateException("手机号码已存在，请更换！");
		}
		
		if (null == user.getAllow_del()) {
			user.setAllow_del(Constants.ALLOW);
		}
		user.setPassword(this.md5password(user.getPassword()));
		user.setCreate_time(new Date());
		user.setCreate_by(create_by);
		user.setStatus(Constants.STATUS_ENABLE);
		int effectRow = userMapper.add(user);
		if (effectRow > 0) {
			this.updateRoles(user.getId(), roleIds);
		}
		return effectRow;
	}

	@Override
	public int update(User user, Integer[] roleIds) {
		user.setUsername(null); //用户名不容许更改
		user.setUser_type(null); //用户类型不容许更改
		
		//判断手机号码是否已存在
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user.getId());
		map.put("mobile", user.getMobile());
		boolean isExist = userMapper.isExist(map);
		if (isExist) {
			throw new ValidateException("手机号码已存在，请更换！");
		}
		
		if (StringUtils.isNotBlank(user.getPassword())) {
			user.setPassword(this.md5password(user.getPassword()));
		}
		int effectRow = userMapper.update(user); //修改用户信息
		if (effectRow > 0) {
			this.updateRoles(user.getId(), roleIds); //修改角色
		}
		return effectRow;
	}
	
	/**
	 * 修改用户角色，先删除，再重新添加角色
	 * @param userId 用户id
	 * @param roleIds 角色ids
	 */
	private void updateRoles(Integer userId, Integer [] roleIds) {
		userRoleService.delete("user_id", userId);
		if (null != roleIds && roleIds.length > 0) {
			for (Integer roleId : roleIds) {
				UserRole userRole = new UserRole();
				userRole.setRole_id(roleId);
				userRole.setUser_id(userId);
				userRoleService.add(userRole);
			}
		}
	}
	
	@Override
	public int delete(int id) {
		User user = this.getOne(id);
		int effectRow = 0;
		if (null != user) {
			if (user.getAllow_del().intValue() == Constants.ALLOW) {
				effectRow = super.delete(id);
				userRoleService.delete("user_id", id);
			} else {
				throw new ValidateException("该用户不容许删除！");
			}
		}
		return effectRow;
	}

	@Override
	public String md5password(String password) {
		return MD5.getInstance().getMD5( UserConstants.MD5_PWD_PREFIX + password);
	}
}
