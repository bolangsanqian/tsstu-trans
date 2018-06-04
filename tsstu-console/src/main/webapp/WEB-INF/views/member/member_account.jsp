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
			width : 238
		});
	});
</script>

</head>
<body>
    <div class="formbody">
    	<form id="form1">
	    	<input type="hidden" id="member_id" name="member_id" value="${member_id }" >
	    	<input type="hidden" id="id" name="id" value="${model.id }" >
	    	<input type="hidden" id="user_type" name="user_type" value="${empty model.user_type ? user_type : model.user_type }" >
		    <table class="form_table">
				<tr>
					<td class="right"><span class="must">*</span>用户名：</td>
					<td>
						<c:choose>
							<c:when test="${empty model }">
								<input type="text" name="username" id="username" value="${model.username }" maxlength="32" placeholder="这里输入用户名" title="用户名" style="width:98%;"/>
							</c:when>
							<c:otherwise>${model.username }</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="right"><c:if test="${empty model.id }"><span class="must">*</span></c:if>密码：</td>
					<td><input type="password" name="password" id="password" maxlength="32" placeholder="这里输入密码" title="密码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><c:if test="${empty model.id }"><span class="must">*</span></c:if>确认密码：</td>
					<td><input type="password" name="repassword" id="repassword" maxlength="32" placeholder="这里输入确认密码" title="确认密码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>手机号码：</td>
					<td><input type="text" name="mobile" id="mobile" maxlength="32" placeholder="这里输入手机号码 " title="手机号码" value="${empty model.mobile ? mobile : model.mobile }" style="width:98%;"/></td>
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
		if ($.trim($("#id").val())=="") {
			add("${basePath}member/account");
		} else {
			edit("${basePath}member/account");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#id").val()) == "") {
			if ($.trim($("#username").val()) == "") {
				$("#username").tips("请输入名称").focus();
				return;
			}
			if ($.trim($("#password").val()) == "") {
				$("#password").tips("请输入密码").focus();
				return;
			}
			if ($.trim($("#repassword").val()) == "") {
				$("#repassword").tips("请输入确认密码").focus();
				return;
			}
			if ($.trim($("#password").val()) != $.trim($("#repassword").val())) {
				$("#repassword").tips("确认密码输入错误").focus();
				return;
			}
		}
		if ($.trim($("#mobile").val()) == "") {
			$("#mobile").tips("请输入手机号码").focus();
			return;
		}
		return true;
	}
</script>
</body>
</html>

