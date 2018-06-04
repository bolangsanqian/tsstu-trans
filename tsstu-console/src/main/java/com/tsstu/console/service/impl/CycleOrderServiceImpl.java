package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.CycleOrderMapper;
import com.tsstu.console.model.CycleOrder;
import com.tsstu.console.service.CycleOrderService;

/**
 * 周期订单业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class CycleOrderServiceImpl extends BaseServiceImpl<CycleOrder> implements CycleOrderService {

    @Autowired
    private CycleOrderMapper cycleOrderMapper;
    
    @Override
	public BaseMapper<CycleOrder> getDao() {
		return cycleOrderMapper;
	}
}
