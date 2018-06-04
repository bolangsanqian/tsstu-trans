package com.tsstu.console.service;

import java.math.BigDecimal;
import java.util.List;

import com.tsstu.console.model.Member;
import com.tsstu.console.model.User;

/**
 * 会员管理业务接口
 * @author： admin
 * @date：2017-05-03
 **/
public interface MemberService extends BaseService<Member> {

	/**
	 * 查询运营中心列表
	 * @return
	 */
	List<Member> getEnableOperationList();

	/**
	 * 删除公众号二维码
	 * @param id 会员id
	 */
	int delWxQrcode(Integer id);

	/**
	 * 查询交易所信息
	 * @return
	 */
	Member getExchange();

	/**
	 * 查询可用的普通会员列表
	 * @return
	 */
	List<Member> getEnableCommonMemberList();
	
	/**
	 * 查询可用的交易所、运营中心、会员列表
	 * @return
	 */
	List<Member> getEnableMemberList();
	
	/**
	 * 调整会员资金
	 * @param id 会员id
	 * @param amount 调整金额
	 * @return
	 */
	boolean changeBalance(Integer id, BigDecimal amount);

	/**
	 * 获取登录账户
	 * @param id 会员id
	 * @return
	 */
	User getAccount(Integer id);

	/**
	 * 创建登录账户
	 * @param user 用户
	 * @param member_id 会员id
	 * @param create_by 创建人id
	 * @return
	 */
	int createAccount(User user, Integer member_id, Integer create_by);

	/**
	 * 创建登录账户
	 * @param user 用户
	 * @param member_id 会员id
	 * @return
	 */
	int updateAccount(User user, Integer member_id);

}
