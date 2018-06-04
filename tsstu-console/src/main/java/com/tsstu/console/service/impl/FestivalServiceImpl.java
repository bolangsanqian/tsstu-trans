package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.FestivalMapper;
import com.tsstu.console.model.Festival;
import com.tsstu.console.service.FestivalService;

/**
 * 节假日业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class FestivalServiceImpl extends BaseServiceImpl<Festival> implements FestivalService {

    @Autowired
    private FestivalMapper festivalMapper;
    
    @Override
	public BaseMapper<Festival> getDao() {
		return festivalMapper;
	}
}
