package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.LogMapper;
import com.tsstu.console.model.Log;
import com.tsstu.console.service.LogService;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	@Autowired
    private LogMapper logMapper;

	@Override
	public BaseMapper<Log> getDao() {
		return logMapper;
	}
}
