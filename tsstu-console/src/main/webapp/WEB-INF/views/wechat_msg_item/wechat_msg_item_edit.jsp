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
			width : 261
		});
	});
	
</script>

</head>
<body>
    <div class="formbody">
    	<form id="form1">
	    	<input type="hidden" id="id" name="id" value="${model.id }" >
	    	<input type="hidden" id="wechat_msg_id" name="wechat_msg_id" value="${empty model.wechat_msg_id ? wechat_msg_id : model.wechat_msg_id }" >
		    <table class="form_table">
				<tr>
					<td class="right"><span class="must">*</span>标题：</td>
					<td><input type="text" name="title" id="title" value="${model.title }" maxlength="32" placeholder="这里输入标题" title="标题" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>图片地址：</td>
					<td><input type="text" name="image_url" id="image_url" value="${model.image_url }" maxlength="32" placeholder="这里输入图片地址" title="图片地址" style="width:98%;"/></td>
				</tr>
				<tr>
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
					<td class="right"><span class="must">*</span>描述：</td>
					<td>
						<textarea rows="4" cols="" name="description" id="description" maxlength="32" placeholder="这里输入内容" title="输入内容" style="width:98%;">${model.description }</textarea>
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
			add("${basePath}wechat_msg_item/add");
		} else {
			edit("${basePath}wechat_msg_item/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#title").val())=="") {
			$("#title").tips("请输入标题").focus();
			return;
		}
		if ($.trim($("#description").val())=="") {
			$("#description").tips("请输入描述").focus();
			return;
		}
		if ($.trim($("#image_url").val())=="") {
			$("#image_url").tips("请输入图片地址").focus();
			return;
		}
		if ($.trim($("#url").val())=="") {
			$("#url").tips("请输入跳转地址").focus();
			return;
		}
		if ($.trim($("#status").val())=="") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		if ($.trim($("#sort").val())=="") {
			$("#sort").tips("请输入排序").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

