package com.tsstu.console.service;

import com.tsstu.console.model.TradingDay;

/**
 * 交易日管理业务接口
 * @author： admin
 * @date：2017-05-02
 **/
public interface TradingDayService extends BaseService<TradingDay> {

	/**
	 * 根据产品分类id添加交易日
	 * @param product_category_id 产品分类id
	 * @return
	 */
	boolean addByCategoryId(Integer product_category_id);
	
	/**
	 * 根据产品分类id重置交易日
	 * @param product_category_id 产品分类id
	 * @return
	 */
	boolean resetByCategoryId(Integer product_category_id);

}
