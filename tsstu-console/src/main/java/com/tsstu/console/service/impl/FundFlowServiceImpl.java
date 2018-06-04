package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.FundFlowMapper;
import com.tsstu.console.model.FundFlow;
import com.tsstu.console.service.FundFlowService;

/**
 * 资金流水业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class FundFlowServiceImpl extends BaseServiceImpl<FundFlow> implements FundFlowService {

    @Autowired
    private FundFlowMapper fundFlowMapper;
    
    @Override
	public BaseMapper<FundFlow> getDao() {
		return fundFlowMapper;
	}
}
