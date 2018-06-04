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
<link rel="stylesheet" href="static/css/fund_flow.css?t=${time }" />
</head>
<body>
	<div class="details_top">
		<b onclick="toUrl('my')"><div></div></b>
		<p>资金流水</p>
		<span><a href="/Issue/index.html"></a></span>
	</div>
	<ul id="hold-list" class="tsstu-list">
		<li>
			<div class="left">
				<p class="name">下单</p>
				<p class="balance">余额：5000.00元</p>
			</div>
			<div class="right">
				<p class="amount in">+1000</p>
				<p class="time">2017-05-22 00:01:00</p>
			</div>
		</li>
		<li>
			<div class="left">
				<p class="name">结算</p>
				<p class="balance">余额：5000.00元</p>
			</div>
			<div class="right">
				<p class="amount in">+100</p>
				<p class="time">2017-05-22 00:01:00</p>
			</div>
		</li>
		<li>
			<div class="left">
				<p class="name">结算</p>
				<p class="balance">余额：5000.00元</p>
			</div>
			<div class="right">
				<p class="amount out">-90</p>
				<p class="time">2017-05-22 00:01:00</p>
			</div>
		</li>
		<li>
			<div class="left">
				<p class="name">下单</p>
				<p class="balance">余额：5000.00元</p>
			</div>
			<div class="right">
				<p class="amount out">-100</p>
				<p class="time">2017-05-22 00:01:00</p>
			</div>
		</li>
	</ul>
<script type="text/javascript">
	function switchTab(tab, type) {
		$(tab).addClass("active");
		$(tab).siblings().removeClass("active");
		if (type == "hold") {
			$("#hold-list").show();
			$("#history-list").hide();
		} else {
			$("#hold-list").hide();
			$("#history-list").show();
		}
	}
</script>
</body>
</html>