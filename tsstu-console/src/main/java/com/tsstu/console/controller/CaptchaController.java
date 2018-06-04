package com.tsstu.console.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tsstu.console.core.annotation.LoginNotRequired;
import com.tsstu.common.util.VerifyCodeUtils;

/**
 * 图形验证码
 * @author liwei
 * 日期：2017年4月30日01:57:39
 */

@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {
	  
	@LoginNotRequired
	@RequestMapping("/{type}")
	public void generate(@PathVariable("type")String type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //存入会话session  
        HttpSession session = request.getSession(true);  
        session.setAttribute("captcha_" + type, verifyCode.toLowerCase());  
        //生成图片  
        int w = 200, h = 80;  
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);  
    }  
}
