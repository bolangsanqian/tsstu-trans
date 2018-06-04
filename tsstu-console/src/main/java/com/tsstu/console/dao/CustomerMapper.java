package com.tsstu.console.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.tsstu.console.model.Customer;

/**
 * 客户管理Mapper 接口
 * @author： admin
 * @date： 2017-05-03
 **/
public interface CustomerMapper extends BaseMapper<Customer> {

	/**
	 * 调整客户资金
	 * @param id 客户id
	 * @param amount 调整金额
	 * @return
	 */
	int changeBalance(@Param("id")Integer id, @Param("amount")BigDecimal amount);

	
}