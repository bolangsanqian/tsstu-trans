package com.tsstu.front.service;

import java.math.BigDecimal;

import com.tsstu.front.core.entity.Result;
import com.tsstu.front.model.Customer;
import com.tsstu.front.model.CycleOrder;
import com.tsstu.front.model.CycleProduct;
import com.tsstu.front.model.HoldTime;
import com.tsstu.front.model.ProductCategory;

/**
 * 周期订单业务接口
 * @author： admin
 * @date：2017-05-04
 **/
public interface CycleOrderService extends BaseService<CycleOrder> {

	/**
	 * 交易验证(下单验证)
	 * @param customer 交易客户
	 * @param productCategory 产品分类
	 * @param cycleProduct 交易产品
	 * @param holdTime 持仓时间
	 * @param amount 交易金额
	 * @param direction 下单方向
	 * @return
	 */
	Result tradeCheck(Customer customer, ProductCategory productCategory, CycleProduct cycleProduct, HoldTime holdTime, BigDecimal amount, Integer direction);

	/**
	 * 交易(下单)
	 * @param customer 交易客户
	 * @param cycleOrder 交易订单
	 * @param productCategory 产品分类
	 * @param cycleProduct 交易产品
	 * @param holdTime 持仓时间
	 * @param amount 交易金额
	 * @param direction 下单方向
	 * @return
	 */
	boolean trade(Customer customer, CycleOrder cycleOrder, ProductCategory productCategory, CycleProduct cycleProduct, HoldTime holdTime, BigDecimal amount, Integer direction);

}
