package com.tsstu.console.service;

import com.tsstu.console.model.MemberWithdrawOrder;

/**
 * 会员提现订单业务接口
 * @author： admin
 * @date：2017-05-12
 **/
public interface MemberWithdrawOrderService extends BaseService<MemberWithdrawOrder> {
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
	
	/**
	 * 付款通过
	 * @param ids 提现订单id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @param pay_method 付款方式
	 * @return
	 */
	boolean payPass(String ids, Integer pay_id, String review_remark, Integer pay_method);

	/**
	 * 付款驳回
	 * @param ids 提现订单id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	boolean payReject(String ids, Integer pay_id, String review_remark);
	
	/**
	 * 付款通过
	 * @param id 提现订单id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @param pay_method 付款方式
	 * @return
	 */
	int payPass(Integer id, Integer pay_id, String review_remark, Integer pay_method);

	/**
	 * 付款驳回
	 * @param id 提现订单id
	 * @param review_id 审批人id
	 * @param review_remark 审批备注
	 * @return
	 */
	int payReject(Integer id, Integer pay_id, String review_remark);
}
