package com.tsstu.console.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.common.constants.Constants;
import com.tsstu.common.constants.Constants.REVIEW_STATUS;
import com.tsstu.common.constants.Constants.WITHDRAW_STATUS;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.MemberWithdrawOrderMapper;
import com.tsstu.console.model.MemberWithdrawOrder;
import com.tsstu.console.service.MemberWithdrawOrderService;

/**
 * 会员提现订单业务实现类
 * @author： admin
 * @date： 2017-05-12
 */
@Service
public class MemberWithdrawOrderServiceImpl extends BaseServiceImpl<MemberWithdrawOrder> implements MemberWithdrawOrderService {

    @Autowired
    private MemberWithdrawOrderMapper memberWithdrawOrderMapper;
    
    @Override
	public BaseMapper<MemberWithdrawOrder> getDao() {
		return memberWithdrawOrderMapper;
	}
    
    @Override
    @Transactional
	public boolean reviewPass(String idstr, Integer review_id, String review_remark) {
    	int effectRow = 0;
    	String [] ids = idstr.split(",");
    	for (String id : ids) {
    		effectRow += this.reviewPass(Integer.valueOf(id), review_id, review_remark);
		}
		return effectRow == ids.length;
	}
    
    @Override
    public int reviewPass(Integer id, Integer review_id, String review_remark) {
    	MemberWithdrawOrder order = this.getOne(id);
    	if (null == order) {
			throw new ValidateException("提现订单不存在！");
    	} else if (order.getStatus().intValue() != WITHDRAW_STATUS.PROCESS) {
			throw new ValidateException("提现订单非处理中状态");
		} else if (order.getReview_sign().intValue() == Constants.FALSE) {
			throw new ValidateException("该提现订单不需要审批");
		} else if (order.getReview_status().intValue() != REVIEW_STATUS.REVIEW_WAIT) {
			throw new ValidateException("提现订单非待审批状态");
		}
		order = new MemberWithdrawOrder();
		order.setId(id);
		order.setReview_status(REVIEW_STATUS.REVIEW_PASS);
		order.setReview_id(review_id);
		order.setReview_remark(review_remark);
		order.setReview_time(new Date());
		return this.update(order);
	}

	@Override
	 @Transactional
	public boolean reviewReject(String idstr, Integer review_id, String review_remark) {
		int effectRow = 0;
    	String [] ids = idstr.split(",");
    	for (String id : ids) {
    		effectRow += this.reviewReject(Integer.valueOf(id), review_id, review_remark);
		}
		return effectRow == ids.length;
	}
	
	@Override
    public int reviewReject(Integer id, Integer review_id, String review_remark) {
		MemberWithdrawOrder order = this.getOne(id);
		if (null == order) {
			throw new ValidateException("提现订单不存在！");
    	} else if (order.getStatus().intValue() != WITHDRAW_STATUS.PROCESS) {
			throw new ValidateException("提现订单非处理中状态");
		} else if (order.getReview_sign().intValue() == Constants.FALSE) {
			throw new ValidateException("该提现订单不需要审批");
		} else if (order.getReview_status().intValue() != REVIEW_STATUS.REVIEW_WAIT) {
			throw new ValidateException("提现订单非待审批状态");
		}
		order = new MemberWithdrawOrder();
		order.setId(id);
		order.setReview_status(REVIEW_STATUS.REVIEW_REJECT);
		order.setReview_id(review_id);
		order.setReview_remark(review_remark);
		order.setReview_time(new Date());
		order.setPay_remark(review_remark);
		order.setStatus(WITHDRAW_STATUS.FAILURE);
		return this.update(order);
	}
	//===============================================付款=========================================================//
	@Override
    @Transactional
	public boolean payPass(String idstr, Integer pay_id, String pay_remark, Integer pay_method) {
    	int effectRow = 0;
    	String [] ids = idstr.split(",");
    	for (String id : ids) {
    		effectRow += this.payPass(Integer.valueOf(id), pay_id, pay_remark, pay_method);
		}
		return effectRow == ids.length;
	}
    
    @Override
    public int payPass(Integer id, Integer pay_id, String pay_remark, Integer pay_method) {
    	MemberWithdrawOrder order = this.getOne(id);
    	if (null == order) {
			throw new ValidateException("提现订单不存在！");
    	} else if (order.getStatus().intValue() != WITHDRAW_STATUS.PROCESS) {
			throw new ValidateException("提现订单非处理中状态");
		} else if (order.getReview_status().intValue() != REVIEW_STATUS.REVIEW_PASS) {
			throw new ValidateException("该提现订单未审批或审批未通过");
		}
		order = new MemberWithdrawOrder();
		order.setId(id);
		order.setStatus(WITHDRAW_STATUS.SUCCESS);
		order.setPay_id(pay_id);
		order.setPay_remark(pay_remark);
		order.setPay_time(new Date());
		order.setPay_method(pay_method);
		return this.update(order);
	}

	@Override
	 @Transactional
	public boolean payReject(String idstr, Integer pay_id, String pay_remark) {
		int effectRow = 0;
    	String [] ids = idstr.split(",");
    	for (String id : ids) {
    		effectRow += this.payReject(Integer.valueOf(id), pay_id, pay_remark);
		}
		return effectRow == ids.length;
	}
	
	@Override
    public int payReject(Integer id, Integer pay_id, String pay_remark) {
		MemberWithdrawOrder order = this.getOne(id);
		if (null == order) {
			throw new ValidateException("提现订单不存在！");
    	} else if (order.getStatus().intValue() != WITHDRAW_STATUS.PROCESS) {
			throw new ValidateException("提现订单非处理中状态");
		} else if (order.getReview_status().intValue() != REVIEW_STATUS.REVIEW_PASS) {
			throw new ValidateException("该提现订单未审批或审批未通过");
		}
		order = new MemberWithdrawOrder();
		order.setId(id);
		order.setStatus(WITHDRAW_STATUS.FAILURE);
		order.setPay_id(pay_id);
		order.setPay_remark(pay_remark);
		order.setPay_time(new Date());
		order.setPay_remark(pay_remark);
		order.setStatus(WITHDRAW_STATUS.FAILURE);
		return this.update(order);
	}
}
