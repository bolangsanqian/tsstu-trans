package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.WechatMsgMapper;
import com.tsstu.console.model.WechatMsg;
import com.tsstu.console.service.WechatMsgService;

/**
 * 消息管理业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class WechatMsgServiceImpl extends BaseServiceImpl<WechatMsg> implements WechatMsgService {

    @Autowired
    private WechatMsgMapper wechatMsgMapper;
    
    @Override
	public BaseMapper<WechatMsg> getDao() {
		return wechatMsgMapper;
	}
}
