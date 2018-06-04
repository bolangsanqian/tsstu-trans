package com.tsstu.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.HoldTimeMapper;
import com.tsstu.console.model.HoldTime;
import com.tsstu.console.service.HoldTimeService;

/**
 * 持单时间业务实现类
 * @author： admin
 * @date： 2017-05-09
 */
@Service
public class HoldTimeServiceImpl extends BaseServiceImpl<HoldTime> implements HoldTimeService {

    @Autowired
    private HoldTimeMapper holdTimeMapper;
    
    @Override
	public BaseMapper<HoldTime> getDao() {
		return holdTimeMapper;
	}

	@Override
	public List<HoldTime> getDictList() {
		return holdTimeMapper.getDictList();
	}
}
