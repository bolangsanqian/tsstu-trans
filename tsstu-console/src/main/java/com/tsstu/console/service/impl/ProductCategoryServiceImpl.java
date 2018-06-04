package com.tsstu.console.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.common.util.FileUtil;
import com.tsstu.common.constants.Constants;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.ProductCategoryMapper;
import com.tsstu.console.model.ProductCategory;
import com.tsstu.console.service.CycleProductService;
import com.tsstu.console.service.ProductCategoryService;
import com.tsstu.console.service.TradingDayService;
import com.tsstu.console.util.PathUtil;

/**
 * 产品分类业务实现类
 * @author： admin
 * @date： 2017-05-01
 */
@Service
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    
    @Autowired
    private TradingDayService tradingDayService;
    
    @Autowired
    private CycleProductService cycleProductService;
    
    @Override
	public BaseMapper<ProductCategory> getDao() {
		return productCategoryMapper;
	}

	@Override
	public List<ProductCategory> getEnableList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.STATUS_ENABLE);
		return productCategoryMapper.getList(map);
	}
	
	@Override
	@Transactional
	public int add(ProductCategory model) {
		model.setCode(model.getCode().toUpperCase());
		boolean bo = this.isExist("code", model.getCode());
		if (bo) {
			throw new ValidateException("分类代码已存在，请更换！");
		}
		
		int effectRow = super.add(model);
		boolean bo2 = false;
		if (effectRow > 0) {
			bo2 = tradingDayService.addByCategoryId(model.getId());
		}
		return bo2 ? 1 : 0;
	}
	
	@Override
	public int update(ProductCategory model) {
		model.setCode(model.getCode().toUpperCase());
		boolean bo = this.isExist("id", model.getId(), "code", model.getCode());
		if (bo) {
			throw new ValidateException("分类代码已存在，请更换！");
		}
		return super.update(model);
	}
	
	@Override
	@Transactional
	public int delete(int id) {
		//删除图片
		ProductCategory category = this.getOne(id);
    	if (null != category.getIcon_url() && !"".equals(category.getIcon_url())) {
    		String filePath = PathUtil.getClasspath() + category.getIcon_url();
    		FileUtil.delFile(filePath);
    	}
		
		//判断是否已经引用
		int count = cycleProductService.getCount("product_category_id", id);
		if (count > 0) {
			throw new ValidateException("请先到时限产品页面删除产品后，再删产品分类！");
		}
		
		int effectRow = super.delete(id);
		if (effectRow > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("product_category_id", id);
			effectRow = tradingDayService.delete(map);
		}
		return effectRow;
	}
}
