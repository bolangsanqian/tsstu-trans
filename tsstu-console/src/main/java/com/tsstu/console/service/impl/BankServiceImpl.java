package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BankMapper;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.model.Bank;
import com.tsstu.console.service.BankService;

/**
 * 银行管理业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class BankServiceImpl extends BaseServiceImpl<Bank> implements BankService {

    @Autowired
    private BankMapper bankMapper;
    
    @Override
	public BaseMapper<Bank> getDao() {
		return bankMapper;
	}
}
