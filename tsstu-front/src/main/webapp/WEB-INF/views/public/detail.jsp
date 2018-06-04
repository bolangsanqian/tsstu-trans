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
		<b onclick="javascript:history.go(-1)"><div></div></b>
		<p>详情</p>
		<span><a href="my/index"><div></div></a></span>
	</div>

	<form action="/User/doSaveInfo.html" method="post" class="ajax-form">
		<div class="user_info_style">
			<div class="user_user">
				<dl>
					<dt>订单号</dt>
					<dd>18229772810</dd>
				</dl>
				<dl>
					<dt>商品</dt>
					<dd>比特币</dd>
				</dl>
				<dl>
					<dt>方向</dt>
					<dd>买涨</dd>
				</dl>
				<dl>
					<dt>交易金额</dt>
					<dd>￥1000</dd>
				</dl>
				<dl>
					<dt>盈亏金额</dt>
					<dd>￥1000</dd>
				</dl>
				<dl>
					<dt>下单时间</dt>
					<dd class="id_card">2017-05-22 12:00:00</dd>
				</dl>

				<dl>
					<dt>收盘时间</dt>
					<dd>2017-05-15 11:14:26</dd>
				</dl>
				<dl>
					<dt>状态</dt>
					<dd style="color: #33b936;">已结算</dd>
				</dl>
				<div class="clear"></div>
			</div>
		</div>
	</form>
</body>
</html>