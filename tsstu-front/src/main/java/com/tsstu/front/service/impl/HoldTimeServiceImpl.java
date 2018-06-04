package com.tsstu.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.front.dao.BaseMapper;
import com.tsstu.front.dao.HoldTimeMapper;
import com.tsstu.front.model.HoldTime;
import com.tsstu.front.service.HoldTimeService;

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
	public List<HoldTime> getDictList(Integer product_id) {
		return holdTimeMapper.getDictList(product_id);
	}
}
