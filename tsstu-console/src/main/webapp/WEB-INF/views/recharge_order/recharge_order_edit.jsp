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
					<td class="right"><span class="must">*</span>客户id：</td>
					<td><input type="text" name="customer_id" id="customer_id" value="${model.customer_id }" maxlength="32" placeholder="这里输入客户id" title="客户id" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>充值流水号：</td>
					<td><input type="text" name="flow_no" id="flow_no" value="${model.flow_no }" maxlength="32" placeholder="这里输入充值流水号" title="充值流水号" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>支付方式：</td>
					<td>
						<div class="vocation">
						    <select id="payment_method" name="payment_method" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_CUST_PAYMENT_METHOD }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.payment_method eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
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
							    <c:forEach items="${D_RECHARGE_STATUS }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>支付单号：</td>
					<td><input type="text" name="pay_sn" id="pay_sn" value="${model.pay_sn }" maxlength="32" placeholder="这里输入支付单号" title="支付单号" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>备注：</td>
					<td><input type="text" name="remark" id="remark" value="${model.remark }" maxlength="32" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>支付IP地址：</td>
					<td><input type="text" name="pay_ip" id="pay_ip" value="${model.pay_ip }" maxlength="32" placeholder="这里输入支付IP地址" title="支付IP地址" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>运营中心：</td>
					<td><input type="text" name="agency_member_id" id="agency_member_id" value="${model.agency_member_id }" maxlength="32" placeholder="这里输入运营中心" title="运营中心" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>所属微会员：</td>
					<td><input type="text" name="member_id" id="member_id" value="${model.member_id }" maxlength="32" placeholder="这里输入所属微会员" title="所属微会员" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>代理会员：</td>
					<td><input type="text" name="agent_member_id" id="agent_member_id" value="${model.agent_member_id }" maxlength="32" placeholder="这里输入代理会员" title="代理会员" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>充值时间戳：</td>
					<td><input type="text" name="recharge_timestamp" id="recharge_timestamp" value="${model.recharge_timestamp }" maxlength="32" placeholder="这里输入充值时间戳" title="充值时间戳" style="width:98%;"/></td>
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
			add("${basePath}recharge_order/add");
		} else {
			edit("${basePath}recharge_order/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#customer_id").val())=="" && $.trim($("#customer_id").val()) == "") {
			$("#customer_id").tips("请输入客户id").focus();
			return;
		}
		if ($.trim($("#flow_no").val())=="" && $.trim($("#flow_no").val()) == "") {
			$("#flow_no").tips("请输入充值流水号").focus();
			return;
		}
		if ($.trim($("#payment_method").val())=="" && $.trim($("#payment_method").val()) == "") {
			$("#payment_method").tips("请选择支付方式").focus();
			return;
		}
		if ($.trim($("#status").val())=="" && $.trim($("#status").val()) == "") {
			$("#status").tips("请选择状态").focus();
			return;
		}
		if ($.trim($("#pay_sn").val())=="" && $.trim($("#pay_sn").val()) == "") {
			$("#pay_sn").tips("请输入支付单号").focus();
			return;
		}
		if ($.trim($("#remark").val())=="" && $.trim($("#remark").val()) == "") {
			$("#remark").tips("请输入备注").focus();
			return;
		}
		if ($.trim($("#pay_ip").val())=="" && $.trim($("#pay_ip").val()) == "") {
			$("#pay_ip").tips("请输入支付IP地址").focus();
			return;
		}
		if ($.trim($("#agency_member_id").val())=="" && $.trim($("#agency_member_id").val()) == "") {
			$("#agency_member_id").tips("请输入运营中心").focus();
			return;
		}
		if ($.trim($("#member_id").val())=="" && $.trim($("#member_id").val()) == "") {
			$("#member_id").tips("请输入所属微会员").focus();
			return;
		}
		if ($.trim($("#agent_member_id").val())=="" && $.trim($("#agent_member_id").val()) == "") {
			$("#agent_member_id").tips("请输入代理会员").focus();
			return;
		}
		if ($.trim($("#recharge_timestamp").val())=="" && $.trim($("#recharge_timestamp").val()) == "") {
			$("#recharge_timestamp").tips("请输入充值时间戳").focus();
			return;
		}
		return true;
	}
	
</script>
</body>
</html>

