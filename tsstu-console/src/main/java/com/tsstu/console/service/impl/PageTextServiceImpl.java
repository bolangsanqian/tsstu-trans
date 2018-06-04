package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.PageTextMapper;
import com.tsstu.console.model.PageText;
import com.tsstu.console.service.PageTextService;

/**
 * 文案管理业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class PageTextServiceImpl extends BaseServiceImpl<PageText> implements PageTextService {

    @Autowired
    private PageTextMapper pageTextMapper;
    
    @Override
	public BaseMapper<PageText> getDao() {
		return pageTextMapper;
	}
}
