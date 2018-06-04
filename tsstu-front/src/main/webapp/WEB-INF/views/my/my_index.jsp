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
<style type="text/css">
	body {
		background-color: #f2f2f2;
	}
</style>
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
				<a href="javascript:toUrl('my/info')" title="个人信息">个人信息</a><span></span>
			</li>
			<li>
				<div></div>
				<a href="javascript:toUrl('my/contract_bank')" title="签约银行">签约银行</a><span></span>
			</li>
			<li>
				<div></div>
				<a href="javascript:toUrl('hold/index')" title="历史订单">历史订单</a><span></span>
			</li>
			<li>
				<div></div>
				<a href="javascript:toUrl('my/fund_flow')" title="资金流水">资金流水</a><span></span>
			</li>
			<li>
				<div></div>
				<a href="javascript:toUrl('my/edit_password')" title="修改密码">修改密码</a><span></span>
			</li>
		</ul>
	</div>
	
	<a href="javascript:toUrl('logout')" class="cancel_user">退出账户</a>
	
	<%@ include file="../public/bottom.jsp"%>
</body>
</html>