package com.tsstu.console.service;

import com.tsstu.console.model.ContractBank;

/**
 * 签约管理业务接口
 * @author： admin
 * @date：2017-05-04
 **/
public interface ContractBankService extends BaseService<ContractBank> {

	/**
	 * 解约银行
	 * @param customerId 客户id
	 * @return
	 */
	boolean cancelBank(Integer customerId);
	
	/**
	 * 审批通过签约银行
	 * @param id 签约id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	boolean reviewPass(Integer id, int review_id, String review_remark);

	/**
	 * 驳回签约银行
	 * @param id 签约id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	boolean reviewReject(Integer id, int review_id, String review_remark);

}
