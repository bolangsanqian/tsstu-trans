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
					<td class="right"><span class="must">*</span>真实姓名：</td>
					<td><input type="text" name="real_name" id="real_name" value="${model.real_name }" maxlength="32" placeholder="这里输入真实姓名" title="真实姓名" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>身份证：</td>
					<td><input type="text" name="identity" id="identity" value="${model.identity }" maxlength="32" placeholder="这里输入身份证" title="身份证" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>银行编号：</td>
					<td>
						<div class="vocation">
						    <select id="bank_no" name="bank_no" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${DICT_CONTRACT_BANK }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.bank_no eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>银行卡号：</td>
					<td><input type="text" name="bankcard_no" id="bankcard_no" value="${model.bankcard_no }" maxlength="32" placeholder="这里输入银行卡号" title="银行卡号" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>省份：</td>
					<td>
						<div class="vocation">
						    <select id="province_code" name="province_code" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${provRegionList }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.province_code eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>城市：</td>
					<td>
						<div class="vocation">
						    <select id="city_code" name="city_code" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${cityRegionList }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.city_code eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>银行支行：</td>
					<td><input type="text" name="bank_branch" id="bank_branch" value="${model.bank_branch }" maxlength="32" placeholder="这里输入银行支行" title="银行支行" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>状态：</td>
					<td>
						<div class="vocation">
						    <select id="status" name="status" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_CONTRACT_BANK_STATUS }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>审批备注：</td>
					<td><input type="text" name="review_remark" id="review_remark" value="${model.review_remark }" maxlength="32" placeholder="这里输入审批备注" title="审批备注" style="width:98%;"/></td>
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
			add("${basePath}member_contract_bank/add");
		} else {
			edit("${basePath}member_contract_bank/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#member_id").val())=="" && $.trim($("#member_id").val()) == "") {
			$("#member_id").tips("请输入会员编号").focus();
			return;
		}
		if ($.trim($("#real_name").val())=="" && $.trim($("#real_name").val()) == "") {
			$("#real_name").tips("请输入真实姓名").focus();
			return;
		}
		if ($.trim($("#identity").val())=="" && $.trim($("#identity").val()) == "") {
			$("#identity").tips("请输入身份证").focus();
			return;
		}
		if ($.trim($("#bank_no").val())=="" && $.trim($("#bank_no").val()) == "") {
			$("#bank_no").tips("请选择银行编号").focus();
			return;
		}
		if ($.trim($("#bankcard_no").val())=="" && $.trim($("#bankcard_no").val()) == "") {
			$("#bankcard_no").tips("请输入银行卡号").focus();
			return;
		}
		if ($.trim($("#province_code").val())=="" && $.trim($("#province_code").val()) == "") {
			$("#province_code").tips("请选择省份").focus();
			return;
		}
		if ($.trim($("#city_code").val())=="" && $.trim($("#city_code").val()) == "") {
			$("#city_code").tips("请选择城市").focus();
			return;
		}
		if ($.trim($("#bank_branch").val())=="" && $.trim($("#bank_branch").val()) == "") {
			$("#bank_branch").tips("请输入银行支行").focus();
			return;
		}
		if ($.trim($("#status").val())=="" && $.trim($("#status").val()) == "") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		if ($.trim($("#review_remark").val())=="" && $.trim($("#review_remark").val()) == "") {
			$("#review_remark").tips("请输入审批备注").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

