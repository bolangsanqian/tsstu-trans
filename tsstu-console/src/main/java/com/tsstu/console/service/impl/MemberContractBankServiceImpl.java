package com.tsstu.console.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.common.constants.Constants.D_CONTRACT_BANK_STATUS;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.MemberContractBankMapper;
import com.tsstu.console.model.MemberContractBank;
import com.tsstu.console.service.MemberContractBankService;

/**
 * 签约管理(会员)业务实现类
 * @author： admin
 * @date： 2017-05-12
 */
@Service
public class MemberContractBankServiceImpl extends BaseServiceImpl<MemberContractBank> implements MemberContractBankService {

    @Autowired
    private MemberContractBankMapper memberContractBankMapper;
    
    @Override
	public BaseMapper<MemberContractBank> getDao() {
		return memberContractBankMapper;
	}

	@Override
	public boolean reviewPass(Integer id, int review_id, String review_remark) {
		MemberContractBank contract = this.getOne(id);
		if (null == contract) {
			throw new ValidateException("签约银行不存在！");
		} else if (contract.getStatus().intValue() != 0 && contract.getStatus().intValue() != 1) {
			throw new ValidateException("签约银行状态异常！");
		}
		contract = new MemberContractBank();
		contract.setId(id);
		contract.setStatus(D_CONTRACT_BANK_STATUS.YTG);
		contract.setReview_id(review_id);
		contract.setReview_remark(review_remark);
		contract.setReview_time(new Date());
		int effectRow = this.update(contract);
		return effectRow > 0;
	}

	@Override
	public boolean reviewReject(Integer id, int review_id, String review_remark) {
		MemberContractBank contract = this.getOne(id);
		if (null == contract) {
			throw new ValidateException("签约银行不存在！");
		} else if (contract.getStatus().intValue() != 0 && contract.getStatus().intValue() != 1) {
			throw new ValidateException("签约银行状态异常！");
		}
		contract = new MemberContractBank();
		contract.setId(id);
		contract.setStatus(D_CONTRACT_BANK_STATUS.YBH);
		contract.setReview_id(review_id);
		contract.setReview_remark(review_remark);
		contract.setReview_time(new Date());
		int effectRow = this.update(contract);
		return effectRow > 0;
	}
}
