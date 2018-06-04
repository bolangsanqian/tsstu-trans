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
			登录
		</div>
		<div class="right">
			<ul class="list-set">
				<li class="item item-user"><a href="javascript:toUrl('register')">注册</a></li>
			</ul>
		</div>
	</header>

	<!-- content -->
	<div class="content" id="page_login">
		<form method="get" action="my/index">
			<ul class="input-wrapper">
                <li class="i-username">
                    <input type="text" name="vwriter" id="user_name" placeholder="请输入手机号"/>
                </li>
                <li class="i-password">
                    <input type="password" name="vpassword" id="password" placeholder="请输入密码" />
                </li>
                <li class="i-vcode">
                    <input type="text" name="captcha" id="captcha" placeholder="请输入右图验证码" style="float: left;" />
                    <img id="code_img" onclick="changeCaptchaImage(this)" src="captcha/login?t=${time }" />
                </li>
				
            </ul>
            <div class="form-control">
                <button class="btn-login" type="buttom" onclick="login()">登 录</button>
            </div>
            <div class="form-control clearfix">
                <label class="left auto-login"><input type="checkbox" name="rmflag" value="1" checked />
	                <span class="rmflag-ico"></span>记住账号和密码
                </label>
                <a class="right" href="javascript:toUrl('my/find_password');">忘记密码</a>
            </div>
		</form>
		<div class="other-login-ways">
			<p class="tit"><span>或用其它方式快速登录</span></p>
			<div class="cont">
				<a class="way-weixin" href="javascript:void(0)https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx160fa51254add6be&redirect_uri=https%3A%2F%2Fpassport.tianya.cn%2Flogin%2Fweixin.do%3Fclient%3Dtouch%26fowardURL%3Dhttp%253A%252F%252Fwww.tianya.cn%252Fm%252F&response_type=code&scope=snsapi_login#wechat_redirect" title="微信帐号登录">微信登录</a>
				<a class="way-qq" href="javascript:void(0)https://graph.qq.com/oauth2.0/authorize?client_id=100253980&response_type=code&redirect_uri=https%3A%2F%2Fpassport.tianya.cn%2Flogin%2Fqq.do%3Fclient%3Dtouch%26fowardURL%3Dhttp%253A%252F%252Fwww.tianya.cn%252Fm%252F" title="QQ帐号登录">QQ登录</a>
				<a class="way-weibo" href="javascript:void(0)https://api.weibo.com/oauth2/authorize?client_id=482040646&response_type=code&with_offical_account=1&redirect_uri=https%3A%2F%2Fpassport.tianya.cn%2Flogin%2Fsinaweibo.do%3Fclient%3Dtouch%26fowardURL%3Dhttp%253A%252F%252Fwww.tianya.cn%252Fm%252F" title="新浪微博帐号登录">微博登录</a>
			</div>
		</div>
	</div>
<script type="text/javascript">
	function changeCaptchaImage(img) {
		$(img).attr("src", "captcha/login?t=" + (new Date().getTime()));
	}
	
	function login() {
		toUrl("my/index")
	}
</script>	
</body>
</html>