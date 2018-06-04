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
			width : 221
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
					<td class="right"><span class="must">*</span>客户id：</td>
					<td><input type="text" name="customer_id" id="customer_id" value="${model.customer_id }" maxlength="32" placeholder="这里输入客户id" title="客户id" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>用户名：</td>
					<td><input type="text" name="username" id="username" value="${model.username }" maxlength="32" placeholder="这里输入用户名" title="用户名" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>密码：</td>
					<td><input type="text" name="password" id="password" value="${model.password }" maxlength="32" placeholder="这里输入密码" title="密码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>运营中心：</td>
					<td>
						<div class="vocation">
						    <select id="operation_member_id" name="operation_member_id" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${DICT_OPERATION }" var="item" varStatus="item_status">
									<option value="${item.item_value}" <c:if test="${model.operation_member_id eq item.item_value}">selected</c:if>>${item.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>所属会员：</td>
					<td>
						<div class="vocation">
						    <select id="member_id" name="member_id" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${DICT_COMMON_MEMBER }" var="item" varStatus="item_status">
									<option value="${item.item_value}" <c:if test="${model.member_id eq item.item_value}">selected</c:if>>${item.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>代理会员：</td>
					<td>
						<div class="vocation">
						    <select id="agent_member_id" name="agent_member_id" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${DICT_AGENT_MEMBER }" var="item" varStatus="item_status">
									<option value="${item.item_value}" <c:if test="${model.agent_member_id eq item.item_value}">selected</c:if>>${item.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>审核人：</td>
					<td><input type="text" name="review_by" id="review_by" value="${model.review_by }" maxlength="32" placeholder="这里输入审核人" title="审核人" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>审批状态 ：</td>
					<td>
						<div class="vocation">
						    <select id="review_status" name="review_status" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_REVIEW_STATUS }" var="item" varStatus="item_status">
									<option value="${item.item_value}" <c:if test="${model.review_status eq item.item_value}">selected</c:if>>${item.item_name}</option>
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
			add("${basePath}broker_apply/add");
		} else {
			edit("${basePath}broker_apply/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#customer_id").val())=="") {
			$("#customer_id").tips("请输入客户id").focus();
			return;
		}
		if ($.trim($("#username").val())=="") {
			$("#username").tips("请输入用户名").focus();
			return;
		}
		if ($.trim($("#password").val())=="") {
			$("#password").tips("请输入密码").focus();
			return;
		}
		if ($.trim($("#operation_member_id").val())=="") {
			$("#operation_member_id").tips("请选择运营中心").focus();
			return;
		}
		if ($.trim($("#member_id").val())=="") {
			$("#member_id").tips("请选择所属会员").focus();
			return;
		}
		if ($.trim($("#agent_member_id").val())=="") {
			$("#agent_member_id").tips("请选择代理会员").focus();
			return;
		}
		if ($.trim($("#review_by").val())=="") {
			$("#review_by").tips("请输入审核人").focus();
			return;
		}
		if ($.trim($("#review_status").val())=="") {
			$("#review_status").tips("请选择审批状态 ").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

