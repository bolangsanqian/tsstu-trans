<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
<style type="text/css">
	.form_table td.right {
		width: 130px;
	}
</style>
<script type="text/javascript">
	$(function($){
		//初始化下拉框
		$(".select3").uedSelect({
			width : 197
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
					<td class="right"><span class="must">*</span>会员名称：</td>
					<td><input type="text" name="name" id="name" value="${model.name }" maxlength="32" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>公司名字：</td>
					<td><input type="text" name="company_name" id="company_name" value="${model.company_name }" maxlength="32" placeholder="这里输入公司名字" title="公司名字" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>公司领导：</td>
					<td><input type="text" name="company_leader" id="company_leader" value="${model.company_leader }" maxlength="32" placeholder="这里输入公司领导" title="公司领导" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>手机号码：</td>
					<td><input type="text" name="mobile" id="mobile" value="${model.mobile }" maxlength="32" placeholder="这里输入手机号码" title="手机号码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>证件类型：</td>
					<td>
						<div class="vocation">
						    <select id="identity_type" name="identity_type" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${MEMBER_IDENTITY_TYPE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.identity_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
					<td class="right"><span class="must">*</span>证件号：</td>
					<td><input type="text" name="identity" id="identity" value="${model.identity }" maxlength="32" placeholder="这里输入证件号" title="证件号" style="width:98%;"/></td>
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
					<td class="right"><span class="must">*</span>默认会员：</td>
					<td>
						<div class="vocation">
						    <select id="is_default" name="is_default" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.is_default eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>运营中心：</td>
					<td>
						<div class="vocation">
						    <select id="operation_member_id" name="operation_member_id" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${DICT_OPERATION }" var="item" varStatus="status">
									<option value="${item.item_value}" <c:if test="${model.operation_member_id eq item.item_value}">selected</c:if>>${item.item_value} - ${item.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
					<td class="right"><span class="must">*</span>客户激活金额：</td>
					<td><input type="number" name="customer_active_amount" id="customer_active_amount" value="${model.customer_active_amount }" maxlength="32" placeholder="这里输入客户激活金额" title="客户激活金额" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>交易所佣金：</td>
					<td><input type="number" name="exchange_commission" id="exchange_commission" value="${model.exchange_commission }" maxlength="32" placeholder="这里输入交易所佣金" title="交易所佣金" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>运营中心佣金：</td>
					<td><input type="number" name="operation_commission" id="operation_commission" value="${model.operation_commission }" maxlength="32" placeholder="这里输入运营中心佣金" title="运营中心佣金" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>会员佣金：</td>
					<td><input type="number" name="member_commission" id="member_commission" value="${model.member_commission }" maxlength="32" placeholder="这里输入会员佣金" title="会员佣金" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>直接佣金：</td>
					<td><input type="number" name="direct_commission" id="direct_commission" value="${model.direct_commission }" maxlength="32" placeholder="这里输入经纪人直接佣金" title="经纪人直接佣金" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>间接佣金：</td>
					<td><input type="number" name="indirect_commission" id="indirect_commission" value="${model.indirect_commission }" maxlength="32" placeholder="这里输入经济人间接佣金" title="经济人间接佣金" style="width:98%;"/></td>
					<td class="right"><span class="must">*</span>保证金：</td>
					<td><input type="number" name="direct_b_commission" id="direct_b_commission" value="${model.direct_b_commission }" maxlength="32" placeholder="这里输入经纪人直接分红" title="经纪人直接分红" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>信用额度：</td>
					<td><input type="number" name="indirect_b_commission" id="indirect_b_commission" value="${model.indirect_b_commission }" maxlength="32" placeholder="这里输入经纪人间接分红" title="经纪人间接分红" style="width:98%;"/></td>
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
	//保存
	function save() {
		if (!inputCheck()) {
			return false;
		}
		if ($.trim($("#id").val())=="") {
			add("${basePath}member/add");
		} else {
			edit("${basePath}member/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#name").val())=="") {
			$("#name").tips("请输入名称").focus();
			return;
		}
		if ($.trim($("#company_name").val())=="") {
			$("#company_name").tips("请输入公司名字").focus();
			return;
		}
		if ($.trim($("#company_leader").val())=="") {
			$("#company_leader").tips("请输入公司领导").focus();
			return;
		}
		if ($.trim($("#mobile").val())=="") {
			$("#mobile").tips("请输入手机号码").focus();
			return;
		}
		if ($.trim($("#identity_type").val())=="") {
			$("#identity_type").tips("请选择证件类型").focus();
			return;
		}
		if ($.trim($("#identity").val())=="") {
			$("#identity").tips("请输入证件号").focus();
			return;
		}
		if ($.trim($("#status").val())=="") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		if ($.trim($("#is_default").val())=="") {
			$("#is_default").tips("请选择是否为默认会员").focus();
			return;
		}
		if ($.trim($("#operation_member_id").val())=="") {
			$("#operation_member_id").tips("请选择运营中心").focus();
			return;
		}
		if ($.trim($("#customer_active_amount").val())=="") {
			$("#customer_active_amount").tips("请输入客户激活金额").focus();
			return;
		}
		if ($.trim($("#exchange_commission").val())=="") {
			$("#exchange_commission").tips("请输入交易所佣金").focus();
			return;
		}
		if ($.trim($("#operation_commission").val())=="") {
			$("#operation_commission").tips("请输入运营中心佣金").focus();
			return;
		}
		if ($.trim($("#member_commission").val())=="") {
			$("#member_commission").tips("请输入会员佣金").focus();
			return;
		}
		if ($.trim($("#direct_commission").val())=="") {
			$("#direct_commission").tips("请输入经纪人直接佣金").focus();
			return;
		}
		if ($.trim($("#indirect_commission").val())=="") {
			$("#indirect_commission").tips("请输入经济人间接佣金").focus();
			return;
		}
		if ($.trim($("#direct_b_commission").val())=="") {
			$("#direct_b_commission").tips("请输入经纪人直接分红").focus();
			return;
		}
		if ($.trim($("#indirect_b_commission").val())=="") {
			$("#indirect_b_commission").tips("请输入经纪人间接分红").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

