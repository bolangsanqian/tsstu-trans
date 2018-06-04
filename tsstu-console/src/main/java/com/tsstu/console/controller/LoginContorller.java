package com.tsstu.console.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsstu.common.util.CommonUtils;
import com.tsstu.common.constants.Constants;
import com.tsstu.common.constants.Constants.MemberConstants;
import com.tsstu.common.constants.Constants.UserConstants;
import com.tsstu.console.core.annotation.LoginNotRequired;
import com.tsstu.console.core.entity.Menu;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.model.AgentMember;
import com.tsstu.console.model.Customer;
import com.tsstu.console.model.Member;
import com.tsstu.console.model.Permission;
import com.tsstu.console.model.User;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.CustomerService;
import com.tsstu.console.service.MemberService;
import com.tsstu.console.service.MenuService;
import com.tsstu.console.service.PermissionService;
import com.tsstu.console.service.UserService;
/**
 * 登录控制器
 * @author liwei
 * 日期：2017年4月19日23:37:38
 */
@Controller
public class LoginContorller extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AgentMemberService agentMemberService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 登录页面
	 * @return
	 */
	@LoginNotRequired
	@RequestMapping(value={"", "/login", "/login.html", "/login.jsp"}, method=RequestMethod.GET)
	public String toLogin() {
		if (isLogin()) {
			return "index";
		}
		return "login";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@LoginNotRequired
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Result login(String username, String password, String captcha, HttpSession session) {
		//判断是否已登录
		User user = (User)session.getAttribute(UserConstants.LOGIN_USER_INFO);
        if (null != user) {
        	return error(5001, "您已经登录！");
        }
		
        //是否输入验证
        //用户名
        if (CommonUtils.isNullOrEmpty(username)) {
        	return error("请输入用户名！", "username");
        }
        //密码
        if (CommonUtils.isNullOrEmpty(password)) {
        	return error("请输入密码！", "password");
        }
        //验证码
        if (CommonUtils.isNullOrEmpty(captcha)) {
        	return error("请输入验证码！", "captcha");
        } else {
        	String session_captcha = (String)session.getAttribute("captcha_login");
        	session.removeAttribute("captcha_login");
        	if (!captcha.equalsIgnoreCase(session_captcha)) {
        		return error("验证码输入错误！", "captcha");
        	}
        }
       
        //验证用户名是否存在、用户是否可登录
        user = userService.getByUsername(username);
        if (null == user) {
        	return error("用户名不存在！", "username");
        } else if (user.getStatus().intValue() == Constants.STATUS_DISABLE) {
    		return error("该用户已被禁止登录！", "username");
    	}
        
        //验证密码是否正确
    	if (userService.checkPassword(user.getPassword(), password)) { //密码正确、登录成功
			saveDataToSession(session, user); //保存相关数据到session
    		saveOperateLog("用户登录", "login", null, 5); //写登录成功日志
    		return success();
    	} else {
    		return error("密码错误！", "password");
    	}
	}
	
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(UserConstants.LOGIN_USER_INFO); //登录用户信息
		session.removeAttribute(UserConstants.LOGIN_USER_PERMISSION); //登录用户拥有的权限列表
		session.removeAttribute(UserConstants.LEFT_MENU_LIST); //登录用户左侧菜单
		session.removeAttribute(MemberConstants.LOGIN_MEMBER_IFNO); //会员信息
		session.removeAttribute(MemberConstants.LOGIN_AGENT_MEMBER_IFNO); //代理会员信息
		session.removeAttribute(MemberConstants.LOGIN_BROKER_IFNO); //经纪人信息
		session.invalidate();
		return "redirect:/";
	}
	
	/**
	 * 保存数据到sessoin
	 * @param session
	 * @param user
	 */
	private void saveDataToSession(HttpSession session, User user) {
		//1、保存登录用户信息到session
		session.setAttribute(UserConstants.LOGIN_USER_INFO, user);
		
		//2、获取权限列表
		List<Permission> permissionList = null;
		if ("admin".equals(user.getUsername())) { //管理员加载所有权限
			permissionList = permissionService.getEnableList();
		} else {
			permissionList = permissionService.getListByUserId(user.getId());
		}
		
		//3、保存登录用户所拥有的权限到session
		Map<String, Permission> userPermissionMap = new HashMap<String, Permission>();
		for (Permission permission: permissionList) {
			userPermissionMap.put(permission.getPermission_sign(), permission);
		}
		session.setAttribute(UserConstants.LOGIN_USER_PERMISSION, userPermissionMap);
		
		//4、构建左侧菜单并且保存到session
		List<Menu> leftMenuList = menuService.buildMenuTree(permissionList);
		session.setAttribute(UserConstants.LEFT_MENU_LIST, leftMenuList);
		
		//5、会员、代理、经纪人信息保存到session
		if (super.isCommonMember() || super.isOperation() || super.isExchange()) {
			Member member = memberService.getOne("user_id", user.getId());
			session.setAttribute(MemberConstants.LOGIN_MEMBER_IFNO, member);
		} else if (super.isAgentMember()) {
			AgentMember agentMember = agentMemberService.getOne("user_id", user.getId());
			session.setAttribute(MemberConstants.LOGIN_AGENT_MEMBER_IFNO, agentMember);
		} else if (super.isBroker()) {
			Customer customer = customerService.getOne("user_id", user.getId());
			session.setAttribute(MemberConstants.LOGIN_BROKER_IFNO, customer);
		}
	}
	
}
