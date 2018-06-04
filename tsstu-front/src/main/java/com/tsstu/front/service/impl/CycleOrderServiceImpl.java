package com.tsstu.front.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.common.util.DateUtil;
import com.tsstu.front.core.entity.Result;
import com.tsstu.front.dao.BaseMapper;
import com.tsstu.front.dao.CycleOrderMapper;
import com.tsstu.front.model.Customer;
import com.tsstu.front.model.CycleOrder;
import com.tsstu.front.model.CycleProduct;
import com.tsstu.front.model.HoldTime;
import com.tsstu.front.model.ProductCategory;
import com.tsstu.front.service.CycleOrderService;

/**
 * 周期订单业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class CycleOrderServiceImpl extends BaseServiceImpl<CycleOrder> implements CycleOrderService {

    @Autowired
    private CycleOrderMapper cycleOrderMapper;
    
    @Override
	public BaseMapper<CycleOrder> getDao() {
		return cycleOrderMapper;
	}

	@Override
	public Result tradeCheck(Customer customer, ProductCategory productCategory, CycleProduct cycleProduct, HoldTime holdTime, BigDecimal amount, Integer direction) {
		return new Result().success();
	}

	@Override
	public boolean trade(Customer customer, CycleOrder cycleOrder, ProductCategory productCategory, CycleProduct cycleProduct, HoldTime holdTime, BigDecimal amount, Integer direction) {

		Date now = new Date(); //当前时间
		Date closeDate = DateUtil.addSecond(now, holdTime.getHold_time() * 60);
		BigDecimal fee = new BigDecimal(0); //手续费
		Integer operation_member_id = customer.getOperation_member_id(); //运营中心id
		Integer member_id = customer.getMember_id(); //会员id
		Integer agent_member_id = customer.getAgent_member_id(); //代理会员id
		BigDecimal open_price = new BigDecimal(0); //开仓价格
		
		
		// 创建订单
		cycleOrder.setCustomer_id(customer.getId());
		cycleOrder.setOperation_member_id(operation_member_id);
		cycleOrder.setMember_id(member_id);
		cycleOrder.setAgent_member_id(agent_member_id);
		cycleOrder.setFlow_no(System.currentTimeMillis() + "");
		cycleOrder.setAmount(amount);
		cycleOrder.setFee(fee);
		cycleOrder.setProduct_category_id(productCategory.getId());
		cycleOrder.setProduct_category_code(productCategory.getCode());
		cycleOrder.setCycle_product_id(cycleProduct.getId());
		cycleOrder.setProduct_code(cycleProduct.getCode());
		cycleOrder.setProduct_name(cycleProduct.getName());
		cycleOrder.setDirection(direction);
		cycleOrder.setOpen_time(now);
		cycleOrder.setOpen_timestamp(now.getTime() / 1000);
		cycleOrder.setHold_time(holdTime.getHold_time());
		cycleOrder.setOpen_price(open_price);
		cycleOrder.setClose_time(closeDate);
		cycleOrder.setClose_timestamp(closeDate.getTime() / 1000);
		cycleOrder.setStatus(0);
		cycleOrder.setYk_amount(new BigDecimal(0));
		cycleOrder.setTime_unit(holdTime.getTime_unit());
		cycleOrder.setProfit(holdTime.getProfit());
		cycleOrder.setSettlement_type(0);
		cycleOrderMapper.add(cycleOrder);
		return true;
	}
}
