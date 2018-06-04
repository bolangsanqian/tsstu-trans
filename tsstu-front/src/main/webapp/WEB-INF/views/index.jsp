<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="public/header.jsp" %>
<meta name="viewport" id="viewport" content="width= 750, user-scalable=no, minimal-ui">
<link rel="stylesheet" href="static/plugins/swiper-3.4.2/dist/css/swiper.min.css">
<link rel="stylesheet" href="static/css/index.css?t=${time }">
<script src="static/plugins/swiper-3.4.2/dist/js/swiper.min.js"></script>
<script type="text/javascript">
	$(function($) {
		// 产品详情
		$(".tsstu-product-list ul").click(function() {
			var code = $(this).attr("data-code");
			var url = "trade/index?code=" + code;
			toUrl(url);
		});
		
		//轮播图
		var swiper = new Swiper('.swiper-container', {
		    pagination: '.swiper-pagination',
		    paginationClickable: true
		});
		
		//设置商品列表高度
		var h_window = document.documentElement.clientHeight;
		var h_swiper = $(".swiper-container").outerHeight(); 
		var h_title = $(".tsstu-title").outerHeight(true);
		var h_footer = $(".tsstu-footer").outerHeight(); 
		var h = h_window -h_swiper - h_title - h_footer - 10;
		//alert("windowHight:" + windowHight + ",h_swiper:" + h_swiper + ",h_title:" + h_title + "" + ",h_footer:" + h_footer + ",h：" + h)
		$(".tsstu-product-list").height(h);
	});
</script>
</head>
<body>
	<!-- 隐藏表单域 -->
	<input type="hidden" id="codes" name="codes" value="USDJPY,BTCCNY,BTCUSD,LTCCNY">
	
	<!-- 轮播图 -->
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495624668842&di=12f64b802ac9bb24ed8ead16dd019e2f&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F28%2F30%2F26b58PICQh7_1024.jpg"/></div>
            <div class="swiper-slide"><img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495624668842&di=12f64b802ac9bb24ed8ead16dd019e2f&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F28%2F30%2F26b58PICQh7_1024.jpg"/></div>
            <div class="swiper-slide"><img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495624668842&di=12f64b802ac9bb24ed8ead16dd019e2f&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F28%2F30%2F26b58PICQh7_1024.jpg"/></div>
            <div class="swiper-slide"><img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495624668842&di=12f64b802ac9bb24ed8ead16dd019e2f&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F28%2F30%2F26b58PICQh7_1024.jpg"/></div>
            <div class="swiper-slide"><img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495624668842&di=12f64b802ac9bb24ed8ead16dd019e2f&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F28%2F30%2F26b58PICQh7_1024.jpg"/></div>
            <div class="swiper-slide"><img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495624668842&di=12f64b802ac9bb24ed8ead16dd019e2f&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F28%2F30%2F26b58PICQh7_1024.jpg"/></div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    
    <!-- 标题 -->
    <div class="tsstu-title">
    	<ul>
    		<li class="name">商品</li>
    		<li class="now-price">现价</li>
    		<li class="high-price">最高</li>
    		<li class="low-price">最低</li>
    	</ul>
    </div>
    
    <!-- 产品列表 -->
    <div class="tsstu-product-list">
    	<c:forEach items="${list }" var="item" varStatus="status">
    		<ul class="up" data-code="${item.code }">
	    		<li class="name">&nbsp;&nbsp;&nbsp;${item.name }</li>
	    		<li class="now-price">
	    			<span class="current-price code-${item.code }">--</span>
	    			<span class="span-arrow-img"><img class="arrow-img code-${item.code }"" src="static/images/img_arrow_up.gif" /></span>
	    		</li>
	    		<li class="high-price code-${item.code }">--</li>
	    		<li class="low-price code-${item.code }">--</li>
	    	</ul>
    	</c:forEach>
    	<div class="bottom-empty"></div>
    </div>
    
	<%@ include file="public/bottom.jsp"%>
	
	<script type="text/javascript">
		function refresh_price_callback(quotation) {
			var current_price_el = $(".current-price.code-" + quotation.code);
			var old_price = current_price_el.attr("data-price");
			if (old_price != quotation.bid) {
				var arrow_img_el = $(".arrow-img.code-" + quotation.code);
				if (quotation.bid > old_price) {
					current_price_el.parent().parent().removeClass("down").addClass("up");
					arrow_img_el.attr("src", "static/images/img_arrow_up.gif").css({"margin-top" : "65px"});
				} else {
					current_price_el.parent().parent().removeClass("up").addClass("down");
					arrow_img_el.attr("src", "static/images/img_arrow_down.gif").css({"margin-top" : "20px"});
				}
				arrow_img_el.animate({'margin-top':'45px'});
				$(".current-price.code-" + quotation.code).attr("data-price", quotation.bid).html(quotation.bid);
				$(".high-price.code-" + quotation.code).html(quotation.high);
				$(".low-price.code-" + quotation.code).html(quotation.low);
			}
		}
	</script>
</body>
</html>
