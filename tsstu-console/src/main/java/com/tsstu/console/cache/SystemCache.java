package com.tsstu.console.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsstu.console.cache.ehcache.EhCache;
import com.tsstu.common.constants.Constants;
import com.tsstu.common.constants.Constants.CacheConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.console.core.entity.DictMember;
import com.tsstu.console.model.AgentMember;
import com.tsstu.console.model.Config;
import com.tsstu.console.model.HoldTime;
import com.tsstu.console.model.Member;
import com.tsstu.console.model.Permission;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.ConfigService;
import com.tsstu.console.service.HoldTimeService;
import com.tsstu.console.service.MemberService;

@SuppressWarnings("all")
@Component
public class SystemCache {

	private static final String CACHE_NAME = "system";
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EhCache ehCacheManager;
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AgentMemberService agentMemberService;
	
	@Autowired
	private HoldTimeService holdTimeService;
	
	public EhCache getEhCacheManager() {
		return ehCacheManager;
	}

	public void setEhCacheManager(EhCache ehCacheManager) {
		this.ehCacheManager = ehCacheManager;
	}
	
	public void put(String cacheName, String key, Object value) {
		ehCacheManager.put(cacheName, key, value);
	}
	
	public Object get(String cacheName, String key) {
		return ehCacheManager.getValue(cacheName, key);
	}
	
	public void remove(String cacheName, String key) {
		ehCacheManager.remove(cacheName, key);
	}
	
	/**
	 * 初始化数据到缓存
	 */
	public void init() {
		this.loadConfig();
		this.initMemberList();
		this.initAgentMemberList();
	}

	/**
	 * 获取权限列表
	 * @return
	 */
	public Map<String, Permission> getPermissionMap() {
		return (Map<String, Permission>)ehCacheManager.getValue(CACHE_NAME, CacheConstants.PERMISSION_LIST);
	}
	
	/**
	 * 设置权限列表
	 * @param list 权限列表
	 */
	public void putPermissionMap(Map<String, Permission> permissionMap) {
		ehCacheManager.put(CACHE_NAME, CacheConstants.PERMISSION_LIST, permissionMap);
	}
	
	/**
	 * 设置权限列表
	 * @param list 权限列表
	 */
	public void loadConfig() {
		logger.info("=================加载系统参数到缓存=================");
		List<Config> list = configService.getList();
		Map<String, String> configMap = new HashMap<String, String>();
		if (null != list) {
			for (Config config : list) {
				configMap.put(config.getConfig_code(), config.getConfig_value());
				logger.info("{}, {}:{}", config.getConfig_name(), config.getConfig_code(), config.getConfig_value());
			}
		}
		ehCacheManager.put(CACHE_NAME, CacheConstants.PERMISSION_LIST, configMap);
	}
	
	/**
	 * 获取系统配置
	 * @param code 配置代码
	 * @return
	 */
	public String getConfig(String code) {
		Map<String, String> map = (Map<String, String>)ehCacheManager.getValue(CACHE_NAME, CacheConstants.PERMISSION_LIST);
		return map.get(code);
	}
	
	public void loadHoldTime() {
		List<HoldTime> list = holdTimeService.getDictList();
		ehCacheManager.put(CACHE_NAME, CacheConstants.HOLD_TIME_LIST, list);
	}
	
