package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.ConfigMapper;
import com.tsstu.console.model.Config;
import com.tsstu.console.service.ConfigService;

@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements ConfigService {

	@Autowired
    private ConfigMapper configMapper;
	
	@Override
	public BaseMapper<Config> getDao() {
		return configMapper;
	}
}
