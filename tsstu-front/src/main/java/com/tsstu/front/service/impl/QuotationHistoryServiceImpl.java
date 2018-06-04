package com.tsstu.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.front.dao.BaseMapper;
import com.tsstu.front.dao.QuotationHistoryMapper;
import com.tsstu.common.model.QuotationHistory;
import com.tsstu.front.service.QuotationHistoryService;

/**
 * 历史行情业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class QuotationHistoryServiceImpl extends BaseServiceImpl<QuotationHistory> implements QuotationHistoryService {

    @Autowired
    private QuotationHistoryMapper quotationHistoryMapper;
    
    @Override
	public BaseMapper<QuotationHistory> getDao() {
		return quotationHistoryMapper;
	}
}
