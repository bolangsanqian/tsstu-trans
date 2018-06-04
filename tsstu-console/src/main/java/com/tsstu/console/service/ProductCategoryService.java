package com.tsstu.console.service;

import java.util.List;

import com.tsstu.console.model.ProductCategory;

/**
 * 产品分类业务接口
 * @author： admin
 * @date：2017-05-01
 **/
public interface ProductCategoryService extends BaseService<ProductCategory> {

	/**
	 * 获取启用状态产品分类列表
	 * @return
	 */
	List<ProductCategory> getEnableList();
	
}
