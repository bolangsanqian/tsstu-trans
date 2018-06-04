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
					<td class="right"><span class="must">*</span>开盘价：</td>
					<td><input type="text" name="open" id="open" value="${model.open }" maxlength="32" placeholder="这里输入开盘价" title="开盘价" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>最低价：</td>
					<td><input type="text" name="low" id="low" value="${model.low }" maxlength="32" placeholder="这里输入最低价" title="最低价" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>最高价：</td>
					<td><input type="text" name="high" id="high" value="${model.high }" maxlength="32" placeholder="这里输入最高价" title="最高价" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>收盘价：</td>
					<td><input type="text" name="close" id="close" value="${model.close }" maxlength="32" placeholder="这里输入收盘价" title="收盘价" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>开盘时间：</td>
					<td><input type="text" name="firstdt" id="firstdt" value="${model.firstdt }" maxlength="32" placeholder="这里输入开盘时间" title="开盘时间" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>收盘时间：</td>
					<td><input type="text" name="lastdt" id="lastdt" value="${model.lastdt }" maxlength="32" placeholder="这里输入收盘时间" title="收盘时间" style="width:98%;"/></td>
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
			add("${basePath}quotation_history/add");
		} else {
			edit("${basePath}quotation_history/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#open").val())=="") {
			$("#open").tips("请输入开盘价").focus();
			return;
		}
		if ($.trim($("#low").val())=="") {
			$("#low").tips("请输入最低价").focus();
			return;
		}
		if ($.trim($("#high").val())=="") {
			$("#high").tips("请输入最高价").focus();
			return;
		}
		if ($.trim($("#close").val())=="") {
			$("#close").tips("请输入收盘价").focus();
			return;
		}
		if ($.trim($("#firstdt").val())=="") {
			$("#firstdt").tips("请输入开盘时间").focus();
			return;
		}
		if ($.trim($("#lastdt").val())=="") {
			$("#lastdt").tips("请输入收盘时间").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

