package com.tsstu.console.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.common.constants.Constants.REVIEW_STATUS;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.MemberFundChangeMapper;
import com.tsstu.console.model.MemberFundChange;
import com.tsstu.console.service.MemberFundChangeService;
import com.tsstu.console.service.MemberService;

/**
 * 加减币管理(会员)业务实现类
 * @author： admin
 * @date： 2017-05-12
 */
@Service
public class MemberFundChangeServiceImpl extends BaseServiceImpl<MemberFundChange> implements MemberFundChangeService {

    @Autowired
    private MemberFundChangeMapper memberFundChangeMapper;
    
    @Autowired
    private MemberService memberService;
    
    @Override
	public BaseMapper<MemberFundChange> getDao() {
		return memberFundChangeMapper;
	}
    
    @Override
	@Transactional
	public boolean reviewPass(String idstr, Integer review_id, String review_remark) {
		int effectRow = 0;
		String [] ids = idstr.split(",");
		for (String id : ids) {
			MemberFundChange fundChange = this.getOne(Integer.valueOf(id));
			if (null == fundChange) {
				throw new ValidateException("加减币订单不存在！");
			} else if (fundChange.getStatus().intValue() != REVIEW_STATUS.REVIEW_WAIT){
				throw new ValidateException("加减币订单非待审批状态！");
			}
			
			// 调整客户余额
			boolean bo = memberService.changeBalance(fundChange.getMember_id(), fundChange.getAmount());
			if (bo) {
				// 修改加减币订单状态
				fundChange = new MemberFundChange();
				fundChange.setId(Integer.valueOf(id));
				fundChange.setStatus(REVIEW_STATUS.REVIEW_PASS);  //1：已通过
				fundChange.setReview_id(review_id);
				fundChange.setReview_time(new Date());
				fundChange.setReview_remark(review_remark);
				effectRow += this.update(fundChange);
			}
		}
		return effectRow == ids.length;
	}

	@Override
	@Transactional
	public boolean reviewReject(String idstr, Integer review_id, String review_remark) {
		int effectRow = 0;
		String [] ids = idstr.split(",");
		for (String id : ids) {
			MemberFundChange fundChange = this.getOne(Integer.valueOf(id));
			if (null == fundChange) {
				throw new ValidateException("加减币订单不存在！");
			} else if (fundChange.getStatus().intValue() != REVIEW_STATUS.REVIEW_WAIT){
				throw new ValidateException("加减币订单非待审批状态！");
			}
			fundChange = new MemberFundChange();
			fundChange.setId(Integer.valueOf(id));
			fundChange.setStatus(REVIEW_STATUS.REVIEW_REJECT);  //2：已驳回
			fundChange.setReview_id(review_id);
			fundChange.setReview_time(new Date());
			fundChange.setReview_remark(review_remark);
			effectRow += this.update(fundChange);
		}
		return effectRow == ids.length;
	}
}
