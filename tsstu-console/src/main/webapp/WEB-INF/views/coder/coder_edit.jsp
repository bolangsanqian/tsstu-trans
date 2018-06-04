<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tsstu" uri="http://www.tsstu.com/wp"%>
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
					<td class="right"><span id="test" class="must">*</span>模块名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入模块名" title="模块名" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>包名：</td>
					<td><input type="text" name="package_name" id="package_name" value="${model.package_name }" maxlength="32" placeholder="这里输入包名" title="包名" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>表名：</td>
					<td><input type="text" name="table_name" id="table_name" value="${model.table_name }" maxlength="32" placeholder="这里输入表名" title="表名" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>类名：</td>
					<td><input type="text" name="class_name" id="class_name"  value="${model.class_name }"  maxlength="32" placeholder="这里输入类名" title="类名" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>添加按钮：</td>
					<td>
						<div class="vocation">
						    <select id="add_button" name="add_button" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.add_button eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
					<td class="right"><span class="must">*</span>删除按钮：</td>
					<td>
						<div class="vocation">
						    <select id="del_button" name="del_button" class="select3">
							    <option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.del_button eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
							    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>修改按钮：</td>
					<td>
						<div class="vocation">
						    <select id="edit_button" name="edit_button" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.edit_button eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
					<td class="right"><span class="must">*</span>查询按钮：</td>
					<td>
						<div class="vocation">
						    <select id="query_button" name="query_button" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.query_button eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>导出按钮：</td>
					<td>
						<div class="vocation">
						    <select id="export_button" name="export_button" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.export_button eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
					<td class="right"><span class="must">*</span>导入按钮：</td>
					<td>
						<div class="vocation">
						    <select id="import_button" name="import_button" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.import_button eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>权限代码：</td>
					<td>
						<div class="vocation">
						    <select id="permission" name="permission" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.permission eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
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

	$(function() {
		$("#table_name").keyup(function() {
			var val = "";
			var arr = $(this).val().split("_");
			if (arr.length > 1) {
				for (var i=1; i<arr.length; i++) {
					val += arr[i].substr(0, 1).toLocaleUpperCase() + arr[i].substring(1);
				}
			}
			$("#class_name").val(val);
		});
	});

	//保存
	function save() {
		if (!inputCheck()) {
			return false;
		}
		if ($.trim($("#id").val())=="") {
			add("${basePath}coder/add");
		} else {
			edit("${basePath}coder/edit");
		}
	}

	//输入验证
	function inputCheck() {
		if ($.trim($("#name").val()) == "") {
			$("#name").tips("请输入模块名称").focus();
			return;
		}
		if ($.trim($("#package_name").val()) == "") {
			$("#package_name").tips("请输入包名").focus();
			return;
		}
		if ($.trim($("#class_name").val()) == "") {
			$("#class_name").tips("请输入类名").focus();
			return;
		}
		if ($.trim($("#table_name").val()) == "") {
			$("#table_name").tips("请输入表名").focus();
			return;
		}
		if ($.trim($("#add_button").val()) == "") {
			$("#add_button").tips("请选择是否生成添加按钮").focus();
			return;
		}
		if ($.trim($("#del_button").val()) == "") {
			$("#del_button").tips("请选择是否生成删除按钮").focus();
			return;
		}
		if ($.trim($("#edit_button").val()) == "") {
			$("#edit_button").tips("请选择是否生成修改按钮").focus();
			return;
		}
		if ($.trim($("#export_button").val()) == "") {
			$("#export_button").tips("请选择是否生成导出按钮").focus();
			return;
		}
		if ($.trim($("#import_button").val()) == "") {
			$("#import_button").tips("请选择是否生成导入按钮").focus();
			return;
		}
		if ($.trim($("#permission").val()) == "") {
			$("#permission").tips("请选择是否生成权限代码").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

