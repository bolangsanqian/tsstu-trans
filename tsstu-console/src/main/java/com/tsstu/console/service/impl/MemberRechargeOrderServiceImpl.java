package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.MemberRechargeOrderMapper;
import com.tsstu.console.model.MemberRechargeOrder;
import com.tsstu.console.service.MemberRechargeOrderService;

/**
 * 会员充值订单业务实现类
 * @author： admin
 * @date： 2017-05-11
 */
@Service
public class MemberRechargeOrderServiceImpl extends BaseServiceImpl<MemberRechargeOrder> implements MemberRechargeOrderService {

    @Autowired
    private MemberRechargeOrderMapper memberRechargeOrderMapper;
    
    @Override
	public BaseMapper<MemberRechargeOrder> getDao() {
		return memberRechargeOrderMapper;
	}
}
