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
					<td class="right"><span class="must">*</span>区域代码：</td>
					<td><input type="text" name="code" id="code" value="${model.code }" maxlength="32" placeholder="这里输入区域代码" title="区域代码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>区域名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入区域名称" title="区域名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>父级代码：</td>
					<td><input type="text" name="parent_code" id="parent_code" value="${model.parent_code }" maxlength="32" placeholder="这里输入父级代码" title="父级代码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>层级：</td>
					<td><input type="text" name="level" id="level" value="${model.level }" maxlength="32" placeholder="这里输入层级" title="层级" style="width:98%;"/></td>
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
			add("${basePath}region/add");
		} else {
			edit("${basePath}region/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#code").val())=="" && $.trim($("#code").val()) == "") {
			$("#code").tips("请输入区域代码").focus();
			return;
		}
		if ($.trim($("#name").val())=="" && $.trim($("#name").val()) == "") {
			$("#name").tips("请输入区域名称").focus();
			return;
		}
		if ($.trim($("#parent_code").val())=="" && $.trim($("#parent_code").val()) == "") {
			$("#parent_code").tips("请输入父级代码").focus();
			return;
		}
		if ($.trim($("#level").val())=="" && $.trim($("#level").val()) == "") {
			$("#level").tips("请输入层级").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

