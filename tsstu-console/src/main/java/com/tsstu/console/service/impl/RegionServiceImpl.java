package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.RegionMapper;
import com.tsstu.console.model.Region;
import com.tsstu.console.service.RegionService;

/**
 * 区域管理业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class RegionServiceImpl extends BaseServiceImpl<Region> implements RegionService {

    @Autowired
    private RegionMapper regionMapper;
    
    @Override
	public BaseMapper<Region> getDao() {
		return regionMapper;
	}
}
