package com.tsstu.console.service;

import java.math.BigDecimal;

import com.tsstu.console.model.Customer;

/**
 * 客户管理业务接口
 * @author： admin
 * @date：2017-05-03
 **/
public interface CustomerService extends BaseService<Customer> {

	/**
	 * 禁用账户
	 * @param id 客户id
	 * @return
	 */
	boolean disableAccount(Integer id);

	/**
	 * 启用账户
	 * @param id 客户id
	 * @return
	 */
	boolean enableAccount(Integer id);

	/**
	 * 注销账户
	 * @param id 客户id
	 * @return
	 */
	boolean cancelAccount(Integer id);

	/**
	 * 容许充值
	 * @param id 客户id
	 * @return
	 */
	boolean allowRecharge(Integer id);

	/**
	 * 禁止充值
	 * @param id 客户id
	 * @return
	 */
	boolean stopRecharge(Integer id);

	/**
	 * 容许提现
	 * @param id 客户id
	 * @return
	 */
	boolean allowWithdraw(Integer id);

	/**
	 * 禁止提现
	 * @param id 客户id
	 * @return
	 */
	boolean stopWithdraw(Integer id);

	/**
	 * 容许交易
	 * @param id 客户id
	 * @return
	 */
	boolean allowTrade(Integer id);

	/**
	 * 禁止提现
	 * @param id 客户id
	 * @return
	 */
	boolean stopTrade(Integer id);

	/**
	 * 容许登录
	 * @param id 客户id
	 * @return
	 */
	boolean allowLogin(Integer id);

	/**
	 * 禁止登录
	 * @param id 客户id
	 * @return
	 */
	boolean stopLogin(Integer id);

	/**
	 * 解绑微信
	 * @param id 客户id
	 * @return
	 */
	boolean unbindWechat(Integer id);

	/**
	 * 调整客户资金
	 * @param id 客户编号
	 * @param amount 调整金额
	 * @return
	 */
	boolean changeBalance(Integer id, BigDecimal amount);

}
