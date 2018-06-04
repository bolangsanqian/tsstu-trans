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
			width : 258
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
					<td class="right"><span class="must">*</span>类型：</td>
					<td>
						<div class="vocation">
						    <select id="coupon_type" name="coupon_type" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_COUPON_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.coupon_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>金额：</td>
					<td><input type="text" name="amount" id="amount" value="${model.amount }" maxlength="32" placeholder="这里输入金额" title="金额" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>最低使用金额：</td>
					<td><input type="text" name="min_use_amount" id="min_use_amount" value="${model.min_use_amount }" maxlength="32" placeholder="这里输入最低使用金额" title="最低使用金额" style="width:98%;"/></td>
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
					<td class="right">备注：</td>
					<td>
						<textarea rows="3" cols="" id="remark" name="remark" maxlength="32" placeholder="这里输入备注" title="备注" style="width:98%;">${model.remark }</textarea>
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
			add("${basePath}coupon/add");
		} else {
			edit("${basePath}coupon/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#name").val())=="") {
			$("#name").tips("请输入名称").focus();
			return;
		}
		if ($.trim($("#coupon_type").val())=="") {
			$("#coupon_type").tips("请选择优惠券类型").focus();
			return;
		}
		if ($.trim($("#amount").val())=="") {
			$("#amount").tips("请输入金额").focus();
			return;
		}
		if ($.trim($("#min_use_amount").val())=="") {
			$("#min_use_amount").tips("请输入最低使用金额").focus();
			return;
		}
		if ($.trim($("#status").val())=="") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

