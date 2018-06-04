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
			width : 220
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
					<td class="right"><span class="must">*</span>代码：</td>
					<td><input type="text" name="code" id="code" value="${model.code }" maxlength="32" placeholder="这里输入代码" title="代码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>交易模式：</td>
					<td>
						<div class="vocation">
						    <select id="trade_mode" name="trade_mode" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRADE_MODE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.trade_mode eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>内容：</td>
					<td><input type="text" name="text" id="text" value="${model.text }" maxlength="32" placeholder="这里输入内容" title="内容" style="width:98%;"/></td>
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
			add("${basePath}page_text/add");
		} else {
			edit("${basePath}page_text/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#code").val())=="" && $.trim($("#code").val()) == "") {
			$("#code").tips("请输入代码").focus();
			return;
		}
		if ($.trim($("#name").val())=="" && $.trim($("#name").val()) == "") {
			$("#name").tips("请输入名称").focus();
			return;
		}
		if ($.trim($("#trade_mode").val())=="" && $.trim($("#trade_mode").val()) == "") {
			$("#trade_mode").tips("请选择交易模式").focus();
			return;
		}
		if ($.trim($("#text").val())=="" && $.trim($("#text").val()) == "") {
			$("#text").tips("请输入内容").focus();
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

