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
					<td class="right"><span class="must">*</span>银行名称：</td>
					<td><input type="text" name="bank_name" id="bank_name" value="${model.bank_name }" maxlength="32" placeholder="这里输入银行名称" title="银行名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>银行编号：</td>
					<td><input type="text" name="bank_no" id="bank_no" value="${model.bank_no }" maxlength="32" placeholder="这里输入银行编号" title="银行编号" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>图标：</td>
					<td><input type="text" name="icon" id="icon" value="${model.icon }" maxlength="32" placeholder="这里输入图标" title="图标" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>用途：</td>
					<td>
						<div class="vocation">
						    <select id="purpose" name="purpose" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_BANK_PURPOSE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.purpose eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
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
			add("${basePath}bank/add");
		} else {
			edit("${basePath}bank/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#bank_name").val())=="" && $.trim($("#bank_name").val()) == "") {
			$("#bank_name").tips("请输入银行名称").focus();
			return;
		}
		if ($.trim($("#bank_no").val())=="" && $.trim($("#bank_no").val()) == "") {
			$("#bank_no").tips("请输入银行编号").focus();
			return;
		}
		if ($.trim($("#icon").val())=="" && $.trim($("#icon").val()) == "") {
			$("#icon").tips("请输入图标").focus();
			return;
		}
		if ($.trim($("#purpose").val())=="" && $.trim($("#purpose").val()) == "") {
			$("#purpose").tips("请选择用途").focus();
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

