package com.tsstu.console.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.common.constants.Constants;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.CustomerMapper;
import com.tsstu.console.model.Customer;
import com.tsstu.console.service.ContractBankService;
import com.tsstu.console.service.CustomerService;

/**
 * 客户管理业务实现类
 * @author： admin
 * @date： 2017-05-03
 */
@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private ContractBankService contractBankService;
    
    @Override
	public BaseMapper<Customer> getDao() {
		return customerMapper;
	}

	@Override
	public boolean disableAccount(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("status", Constants.STATUS_DISABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public boolean enableAccount(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("status", Constants.STATUS_ENABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	@Transactional
	public boolean cancelAccount(Integer id) {
		//变更客户信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		Customer customer = customerMapper.getOne(map);
		map.put("mobile", customer.getMobile() + "_" + System.currentTimeMillis());
		map.put("wx_openid", "");
		map.put("org_mobile", customer.getMobile());
		map.put("org_wx_openid", customer.getWx_openid());
		map.put("status", Constants.STATUS_CANCEL);
		int effectRow = this.update(map);
		if (effectRow > 0) {
			//解约银行
			contractBankService.cancelBank(id);
		}
		return effectRow > 0;
	}

	@Override
	public boolean allowRecharge(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("recharge_status", Constants.STATUS_ENABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public boolean stopRecharge(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("recharge_status", Constants.STATUS_DISABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public boolean allowWithdraw(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("withdraw_status", Constants.STATUS_ENABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public boolean stopWithdraw(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("withdraw_status", Constants.STATUS_DISABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public boolean allowTrade(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("trade_status", Constants.STATUS_ENABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public boolean stopTrade(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("trade_status", Constants.STATUS_DISABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public boolean allowLogin(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("login_status", Constants.STATUS_ENABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public boolean stopLogin(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("login_status", Constants.STATUS_DISABLE);
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public boolean unbindWechat(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("wx_openid", "");
		int effectRow = customerMapper.update(map);
		return effectRow > 0;
	}

	@Override
	public synchronized boolean changeBalance(Integer customer_id, BigDecimal amount) {
		int effectRow = customerMapper.changeBalance(customer_id, amount);
		return effectRow > 0;
	}
}
