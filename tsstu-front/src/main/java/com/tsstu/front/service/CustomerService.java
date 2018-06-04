package com.tsstu.front.service;

import java.math.BigDecimal;

import com.tsstu.front.model.Customer;

/**
 * 客户管理业务接口
 * @author： admin
 * @date：2017-05-03
 **/
public interface CustomerService extends BaseService<Customer> {

	/**
	 * 调整客户资金
	 * @param id 客户编号
	 * @param amount 调整金额
	 * @return
	 */
	boolean changeBalance(Integer id, BigDecimal amount);

}
