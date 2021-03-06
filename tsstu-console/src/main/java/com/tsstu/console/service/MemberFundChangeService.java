package com.tsstu.console.service;

import com.tsstu.console.model.MemberFundChange;

/**
 * 加减币管理(会员)业务接口
 * @author： admin
 * @date：2017-05-12
 **/
public interface MemberFundChangeService extends BaseService<MemberFundChange> {
	/**
	 * 审批通过
	 * @param id 加减币id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	boolean reviewPass(String ids, Integer review_id, String review_remark);

	/**
	 * 审批驳回
	 * @param id 加减币id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	boolean reviewReject(String ids, Integer review_id, String review_remark);
}
