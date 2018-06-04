<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
<!-- 百度富文本编辑框-->
<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "${bashPath}static/plugins/ueditor/";</script>
<script type="text/javascript" charset="utf-8" src="static/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="static/plugins/ueditor/ueditor.all.js"></script>
<!-- 百度富文本编辑框-->
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
		
		//加载百度富文本
		UE.getEditor('content');	
	});
	
</script>

</head>
<body>
    <div class="formbody">
    	<form id="form1">
	    	<input type="hidden" id="id" name="id" value="${model.id }" >
		    <table class="form_table">
				<tr>
					<td class="right"><span class="must">*</span>标题：</td>
					<td><input type="text" name="title" id="title" value="${model.title }" maxlength="32" placeholder="这里输入标题" title="标题" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>内容：</td>
					<td>
						<textarea name="content" id="content" style="width: 85%; height: 300px;">${model.content }</textarea>  
					</td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>分组：</td>
					<td>
						<div class="vocation">
						    <select id="article_group" name="article_group" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_ARTICEL_GROUP }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.article_group eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>状态：</td>
					<td>
						<div class="vocation">
						    <select id="status" name="status" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="dict_status">
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
			add("${basePath}article/add");
		} else {
			edit("${basePath}article/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#title").val())=="" && $.trim($("#title").val()) == "") {
			$("#title").tips("请输入标题").focus();
			return;
		}
		if ($.trim($("#content").val())=="" && $.trim($("#content").val()) == "") {
			$("#content").tips("请输入内容").focus();
			return;
		}
		if ($.trim($("#article_group").val())=="" && $.trim($("#article_group").val()) == "") {
			$("#article_group").tips("请选择分组").focus();
			return;
		}
		if ($.trim($("#status").val())=="" && $.trim($("#status").val()) == "") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		if ($.trim($("#sort").val())=="" && $.trim($("#sort").val()) == "") {
			$("#sort").tips("请输入排序").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

