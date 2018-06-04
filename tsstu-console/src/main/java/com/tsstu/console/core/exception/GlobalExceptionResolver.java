package com.tsstu.console.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 统一的异常处理类
 * @author liwei
 * 日期：2017年4月14日14:23:07
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	protected Logger logger =  LoggerFactory.getLogger(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
		ModelAndView mv = new ModelAndView();
		String message = adaptMessage(e);
		if (!(e instanceof BusinessException)) {
			// 自定义异常不用再日志中输出
			logger.error(e.getMessage(), e);
		}
		int code = adaptCode(e);
		String accept = request.getHeader("Accept");
		if (null != accept && accept.indexOf("text/html") >= 0) {
			//如果请求的是页面，则跳转至对应的错误页面
			request.setAttribute("msg", message);
			request.setAttribute("errorCode", code);
			request.setAttribute("errorText", ExceptionCodeEmum.getName(code));
			String viewName = adaptViewName(e);
			mv.setViewName(viewName);
		} else {
			//请求的是json数据，返回错误的错误码，和错误信息
			MappingJackson2JsonView view = new MappingJackson2JsonView();
			view.addStaticAttribute("ok", false);
			view.addStaticAttribute("msg", message);
			mv.setView(view);
		}
		return mv;
	}
	
	/**
	 * 根据不同的异常，适配不同的错误消息
	 * @param e
	 * @return
	 */
	private String adaptMessage(Exception ex) {
		String result = "系统出错!";
		if (ex instanceof BusinessException) {
			result = ex.getMessage();
		} 
		return result;
	}
	
	/**
	 * 根据不同的异常，适配不同的错误页面
	 * @param e
	 * @return
	 */
	private String adaptViewName(Exception ex) {
		String result = "error";
		if (ex instanceof LoginTimeoutException) {
			result = "redirect:login";
		} else if (ex instanceof AuthorityException) {
			result = "error/401";
		}
		return result;
	}
	
	/**
	 * 根据不同的异常，适配不同的错误代码
	 * @param e
	 * @return
	 */
	private int adaptCode(Exception e) {
		int result = 1000;
		if (e instanceof BusinessException) {
			result = ExceptionCodeEmum.BUSINESS.getCode();
		} else if (e instanceof SystemException) {
			result = ExceptionCodeEmum.SYSTEM.getCode();
		} else if (e instanceof ValidateException) {
			result = ExceptionCodeEmum.VALIDATE.getCode();
		} else if (e instanceof AuthorityException) {
			result = ExceptionCodeEmum.AUTHORITY.getCode();
		}  else if (e instanceof LoginTimeoutException) {
			result = ExceptionCodeEmum.LOGINTIMEOUT.getCode();
		}
		return result;
	}


	
	

}
