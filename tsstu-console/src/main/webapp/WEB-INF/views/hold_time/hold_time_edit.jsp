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
			width : 272
		});
	});
	
</script>

</head>
<body>
    <div class="formbody">
    	<form id="form1">
	    	<input type="hidden" id="id" name="id" value="${model.id }" >
	    	<input type="hidden" id="cycle_product_id" name="cycle_product_id" value="${cycle_product_id }" >
		    <table class="form_table">
				<tr>
					<td class="right"><span class="must">*</span>持单时间：</td>
					<td><input type="text" name="hold_time" id="hold_time" value="${model.hold_time }" maxlength="32" placeholder="这里输入持单时间" title="持单时间" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>时间单位：</td>
					<td>
						<select id="time_unit" name="time_unit" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_HOLD_TIME_UNIT }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${model.time_unit eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>潜在收益比例：</td>
					<td><input type="text" name="profit" id="profit" value="${model.profit }" maxlength="32" placeholder="这里输入潜在收益比例" title="潜在收益比例" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>时间段列表：</td>
					<td><input type="text" name="time_list" id="time_list" value="${model.time_list }" maxlength="32" placeholder="这里输入时间段列表" title="时间段列表" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>金额列表：</td>
					<td><input type="text" name="amount_list" id="amount_list" value="${model.amount_list }" maxlength="32" placeholder="这里输入金额列表" title="金额列表" style="width:98%;"/></td>
				</tr>
				<tr>
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
			add("${basePath}hold_time/add");
		} else {
			edit("${basePath}hold_time/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#hold_time").val())=="" && $.trim($("#hold_time").val()) == "") {
			$("#hold_time").tips("请输入持单时间").focus();
			return;
		}
		if ($.trim($("#time_unit").val())=="" && $.trim($("#time_unit").val()) == "") {
			$("#time_unit").tips("请输入时间单位").focus();
			return;
		}
		if ($.trim($("#profit").val())=="" && $.trim($("#profit").val()) == "") {
			$("#profit").tips("请输入潜在收益比例").focus();
			return;
		}
		if ($.trim($("#time_list").val())=="" && $.trim($("#time_list").val()) == "") {
			$("#time_list").tips("请输入时间段列表").focus();
			return;
		}
		if ($.trim($("#amount_list").val())=="" && $.trim($("#amount_list").val()) == "") {
			$("#amount_list").tips("请输入金额列表").focus();
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

