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
			width : 200
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
					<td class="right"><span class="must">*</span>产品分类：</td>
					<td>
						<div class="vocation">
						    <select id="product_category_id" name="product_category_id" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${categoryList }" var="item" varStatus="status">
									<option value="${item.id }" <c:if test="${model.product_category_id eq item.id}">selected</c:if>>${item.code} - ${item.name }</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>星期：</td>
					<td>
						<div class="vocation">
						    <select id="week" name="week" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_WEEK }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.week eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>开盘时间：</td>
					<td><input type="text" name="opening_time" id="opening_time" value="${model.opening_time }" maxlength="32" placeholder="这里输入开盘时间" title="开盘时间" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>收盘时间：</td>
					<td><input type="text" name="closing_time" id="closing_time" value="${model.closing_time }" maxlength="32" placeholder="这里输入收盘时间" title="收盘时间" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>收盘日期：</td>
					<td>
						<div class="vocation">
						    <select id="closing_day" name="closing_day" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${CLOSING_DAY }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.closing_day eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
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
			add("${basePath}trading_day/add");
		} else {
			edit("${basePath}trading_day/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#product_category_id").val())=="" && $.trim($("#product_category_id").val()) == "") {
			$("#product_category_id").tips("请选择产品分类").focus();
			return;
		}
		if ($.trim($("#week").val())=="" && $.trim($("#week").val()) == "") {
			$("#week").tips("请选择星期").focus();
			return;
		}
		if ($.trim($("#opening_time").val())=="" && $.trim($("#opening_time").val()) == "") {
			$("#opening_time").tips("请输入开盘时间").focus();
			return;
		}
		if ($.trim($("#closing_time").val())=="" && $.trim($("#closing_time").val()) == "") {
			$("#closing_time").tips("请输入收盘时间").focus();
			return;
		}
		if ($.trim($("#closing_day").val())=="" && $.trim($("#closing_day").val()) == "") {
			$("#closing_day").tips("请选择收盘日期").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

