
package com.tsstu.console.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.tsstu.common.constants.Constants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.common.constants.Constants.TASK;
import com.tsstu.console.cache.SystemCache;
import com.tsstu.console.core.entity.DictMember;
import com.tsstu.console.model.AgentMember;
import com.tsstu.console.model.Bank;
import com.tsstu.console.model.Config;
import com.tsstu.console.model.CycleProduct;
import com.tsstu.console.model.DictionaryItem;
import com.tsstu.console.model.HoldTime;
import com.tsstu.console.model.Member;
import com.tsstu.console.model.Permission;
import com.tsstu.console.model.Task;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.BankService;
import com.tsstu.console.service.ConfigService;
import com.tsstu.console.service.CycleProductService;
import com.tsstu.console.service.DictionaryItemService;
import com.tsstu.console.service.HoldTimeService;
import com.tsstu.console.service.MemberService;
import com.tsstu.console.service.PermissionService;
import com.tsstu.console.service.QuotationService;
import com.tsstu.console.service.TaskService;
import com.tsstu.console.task.DeleteQuotationTask;
import com.tsstu.console.task.HuobiQuotationTask;
import com.tsstu.console.task.YaoyaoQuotationTask;


/**
 * 初始化系统初始化数据
 * @author liwei
 * @date 2017年4月20日16:56:09
 */
@Component
public class ApplicationInitListener implements ServletContextAware {

	private static Logger logger = LoggerFactory.getLogger(ApplicationInitListener.class);
    
	private ServletContext servletContext;
	
	@Autowired
	private DictionaryItemService dictionaryItemService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private SystemCache systemCache;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AgentMemberService agentMemberService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private CycleProductService cycleProductService;
	
	@Autowired
	private HoldTimeService holdTimeService;
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private QuotationService quotationService;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@PostConstruct 
	public void init() throws IOException {
		systemCache.init();
		this.initDictionary();
		this.initPermissionList();
		this.initMemberList();
		this.initAgentMemberList();
		this.initContractBank();
		this.initCycleProduct();
		this.initHoldTime();
		this.initConfig();
		this.initTask();
	}
	
	/**
	 * 初始化数据字典
	 */
	public void initDictionary() {
		logger.info("==================初始化数据字典到ServletContext==================");
		List<DictionaryItem> dictItems = dictionaryItemService.getList();
		for (DictionaryItem dictItem : dictItems) {
			String dict_code = dictItem.getDict_code();
			@SuppressWarnings("unchecked")
			List<DictionaryItem> items = (List<DictionaryItem>)this.servletContext.getAttribute(dict_code);
			if (null == items) {
				items = new ArrayList<DictionaryItem>();
				this.servletContext.setAttribute(dict_code, items);
			}
			logger.info("dictionary==>{}, {}:{}", dict_code, dictItem.getItem_value(), dictItem.getItem_name());
			items.add(dictItem);
		}
	}
	
	/**
	 * 初始化权限类别到缓存
	 */
	public void initPermissionList() {
		logger.info("==================初始化权限列表到缓存==================");
		List<Permission> list = permissionService.getEnableList();
		Map<String, Permission> permssionMap = new HashMap<String, Permission>();
		for (Permission permission : list) {
			permssionMap.put(permission.getPermission_sign(), permission);
			logger.info("permission==>{}--{}", permission.getName(), permission.getPermission_sign());
		}
		systemCache.putPermissionMap(permssionMap);
	}
	
	/**
	 * 加载会员字典集合到Appliction
	 * @param list 权限列表
	 */
	public void initMemberList() {
		logger.info("=================加载运营中心、普通会员字典到Appliction=================");
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
		this.servletContext.setAttribute(DictConstants.DICT_OPERATION, operationMember);
		
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
		this.servletContext.setAttribute(DictConstants.DICT_COMMON_MEMBER, dictCommonMember);
		
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
		this.servletContext.setAttribute(DictConstants.DICT_MEMBER, dictMember);
	}
	
	/**
	 * 加载代理会员字典集合到Appliction
	 * @param list 权限列表
	 */
	public void initAgentMemberList() {
		logger.info("=================加载代理会员字典到Appliction=================");
		// 交易所、运营中心、普通会员
		List<AgentMember> memberList = agentMemberService.getList("status", Constants.STATUS_ENABLE);
		List<DictMember> list = new ArrayList<DictMember>();
		if (null != memberList) {
			for (AgentMember agentMember : memberList) {
				DictMember dict = new DictMember();
				dict.setItem_value(agentMember.getId().toString());
				dict.setMember_id(agentMember.getMember_id());
				dict.setOperation_member_id(agentMember.getOperation_member_id());
				dict.setItem_name(agentMember.getName());
				list.add(dict);
				logger.info("{}:{}", agentMember.getId(), agentMember.getName());
			}
		}
		this.servletContext.setAttribute(DictConstants.DICT_AGENT_MEMBER, list);
	}
	
