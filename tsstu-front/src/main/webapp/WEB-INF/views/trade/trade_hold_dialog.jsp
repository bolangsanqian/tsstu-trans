<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 持仓列表弹出框-开始 -->
<div id=hold_list_dialog class="tsstu-dialog">
	<div class="header">
		<div class="title">持仓列表<span class="close" onclick="closeDialog('#hold_list_dialog')">x</span></div>
	</div>
	<div class="body swiper-container">
		<ul class="tsstu-list swiper-wrapper">
			<c:forEach items="${list }" var="order" varStatus="status">
			<li class="swiper-slide order-item" data-id="${order.id }">
				<div class="left">
					<span class="name">${order.product_name }</span>
					<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
					<span class="amount yl">${order.direction eq -1 ? '买跌' : '买涨' }(￥${order.amount })</span>
					<p class="price">
						<span>${order.open_price }</span>-<span class="current-price yl">10001</span>
					</p>
					<p class="open-time"><fmt:formatDate value="${order.open_time }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
				</div>
				<div class="right">
					<p class="yk-amount yl">0</p>
					<p class="close-time">00:01:00</p>
				</div>
			</li>
			</c:forEach>
		</ul>
		<div class="swiper-scrollbar"></div>
	</div>
</div>
<!-- 持仓列表弹出框-结束 -->