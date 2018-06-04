package com.tsstu.console.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.tsstu.console.model.Member;

/**
 * 会员管理Mapper 接口
 * @author： admin
 * @date： 2017-05-03
 **/
public interface MemberMapper extends BaseMapper<Member> {

	int delWxQrcode(Integer id);

	/**
	 * 调整会员资金
	 * @param id 会员id
	 * @param amount 调整金额
	 * @return
	 */
	int changeBalance(@Param("id")Integer id, @Param("amount")BigDecimal amount);

}