	/**
	 * 加载代理会员字典集合到Appliction
	 * @param list 权限列表
	 */
	public void initContractBank() {
		logger.info("=================加载签约银行字典到Appliction=================");
		List<Bank> bankList = bankService.getList("status", Constants.STATUS_ENABLE);
		List<DictionaryItem> list = new ArrayList<DictionaryItem>();
		if (null != bankList) {
			for (Bank bank : bankList) {
				DictionaryItem item = new DictionaryItem();
				item.setItem_value(bank.getBank_no());
				item.setItem_name(bank.getBank_name());
				list.add(item);
				logger.info("{}:{}", bank.getBank_no(), bank.getBank_name());
			}
		}
		this.servletContext.setAttribute(DictConstants.DICT_CONTRACT_BANK, list);
	}
	
	/**
	 * 加载周期产品字典集合到Appliction
	 * @param list 权限列表
	 */
	public void initCycleProduct() {
		logger.info("=================加载周期产品字典到Appliction=================");
		List<CycleProduct> cycleProductList = cycleProductService.getList("status", Constants.STATUS_ENABLE);
		List<DictionaryItem> list = new ArrayList<DictionaryItem>();
		if (null != cycleProductList) {
			for (CycleProduct cycleProduct : cycleProductList) {
				DictionaryItem item = new DictionaryItem();
				item.setItem_value(cycleProduct.getCode());
				item.setItem_name(cycleProduct.getName());
				list.add(item);
				logger.info("{}:{}", cycleProduct.getCode(), cycleProduct.getName());
			}
		}
		this.servletContext.setAttribute(DictConstants.DICT_CYCLE_PRODUCT, list);
	}
	
	/**
	 * 加载持仓时间字典集合到Appliction
	 * @param list 权限列表
	 */
	public void initHoldTime() {
		logger.info("=================加载持仓时间字典集合到Appliction=================");
		List<HoldTime> holdTimeList = holdTimeService.getDictList();
		List<DictionaryItem> list = new ArrayList<DictionaryItem>();
		if (null != holdTimeList) {
			for (HoldTime holdTime : holdTimeList) {
				DictionaryItem item = new DictionaryItem();
				item.setItem_value(holdTime.getHold_time().toString());
				item.setItem_name(holdTime.getTime_unit());
				list.add(item);
				logger.info("{}:{}", holdTime.getHold_time(), holdTime.getTime_unit());
			}
		}
		this.servletContext.setAttribute(DictConstants.DICT_HOLD_TIME, list);
	}
	
	/**
	 * 加载系统配置字典集合到Appliction
	 * @param list 权限列表
	 */
	public void initConfig() {
		logger.info("=================加载系统配置字典集合到Appliction=================");
		List<Config> configList = configService.getList();
		if (null != configList) {
			for (Config config : configList) {
				this.servletContext.setAttribute(config.getConfig_code(), config.getConfig_value());
			}
		}
	}
	
	/**
	 * 加载自动启动任务
	 */
	public void initTask() {
		
		//初始化实时行情和k线行情数据到缓存
		quotationService.initToCache();
		
		// 修改自动启动任务状态为启动
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constants.STATUS_ENABLE);
		map.put("auto_startup", Constants.NUM_ONE);
		taskService.update(map);
		
		// 修改非自动启动任务为停用
		map.put("status", Constants.STATUS_DISABLE);
		map.put("auto_startup", Constants.NUM_ZORE);
		taskService.update(map);
		
		//加载自动启动任务
		List<Task> taskList = taskService.getList("auto_startup", Constants.STATUS_ENABLE);
		if (null != taskList) {
			for (Task task : taskList) {
				if (task.getCode().equals(TASK.TASK_HQ_SHUJU_11)) {
					YaoyaoQuotationTask.getInstance().start();
				} else if (task.getCode().equals(TASK.TASK_HQ_HUOBI)) {
					HuobiQuotationTask.getInstance().start();
				}  else if (task.getCode().equals(TASK.TASK_HQ_DELETE)) {
					DeleteQuotationTask.getInstance().start();
				}
			}
		}
		
	}
}