package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.QuotationDatasourceMapper;
import com.tsstu.console.model.QuotationDatasource;
import com.tsstu.console.service.QuotationDatasourceService;

/**
 * 行情数据源业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class QuotationDatasourceServiceImpl extends BaseServiceImpl<QuotationDatasource> implements QuotationDatasourceService {

    @Autowired
    private QuotationDatasourceMapper quotationDatasourceMapper;
    
    @Override
	public BaseMapper<QuotationDatasource> getDao() {
		return quotationDatasourceMapper;
	}
}
