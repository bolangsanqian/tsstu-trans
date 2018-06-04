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
			width : 240
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
					<td class="right"><span class="must">*</span>关键字：</td>
					<td><input type="text" name="keyword" id="keyword" value="${model.keyword }" maxlength="32" placeholder="这里输入关键字" title="关键字" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>匹配方式：</td>
					<td>
						<div class="vocation">
						    <select id="match_type" name="match_type" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_WXMSG_MATCH_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.match_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>消息类型：</td>
					<td>
						<div class="vocation">
						    <select id="msg_type" name="msg_type" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_WXMSG_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.msg_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
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
							    <c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>内容：</td>
					<td>
						<textarea rows="4" cols="" name="content" id="content" maxlength="32" placeholder="这里输入内容" title="输入内容" style="width:98%;">${model.content }</textarea>
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
			add("${basePath}wechat_msg/add");
		} else {
			edit("${basePath}wechat_msg/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#keyword").val())=="") {
			$("#keyword").tips("请输入关键字").focus();
			return;
		}
		if ($.trim($("#match_type").val())=="") {
			$("#match_type").tips("请选择匹配方式").focus();
			return;
		}
		if ($.trim($("#msg_type").val())=="") {
			$("#msg_type").tips("请选择消息类型").focus();
			return;
		}
		if ($.trim($("#content").val())=="") {
			$("#content").tips("请输入内容").focus();
			return;
		}
		if ($.trim($("#status").val())=="") {
			$("#status").tips("请选择状态(0：禁用，1：启用)").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

