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
			width : 170
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
					<td class="right"><span class="must">*</span>产品代码：</td>
					<td><input type="text" name="code" id="code" value="${model.code }" maxlength="32" placeholder="这里输入产品代码" title="产品代码" style="width:98%;" onkeyup="toUpperCase(this)"/></td>
					<td class="right"><span class="must">*</span>分类名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入分类名称" title="分类名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>数据源：</td>
					<td>
						<div class="vocation">
						    <select id="datasource_code" name="datasource_code" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${dsList }" var="item" varStatus="item_status">
									<option value="${item.code}" <c:if test="${model.datasource_code eq item.code}">selected</c:if>>${item.code} - ${item.name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
					<td class="right"><span class="must">*</span>汇率类型：</td>
					<td>
						<div class="vocation">
						    <select id="rate_type" name="rate_type" class="select3" onchange="changeRateType(this.value)">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_FOREIGN_RATE_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.rate_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
				</tr>
				<tr>
					<td class="right"><span class="must" id="rate_type_must" style="display: none;">*</span>汇率：</td>
					<td><input type="number" name="rate" id="rate" value="${model.rate }" maxlength="32" placeholder="这里输入汇率" title="汇率" style="width:98%;"/></td>
					<td class="right">图标：</td>
					<td><input type="text" name="icon_url" id="icon_url" value="${model.icon_url }" maxlength="32" placeholder="这里输入图标" title="图标" style="width:98%;"/></td>
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
					<td class="right"><span class="must">*</span>排序：</td>
					<td><input type="number" name="sort" id="sort" value="${model.sort }" maxlength="32" placeholder="这里输入排序" title="排序" style="width:98%;"/></td>
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
			add("${basePath}product_category/add");
		} else {
			edit("${basePath}product_category/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#code").val())=="" && $.trim($("#code").val()) == "") {
			$("#code").tips("请输入产品代码").focus();
			return;
		}
		if ($.trim($("#name").val())=="" && $.trim($("#name").val()) == "") {
			$("#name").tips("请输入分类名称").focus();
			return;
		}
		if ($.trim($("#datasource_code").val())=="" && $.trim($("#datasource_code").val()) == "") {
			$("#datasource_code").tips("请选择数据源").focus();
			return;
		}
		if ($.trim($("#rate_type").val())=="" && $.trim($("#rate_type").val()) == "") {
			$("#rate_type").tips("请选择汇率类型").focus();
			return;
		}
		//汇率类型为固定类型是，汇率必填
		if ($.trim($("#rate_type").val())=="1" && $.trim($("#rate").val())=="" && $.trim($("#rate").val()) == "") {
			$("#rate").tips("请输入汇率").focus();
			return;
		}
		if ($.trim($("#status").val())=="" && $.trim($("#status").val()) == "") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		if ($.trim($("#sort").val())=="" && $.trim($("#sort").val()) == "") {
			$("#sort").tips("请输入排序").focus();
			return;
		}
		return true;
	}
	
	function changeRateType(type) {
		if (type == 1) {
			$("#rate_type_must").show();
		} else {
			$("#rate_type_must").hide();
		}
	}
	
</script>
</body>
</html>

