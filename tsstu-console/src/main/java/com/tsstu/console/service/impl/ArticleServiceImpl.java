package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.ArticleMapper;
import com.tsstu.console.model.Article;
import com.tsstu.console.service.ArticleService;

/**
 * 文章管理业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    
    @Override
	public BaseMapper<Article> getDao() {
		return articleMapper;
	}
}
