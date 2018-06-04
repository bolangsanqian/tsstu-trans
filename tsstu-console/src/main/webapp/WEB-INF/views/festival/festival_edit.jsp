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
					<td class="right"><span class="must">*</span>名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>开始时间：</td>
					<td><input type="text" name="begin_time" id="begin_time" value="${model.begin_time }" maxlength="32" placeholder="这里输入开始时间" title="开始时间" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>结束时间：</td>
					<td><input type="text" name="end_time" id="end_time" value="${model.end_time }" maxlength="32" placeholder="这里输入结束时间" title="结束时间" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>描述：</td>
					<td><input type="text" name="remark" id="remark" value="${model.remark }" maxlength="32" placeholder="这里输入描述" title="描述" style="width:98%;"/></td>
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
			add("${basePath}festival/add");
		} else {
			edit("${basePath}festival/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#name").val())=="" && $.trim($("#name").val()) == "") {
			$("#name").tips("请输入名称").focus();
			return;
		}
		if ($.trim($("#begin_time").val())=="" && $.trim($("#begin_time").val()) == "") {
			$("#begin_time").tips("请输入开始时间").focus();
			return;
		}
		if ($.trim($("#end_time").val())=="" && $.trim($("#end_time").val()) == "") {
			$("#end_time").tips("请输入结束时间").focus();
			return;
		}
		if ($.trim($("#remark").val())=="" && $.trim($("#remark").val()) == "") {
			$("#remark").tips("请输入描述").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

