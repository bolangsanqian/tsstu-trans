package com.tsstu.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tsstu.console.core.annotation.LoginNotRequired;
/**
 * 登录控制器
 * @author liwei
 * 日期：2017年4月19日23:37:38
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController {
	
	/**
	 * 404错误
	 * @return
	 */
	@LoginNotRequired
	@RequestMapping("/404")
	public String error_404() {
		return "/404";
	}
	
	/**
	 * 500错误
	 * @return
	 */
	@LoginNotRequired
	@RequestMapping("/500")
	public String error_500() {
		return "error/500";
	}
}
