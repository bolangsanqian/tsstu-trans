package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.WechatMsgItemMapper;
import com.tsstu.console.model.WechatMsgItem;
import com.tsstu.console.service.WechatMsgItemService;

/**
 * 消息子项(微信)业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class WechatMsgItemServiceImpl extends BaseServiceImpl<WechatMsgItem> implements WechatMsgItemService {

    @Autowired
    private WechatMsgItemMapper wechatMsgItemMapper;
    
    @Override
	public BaseMapper<WechatMsgItem> getDao() {
		return wechatMsgItemMapper;
	}
}
