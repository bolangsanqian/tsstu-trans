package com.tsstu.console.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.CycleProductMapper;
import com.tsstu.console.dao.HoldTimeMapper;
import com.tsstu.console.model.CycleProduct;
import com.tsstu.console.service.CycleProductService;

/**
 * 产品管理(时限模式)业务实现类
 * @author： admin
 * @date： 2017-05-02
 */
@Service
public class CycleProductServiceImpl extends BaseServiceImpl<CycleProduct> implements CycleProductService {

    @Autowired
    private CycleProductMapper cycleProductMapper;
    
    @Autowired
    private HoldTimeMapper holdTimeMapper;
    
    @Override
	public BaseMapper<CycleProduct> getDao() {
		return cycleProductMapper;
	}
    
    @Override
    @Transactional
    public int delete(int id) {
    	int effectRow = super.delete(id);
    	if (effectRow > 0) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("cycle_product_id", id);
    		holdTimeMapper.delete(map);
    	}
    	return effectRow;
    }
    
    @Override
    public int deleteBatch(Object[] ids) {
    	int effectRow = super.deleteBatch(ids);
    	if (effectRow > 0) {
    		for (Object id : ids) {
    			Map<String, Object> map = new HashMap<String, Object>();
        		map.put("cycle_product_id", id);
        		holdTimeMapper.delete(map);
			}
    	}
    	return effectRow;
    }
    
    @Override
    public int add(CycleProduct model) {
    	model.setCode(model.getCode().toUpperCase());
    	boolean bo = this.isExist("code", model.getCode());
		if (bo) {
			throw new ValidateException("商品代码已存在，请更换！");
		}
    	return super.add(model);
    }
    
    @Override
    public int update(CycleProduct model) {
    	model.setCode(model.getCode().toUpperCase());
    	boolean bo = this.isExist("id", model.getId(), "code", model.getCode());
		if (bo) {
			throw new ValidateException("商品代码已存在，请更换！");
		}
    	return super.update(model);
    }
}
