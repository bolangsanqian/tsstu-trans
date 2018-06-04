<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
<style type="text/css">
	.form_table td.right {
		width: 100px;
	}
</style>
<script type="text/javascript">
	$(function($){
		//初始化下拉框
		$(".select3").uedSelect({
			width : 221
		});
	});
	
</script>

</head>
<body>
    <div class="formbody">
    	<form id="form1">
	    	<input type="hidden" id="id" name="id" value="${model.id }" >
		    <table class="form_table">
		    	<tr>
					<td class="right"><span class="must">*</span>用户名：</td>
					<td>${model.username }</td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>登录密码：</td>
					<td><input type="password" name="password" id="password" maxlength="32" placeholder="这里输入登录密码" title="登录密码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>确认密码：</td>
					<td><input type="password" name="repassword" id="repassword"  maxlength="32" placeholder="这里输入确认密码" title="确认密码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="center" colspan="4">
			        	<a class="button button-primary button-rounded button-small" onclick="save();">保存</a>
			        	<a class="button button-action button-rounded button-small" onclick="parentDialog.close()">取消</a>
					</td>
				</tr>
			</table>
		</form>
    </div>
<script type="text/javascript">
	//保存
	function save() {
		if (!inputCheck()) {
			return false;
		}
		edit("${basePath}broker/edit");
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#password").val())=="") {
			$("#password").tips("请输入登录密码").focus();
			return;
		}
		if ($.trim($("#repassword").val())=="") {
			$("#repassword").tips("请输入确认密码").focus();
			return;
		}
		if ($.trim($("#password").val()) != $.trim($("#repassword").val())) {
			$("#repassword").tips("两次密码输入不一致！").focus();
			return
		}
		return true;
	}
	
</script>
</body>
</html>