	/**
	 * 加载会员字典集合到Appliction
	 * @param list 权限列表
	 */
	public void initMemberList() {
		//运营中心
		logger.info("开始加载运营中心字典...");
		List<Member> operationList = memberService.getEnableOperationList();
		List<DictMember> operationMember = new ArrayList<DictMember>();
		if (null != operationList) {
			for (Member member : operationList) {
				DictMember dict = new DictMember();
				dict.setItem_value(member.getId().toString());
				dict.setItem_name(member.getName());
				operationMember.add(dict);
				logger.info("{}:{}", member.getId(), member.getName());
			}
		}
		ehCacheManager.put(CACHE_NAME, DictConstants.DICT_OPERATION, operationMember);
		
		// 普通会员
		logger.info("开始加载普通会员字典...");
		List<Member> commonMemberList = memberService.getEnableCommonMemberList();
		List<DictMember> dictCommonMember = new ArrayList<DictMember>();
		if (null != commonMemberList) {
			for (Member member : commonMemberList) {
				DictMember dict = new DictMember();
				dict.setItem_value(member.getId().toString());
				dict.setOperation_member_id(member.getOperation_member_id());
				dict.setItem_name(member.getName());
				dictCommonMember.add(dict);
				logger.info("{}:{}", member.getId(), member.getName());
			}
		}
		ehCacheManager.put(CACHE_NAME, DictConstants.DICT_COMMON_MEMBER, dictCommonMember);
		
		// 交易所、运营中心、普通会员
		logger.info("开始加载交易所、运营中心、普通会员字典...");
		List<Member> memberList = memberService.getEnableMemberList();
		List<DictMember> dictMember = new ArrayList<DictMember>();
		if (null != memberList) {
			for (Member member : memberList) {
				DictMember dict = new DictMember();
				dict.setItem_value(member.getId().toString());
				dict.setOperation_member_id(member.getOperation_member_id());
				dict.setItem_name(member.getName());
				dictMember.add(dict);
				logger.info("{}:{}", member.getId(), member.getName());
			}
		}
		ehCacheManager.put(CACHE_NAME, DictConstants.DICT_MEMBER, dictMember);
	}
	
	/**
	 * 加载代理会员字典集合到Appliction
	 * @param list 权限列表
	 */
	public void initAgentMemberList() {
		logger.info("=================加载代理会员字典到Appliction=================");
		// 交易所、运营中心、普通会员
		List<AgentMember> agentMemberList = agentMemberService.getList("status", Constants.STATUS_ENABLE);
		List<DictMember> list = new ArrayList<DictMember>();
		if (null != agentMemberList) {
			for (AgentMember agentMember : agentMemberList) {
				DictMember dict = new DictMember();
				dict.setItem_value(agentMember.getId().toString());
				dict.setMember_id(agentMember.getMember_id());
				dict.setOperation_member_id(agentMember.getOperation_member_id());
				dict.setItem_name(agentMember.getName());
				list.add(dict);
				logger.info("{}:{}", agentMember.getId(), agentMember.getName());
			}
		}
		ehCacheManager.put(CACHE_NAME, DictConstants.DICT_AGENT_MEMBER, list);
	}
	
	public List<DictMember> getOperationList() {
		List<DictMember> list = (List<DictMember>)ehCacheManager.getValue(CACHE_NAME, DictConstants.DICT_OPERATION);
		return list;
	}
	
	/**
	 * 普通会员列表
	 * @param operataion_member_id 运营中心id
	 * @return
	 */
	public List<DictMember> getCommonMemberList(Integer operataion_member_id) {
		List<DictMember> list = (List<DictMember>)ehCacheManager.getValue(CACHE_NAME, DictConstants.DICT_COMMON_MEMBER);
		if (operataion_member_id > 0 && null != list) {
			Iterator<DictMember> it = list.iterator();
			while (it.hasNext()) {
				DictMember d = it.next();
				if (operataion_member_id > 0 && d.getOperation_member_id().intValue() != operataion_member_id.intValue()) {
					it.remove();
				}
			}
		}
		return list;
	}
	
	/**
	 * 代理会员列表
	 * @param operataion_member_id 运营中心id
	 * @param member_id 会员id
	 * @return
	 */
	public List<DictMember> getAgentMemberList(Integer operataion_member_id, Integer member_id) {
		List<DictMember> list = (List<DictMember>)ehCacheManager.getValue(CACHE_NAME, DictConstants.DICT_AGENT_MEMBER);
		if (null != list) {
			Iterator<DictMember> it = list.iterator();
			while (it.hasNext()) {
				DictMember d = it.next();
				if (operataion_member_id.intValue() > 0 && operataion_member_id.intValue() != d.getOperation_member_id().intValue()) {
					it.remove();
				} else if (member_id.intValue() > 0 && member_id.intValue() != d.getMember_id().intValue()) {
					it.remove();
				}
			}
		}
		return list;
	}
	
}
