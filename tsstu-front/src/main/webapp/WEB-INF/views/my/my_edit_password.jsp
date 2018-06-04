<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp"%>
<meta name="viewport" id="viewport" content="width= 750, user-scalable=no, minimal-ui">
<link rel="stylesheet" href="static/css/normalize.css?t=${time }" />
<link rel="stylesheet" href="static/css/main.css?t=${time }" />
<link rel="stylesheet" href="static/css/user_password.css?t=${time }" />
</head>
<body>
	<div class="details_top">
		<b onclick="toUrl('my')"><div></div></b>
		<p>密码修改</p>
		<span><a href="/Issue/index.html"></a></span>
	</div>

	<form action="/User/changePwd.html" method="post" class="ajax-form">
		<div class="user_big_style">
			<div class="user_button">保存</div>
			<div class="user_user">
				<dl>
					<dt>原密码</dt>
					<dd>
						<input type="password" placeholder="请填写原密码" class="old_password"
							name="old_password" maxlength="15">
					</dd>
				</dl>
				<dl>
					<dt>新密码</dt>
					<dd>
						<input type="password" placeholder="请填写新密码" class="new_password"
							name="password" maxlength="15">
					</dd>
				</dl>
				<dl>
					<dt>确认密码</dt>
					<dd>
						<input type="password" placeholder="请与新密码保持一致" class="ok_password"
							name="repassword" maxlength="15">
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
		</div>
	</form>

	<div class="opa_background"></div>

	<div class="loading_style">
		<img src="static/images/global/loading.gif">
	</div>

	<div class="Ddivok1">
		<div class="Ddivok1_opa"></div>
		<img src="static/images/global/Ddivok1_img.png">
			<p></p>
	</div>
</body>
</html>