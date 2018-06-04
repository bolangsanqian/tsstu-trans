package com.tsstu.console.dao;

import com.tsstu.console.model.ContractBank;

/**
 * 签约管理Mapper 接口
 * @author： admin
 * @date： 2017-05-04
 **/
public interface ContractBankMapper extends BaseMapper<ContractBank> {

	/**
	 * 解约银行
	 * @param customer_id 客户id
	 * @return
	 */
	int cancelBank(Integer customer_id);

	
}