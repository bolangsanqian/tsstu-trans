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
    		<input type="hidden" id="dict_code" name="dict_code" value="${dict_code }" >
	    	<input type="hidden" id="id" name="id" value="${model.id }" >
		    <table class="form_table">
				<tr>
					<td class="right"><span class="must">*</span>项名称：</td>
					<td><input type="text" name="item_name" id="item_name" value="${model.item_name }" maxlength="32" placeholder="这里输入项名称" title="项值" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>项值：</td>
					<td><input type="text" name="item_value" id="item_value" value="${model.item_value }" maxlength="32" placeholder="这里输入项值" title="项值" style="width:98%;"/></td>
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
			add("${basePath}dictionary_item/add");
		} else {
			edit("${basePath}dictionary_item/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#item_name").val()) == "") {
			$("#item_name").tips("请输入项名称").focus();
			return;
		}
		if ($.trim($("#item_value").val()) == "") {
			$("#item_value").tips("请输入项值").focus();
			return;
		}
		if ($.trim($("#sort").val()) == "") {
			$("#sort").tips("请输入排序").focus();
			return;
		}
		return true;
	}
</script>
</body>
</html>

