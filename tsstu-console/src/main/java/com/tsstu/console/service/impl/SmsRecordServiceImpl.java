package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.SmsRecordMapper;
import com.tsstu.console.model.SmsRecord;
import com.tsstu.console.service.SmsRecordService;

/**
 * 短信记录业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class SmsRecordServiceImpl extends BaseServiceImpl<SmsRecord> implements SmsRecordService {

    @Autowired
    private SmsRecordMapper smsRecordMapper;
    
    @Override
	public BaseMapper<SmsRecord> getDao() {
		return smsRecordMapper;
	}
}
