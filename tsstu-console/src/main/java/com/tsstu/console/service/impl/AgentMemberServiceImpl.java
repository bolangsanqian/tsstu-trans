package com.tsstu.console.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.common.constants.Constants;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.AgentMemberMapper;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.CustomerMapper;
import com.tsstu.console.model.AgentMember;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.common.util.ShareCodeUtil;

/**
 * 代理会员业务实现类
 * @author： admin
 * @date： 2017-05-04
 */
@Service
public class AgentMemberServiceImpl extends BaseServiceImpl<AgentMember> implements AgentMemberService {

    @Autowired
    private AgentMemberMapper agentMemberMapper;
    
    @Autowired
    private CustomerMapper customerMapper;
    
    @Override
	public BaseMapper<AgentMember> getDao() {
		return agentMemberMapper;
	}

	@Override
	public List<AgentMember> getEnableAgentMemberList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.STATUS_ENABLE);
		return agentMemberMapper.getList(map);
	}
	
	@Transactional
	@Override
	public int add(AgentMember model) {
		//同一个会员下的代理会员名称不容许重复
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", model.getName());
		map.put("member_id", model.getMember_id());
		boolean isExist = agentMemberMapper.isExist(map);
		if (isExist) {
			throw new ValidateException("代理名称已存在，请更换！");
		}
		
		int effectRow = super.add(model);
		if (effectRow <= 0) {
			throw new ValidateException("添加失败！");
		} else {
			String invite_code = ShareCodeUtil.generateShareCode(model.getId(), ShareCodeUtil.CodeType.AGENT_MEMBER);
			model.setInvite_code(invite_code);
			effectRow = agentMemberMapper.update(model);
			if (effectRow <= 0) {
				throw new ValidateException("添加失败！");
			}
		}
		return effectRow;
	}
	
	@Override
	public int update(AgentMember model) {
		//同一个会员下的代理会员名称不容许重复
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", model.getId());
		map.put("name", model.getName());
		map.put("member_id", model.getMember_id());
		boolean isExist = agentMemberMapper.isExist(map);
		if (isExist) {
			throw new ValidateException("代理名称已存在，请更换！");
		}
		return super.update(model);
	}
	
	@Override
	public int delete(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("agent_member_id", id);
		int count = customerMapper.getCount(map);
		if (count > 0) {
			throw new ValidateException("该代理会员下已存在客户，不容许删除！");
		}
		return super.delete(id);
	}

	@Override
	public int updateQrcodeUrl(Integer id, String wx_qrcode_url) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("wx_qrcode_url", wx_qrcode_url);
		return super.update(map);
	}
}
