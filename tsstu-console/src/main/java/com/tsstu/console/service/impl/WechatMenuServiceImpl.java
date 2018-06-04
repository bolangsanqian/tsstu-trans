package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.WechatMenuMapper;
import com.tsstu.console.model.WechatMenu;
import com.tsstu.console.service.WechatMenuService;

/**
 * 菜单管理(微信)业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class WechatMenuServiceImpl extends BaseServiceImpl<WechatMenu> implements WechatMenuService {

    @Autowired
    private WechatMenuMapper wechatMenuMapper;
    
    @Override
	public BaseMapper<WechatMenu> getDao() {
		return wechatMenuMapper;
	}
}
