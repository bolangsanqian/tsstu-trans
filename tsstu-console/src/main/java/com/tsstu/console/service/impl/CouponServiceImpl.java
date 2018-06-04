package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.CouponMapper;
import com.tsstu.console.model.Coupon;
import com.tsstu.console.service.CouponService;

/**
 * 优惠券业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class CouponServiceImpl extends BaseServiceImpl<Coupon> implements CouponService {

    @Autowired
    private CouponMapper couponMapper;
    
    @Override
	public BaseMapper<Coupon> getDao() {
		return couponMapper;
	}
}
