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
	    	<input type="hidden" id="coder_id" name="coder_id" value="${coder_id }" >
		    <table class="form_table">
				<tr>
					<td class="right"><span class="must">*</span>字段名称：</td>
					<td><input type="text" id="name" name="name" value="${model.name }" maxlength="32" placeholder="这里输入字段名称" title="字段名称" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>字段备注：</td>
					<td><input type="text" id="title" name="title" value="${model.title }" maxlength="32" placeholder="这里输入字段备注" title="字段备注" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>字段类型：</td>
					<td>
						<select id="java_type" name="java_type" class="select3">
							<option value="">请选择</option>
						    <c:forEach items="${D_FIELD_TYPE }" var="dict" varStatus="vs_dict">
								<option value="${dict.item_value}" <c:if test="${model.java_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
				   </td>
					<td class="right">字段长度：</td>
					<td><input type="text" id="data_length" name="data_length" value="${model.data_length }" maxlength="32" placeholder="这里输入字段长度" title="字段长度" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>主键：</td>
					<td>
						<div class="vocation">
						    <select id="is_key" name="is_key" class="select3">
							    <option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.is_key eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
					<td class="right"><span class="must">*</span>表单类型：</td>
					<td>
						<div class="vocation">
						    <select id="element_type" name="element_type" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_ELEMENT_TYPE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.element_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>查询条件：</td>
					<td>
						<div class="vocation">
						    <select id="is_search" name="is_search" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.is_search eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
					<td class="right"><span class="must">*</span>前台录入：</td>
					<td>
						<div class="vocation">
						    <select id="is_input" name="is_input" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.is_input eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>列表显示：</td>
					<td>
						<div class="vocation">
						    <select id="is_show" name="is_show" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.is_show eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
					<td class="right"><span class="must">*</span>开启排序：</td>
					<td>
						<div class="vocation">
						    <select id="is_sort" name="is_sort" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
									<option value="${dict.item_value}" <c:if test="${model.is_sort eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					</td>
				</tr>
				<tr>
					<td class="right">数据字典：</td>
					<td><input type="text" name="dict_code" id="dict_code" value="${model.dict_code }" maxlength="32" placeholder="数据字典" title="数据字典" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>排序：</td>
					<td><input type="number" name="sort" id="sort" value="${model.sort }" maxlength="32" placeholder="这里输入排序" title="排序" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="center" colspan="4">
			        	<a class="button button-primary button-rounded button-small" onclick="save();">保存</a>
			        	<a class="button button-action button-rounded button-small" onclick="parentDialog.close();">取消</a>
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
			add("${basePath}coder_attribute/add");
		} else {
			edit("${basePath}coder_attribute/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#name").val()) == "") {
			$("#name").tips("请输入字段名称").focus();
			return;
		}
		if ($.trim($("#title").val()) == "") {
			$("#title").tips("请输入字段备注").focus();
			return;
		}
		if ($.trim($("#java_type").val()) == "") {
			$("#java_type").tips("请选择字段类型").focus();
			return;
		}
		if ($.trim($("#is_key").val()) == "") {
			$("#is_key").tips("请选择主键").focus();
			return;
		}
		if ($.trim($("#element_type").val()) == "") {
			$("#element_type").tips("请选择表单类型").focus();
			return;
		}
		if ($.trim($("#is_search").val()) == "") {
			$("#is_search").tips("请选择查询条件").focus();
			return;
		}
		if ($.trim($("#is_input").val()) == "") {
			$("#is_input").tips("请选择前台录入").focus();
			return;
		}
		if ($.trim($("#is_show").val()) == "") {
			$("#is_show").tips("请选择列表显示").focus();
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

