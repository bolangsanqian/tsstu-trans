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
			width : 189
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
					<td class="right"><span class="must">*</span>手机号码：</td>
					<td><input type="text" name="mobile" id="mobile" value="${model.mobile }" maxlength="32" placeholder="这里输入手机号码" title="手机号码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>变动类型：</td>
					<td>
						<div class="vocation">
						    <select id="change_type" name="change_type" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_FUND_CHANGE_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.change_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>变动金额：</td>
					<td><input type="text" name="amount" id="amount" value="${model.amount }" maxlength="32" placeholder="这里输入变动金额" title="变动金额" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right">申请原因：</td>
					<td><textarea rows="3" cols="" name="apply_reason" id="apply_reason" maxlength="32" placeholder="这里输入描述" title="描述" style="width:98%;">${model.apply_reason }</textarea></td>
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
			add("${basePath}fund_change/add");
		} else {
			edit("${basePath}fund_change/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#mobile").val())=="" && $.trim($("#mobile").val()) == "") {
			$("#mobile").tips("请输入手机号码").focus();
			return;
		}
		if ($.trim($("#change_type").val())=="" && $.trim($("#change_type").val()) == "") {
			$("#change_type").tips("请选择变动类型").focus();
			return;
		}
		if ($.trim($("#amount").val())=="" && $.trim($("#amount").val()) == "") {
			$("#amount").tips("请输入变动金额").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

