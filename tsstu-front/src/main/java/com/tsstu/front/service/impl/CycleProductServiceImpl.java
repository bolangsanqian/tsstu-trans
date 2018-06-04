package com.tsstu.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.front.dao.BaseMapper;
import com.tsstu.front.dao.CycleProductMapper;
import com.tsstu.front.model.CycleProduct;
import com.tsstu.front.service.CycleProductService;

/**
 * 产品管理(时限模式)业务实现类
 * @author： admin
 * @date： 2017-05-02
 */
@Service
public class CycleProductServiceImpl extends BaseServiceImpl<CycleProduct> implements CycleProductService {

    @Autowired
    private CycleProductMapper cycleProductMapper;
    
    @Override
	public BaseMapper<CycleProduct> getDao() {
		return cycleProductMapper;
	}
    
}
