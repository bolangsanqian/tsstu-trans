package com.tsstu.front.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.front.dao.BaseMapper;
import com.tsstu.front.dao.CustomerMapper;
import com.tsstu.front.model.Customer;
import com.tsstu.front.service.CustomerService;

/**
 * 客户管理业务实现类
 * @author： admin
 * @date： 2017-05-03
 */
@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    
    @Override
	public BaseMapper<Customer> getDao() {
		return customerMapper;
	}

	@Override
	public synchronized boolean changeBalance(Integer customer_id, BigDecimal amount) {
		int effectRow = customerMapper.changeBalance(customer_id, amount);
		return effectRow > 0;
	}
}
