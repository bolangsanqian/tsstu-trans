package com.tsstu.console.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.common.util.CommonUtils;
import com.tsstu.console.cache.SystemCache;
import com.tsstu.console.cache.redis.RedisCache;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.common.constants.Constants.MemberConstants;
import com.tsstu.common.constants.Constants.UserConstants;
import com.tsstu.common.constants.Constants.UserTypeConstants;
import com.tsstu.console.core.datasource.DBContextHolder;
import com.tsstu.console.core.datasource.Source;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.exception.SystemException;
import com.tsstu.console.core.page.PageData;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.model.Customer;
import com.tsstu.console.model.DictionaryItem;
import com.tsstu.console.model.Log;
import com.tsstu.console.model.Member;
import com.tsstu.console.model.Permission;
import com.tsstu.console.model.User;
import com.tsstu.console.service.CustomerService;
import com.tsstu.console.service.DictionaryItemService;
import com.tsstu.console.service.LogService;
import com.tsstu.console.util.SpringUtils;

public class BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected SystemCache systemCache;
	
	@Autowired
	protected RedisCache redisCache;
	
	@Autowired
	protected DictionaryItemService dictionaryItemService;
	
	@Autowired
	public CustomerService customerService;
	
	/**
	 * 切换数据源
	 * @param source 数据源
	 */
	protected void switchDataSource(Source source) {
		DBContextHolder.switchDataSource(source);
	}
	
	/**
	 * 得到response对象
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}
	
	/**
	 * 得到request对象
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 得到session对象
	 * @return
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * 初始化分页信息
	 * @return
	 */
	protected Pager<?> initPageData(Pager<?> pager) {
		pager.init(this.getRequest());
		return pager;
	}
	
	/**
	 * 获取请求数据
	 * @return
	 */
	protected PageData getPageData() {
		return new PageData(this.getRequest());
	}
	
	/**
	 * 判断用户是否已登录
	 * @return
	 */
	protected boolean isLogin() {
		return null != this.getLoginUser();
	}
	
	/**
	 * 从session中获取登录用户
	 * @return
	 */
	protected User getLoginUser() {
		HttpSession session = this.getSession();
		User loginUserInfo = (User)session.getAttribute(UserConstants.LOGIN_USER_INFO);
		return loginUserInfo;
	}
	
	/**
	 * 从session中获取登录用户id
	 * @return
	 */
	protected int getLoginUserId() {
		User loginUserInfo = this.getLoginUser();
		if (null != loginUserInfo) {
			return loginUserInfo.getId();
		}
		return 0;
	}
	
	/**
	 * 从session中获取登录用户id
	 * @return
	 */
	protected String getLoginUsername() {
		User loginUserInfo = this.getLoginUser();
		if (null != loginUserInfo) {
			return loginUserInfo.getUsername();
		}
		return "";
	}
	
	/**
	 * 从session中获取登录用户权限列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<Permission> getUserPermissionList() {
		HttpSession session = this.getSession();
		List<Permission> permissionList = (List<Permission>)session.getAttribute(UserConstants.LOGIN_USER_PERMISSION);
		return permissionList;
	}
	
	/**
	 * 从session中获取登录用户权限标识列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getUserPermissionSignList() {
		HttpSession session = this.getSession();
		List<String> permissionList = (List<String>)session.getAttribute(UserConstants.LOGIN_USER_PERMISSION_SIGN);
		return permissionList;
	}
	
	/**
	 * 判断是否有权限
	 * @param sign 权限标识
	 * @return
	 */
	protected boolean hasPermission(String sign) {
		List<String> permissionList = this.getUserPermissionSignList();
		return permissionList.contains(sign);
	}
	
	/**
	 * 记录用户操作日志
	 * @param operateName  操作模块名称
	 * @param url   请求路径，不传默认记录本次请求的全路径
	 * @param params   请求参数，不传默认记录pageData
	 * @param autoRecord   是否自动记录，true表示自动记录
	 */
	protected void saveOperateLog(String operateName, String url, String params, int operateType){
		try{
			if(StringUtils.isBlank(url)){
				url = getRequest().getRequestURL().toString();
				String urlParams = getRequest().getQueryString();
		    	if (StringUtils.isNotBlank(urlParams)) {
		    		url += "?"+ urlParams;
		    	}
			}
			if(null == params) {
				params = this.getPageData().toString();
			}
			String accessIp = this.getClientIP();
			if (operateType == -1) {
				if (url.indexOf("add") > -1) {
					operateType = 1;
				} else if (url.indexOf("del") > -1) {
					operateType = 2;
				} else if (url.indexOf("edit") > -1) {
					operateType = 3;
				} else if (url.indexOf("index") > -1) {
					operateType = 4;
				} else if (url.indexOf("detail") > -1) {
					operateType = 5;
				} else if (url.indexOf("review") > -1) {
					operateType = 6;
				} else {
					operateType = 0;
				}
			} else {
				operateType = 0;
			}
			Log log = new Log();
			log.setCreate_time(new Date());
			log.setOperate_type(operateType);
			log.setIp(accessIp);
			log.setName(operateName);
			log.setParam(params);
			log.setUrl(url);
			log.setUser_id(this.getLoginUserId());
			SpringUtils.getBean(LogService.class).add(log);
		}catch(Exception e){
			throw new SystemException("保存操作日志异常", e);
		}
	}	
	
	/**
	 * 获取客户端ip
	 * @return
	 */
	public String getClientIP() {
		HttpServletRequest request = this.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			return "127.0.0.1";
		}
		if (ip.length() > 20) {
			return ip.split(",")[0];
		} else {
			return ip;
		}
	}
	
	public Result message() {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg("操作成功！");
		result.setData("");
		return result;
	}
	
	public Result message(String msg) {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result message(int status, String msg) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result message(int status, String msg, Object data) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public Result success() {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg("操作成功！");
		result.setData("");
		return result;
	}
	
	public Result success(String msg) {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result success(Object data) {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg("操作成功！");
		result.setData(data);
		return result;
	}
	
	public Result success(String msg, Object data) {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public Result success(int status, String msg, Object data) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public Result error() {
		Result result = new Result();
		result.setStatus(Result.ERROR_CODE);
		result.setMsg("操作失败！");
		result.setData("");
		return result;
	}
	
	public Result error(String msg) {
		Result result = new Result();
		result.setStatus(Result.ERROR_CODE);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result error(Object data) {
		Result result = new Result();
		result.setStatus(Result.ERROR_CODE);
		result.setMsg("操作失败！");
		result.setData(data);
		return result;
	}
	
	public Result error(String msg, Object data) {
		Result result = new Result();
		result.setStatus(Result.ERROR_CODE);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public Result error(int status, String msg) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result error(int status, String msg, Object data) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public List<DictionaryItem> getDictionary(String dict_code) {
		return dictionaryItemService.getList("dict_code", dict_code);
	}
	
	/**
	 * 是否超级管理员
	 * @return
	 */
	public boolean isAdmin() {
		return this.getLoginUser().getUser_type().intValue() == UserTypeConstants.ADMIN;
	}
	
	/**
	 * 是否管理员
	 * @return
	 */
	public boolean isManager() {
		return this.getLoginUser().getUser_type().intValue() == UserTypeConstants.MANAGER;
	}
	
	/**
	 * 是否为普通会员
	 * @return
	 */
	public boolean isBroker() {
		return this.getLoginUser().getUser_type().intValue() == UserTypeConstants.BROKER;
	}
	
	/**
	 * 是否为代理会员
	 * @return
	 */
	public boolean isAgentMember() {
		return this.getLoginUser().getUser_type().intValue() == UserTypeConstants.AGENT_MEMBER;
	}
	
	/**
	 * 是否为会员
	 * @return
	 */
	public boolean isCommonMember() {
		return this.getLoginUser().getUser_type().intValue() == UserTypeConstants.COMMON_MEMBER;
	}
	
	/**
	 * 是否为运营中心
	 * @return
	 */
	public boolean isOperation() {
		return this.getLoginUser().getUser_type().intValue() == UserTypeConstants.OPERATION;
	}
	
	/**
	 * 是否为交易所
	 * @return
	 */
	public boolean isExchange() {
		return this.getLoginUser().getUser_type().intValue() == UserTypeConstants.EXCHANGE;
	}
	
	/**
	 * 获取登录会员信息
	 * @return
	 */
	public Member getLoginMember() {
		HttpSession session = this.getSession();
		Member member = (Member)session.getAttribute(MemberConstants.LOGIN_MEMBER_IFNO);
		return member;
	}
	
	/**
	 * 获取登录会员id
	 * @return
	 */
	public Integer getLoginMemberId() {
		return this.getLoginMember().getId();
	}
	
	/**
	 * 获取登录运营中心信息
	 * @return
	 */
	public Member getLoginOperation() {
		return this.getLoginMember();
	}
	
	/**
	 * 获取登录运营中心id
	 * @return
	 */
	public Integer getLoginOperationId() {
		return this.getLoginOperation().getId();
	}
	
	/**
	 * 获取登录代理会员信息
	 * @return
	 */
	public Member getLoginAgentMember() {
		HttpSession session = this.getSession();
		Member member = (Member)session.getAttribute(MemberConstants.LOGIN_AGENT_MEMBER_IFNO);
		return member;
	}
	
	/**
	 * 获取登录代理会员id
	 * @return
	 */
	public Integer getLoginAgentMemberId() {
		return this.getLoginMember().getId();
	}
	
	/**
	 * 获取登录代理会员信息
	 * @return
	 */
	public Customer getLoginBroker() {
		HttpSession session = this.getSession();
		Customer customer = (Customer)session.getAttribute(MemberConstants.LOGIN_BROKER_IFNO);
		return customer;
	}
	
	public void dataPermission(Pager<?> pager, ModelAndView mv) {
		//运营中心、会员
		if (isManager()) {
			mv.addObject(DictConstants.DICT_OPERATION, systemCache.getOperationList());
			mv.addObject(DictConstants.DICT_OPERATION, systemCache.getCommonMemberList(0));
			mv.addObject(DictConstants.DICT_AGENT_MEMBER, systemCache.getAgentMemberList(0, 0));
		} else if (isOperation()) {
			pager.putData("operation_member_id", getLoginOperationId());
			mv.addObject(DictConstants.DICT_OPERATION, systemCache.getCommonMemberList(getLoginOperationId()));
			mv.addObject(DictConstants.DICT_AGENT_MEMBER, systemCache.getAgentMemberList(getLoginOperationId(), 0));
		} else if (isCommonMember()) {
			pager.putData("member_id", getLoginMemberId());
			mv.addObject(DictConstants.DICT_AGENT_MEMBER, systemCache.getAgentMemberList(0, getLoginMemberId()));
		}
		
		//经纪人
		if (isBroker()) {
			Customer customer = getLoginBroker();
			pager.putData("h_number", customer.getH_number());
		} else if (!CommonUtils.isNullOrEmpty(pager.getString("broker_mobile"))) {
			String broker_mobile = pager.getString("broker_mobile");
			Customer customer = customerService.getOne("mobile", broker_mobile);
			if (null != customer) {
				pager.putData("h_number", customer.getH_number());
				pager.putData("h_level", customer.getH_level());
			}
		}
	}
}
