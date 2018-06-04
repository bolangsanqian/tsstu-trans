<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="public/header.jsp" %>
<meta name ="viewport" content ="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<link rel="stylesheet" href="static/css/touch-global.css?t=${time }"/>
<link rel="stylesheet" href="static/css/main_m_68d4b19.css?t=${time }"/>
</head>
<body style="background:#f8f8f8;">
	<!-- header -->
	<header class="header-static clearfix" >
		<!-- <div class="left">
			<ul class="list-nav">
				<li><a href="javascript:void(0)javascript:history.go(-1);"><i class="icon-chevron-left"></i></a></li>
			</ul>
		</div> -->
		<div class="center">
			注册
		</div>
		<div class="right">
			<ul class="list-set">
				<li class="item item-user"><a href="javascript:toUrl('login')">登录</a></li>
			</ul>
		</div>
	</header>

	<!-- content -->
	<div class="content" id="page_login">
		<form method="post" action="/login">
			<ul class="input-wrapper">
                <li class="i-username">
                    <input type="text" name="vwriter" id="user_name" placeholder="请输入手机号"/>
                </li>
                <li class="i-password">
                    <input type="password" name="vpassword" id="password" placeholder="请输入密码" />
                </li>
                <li class="i-invite-code">
                    <input type="text" name="invite_code" id="invite_code" placeholder="请输入邀请码" style="float: left;" />
                </li>
                <li class="i-vcode">
                    <input type="text" name="captcha" id="captcha" placeholder="请输入右图验证码" style="float: left;" />
                    <img id="code_img" onclick="changeCaptchaImage(this)" src="captcha/login?t=${time }" />
                </li>
                <li class="i-vcode-sms">
                    <input type="text" name="sms_captcha" id="sms_captcha" placeholder="请输入短信验证码" style="float: left;" />
                    <span class="sms_button">60秒后重新获取</span>
                </li>
            </ul>
            <div class="form-control">
                <button class="btn-login" type="submit">注 册</button>
            </div>
            <div class="form-control clearfix">
                <label class="left auto-login"><input type="checkbox" name="rmflag" value="1" checked />
	                <span class="rmflag-ico"></span>我已阅读《注册说明》
                </label>
            </div>
		</form>
	</div>
<script type="text/javascript">
	function changeCaptchaImage(img) {
		$(img).attr("src", "captcha/login?t=" + (new Date().getTime()));
	}
</script>	
</body>
</html>