<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="static/css/bottom.css">
<!-- 底部菜单 -->
<ul class="tsstu-footer">
	<li onclick="toUrl('index')">
		<div class="icon"><img src="static/images/bottom/${empty currentMenu or currentMenu eq 'index' ? 'trans_focus.png' : 'trans.png' }?v=3"></div>
		<div class="text ${currentMenu eq 'index' ? 'focus' : ''}">商品</div>
	</li>
	<li onclick="toUrl('hold/index')">
		<div class="icon"><img src="static/images/bottom/${currentMenu eq 'hold' ? 'order_focus.png' : 'order.png' }?v=3"></div>
		<div class="text ${currentMenu eq 'hold' ? 'focus' : ''}">订单</div>
	</li>
	<li onclick="toUrl('find/index')">
		<div class="icon"><img src="static/images/bottom/${currentMenu eq 'find' ? 'find_focus.png' : 'find.png' }?v=3"></div>
		<div class="text ${currentMenu eq 'find' ? 'focus' : ''}">发现</div>
	</li>
	<li onclick="toUrl('my/index')">
		<div class="icon"><img src="static/images/bottom/${currentMenu eq 'my' ? 'me_focus.png' : 'me.png' }?v=3"></div>
		<div class="text ${currentMenu eq 'my' ? 'focus' : ''}">我的</div>
	</li>
</ul>
