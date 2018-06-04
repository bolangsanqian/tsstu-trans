package com.tsstu.console.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.common.constants.Constants;
import com.tsstu.common.constants.Constants.REVIEW_STATUS;
import com.tsstu.common.constants.Constants.RoleConstants;
import com.tsstu.common.constants.Constants.UserTypeConstants;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.BrokerApplyMapper;
import com.tsstu.console.model.Broker;
import com.tsstu.console.model.BrokerApply;
import com.tsstu.console.model.Customer;
import com.tsstu.console.model.User;
import com.tsstu.console.service.BrokerApplyService;
import com.tsstu.console.service.BrokerService;
import com.tsstu.console.service.CustomerService;
import com.tsstu.console.service.UserService;
import com.tsstu.common.util.ShareCodeUtil;

/**
 * 经纪人申请业务实现类
 * @author： admin
 * @date： 2017-05-16
 */
@Service
public class BrokerApplyServiceImpl extends BaseServiceImpl<BrokerApply> implements BrokerApplyService {

    @Autowired
    private BrokerApplyMapper brokerApplyMapper;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BrokerService brokerService;
    
    @Override
	public BaseMapper<BrokerApply> getDao() {
		return brokerApplyMapper;
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
    	BrokerApply model = this.getOne(id);
    	if (null == model) {
			throw new ValidateException("经纪人申请不存在！");
    	} else if (model.getReview_status() != REVIEW_STATUS.REVIEW_WAIT) {
			throw new ValidateException("经纪人申请待审批状态");
		}
    	BrokerApply apply = new BrokerApply();
    	apply.setId(id);
    	apply.setReview_status(REVIEW_STATUS.REVIEW_PASS);
    	apply.setReview_by(review_id);
    	apply.setReview_remark(review_remark);
    	apply.setReview_time(new Date());
		int effectRow = this.update(apply);
		
		if (effectRow > 0) {
			Customer customer = customerService.getOne(model.getCustomer_id());
			
			//创建登录用户
			User user = new User();
			user.setUsername(model.getUsername());
			user.setPassword(model.getPassword());
			user.setMobile(customer.getMobile());
			user.setUser_type(UserTypeConstants.BROKER);
			user.setAllow_del(Constants.NOT_ALLOW);
			user.setCreate_by(review_id);
			user.setCreate_time(new Date());
			Integer[] roles = {RoleConstants.BROKER};
			effectRow = userService.add(user, roles, review_id);
			if (effectRow <= 0) {
				throw new ValidateException("审批失败！");
			}
			
			//创建经纪人
			Broker broker = new Broker();
			broker.setCustomer_id(model.getCustomer_id());
			broker.setUser_id(user.getId());
			broker.setStatus(Constants.STATUS_ENABLE);
			broker.setCreate_time(new Date());
			effectRow = brokerService.add(broker);
			if (effectRow <= 0) {
				throw new ValidateException("审批失败！");
			} else {
				String invite_code = ShareCodeUtil.generateShareCode(broker.getId(), ShareCodeUtil.CodeType.BROKER);
				brokerService.update("id", broker.getId(), "invite_code", invite_code);
			}
		}
		return effectRow;
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
		BrokerApply model = this.getOne(id);
		if (null == model) {
			throw new ValidateException("经纪人申请不存在！");
    	} else if (model.getReview_status() != REVIEW_STATUS.REVIEW_WAIT) {
			throw new ValidateException("经纪人申请待审批状态");
		}
		model = new BrokerApply();
		model.setId(id);
		model.setReview_by(review_id);
		model.setReview_status(REVIEW_STATUS.REVIEW_REJECT);
		model.setReview_remark(review_remark);
		model.setReview_time(new Date());
		return this.update(model);
	}
}
