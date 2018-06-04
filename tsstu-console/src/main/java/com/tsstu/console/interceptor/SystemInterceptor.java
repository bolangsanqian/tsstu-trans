package com.tsstu.console.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tsstu.console.cache.SystemCache;
import com.tsstu.common.constants.Constants.UserConstants;
import com.tsstu.console.controller.BaseController;
import com.tsstu.console.core.exception.AuthorityException;
import com.tsstu.console.model.Permission;
/**
 * 
* 类名称：权限验证、记录系统日志
* @author liwei
* @date 2016年7月22日
 */
@SuppressWarnings("all")
public class SystemInterceptor extends BaseController implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);
	
	@Autowired
	private SystemCache systemCache;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		//获取请求路径
		String path = (request.getServletPath() + (null == request.getPathInfo() ? "" : request.getPathInfo())).substring(1);
		//请求url转换成权限标识
		String sign = path.replaceAll("/", ":");
		
		//缓存中获取权限url集合
		Map<String, Permission> permissionMap = (Map<String, Permission>) systemCache.getPermissionMap();

		//判断是否需要验证权限
		if (!permissionMap.containsKey(sign)) { //不需要
			return true;
		} else { //需要
			Map<String, Permission> userPermissionMap = (Map<String, Permission>)request.getSession().getAttribute(UserConstants.LOGIN_USER_PERMISSION);
			if (null == userPermissionMap) {
				throw new AuthorityException("对不起，你无权限进行该操作！");
			}
			if (!userPermissionMap.containsKey(sign)) {
				throw new AuthorityException("对不起，你无权限进行该操作！");
			}
			
			//记录操作日志
			Permission permission = permissionMap.get(sign);
			this.saveOperateLog(permission.getName(), null, null, -1);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
}
