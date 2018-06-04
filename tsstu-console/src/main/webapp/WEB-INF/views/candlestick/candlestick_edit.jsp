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
					<td class="right"><span class="must">*</span>名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>周期(分钟)：</td>
					<td><input type="text" name="minute" id="minute" value="${model.minute }" maxlength="32" placeholder="这里输入周期(分钟)" title="周期(分钟)" style="width:98%;"/></td>
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
					<td class="right"><span class="must">*</span>前端显示?：</td>
					<td>
						<div class="vocation">
						    <select id="is_show" name="is_show" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_SHOW_HIDE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.is_show eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
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
			add("${basePath}candlestick/add");
		} else {
			edit("${basePath}candlestick/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#name").val())=="") {
			$("#name").tips("请输入名称").focus();
			return;
		}
		if ($.trim($("#minute").val())=="") {
			$("#minute").tips("请输入周期(分钟)").focus();
			return;
		}
		if ($.trim($("#status").val())=="") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		if ($.trim($("#is_show").val())=="") {
			$("#is_show").tips("请选择是否在前端显示").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

