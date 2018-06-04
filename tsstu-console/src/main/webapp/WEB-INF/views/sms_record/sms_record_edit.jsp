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
					<td class="right"><span class="must">*</span>短信类型：</td>
					<td>
						<div class="vocation">
						    <select id="sms_type" name="sms_type" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_SMS_SEND_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.sms_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
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
			add("${basePath}sms_record/add");
		} else {
			edit("${basePath}sms_record/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#sms_type").val())=="" && $.trim($("#sms_type").val()) == "") {
			$("#sms_type").tips("请选择短信类型").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

