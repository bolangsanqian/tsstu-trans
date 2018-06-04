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
			width : 162
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
					<td class="right"><span class="must">*</span>名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>公司名字：</td>
					<td><input type="text" name="company_name" id="company_name" value="${model.company_name }" maxlength="32" placeholder="这里输入公司名字" title="公司名字" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>公司领导：</td>
					<td><input type="text" name="company_leader" id="company_leader" value="${model.company_leader }" maxlength="32" placeholder="这里输入公司领导" title="公司领导" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>手机号码：</td>
					<td><input type="text" name="mobile" id="mobile" value="${model.mobile }" maxlength="32" placeholder="这里输入手机号码" title="手机号码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>证件类型：</td>
					<td>
						<div class="vocation">
						    <select id="identity_type" name="identity_type" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${MEMBER_IDENTITY_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.identity_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
					<td class="right"><span class="must">*</span>证件号：</td>
					<td><input type="text" name="identity" id="identity" value="${model.identity }" maxlength="32" placeholder="这里输入证件号" title="证件号" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>状态：</td>
					<td>
						<div class="vocation">
						    <select id="status" name="status" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
					 <td></td>
					 <td></td>
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
			add("${basePath}operation/add");
		} else {
			edit("${basePath}operation/edit");
		}
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
		if ($.trim($("#identity_type").val())=="" && $.trim($("#identity_type").val()) == "") {
			$("#identity_type").tips("请选择证件类型").focus();
			return;
		}
		if ($.trim($("#identity").val())=="" && $.trim($("#identity").val()) == "") {
			$("#identity").tips("请输入证件号").focus();
			return;
		}
		if ($.trim($("#status").val())=="" && $.trim($("#status").val()) == "") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

