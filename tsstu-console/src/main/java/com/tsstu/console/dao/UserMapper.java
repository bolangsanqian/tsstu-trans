package com.tsstu.console.dao;

import org.apache.ibatis.annotations.Param;

import com.tsstu.console.model.User;

/**
 * 用户Dao 接口
 * @author liwei
 * @since 2017年4月25日21:46:52
 **/
public interface UserMapper extends BaseMapper<User> {

	User getByUsername(@Param("username")String username);
   
}