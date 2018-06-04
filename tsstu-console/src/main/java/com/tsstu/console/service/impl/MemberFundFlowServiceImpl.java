package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.MemberFundFlowMapper;
import com.tsstu.console.model.MemberFundFlow;
import com.tsstu.console.service.MemberFundFlowService;

/**
 * 会员资金流水业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class MemberFundFlowServiceImpl extends BaseServiceImpl<MemberFundFlow> implements MemberFundFlowService {

    @Autowired
    private MemberFundFlowMapper memberFundFlowMapper;
    
    @Override
	public BaseMapper<MemberFundFlow> getDao() {
		return memberFundFlowMapper;
	}
}
