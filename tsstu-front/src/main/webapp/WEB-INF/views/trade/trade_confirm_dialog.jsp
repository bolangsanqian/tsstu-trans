<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 交易确认弹出框-开始 -->
<div id="trade_dialog" class="tsstu-dialog">
	<input type="hidden" id="product_code" name="product_code" value="${product.code }"/>
	<input type="hidden" id="product_id" name="product_id" value="${product.id }"/>
	<input type="hidden" id="direction" name="direction" value="${direction }"/>
	<div class="header">
		<div class="title">订单确认<span class="close" onclick="closeDialog('#trade_dialog')">x</span></div>
	</div>
	<div class="body">
		<div class="sum">
			<div class="title">
				<ul>
					<li>商品</li>
					<li>方向</li>
					<li>现价</li>
					<li>金额</li>
				</ul>
			</div>
			<div class="text">
				<ul>
					<li class="name">${product.name }</li>
					<li class="direction">${direction gt 0 ? '买涨' : '买跌' }</li>
					<li class="current-price">1456.0124</li>
					<li class="amount">￥${defaultAmountList[0] }</li>
				</ul>
			</div>
		</div>
		<div class="time">
			<div class="label">到期时间</div>
			<div class="time-list">
				<div class="swiper-container">
					<ul class="swiper-wrapper">
						<c:forEach items="${holdTimeList }" var="holdTime" varStatus="status">
							<c:choose>
								<c:when test="${holdTime.time_unit eq 'M'}">
								<li class="swiper-slide time-item${status.index eq 0 ? ' active' : '' }" data-id="${holdTime.id }" data-time="${holdTime.hold_time * 60 }" data-time-list="${holdTime.time_list }" data-amount-list="${holdTime.amount_list }">${holdTime.hold_time * 60 }秒</li>
								</c:when>
								<c:when test="${holdTime.time_unit eq 'H'}">
								<li class="swiper-slide time-item${status.index eq 0 ? ' active' : '' }" data-id="${holdTime.id }" data-time="${holdTime.hold_time * 60 * 60 }" data-time-list="${holdTime.time_list }" data-amount-list="${holdTime.amount_list }">${holdTime.hold_time * 60 * 60 }秒</li>
								</c:when>
								<c:otherwise>
								<li class="swiper-slide time-item${status.index eq 0 ? ' active' : '' }" data-id="${holdTime.id }" data-time="${holdTime.hold_time }" data-time-list="${holdTime.time_list }" data-amount-list="${holdTime.amount_list }">${holdTime.hold_time }秒</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="money">
			<div class="label">投资金额</div>
			<div class="money-list">
				<div class="swiper-container">
					<ul class="swiper-wrapper">
						<!-- 金额列表 -->
						<c:forEach items="${defaultAmountList }" var="amount" varStatus="status">
						<li class="swiper-slide money-item${status.index eq 0 ? ' active' : '' }" data-money="${amount }">￥${amount }</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="button">
			<a class="cancel" href="javascript: closeDialog();">取消下单</a>
			<a class="ok" href="javascript: trade()">确认下单</a>
		</div>
	</div>
</div>
<script type="text/javascript">

	$(function($) {
		//时间选中
		$("#trade_dialog .time .time-item").click(function() {
			$(this).siblings().removeClass("active");
			$(this).addClass("active");
			
			//加载时间对应的金额列表
			var amount_list_attr = $(this).attr("data-amount-list");
			load_amount_list(amount_list_attr);
		});
		
		//金额选择
		$("#trade_dialog .money ul").on("click", "li", function() {
			$(this).siblings().removeClass("active");
			$(this).addClass("active");
			
			var amount = $(this).attr("data-money");
			$("#trade_dialog .sum .amount").html("￥" + amount);
		});
	});
	
	//加载金额列表
	function load_amount_list(amount_list_attr) {
		var amount_list = amount_list_attr.split(",");
		var html = "";
		var slide_list = [];
		$("#trade_dialog .money-list ul").empty();
		swiper && swiper.removeAllSlides() && swiper.destroy();
		var html = "";
		for (index in amount_list) {
			var cls = "swiper-slide money-item";
			if (index == 0) {
				cls +=" active";
			}
			html += '<li class="' + cls + '" data-money="' + amount_list[index] + '">￥' + amount_list[index] + '</li>';
		}
		$("#trade_dialog .money-list ul").append(html);
		load_amount_swiper();
	}
	
	function trade() {
		var product_id = $("#product_id").val();
		var direction = $("#direction").val();
		var hold_time_id = $("#trade_dialog .time-list .time-item.active").attr("data-id");
		var amount = $("#trade_dialog .money-list .money-item.active").attr("data-money");
		var url = "trade/trade";
		$.ajax({
			url: url,
			data: {
				"product_id": product_id,
				"hold_time_id": hold_time_id,
				"amount": amount,
				"direction": direction
			},
			type: "post",
			dataType: "json",
			success: function(json) {
				console.log(json);
				if (json.ok) {
					var order = json.data;
					trading(order.id);
				} else {
					alert(json.msg);	
				}
				
			},
			complete: function() {
			}
		});
	}

</script>
<!-- 交易确认弹出框-结束 -->