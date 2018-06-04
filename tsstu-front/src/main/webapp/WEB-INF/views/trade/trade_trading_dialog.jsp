<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 交易中弹出框-开始 -->
<div id="trading_dialog" class="tsstu-dialog2">
	<div class="header">
		<div class="title">${order.product_name }<span class="close" onclick="closeDialog('#trading_dialog')">x</span></div>
	</div>
	<div class="body">
		<div class="time">
			<div class="tsstu-circle-wrap">
				<div class="warp">
					<div class="miao">${miao}</div>
					<div class="price">
						<p class="text">现价</p>
						<p class="current-price">1000.992</p>
					</div>
				</div>
			</div>
		</div>
		<div class="sum">
			<div class="title">
				<ul>
					<li>方向</li>
					<li>金额</li>
					<li>下单价</li>
					<li>预测结果</li>
				</ul>
			</div>
			<div class="text">
				<ul>
					<li class="name">买跌</li>
					<li class="amount">￥50</li>
					<li class="open-price">1456.0124</li>
					<li class="yk-amount">￥+90</li>
				</ul>
			</div>
		</div>
		<div class="button">
			<a class="ok" href="javascript:closeDialog('#trading_dialog')">继续下单</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function($) {
		countDown('${miao}');
	});
	
</script>
<!-- 交易中弹出框-结束 -->