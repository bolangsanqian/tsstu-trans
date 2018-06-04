<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
	request.setAttribute("time", new Date().getTime());
%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath }">
<title>${P_SYS_CONSOLE_NAME }</title>
<link type="text/css" rel="stylesheet" href="static/css/login.css"/>
<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="static/plugins/zDialog/zDialog.js"></script>
<script type="text/javascript" src="static/js/cloud.js"></script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script language="javascript">

	//TOCMAT重启之后 点击左侧列表跳转登录首页 
	if (top.location != self.location) {
		top.location=self.location;
	}

	$(function($) {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
		
		//处理记住密码
		var username = $.cookie("cookie_username");
		var password = $.cookie("cookie_password");
		var cookie_remember = $.cookie("cookie_remember");
		if ($.trim(username) != "") {
			$("#username").val(username);
		}
		if ($.trim(password) != "") {
			$("#password").val(password);
		}
		$("#rememberPwd").attr("checked", cookie_remember ? true : false);
		
		//回车事件
		$(document).keyup(function(event) {
			//event.stopPropagation(); 
			if (event.keyCode == 13) {
				$("#loginBtn").trigger("click");
			}
		});
	});
</script>

</head>
<body style="background-color: #1c77ac; background-image: url(static/images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>

	<div class="logintop">
		<span>欢迎登录后台管理界面平台</span>
		<ul>
			<li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>

	<div class="loginbody">
		<form id="form1">
		<span class="systemlogo" style="background:url(static/images/loginlogo.png) no-repeat center;width:100%; height:71px; margin-top:75px;"></span>
		<div class="loginbox loginbox3">
			<ul>
				<li>
					<input type="text" id="username" name="username" class="loginuser" placeholder="请输入用户名" title="用户名" value="" /></li>
				<li>
					<input type="password" id="password" name="password" class="loginuser" placeholder="请输入密码" title="密码" value="" /></li>
				<li class="yzm">
					<span><input type="text" id="captcha" name="captcha" class="loginuser" placeholder="请输入验证码" title="验证码" value="" /></span>
					<cite><img id="captcha_image" title="图形验证码" style="width: 114px;height: 46px;" src="captcha/login?t=${time }" onclick="changeCaptchaImage()"> </cite>
				</li>
				<li>
					<input id="loginBtn" type="button" class="loginbtn" value="登录" onclick="login()" /><label>
					<input id="rememberPwd" type="checkbox" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label>
				</li>
			</ul>
		</div>
		</form>
	</div>
	<div class="loginbm">
		版权所有 2017 <a href="http://www.tsstu.com">tsstu.com</a> 仅供学习交流，勿用于任何商业用途
	</div>
</body>
<script type="text/javascript">
	//添加
	function login() {
		if (!inputCheck()) {
			return;
		}
		var url = "<%=basePath%>login";
		var data = $("#form1").serialize();
		$.ajax({
			url: url,
			data: data,
			type: "post",
			dataType: "json",
			success: function(json) {
				if (json.ok) {
					remember();
					location.href = "<%=basePath%>index";
				} else {
					if (json.status == 5001) { //已登录
						location.href = "index";
					} else {
						$("#captcha").val("");
						changeCaptchaImage();
						$("#" + json.data).tips(json.msg).focus();						
					}
				}
				
			}
		});
	}
	
	function inputCheck() {
		var username = $("#username");
		var password = $("#password");
		var captcha = $("#captcha");
		if ($.trim(username.val()) == "") {
			$("#username").tips("请输入用户名！").focus();
			return false;
		}
		if ($.trim(password.val()) == "") {
			$("#password").tips("请输入密码！").focus();
			return false;
		}
		if ($.trim(captcha.val()) == "") {
			$("#captcha").tips("请输入验证码！").focus();
			return false;
		}
		return true;
	}
	
	//记住密码
	function remember() {
		if ($("#rememberPwd").is(":checked")) {
			$.cookie("cookie_username", $.trim($("#username").val()), { expires: 7 });
			$.cookie("cookie_password", $.trim($("#password").val()), { expires: 7 });
			$.cookie("cookie_remember", $("#rememberPwd").is(":checked"), { expires: 7 });
		} else {
			$.cookie("cookie_username", "");
			$.cookie("cookie_password", "");
			$.cookie("cookie_remember", "");
		}
	}
	
	function changeCaptchaImage() {
		$("#captcha_image").attr("src", "captcha/login?t=" + (new Date().getTime()));
	}
	
</script>
</html>