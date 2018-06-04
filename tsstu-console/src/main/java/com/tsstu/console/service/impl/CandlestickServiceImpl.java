package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.CandlestickMapper;
import com.tsstu.console.model.Candlestick;
import com.tsstu.console.service.CandlestickService;

/**
 * K线管理业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class CandlestickServiceImpl extends BaseServiceImpl<Candlestick> implements CandlestickService {

    @Autowired
    private CandlestickMapper candlestickMapper;
    
    @Override
	public BaseMapper<Candlestick> getDao() {
		return candlestickMapper;
	}
}
