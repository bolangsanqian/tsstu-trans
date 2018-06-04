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
					<td class="right"><span class="must">*</span>会员编号：</td>
					<td><input type="text" name="member_id" id="member_id" value="${model.member_id }" maxlength="32" placeholder="这里输入会员编号" title="会员编号" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>流水号：</td>
					<td><input type="text" name="flow_no" id="flow_no" value="${model.flow_no }" maxlength="32" placeholder="这里输入流水号" title="流水号" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>交易单号：</td>
					<td><input type="text" name="trans_no" id="trans_no" value="${model.trans_no }" maxlength="32" placeholder="这里输入交易单号" title="交易单号" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>类型：</td>
					<td>
						<div class="vocation">
						    <select id="change_type" name="change_type" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_MEMBER_FUND_FLOW_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.change_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>交易时间戳：</td>
					<td><input type="text" name="trans_timestamp" id="trans_timestamp" value="${model.trans_timestamp }" maxlength="32" placeholder="这里输入交易时间戳" title="交易时间戳" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>运营中心：</td>
					<td><input type="text" name="operation_member_id" id="operation_member_id" value="${model.operation_member_id }" maxlength="32" placeholder="这里输入运营中心" title="运营中心" style="width:98%;"/></td>
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
			add("${basePath}member_fund_flow/add");
		} else {
			edit("${basePath}member_fund_flow/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#member_id").val())=="" && $.trim($("#member_id").val()) == "") {
			$("#member_id").tips("请输入会员编号").focus();
			return;
		}
		if ($.trim($("#flow_no").val())=="" && $.trim($("#flow_no").val()) == "") {
			$("#flow_no").tips("请输入流水号").focus();
			return;
		}
		if ($.trim($("#trans_no").val())=="" && $.trim($("#trans_no").val()) == "") {
			$("#trans_no").tips("请输入交易单号").focus();
			return;
		}
		if ($.trim($("#change_type").val())=="" && $.trim($("#change_type").val()) == "") {
			$("#change_type").tips("请选择类型").focus();
			return;
		}
		if ($.trim($("#trans_timestamp").val())=="" && $.trim($("#trans_timestamp").val()) == "") {
			$("#trans_timestamp").tips("请输入交易时间戳").focus();
			return;
		}
		if ($.trim($("#operation_member_id").val())=="" && $.trim($("#operation_member_id").val()) == "") {
			$("#operation_member_id").tips("请输入运营中心").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

