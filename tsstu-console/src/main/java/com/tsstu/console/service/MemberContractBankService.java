package com.tsstu.console.service;

import com.tsstu.console.model.MemberContractBank;

/**
 * 签约管理(会员)业务接口
 * @author： admin
 * @date：2017-05-12
 **/
public interface MemberContractBankService extends BaseService<MemberContractBank> {

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
