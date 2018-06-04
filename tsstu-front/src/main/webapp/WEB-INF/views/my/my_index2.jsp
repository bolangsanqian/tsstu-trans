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
	<div class="index_logo">
		<p>个人中心</p>
	</div>

	<div class="tran_big_title">
		<div class="title_money">
			<dl>
				<dt>可用金额</dt>
				<dd>
					<span>0.00</span>
				</dd>
			</dl>
			<dl>
				<dt>冻结金额</dt>
				<dd>0.00</dd>
			</dl>
			<dl>
				<dt>认购冻结</dt>
				<dd>0.00</dd>
			</dl>
		</div>
		<ul>
			<li><a href="/Money/payin.html">充值</a></li>
			<li><i>出金</i></li>
		</ul>
	</div>
	<div class="tran_big_style">
		<ul>
			<li>
				<div></div>
				<a href="customer/info" title="个人信息">个人信息</a><span></span>
			</li>
			<li>
				<div></div>
				<a href="customer/contract_bank" title="签约银行">签约银行</a><span></span>
			</li>
			<li>
				<div></div>
				<a href="/User/address.html" title="资金流水">资金流水</a><span></span>
			</li>
			<li>
				<div></div>
				<a href="customer/edit_password" title="修改密码">修改密码</a><span></span>
			</li>
		</ul>
	</div>
	<a href="/Login/logout.html" class="cancel_user">退出账户</a>
	<div class="opa_background"></div>
	<div class="Ddivok1">
		<div class="Ddivok1_opa"></div>
		<img src="static/images/global/Ddivok1_img.png">
			<p></p>
	</div>
	<div class="appl_appl">
		<div class="appl_news">
			<strong>无法提现</strong>
			<div class="appl_news_block">暂无有效银行卡</div>
		</div>
		<div class="appl_bottom_button">
			<p onclick="$('.appl_appl').hide();$('.opa_background').hide();">取消</p>
			<p class="appl_style">
				<a href="/User/edit_card.html">立即添加</a>
			</p>
		</div>
	</div>

	<div class="index_footer">
		<ul>
			<li><a href="/Issue/index.html"><div></div>
					<b>认购/转让</b></a></li>
			<li><a href="/Bargain/index.html"><div></div>
					<b>议价转让</b></a></li>
			<li><a href="/Trade/index.html"><div></div>
					<b>我的艺术品</b></a></li>
			<li><a href="/Notice/index.html"><div></div>
					<b>公告</b></a></li>
			<li class="style_color"><a href="/User/index.html"><div></div>
					<b>个人中心</b></a></li>
		</ul>
	</div>
</body>
</html>