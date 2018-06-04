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
		    	<input type="hidden" id="pid" name="pid" value="${pid }" >
		    <table class="form_table">
				<tr>
					<td class="right"><span class="must">*</span>名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>菜单类型：</td>
					<td>
						<div class="vocation">
						    <select id="menu_type" name="menu_type" class="select3" onchange="menuTypeChange(this.value)">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_WXMENU_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.menu_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr id="keyword_tr">
					<td class="right"><span class="must">*</span>关键字：</td>
					<td><input type="text" name="keyword" id="keyword" value="${model.keyword }" maxlength="32" placeholder="这里输入关键字" title="关键字" style="width:98%;"/></td>
				</tr>
				<tr id="url_tr" style="display: none;">
					<td class="right"><span class="must">*</span>跳转地址：</td>
					<td><input type="text" name="url" id="url" value="${model.url }" maxlength="32" placeholder="这里输入跳转地址" title="跳转地址" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>状态：</td>
					<td>
						<div class="vocation">
						    <select id="status" name="status" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
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
			add("${basePath}wechat_menu/add");
		} else {
			edit("${basePath}wechat_menu/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#name").val())=="") {
			$("#name").tips("请输入名称").focus();
			return;
		}
		if ($.trim($("#menu_type").val())=="") {
			$("#menu_type").tips("请选择菜单类型").focus();
			return;
		} 
		if ($.trim($("#menu_type").val()) == 0) {
			if ($.trim($("#keyword").val())=="") {
				$("#keyword").tips("请输入关键字").focus();
				return;
			}
		} else {
			if ($.trim($("#url").val())=="") {
				$("#url").tips("请输入跳转地址").focus();
				return;
			}
		}
		if ($.trim($("#sort").val())=="") {
			$("#sort").tips("请输入排序").focus();
			return;
		}
		if ($.trim($("#status").val())=="") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		return true;
	}
	
	function menuTypeChange(value) {
		if (value == 0) {
			$("#keyword_tr").show();
			$("#url_tr").hide();
		} else {
			$("#keyword_tr").hide();
			$("#url_tr").show();
		}
	}
	
</script>
</body>
</html>

