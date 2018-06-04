<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
</head>
<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">会员管理</a></li>
		    <li><a href="#">交易所</a></li>
	    </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
	    <form id="form1">
	    	<input type="hidden" id="id" name="id" value="${model.id }" >
		    <ul class="forminfo">
			    <li><label>名称：</label><input id="name" name="name" type="text" class="dfinput" style="width: 280px;" value="${model.name }"><i>交易所的名称</i></li>
			    <li><label>公司名称：</label><input id="company_name" name="company_name" type="text" class="dfinput" style="width: 280px;" value="${model.company_name }"><i>交易所所属公司</i></li>
			    <li><label>公司领导：</label><input id="company_leader" name="company_leader" type="text" class="dfinput" style="width: 280px;"  value="${model.company_leader }"><i>交易所联系人</i></li>
			    <li><label>手机号码：</label><input id="mobile" name="mobile" type="text" class="dfinput" style="width: 280px;"  value="${model.mobile }"><i>交易所联系人手机号码</i></li>
			    <li><label>账号余额：</label><input type="text" class="dfinput" disabled="disabled" style="width: 280px;" value="${model.balance }" readonly="readonly"><i>交易所账户余额</i></li>
			    <li>
			    	<label>&nbsp;</label>
			    	<tsstu:hasPermission sign="exchange:edit">
			    	<a class="button button-primary button-rounded button-small" onclick="save();">确认保存</a>
			    	</tsstu:hasPermission>
			    	<tsstu:hasPermission sign="member:upload_wx_qrcode">
			    	<a class="button button-action button-rounded button-small" onclick="to_upload_wx_qrcode('${basePath }member/upload_wx_qrcode?id=${model.id }', true)">公众号二维码</a>
			    	</tsstu:hasPermission>
			    </li>
		    </ul>
		</form>
    </div>
<script type="text/javascript">
	//保存
	function save() {
		if (!inputCheck()) {
			return false;
		}
		edit("${basePath}member/edit");
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#name").val())=="" && $.trim($("#name").val()) == "") {
			$("#name").tips("请输入名称").focus();
			return;
		}
		if ($.trim($("#company_name").val())=="" && $.trim($("#company_name").val()) == "") {
			$("#company_name").tips("请输入公司名字").focus();
			return;
		}
		if ($.trim($("#company_leader").val())=="" && $.trim($("#company_leader").val()) == "") {
			$("#company_leader").tips("请输入公司领导").focus();
			return;
		}
		if ($.trim($("#mobile").val())=="" && $.trim($("#mobile").val()) == "") {
			$("#mobile").tips("请输入手机号码").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>
