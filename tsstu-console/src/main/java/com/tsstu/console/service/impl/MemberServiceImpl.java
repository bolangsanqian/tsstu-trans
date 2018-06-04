package com.tsstu.console.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.common.util.FileUtil;
import com.tsstu.common.constants.Constants;
import com.tsstu.common.constants.Constants.MemberTypeConstants;
import com.tsstu.common.constants.Constants.RoleConstants;
import com.tsstu.common.constants.Constants.UserTypeConstants;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.MemberMapper;
import com.tsstu.console.model.Member;
import com.tsstu.console.model.User;
import com.tsstu.console.service.MemberService;
import com.tsstu.console.service.UserService;
import com.tsstu.console.util.PathUtil;

/**
 * 会员管理业务实现类
 * @author： admin
 * @date： 2017-05-03
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    
    @Autowired
    private UserService userService;
    
    @Override
	public BaseMapper<Member> getDao() {
		return memberMapper;
	}

	@Override
	public int delWxQrcode(Integer id) {
		//删除图片
		Member member = this.getOne(id);
    	if (null != member.getWx_qrcode_url() && !"".equals(member.getWx_qrcode_url())) {
    		String filePath = PathUtil.getClasspath() + member.getWx_qrcode_url();
    		FileUtil.delFile(filePath);
    	}
    	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("wx_qrcode_url", "");
		return memberMapper.update(map);
	}

	@Override
	public Member getExchange() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_type", MemberTypeConstants.EXCHANGE);
		return memberMapper.getOne(map);
	}
	
	@Override
	public List<Member> getEnableOperationList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_type", MemberTypeConstants.OPERATION);
		map.put("status", Constants.STATUS_ENABLE );
		return memberMapper.getList(map);
	}

	@Override
	public List<Member> getEnableCommonMemberList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_type", MemberTypeConstants.COMMON_MEMBER);
		map.put("status", Constants.STATUS_ENABLE);
		return memberMapper.getList(map);
	}

	@Override
	public List<Member> getEnableMemberList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.STATUS_ENABLE);
		return memberMapper.getList(map);
	}

	@Override
	public int add(Member model) {
		Member exchangeMember = this.getOne("member_type", MemberTypeConstants.EXCHANGE);
		model.setExchange_member_id(exchangeMember.getId());
		return super.add(model);
	}
	
	@Override
	public boolean changeBalance(Integer id, BigDecimal amount) {
		int effectRow = memberMapper.changeBalance(id, amount);
		return effectRow > 0;
	}

	@Override
	public User getAccount(Integer id) {
		Member member = this.getOne(id);
		if (member.getUser_id() >0) {
			return userService.getOne(member.getUser_id());
		}
		return null;
	}

	@Override
	@Transactional
	public int createAccount(User user, Integer member_id, Integer create_by) {
		Member member = this.getOne(member_id);
		if (member.getUser_id() > 0) {
			throw new ValidateException("创建账号失败！");
		}
	
		int user_type = user.getUser_type();
		int member_type = member.getMember_type();
		if (user_type == UserTypeConstants.COMMON_MEMBER && member_type != MemberTypeConstants.COMMON_MEMBER
				|| user_type == UserTypeConstants.OPERATION && member_type != MemberTypeConstants.OPERATION
				|| user_type == UserTypeConstants.EXCHANGE && member_type != MemberTypeConstants.EXCHANGE) {
			throw new ValidateException("创建账号失败！");
		}
		
		user.setCreate_time(new Date());
		user.setCreate_by(create_by);
		user.setAllow_del(Constants.NOT_ALLOW);
		user.setStatus(Constants.STATUS_ENABLE);
		
		//角色处理
		Integer [] roles = new Integer[1];
		if (user_type == UserTypeConstants.COMMON_MEMBER) {
			roles[0] = RoleConstants.COMMON_MEMBER;
		} else if (user_type == UserTypeConstants.OPERATION) {
			roles[0] = RoleConstants.OPERATION;
		} else if (user_type == UserTypeConstants.EXCHANGE){
			roles[0] = RoleConstants.EXCHANGE;
		}
		int effectRow = userService.add(user, roles, create_by);
		if (effectRow > 0) {
			//回填到会员表
			effectRow = this.update("id", member_id, "user_id", user.getId());
			if (effectRow <= 0) {
				throw new ValidateException("创建账号失败！");
			}
		}
		return effectRow;
	}

	@Override
	public int updateAccount(User user, Integer member_id) {
		Member member = this.getOne(member_id);
		if (user.getId().intValue() != member.getUser_id().intValue()) {
			throw new ValidateException("修改账号失败！");
		}
		user.setUser_type(null);
		if (null != user.getPassword() || !"".equals(user.getPassword())) {
			user.setPassword(null);
		} else {
			user.setPassword(userService.md5password(user.getPassword()));
		}
		int effectRow = userService.update(user);
		return effectRow;
	}
	
}
