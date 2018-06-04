package com.tsstu.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.common.model.Quotation;
import com.tsstu.front.dao.BaseMapper;
import com.tsstu.front.dao.QuotationMapper;
import com.tsstu.front.service.QuotationService;

/**
 * 实时行情业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class QuotationServiceImpl extends BaseServiceImpl<Quotation> implements QuotationService {

    @Autowired
    private QuotationMapper quotationMapper;
    
    @Override
	public BaseMapper<Quotation> getDao() {
		return quotationMapper;
	}
}
