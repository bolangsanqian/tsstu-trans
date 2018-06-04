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
<script type="text/javascript">
	$(function($) {
		$(".opa_background").click(function() {
			$(this).hide();
			$(".city_show").hide();
			$(".card_show").hide();
		});
	});
	
	//显示银行列表
	function showBankList() {
		$(".opa_background").show();
		$(".card_show").show();
	}
	
	//省、城市列表
	function showCityList() {
		$(".opa_background").show();
		$(".city_show").show();
		
	}
</script>
</head>
<body>
	<div class="details_top">
		<b onclick="toUrl('my')"><div></div></b>
		<p>签约银行</p>
		<span><a href="/Issue/index.html"></a></span>
	</div>
	
	<form action="/User/doSaveCard.html" method="post" class="ajax-form">
		<div class="user_big_bg">
			<div class="card_card">
				<dl>
					<dt>真实姓名</dt>
					<dd>
						<input type="text" placeholder="请输入真实姓名" name="card_no" class="card_number" onkeypress="javascript:if(event.keyCode == 32)event.returnValue = false;">
					</dd>
				</dl>
				<dl>
					<dt>身份证号码</dt>
					<dd>
						<input type="text" placeholder="请输入身份证号码" name="card_no" class="card_number" onkeypress="javascript:if(event.keyCode == 32)event.returnValue = false;">
					</dd>
				</dl>
				<dl>
					<dt>卡号</dt>
					<dd>
						<input type="text" placeholder="请输入卡号" name="card_no" class="card_number" onkeypress="javascript:if(event.keyCode == 32)event.returnValue = false;">
					</dd>
				</dl>
				<dl onclick="showBankList()">
					<dt>所属银行</dt>
					<dd>
						<input type="text" placeholder="请选择所属银行" readonly=""
							class="card_tap">
					</dd>
					<input type="hidden" name="bank_id" class="bank_id">
				</dl>
				<dl onclick="showCityList()">
					<dt>地区</dt>
					<dd>
						<input type="text" placeholder="请选择地区" readonly=""
							class="city_tap">
					</dd>
					<input type="hidden" name="card_pid" class="province"> <input
						type="hidden" name="card_cid" class="city">
				</dl>
				<div class="clear"></div>
			</div>
			<div class="three_tab_news">
				<strong>贴士：</strong>
				<p>请确保绑定的银行卡与注册人的姓名及与银行预留的信息一致，并保证开户行名称正确，否则将会延误您出金的时间。</p>
			</div>
		</div>
		<div class="card_news_button">提交</div>

		<div class="card_show">
			<div class="card_show_news">
				<strong>选择所属银行</strong>
				<ul>
					<li id="5"><img src="static/images/bank/cmb.png"><span>招商银行</span></li>
					<li id="3"><img src="static/images/bank/boc.png"><span>中国银行</span></li>
					<li id="4"><img src="static/images/bank/ccb.png"><span>建设银行</span></li>
					<li id="1"><img src="static/images/bank/icbc.png"><span>中国工商银行</span></li>
					<li id="2"><img src="static/images/bank/aboc.png"><span>中国农业银行</span></li>
					<li id="14"><img src="static/images/bank/youzheng.png"><span>邮政储蓄银行</span></li>
					<li id="13"><img src="static/images/bank/minsheng.png"><span>民生银行</span></li>
					<li id="12"><img src="static/images/bank/guangda.png"><span>光大银行</span></li>
					<li id="11"><img src="static/images/bank/bj.png"><span>北京银行</span></li>
					<li id="10"><img src="static/images/bank/huaxia.png"><span>华夏银行</span></li>
					<li id="9"><img src="static/images/bank/pingan.png"><span>平安银行</span></li>
					<li id="8"><img src="static/images/bank/bohai.png"><span>渤海银行</span></li>
					<li id="7"><img src="static/images/bank/cgb.png"><span>广发银行</span></li>
					<li id="6"><img src="static/images/bank/bcm.png"><span>交通银行</span></li>
					<li id="5"><img src="static/images/bank/cmb.png"><span>招商银行</span></li>
					<li id="3"><img src="static/images/bank/boc.png"><span>中国银行</span></li>
					<li id="4"><img src="static/images/bank/ccb.png"><span>建设银行</span></li>
					<li id="1"><img src="static/images/bank/icbc.png"><span>中国工商银行</span></li>
					<li id="2"><img src="static/images/bank/aboc.png"><span>中国农业银行</span></li>
					<li id="14"><img src="static/images/bank/youzheng.png"><span>邮政储蓄银行</span></li>
					<li id="13"><img src="static/images/bank/minsheng.png"><span>民生银行</span></li>
					<li id="12"><img src="static/images/bank/guangda.png"><span>光大银行</span></li>
					<li id="11"><img src="static/images/bank/bj.png"><span>北京银行</span></li>
					<li id="10"><img src="static/images/bank/huaxia.png"><span>华夏银行</span></li>
					<li id="9"><img src="static/images/bank/pingan.png"><span>平安银行</span></li>
					<li id="8"><img src="static/images/bank/bohai.png"><span>渤海银行</span></li>
					<li id="7"><img src="static/images/bank/cgb.png"><span>广发银行</span></li>
					<li id="6"><img src="static/images/bank/bcm.png"><span>交通银行</span></li>
				</ul>
			</div>
		</div>

		<div class="city_show">
			<div class="city_show_news">
				<strong>所在省/市/自治区</strong>
				<ul>
					<li id="110000">北京市</li>
					<li id="120000">天津市</li>
					<li id="130000">河北省</li>
					<li id="140000">山西省</li>
					<li id="150000">内蒙古自治区</li>
					<li id="210000">辽宁省</li>
					<li id="220000">吉林省</li>
					<li id="230000">黑龙江省</li>
					<li id="310000">上海市</li>
					<li id="320000">江苏省</li>
					<li id="330000">浙江省</li>
					<li id="340000">安徽省</li>
					<li id="350000">福建省</li>
					<li id="360000">江西省</li>
					<li id="370000">山东省</li>
					<li id="410000">河南省</li>
					<li id="420000">湖北省</li>
					<li id="430000">湖南省</li>
					<li id="440000">广东省</li>
					<li id="450000">广西自治区</li>
					<li id="460000">海南省</li>
					<li id="500000">重庆市</li>
					<li id="510000">四川省</li>
					<li id="520000">贵州省</li>
					<li id="530000">云南省</li>
					<li id="540000">西藏自治区</li>
					<li id="610000">陕西省</li>
					<li id="620000">甘肃省</li>
					<li id="630000">青海省</li>
					<li id="640000">宁夏自治区</li>
					<li id="650000">新疆自治区</li>
					<li id="110000">北京市</li>
					<li id="120000">天津市</li>
					<li id="130000">河北省</li>
					<li id="140000">山西省</li>
					<li id="150000">内蒙古自治区</li>
					<li id="210000">辽宁省</li>
					<li id="220000">吉林省</li>
					<li id="230000">黑龙江省</li>
					<li id="310000">上海市</li>
					<li id="320000">江苏省</li>
					<li id="330000">浙江省</li>
					<li id="340000">安徽省</li>
					<li id="350000">福建省</li>
					<li id="360000">江西省</li>
					<li id="370000">山东省</li>
					<li id="410000">河南省</li>
					<li id="420000">湖北省</li>
					<li id="430000">湖南省</li>
					<li id="440000">广东省</li>
					<li id="450000">广西自治区</li>
					<li id="460000">海南省</li>
					<li id="500000">重庆市</li>
					<li id="510000">四川省</li>
					<li id="520000">贵州省</li>
					<li id="530000">云南省</li>
					<li id="540000">西藏自治区</li>
					<li id="610000">陕西省</li>
					<li id="620000">甘肃省</li>
					<li id="630000">青海省</li>
					<li id="640000">宁夏自治区</li>
					<li id="650000">新疆自治区</li>
				</ul>
			</div>
		</div>
	</form>

	<div class="opa_background"></div>

	<div class="loading_style">
		<img src="static/images/global/loading.gif">
	</div>

	<div class="Ddivok1">
		<div class="Ddivok1_opa"></div>
		<img src="static/images/global/Ddivok1_img.png">
			<p></p>
	</div>

	<div class="Ddivok2">
		<div class="Ddivok2-content">
			<div class="Ddivok2-content-info">
				<p>是否确认删除？</p>
			</div>
			<div class="Ddivok2-content-button">
				<p onclick="$('.Ddivok2').hide();$('.opa_background').hide();">取消</p>
				<p id="Ddivok-true">确认</p>
			</div>
		</div>
	</div>
</body>
</html>