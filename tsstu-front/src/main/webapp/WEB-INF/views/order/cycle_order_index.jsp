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
<link rel="stylesheet" href="static/css/order-list.css?t=${time }" />
</head>
<body>
	<div class="index_logo">
		<p>订单</p>
	</div>
	<ul class="tsstu-tabs">
		<li class="tab-hold active" onclick="switchTab(this, 'hold')">持仓订单</span>
		<li class="tab-history" onclick="switchTab(this, 'history')">历史订单</span>
	</ul>
	<ul id="hold-list" class="tsstu-list">
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount yl">买跌(￥100)</span>
				<p class="price">
					<span>1000</span>-<span class="current-price yl">10001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount yl">+1000</p>
				<p class="close-time">00:01:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-up"><img src="static/images/buy-up.png"/></span>
				<span class="amount ks">买涨(￥100)</span>
				<p class="price">
					<span>1000</span>-<span class="current-price ks">10001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount ks">-500</p>
				<p class="close-time">00:01:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount ks">买跌(￥100)</span>
				<p class="price">
					<span>1000</span>-<span class="current-price ks">10001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount ks">-99</p>
				<p class="close-time">00:01:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount yl">买跌(￥100)</span>
				<p class="price">
					<span>1000</span>-<span class="current-price yl">10001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount yl">+80</p>
				<p class="close-time">00:01:00</p>
			</div>
		</li>
	</ul>
	<ul id="history-list" class="tsstu-list">
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount  yl">买跌(￥100)</span>
				<p class="price">
					<span>1000</span>-<span class="current-price yl">10001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount yl">+50</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount ks">买跌(￥100)</span>
				<p class="price">
					<span>1000</span>-<span class="current-price ks">10001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount ks">-5000</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount ks">买跌(￥100)</span>
				<p class="price">
					<span>1000</span>-<span class="current-price ks">10001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount ks">-90</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount yl">买跌(￥100)</span>
				<p class="price">
					<span>1000</span>-<span class="current-price yl">10001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount yl">+99</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount ks">买跌(￥100)</span>
				<p class="price">
					<span>1.00088</span>-<span class="current-price ks">1.00099</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount ks">-3000</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount yl">买跌(￥100)</span>
				<p class="price">
					<span>1.00022</span>-<span class="current-price yl">1.00021</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount yl">+2000</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount ks">买跌(￥100)</span>
				<p class="price">
					<span>52222.1</span>-<span class="current-price ks">50003.1</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount ks">-2500</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount yl">买跌(￥100)</span>
				<p class="price">
					<span>1001.65</span>-<span class="current-price yl">1002.65</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount yl">+3500</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount ks">买跌(￥100)</span>
				<p class="price">
					<span>999.23</span>-<span class="current-price ks">999.33</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount ks">-4000</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount yl">买跌(￥100)</span>
				<p class="price">
					<span>1000</span>-<span class="current-price yl">10001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount yl">+1500</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount ks">买跌(￥100)</span>
				<p class="price">
					<span>12000</span>-<span class="current-price ks">12001</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount ks">-1000</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
		<li onclick="toUrl('hold/detail?id=1001')">
			<div class="left">
				<span class="name">英镑/日元</span>
				<span class="icon buy-down"><img src="static/images/buy-down.png"/></span>
				<span class="amount yl">买跌(￥100)</span>
				<p class="price">
					<span>12.221</span>-<span class="current-price yl">12.226</span>
				</p>
				<p class="open-time">2017-05-22 12:00:00</p>
			</div>
			<div class="right">
				<p class="yk-amount yl">+1200</p>
				<p class="close-time">2017-05-22 12:00:00</p>
			</div>
		</li>
	</ul>
	<%@ include file="../public/bottom.jsp"%>
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