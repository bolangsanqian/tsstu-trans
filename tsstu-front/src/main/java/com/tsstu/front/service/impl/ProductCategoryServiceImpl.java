package com.tsstu.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.front.dao.BaseMapper;
import com.tsstu.front.dao.ProductCategoryMapper;
import com.tsstu.front.model.ProductCategory;
import com.tsstu.front.service.ProductCategoryService;

/**
 * 产品分类业务实现类
 * @author： admin
 * @date： 2017-05-01
 */
@Service
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    
    
    @Override
	public BaseMapper<ProductCategory> getDao() {
		return productCategoryMapper;
	}
}
