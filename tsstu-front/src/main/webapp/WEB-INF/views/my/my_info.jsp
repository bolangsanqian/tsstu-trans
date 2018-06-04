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
<link rel="stylesheet" href="static/css/user_index.css?t=${time }" />
</head>
<body>
	<div class="details_top">
		<b onclick="toUrl('my')"><div></div></b>
		<p>个人信息</p>
		<span><a href="my/index"><div></div></a></span>
	</div>

	<form action="/User/doSaveInfo.html" method="post" class="ajax-form">
		<div class="user_info_style">
			<div class="user_user">
				<dl>
					<dt>手机号</dt>
					<dd>18229772810</dd>
				</dl>
				<dl>
					<dt>经销商编号</dt>
					<dd>1168</dd>
				</dl>
				<input type="hidden" id="hidden_none_id" class="hidden_none">
					<dl>
						<dt>用户类型</dt>
						<dd>个人</dd>
					</dl>
					<dl>
						<dt>个人姓名</dt>
						<dd>李维</dd>
					</dl>
					<dl>
						<dt>身份证号</dt>
						<dd class="id_card">430223198706058012</dd>
						<!--<dd style="display: none; width: 140px; text-align: right;">保存</dd>-->
					</dl>

					<dl>
						<dt>注册时间</dt>
						<dd>2017-05-15 11:13:26</dd>
					</dl>
					<dl>
						<dt>实名认证</dt>
						<dd style="color: #33b936;">已通过</dd>
					</dl>
					<div class="clear"></div>
			</div>
		</div>
	</form>

	<div class="opa_background" style="display: none;"></div>

	<div class="loading_style" style="display: none;">
		<img src="static/images/global/loading.gif">
	</div>

	<div class="Ddivok1">
		<div class="Ddivok1_opa"></div>
		<img src="static/images/global/Ddivok1_img.png">
			<p></p>
	</div>

	<div class="Ddivok2">
		<div class="Ddivok2-content">
			<div class="Ddivok2-content-info">
				<p>是否确认删除？</p>
			</div>
			<div class="Ddivok2-content-button">
				<p onclick="$('.Ddivok2').hide();$('.opa_background').hide();">取消</p>
				<p id="Ddivok-true">确认</p>
			</div>
		</div>
	</div>
</body>
</html>