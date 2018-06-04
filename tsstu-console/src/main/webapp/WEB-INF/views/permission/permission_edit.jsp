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
			width : 297
		});
	});
	
</script>

</head>
<body>
    <div class="formbody">
    	<form id="form1">
		    <table class="form_table">
		    	<input type="hidden" id="id" name="id" value="${model.id }" >
		    	<input type="hidden" id="pid" name="pid" value="${pid }" >
	    		<tr>
					<td class="right">上级：</td>
					<td>${pid > 0 ? pModel.name : '无' }</td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>资源名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入字典资源名称" title="资源名称" style="width:98%;"/></td>
				</tr>
				<tr>					
					<td class="right"><span class="must">*</span>资源类型：</td>
					<td>
						<div class="vocation">
						    <select id="permission_type" name="permission_type" class="select3" onchange="changeUrl(this.value)">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_MENU_TYPE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.permission_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>资源路径：</td>
					<td><input type="text" name="url" id="url" value="${model.url }" maxlength="32" placeholder="这里输入资源路径" title="资源路径" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>权限标识：</td>
					<td><input type="text" name="permission_sign" id="permission_sign" value="${model.permission_sign }" maxlength="32" placeholder="这里输入权限标识" title="权限标识" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>状态：</td>
					<td>
						<div class="vocation">
						    <select id="status" name="status" class="select3" <c:if test="${permission_type eq 0 }">"readonly", "readonly"</c:if>>
						    	<option value="">请选择</option>
							    <c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>序号：</td>
					<td><input type="number" name="sort" id="sort" value="${model.sort }" maxlength="32" placeholder="这里输入序号" title="序号" style="width:98%;"/></td>
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
	$(function() {
		$("#url").keyup(function() {
			var val = $(this).val().replace(/\//g, ":");
			$("#permission_sign").val(val);
		});
	});

	//保存
	function save() {
		if (!inputCheck()) {
			return false;
		}
		if ($.trim($("#id").val())=="") {
			add("${basePath}permission/add");
		} else {
			edit("${basePath}permission/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#name").val()) == "") {
			$("#name").tips("请输入资源名称").focus();
			return;
		}
		if ($.trim($("#permission_type").val()) == "") {
			$("#permission_type").tips("请选择资源类型").focus();
			return;
		}
		if ($.trim($("#url").val()) == "") {
			$("#url").tips("请输入资源路径").focus();
			return;
		}
		if ($.trim($("#permission_sign").val()) == "") {
			$("#permission_sign").tips("请输入权限标识").focus();
			return;
		}
		if ($.trim($("#status").val()) == "") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		return true;
	}
	
	function changeUrl(permission_type) {
		if (permission_type == 0) { //菜单
			$("#url").val("#");
			$("#url").attr("readonly", "readonly");
		} else {
			$("#url").removeAttr("readonly");
			$("#url").val("");
		}
	}
</script>
</body>
</html>

