package com.tsstu.console.service;

import com.tsstu.console.model.BrokerApply;

/**
 * 经纪人申请业务接口
 * @author： admin
 * @date：2017-05-16
 **/
public interface BrokerApplyService extends BaseService<BrokerApply> {
	
	/**
	 * 批量审批通过
	 * @param ids 提现订单id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	boolean reviewPass(String ids, Integer review_id, String review_remark);

	/**
	 * 批量驳回
	 * @param ids 提现订单id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	boolean reviewReject(String ids, Integer review_id, String review_remark);
	
	/**
	 * 审批通过
	 * @param id 提现订单id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	int reviewPass(Integer id, Integer review_id, String review_remark);

	/**
	 * 审批驳回
	 * @param id 提现订单id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	int reviewReject(Integer id, Integer review_id, String review_remark);
}
