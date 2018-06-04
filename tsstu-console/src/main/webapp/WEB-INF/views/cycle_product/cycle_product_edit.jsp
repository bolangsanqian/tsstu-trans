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
			width : 160
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
					<td><input type="text" name="code" id="code" value="${model.code }" maxlength="32" placeholder="这里输入产品代码" title="产品代码" style="width:98%;"  onkeyup="toUpperCase(this)"/></td>
					<td class="right"><span class="must">*</span>产品名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入产品名称" title="产品名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>产品分类：</td>
					<td>
						<div class="vocation">
						    <select id="product_category_id" name="product_category_id" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${categoryList }" var="item" varStatus="status">
									<option value="${item.id}" <c:if test="${model.product_category_id eq item.id}">selected</c:if>>${item.code} - ${item.name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
					<td class="right"><span class="must">*</span>单位：</td>
					<td><input type="text" name="unit" id="unit" value="${model.unit }" maxlength="32" placeholder="这里输入单位" title="单位" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>最大持仓金额：</td>
					<td><input type="number" name="max_hold_amount" id="max_hold_amount" value="${model.max_hold_amount }" maxlength="32" placeholder="这里输入最大持仓金额" title="最大持仓金额" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>最小建仓金额：</td>
					<td><input type="number" name="min_create_amount" id="min_create_amount" value="${model.min_create_amount }" maxlength="32" placeholder="这里输入最小建仓金额" title="最小建仓金额" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>手续费公式：</td>
					<td><input type="text" name="fee_formula" id="fee_formula" value="${model.fee_formula }" maxlength="32" placeholder="这里输入手续费公式" title="手续费公式" style="width:98%;"/></td>
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
					<td class="right"><span class="must">*</span>排序：</td>
					<td><input type="number" name="sort" id="sort" value="${model.sort }" maxlength="32" placeholder="这里输入排序" title="排序" style="width:98%;"/></td>
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
			add("${basePath}cycle_product/add");
		} else {
			edit("${basePath}cycle_product/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#code").val())=="" && $.trim($("#code").val()) == "") {
			$("#code").tips("请输入产品代码").focus();
			return;
		}
		if ($.trim($("#name").val())=="" && $.trim($("#name").val()) == "") {
			$("#name").tips("请输入产品名称").focus();
			return;
		}
		if ($.trim($("#unit").val())=="" && $.trim($("#unit").val()) == "") {
			$("#unit").tips("请输入单位").focus();
			return;
		}
		if ($.trim($("#product_category_id").val())=="" && $.trim($("#product_category_id").val()) == "") {
			$("#product_category_id").tips("请输入产品分类").focus();
			return;
		}
		if ($.trim($("#max_hold_amount").val())=="" && $.trim($("#max_hold_amount").val()) == "") {
			$("#max_hold_amount").tips("请输入最大持仓金额").focus();
			return;
		}
		if ($.trim($("#min_create_amount").val())=="" && $.trim($("#min_create_amount").val()) == "") {
			$("#min_create_amount").tips("请输入最小建仓金额").focus();
			return;
		}
		if ($.trim($("#fee_formula").val())=="" && $.trim($("#fee_formula").val()) == "") {
			$("#fee_formula").tips("请输入手续费公式").focus();
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
	
</script>
</body>
</html>

