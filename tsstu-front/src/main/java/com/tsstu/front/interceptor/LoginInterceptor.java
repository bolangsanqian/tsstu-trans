package com.tsstu.front.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.tsstu.front.controller.BaseController;
import com.tsstu.front.core.annotation.LoginNotRequired;
/**
 * 登录拦截器
 * @author liwei
 * 日期： 2017年4月26日16:46:41
 */
public class LoginInterceptor extends BaseController implements HandlerInterceptor  {
	
	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		//判断是否已登录，如果已登录，则直接跳过登录验证
		User user = null;// super.getLoginUser();
		if (null != user) {
			return true;
		}

		//登录验证
		if (!this.loginFilter(request, response, handler)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView modelAndView) throws Exception {

	}
	
	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
	
	/**
	 * 登录验证
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	private boolean loginFilter(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		LoginNotRequired cNotRequired= method.getDeclaringClass().getAnnotation(LoginNotRequired.class); //类注解，不需要登录
		LoginNotRequired mNotRequired = method.getAnnotation(LoginNotRequired.class); //方法注解，不需要登录
		if ((cNotRequired == null && mNotRequired == null)) {
			String accept = request.getHeader("Accept");
			if (null != accept && accept.indexOf("text/html") >= 0) { //非ajax请求
				//请求的路径
		        String contextPath=request.getContextPath();
				response.sendRedirect(contextPath);
			} else { //ajax请求
				PrintWriter out = response.getWriter(); 
	            out.print(JSONObject.toJSONString(error("未登录")));
	            out.flush();
	            return false;
			}
			return false;
		}
		return true;
	}
	
}
