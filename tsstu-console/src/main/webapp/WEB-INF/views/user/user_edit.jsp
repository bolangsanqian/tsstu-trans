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
			width : 259
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
					<td><input type="text" name="username" id="username" value="${model.username }" maxlength="32" placeholder="这里输入用户名" title="用户名" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><c:if test="${empty model.id}"><span class="must">*</span></c:if>密码：</td>
					<td><input type="password" name="password" id="password" maxlength="32" placeholder="这里输入密码" title="密码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right">手机号码：</td>
					<td><input type="text" name="mobile" id="mobile" value="${model.mobile }" maxlength="32" placeholder="这里输入用户名" title="用户名" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>用户类型：</td>
					<td>
						<div class="vocation">
						    <select id="user_type" name="user_type" class="select3" disabled="disabled">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_CONSOLE_USER_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.user_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right">角色：</td>
					<td>
				    	<ul style="overflow-y: auto;height: 70px;">
						    <c:forEach items="${roleList }" var="role" varStatus="roel_status">
					    		<li style="float: left;width: 50%;padding: 3px 0px;">
					    			<input name="roleIds" type="checkbox" <c:if test="${role.hasRole }">checked="checked"</c:if> value="${role.id }" /> ${role.name }
					    		</li>
							</c:forEach>
				    	</ul>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>状态：</td>
					<td>
						<div class="vocation">
						    <select id="status" name="status" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
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
			add("${basePath}user/add");
		} else {
			edit("${basePath}user/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#username").val()) == "") {
			$("#username").tips("请输入用户名").focus();
			return;
		}
		if ($.trim($("#id").val())=="" && $.trim($("#password").val()) == "") {
			$("#password").tips("请输入密码").focus();
			return;
		}
		if ($.trim($("#mobile").val())=="" && $.trim($("#mobile").val()) == "") {
			$("#mobile").tips("请输入密码").focus();
			return;
		}
		if ($.trim($("#user_type").val()) == "") {
			$("#user_type").tips("请选择用户类型").focus();
			return;
		}
		if ($.trim($("#status").val()) == "") {
			$("#status").tips("请选择用户状态").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

