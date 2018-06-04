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
					<td class="right"><span class="must">*</span>配置说明：</td>
					<td><input type="text" name="config_name" id="config_name" value="${model.config_name }" maxlength="32" placeholder="这里输入配置说明" title="配置说明" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>配置代码：</td>
					<td><input type="text" name="config_code" id="config_code" value="${model.config_code }" maxlength="32" placeholder="这里输入配置代码" title="配置代码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>配置值：</td>
					<td><input type="text" name="config_value" id="config_value" value="${model.config_unit }" maxlength="32" placeholder="这里输入配置值" title="配置值" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>配置单位：</td>
					<td><input type="text" name="config_unit" id="config_unit" value="${model.config_value }" maxlength="32" placeholder="这里输入配置单位" title="配置单位" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>所属分组：</td>
					<td>
						<div class="vocation">
						    <select id="config_group" name="config_group" class="select3">
							    <option value="">请选择</option>
							    <c:forEach items="${D_SYS_CONFIG_GROUP }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.config_group eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
					<td class="right"><span class="must">*</span>生效方式：</td>
					<td>
						<div class="vocation">
						    <select id="effective_way" name="effective_way" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_EFFECTIVE_WAY }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.effective_way eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>排序：</td>
					<td><input type="number" name="sort" id="sort" value="${model.sort }" maxlength="32" placeholder="这里输入排序" title="排序" style="width:98%;"/></td>
					<td class="right">JSON数据：</td>
					<td>
						<textarea rows="3" cols="" name="json_data" id="json_data" maxlength="32" placeholder="这里JSON数据" title="描述" style="width:98%;">${model.json_data }</textarea>
					</td>
				</tr>
				<tr>
					<td class="center" colspan="10">
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
			add("${basePath}config/add");
		} else {
			edit("${basePath}config/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#config_name").val()) == "") {
			$("#config_name").tips("请输入配置说明").focus();
			return;
		}
		if ($.trim($("#config_code").val()) == "") {
			$("#config_code").tips("请输入配置代码").focus();
			return;
		}
		if ($.trim($("#config_value").val()) == "") {
			$("#config_value").tips("请输入配置值").focus();
			return;
		}
		if ($.trim($("#config_unit").val()) == "") {
			$("#config_unit").tips("请输入配置单位").focus();
			return;
		}
		if ($.trim($("#config_group").val()) == "") {
			$("#config_group").tips("请选择所属分组").focus();
			return;
		}
		if ($.trim($("#effective_way").val()) == "") {
			$("#effective_way").tips("请选择生效方式").focus();
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

