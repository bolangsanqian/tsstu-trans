<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
<link rel="stylesheet" href="static/css/trade.css?t=${time }" />
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="static/js/trade.js?t=${time }"></script>
<link rel="stylesheet" href="static/css/order-list.css?t=${time }" />
<script type="text/javascript" src="static/plugins/radialIndicator.js"></script>
<link rel="stylesheet" href="static/plugins/swiper-3.4.2/dist/css/swiper.min.css">
<script src="static/plugins/swiper-3.4.2/dist/js/swiper.min.js"></script>
</head>
<body>
	<!-- 隐藏表单域 -->
	<input type="hidden" id="codes" name="codes" value="${product.code }">

	<!-- 标题 -->
	<div class="details_top">
		<b onclick="toUrl('index')"><div></div></b>
		<p>${product.name }</p>
		<span><a href="/Issue/index.html"></a></span>
	</div>
	
	<!-- 隐藏表单域 -->
	<input type="hidden" id="product_code" name="product_code" value="${product.code }"/>
	<input type="hidden" id="product_id" name="product_id" value="${product.id }"/>
	<input type="hidden" id="minute" name="minute" value="1"/>
	
	<!-- 最新价格 -->
	<div class="tsstu-header">
		<ul>
			<li class="real-time">
				<p data-price="">
					<span class="price">${quotation.bid}</span>
					<span class="img"><img class="arrow-img" src="static/images/img_arrow_up.gif"></span>
				</p>
				
			</li>
			<li class="open">
				<p class="title">开盘价</p>
				<p class="price">${quotation.open }</p>
			</li>
			<li class="high">
				<p class="title">最高价</p>
				<p class="price">${quotation.high }</p>
			</li>
			<li class="low">
				<p class="title">最低价</p>
				<p class="price">${quotation.low }</p>
			</li>
		</ul>
	</div>
	
	<!-- k线时间选择 -->
	<div class="tsstu-nav">
		<ul>
			<li class="switch">
				<span class="active" data-type="stock">k线</span>
				<span data-type="line">曲线</span>
			</li>
			<li class="time active" data-minute="1">1M</li>
			<li class="time" data-minute="5">5M</li>
			<li class="time" data-minute="15">15M</li>
			<li class="time" data-minute="30">30M</li>
			<li class="time" data-minute="60">1H</li>
			<li class="time" data-minute="1440">1D</li>
		</ul>
	</div>
	
	<!-- 底部按钮 -->
	<div class="tsstu-button">
		<ul>
			<li class="buy-down" onclick="trading(1355)">
				<div class="icon"><img src="static/images/trade/buy-down.png?v=1" /></div>
				<div class="text">买跌</div>
			</li>
			<li class="hold" onclick="hold_list()">
				<div class="icon"><img src="static/images/trade/hold.png?v=1" /></div>
				<div class="text">持仓</div>
			</li>
			<li class="buy-up" onclick="trade_confirm(1)">
				<div class="icon"><img src="static/images/trade/buy-up.png?v=1" /></div>
				<div class="text">买涨</div>
			</li>
		</ul>
	</div>
	
	<!-- k线/曲线 -->
	<div id="container" style="width: 100%;"></div>
	
	<!-- 遮盖层 -->
	<div class="tsstu-mask"></div>
	
	<script type="text/javascript" src="static/js/quotation.js?t=${time }"></script>

	<script type="text/javascript">
		//刷新价格
		function refresh_price_callback(quotation) {
			var current_price_el = $(".tsstu-header .real-time .price");
			var old_price = current_price_el.attr("data-price");
			if (old_price != quotation.bid) {
				var arrow_img_el = $(".arrow-img");
				if (quotation.bid > old_price) {
					$(".tsstu-header>ul").addClass("up").removeClass("down");
					arrow_img_el.attr("src", "static/images/img_arrow_up.gif").css({"margin-bottom" : "0px"});
				} else {
					$(".tsstu-header>ul").addClass("down").removeClass("up");
					arrow_img_el.attr("src", "static/images/img_arrow_down.gif").css({"margin-bottom" : "20px"});
				}
				arrow_img_el.animate({'margin-bottom':'10px'});
				current_price_el.attr("data-price", quotation.bid).html(quotation.bid);
			}
		}
		
		//刷新曲线
		function refresh_price_callback2(quotation, quotation_history) {
			var minute = $("#minute").val();
			var last_quotation = history_list[history_list.length - 1];
			quotation_history.ctm = parseInt(quotation_history.ctm / (minute * 60)) * (minute * 60);
			if (quotation_history.ctm > last_quotation.ctm) {
				if (history_list.length >= limit) {
				   history_list.shift();
				}
				history_list.push(quotation_history);
			} else {
				history_list.pop()
				last_quotation.open = quotation_history.open;
				last_quotation.close = quotation_history.close;
				history_list.push(last_quotation);
			}
			var chart_option = change_chart_data(history_list);
			myChart.setOption(chart_option, true);
		}
		
		//最高价、最低价
		function refresh_price_callback3(quotation, quotation_history) {
			$(".tsstu-header .low .price").html(quotation.low);
			$(".tsstu-header .high .price").html(quotation.high);
		}
	</script>
	
   </body>
</html>

