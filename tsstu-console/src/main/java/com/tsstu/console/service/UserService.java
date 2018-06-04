package com.tsstu.console.service;

import com.tsstu.console.model.User;

public interface UserService extends BaseService<User> {
	
	User getByUsername(String username);
	
	/**
	 * 密码验证
	 * @param md5password 加密后的密码
	 * @param password 需要验证的密码
	 * @return
	 */
	boolean checkPassword(String md5password, String password);
	
	/**
	 * 加密
	 * @param password 需要加密的密码
	 * @return
	 */
	String md5password(String password);

	/**
	 * 添加用户
	 * @param user 用户对象
	 * @param roleIds 角色ids
	 * @return
	 */
	int add(User user, Integer[] roleIds, Integer create_by);

	/**
	 * 修改用户
	 * @param user 用户对象
	 * @param roleIds 角色ids
	 * @return
	 */
	int update(User user, Integer[] roleIds);
}
