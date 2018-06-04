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
		    <table class="form_table">
		    	<input type="hidden" id="id" name="id" value="${model.id }" >
				<tr>
					<td class="right"><span class="must">*</span>字典名称：</td>
					<td><input type="text" name="dict_name" id="dict_name" value="${model.dict_name }" maxlength="32" placeholder="这里输入字典名称" title="字典名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>字典代码：</td>
					<td>
						<input type="hidden" name="old_dict_code" id="old_dict_code" value="${model.dict_code }">
						<input type="text" name="dict_code" id="dict_code" value="${model.dict_code }" maxlength="32" placeholder="这里输入字典代码" title="字典代码" style="width:98%;" onkeyup="toUpperCase(this)"/>
					</td>
				</tr>
				<tr>
					<td class="right">描述：</td>
					<td>
						<textarea rows="3" cols="" name="remark" id="remark" maxlength="32" placeholder="这里输入描述" title="描述" style="width:98%;">${model.remark }</textarea>
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
			add("${basePath}dictionary/add");
		} else {
			edit("${basePath}dictionary/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#dict_name").val()) == "") {
			$("#dict_name").tips("请输入字典名称").focus();
			return;
		}
		if ($.trim($("#dict_code").val()) == "") {
			$("#dict_code").tips("请输入字典代码").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

