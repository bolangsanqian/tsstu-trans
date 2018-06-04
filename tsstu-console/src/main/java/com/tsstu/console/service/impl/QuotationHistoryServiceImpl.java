package com.tsstu.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.common.model.QuotationHistory;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.QuotationHistoryMapper;
import com.tsstu.console.service.QuotationHistoryService;

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

	@Override
	public int deleteQuotationHistory(String code, Integer minute, Integer keepCount) {
		keepCount = keepCount - 1;
		return quotationHistoryMapper.deleteQuotationHistory(code, minute, keepCount);
	}

	@Override
	public List<QuotationHistory> getListByCodeAndMinute(String code, int minute, int limit) {
		return quotationHistoryMapper.getListByCodeAndMinute(code, minute, limit);
	}
}
