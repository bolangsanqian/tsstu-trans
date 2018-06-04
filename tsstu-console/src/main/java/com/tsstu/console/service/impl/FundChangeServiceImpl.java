package com.tsstu.console.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.common.constants.Constants.REVIEW_STATUS;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.FundChangeMapper;
import com.tsstu.console.model.Customer;
import com.tsstu.console.model.FundChange;
import com.tsstu.console.service.CustomerService;
import com.tsstu.console.service.FundChangeService;

/**
 * 加减币管理业务实现类
 * @author： admin
 * @date： 2017-05-12
 */
@Service
public class FundChangeServiceImpl extends BaseServiceImpl<FundChange> implements FundChangeService {

    @Autowired
    private FundChangeMapper fundChangeMapper;
    
    @Autowired
    private CustomerService customerService;
    
    @Override
	public BaseMapper<FundChange> getDao() {
		return fundChangeMapper;
	}
    
    @Override
    public int add(FundChange model) {
    	Customer customer = customerService.getOne("mobile", model.getMobile());
    	if (null == customer) {
    		throw new ValidateException("该手机号码对应的客户不存在，请核实！");
    	}
    	return super.add(model);
    }

	@Override
	@Transactional
	public boolean reviewPass(String idstr, Integer review_id, String review_remark) {
		int effectRow = 0;
		String [] ids = idstr.split(",");
		for (String id : ids) {
			FundChange fundChange = this.getOne(Integer.valueOf(id));
			if (null == fundChange) {
				throw new ValidateException("加减币订单不存在！");
			} else if (fundChange.getStatus().intValue() != REVIEW_STATUS.REVIEW_WAIT){
				throw new ValidateException("加减币订单非待审批状态！");
			}
			
			// 调整客户余额
			boolean bo = customerService.changeBalance(fundChange.getCustomer_id(), fundChange.getAmount());
			if (bo) {
				// 修改加减币订单状态
				fundChange = new FundChange();
				fundChange.setId(Integer.valueOf(id));
				fundChange.setStatus(REVIEW_STATUS.REVIEW_PASS);  //1：已通过
				fundChange.setReview_id(review_id);
				fundChange.setReview_time(new Date());
				fundChange.setReview_remark(review_remark);
				effectRow += this.update(fundChange);
			}
		}
		return effectRow == ids.length;
	}

	@Override
	@Transactional
	public boolean reviewReject(String idstr, Integer review_id, String review_remark) {
		int effectRow = 0;
		String [] ids = idstr.split(",");
		for (String id : ids) {
			FundChange fundChange = this.getOne(Integer.valueOf(id));
			if (null == fundChange) {
				throw new ValidateException("加减币订单不存在！");
			} else if (fundChange.getStatus().intValue() != REVIEW_STATUS.REVIEW_WAIT){
				throw new ValidateException("加减币订单非待审批状态！");
			}
			fundChange = new FundChange();
			fundChange.setId(Integer.valueOf(id));
			fundChange.setStatus(REVIEW_STATUS.REVIEW_REJECT);  //2：已驳回
			fundChange.setReview_id(review_id);
			fundChange.setReview_time(new Date());
			fundChange.setReview_remark(review_remark);
			effectRow += this.update(fundChange);
		}
		return effectRow == ids.length;
	}
    
}
