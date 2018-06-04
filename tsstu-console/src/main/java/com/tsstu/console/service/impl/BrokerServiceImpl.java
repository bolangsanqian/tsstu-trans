package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.BrokerMapper;
import com.tsstu.console.model.Broker;
import com.tsstu.console.service.BrokerService;

/**
 * 经纪人业务实现类
 * @author： admin
 * @date： 2017-05-16
 */
@Service
public class BrokerServiceImpl extends BaseServiceImpl<Broker> implements BrokerService {

    @Autowired
    private BrokerMapper brokerMapper;
    
    @Override
	public BaseMapper<Broker> getDao() {
		return brokerMapper;
	}
}
