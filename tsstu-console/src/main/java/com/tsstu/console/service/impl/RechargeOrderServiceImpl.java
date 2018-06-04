package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.RechargeOrderMapper;
import com.tsstu.console.model.RechargeOrder;
import com.tsstu.console.service.RechargeOrderService;

/**
 * 充值订单业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class RechargeOrderServiceImpl extends BaseServiceImpl<RechargeOrder> implements RechargeOrderService {

    @Autowired
    private RechargeOrderMapper rechargeOrderMapper;
    
    @Override
	public BaseMapper<RechargeOrder> getDao() {
		return rechargeOrderMapper;
	}
}
