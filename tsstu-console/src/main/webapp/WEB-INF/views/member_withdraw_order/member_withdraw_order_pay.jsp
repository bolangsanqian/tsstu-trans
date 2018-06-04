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
		    <table class="form_table">
				<tr>
		    		<td class="right">付款方式：</td>
					<td>
						<div class="vocation">
						    <select id="pay_method" name="pay_method" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_MEMBER_WITHDRAW_PAY_METHOD }" var="item" varStatus="status">
									<option value="${item.item_value}" <c:if test="${model.member_id eq item.item_value}">selected</c:if>>${item.item_value} - ${item.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
		    	</tr>
				<tr>
					<td class="right"><span class="must">*</span>付款备注：</td>
					<td><textarea rows="8" cols="4" name="pay_remark" id="pay_remark" maxlength="32" placeholder="这里输入付款备注" title="审批备注" style="width:98%;">${model.pay_remark }</textarea></td>
				</tr>
				<tr>
					<td class="center" colspan="4">
			        	<a class="button button-primary button-rounded button-small" onclick="pay_pass('${bashPath}member_withdraw_order/pay');">付款</a>
			        	<a class="button button-primary button-rounded button-small" onclick="pay_reject('${bashPath}member_withdraw_order/pay');">驳回</a>
			        	<a class="button button-action button-rounded button-small" onclick="parentDialog.close()">取消</a>
					</td>
				</tr>
			</table>
		</form>
    </div>
<script type="text/javascript">
	// 付款通过
	function pay_pass(url) {
		if ($.trim($("#pay_method").val())=="") {
			$("#pay_method").tips("请选择付款方式").focus();
			return;
		}
		pay(url, 1, "确定要付款吗？");
		
	}
	
	// 付款驳回
	function pay_reject(url) {
		pay(url, 2, "确定要驳回订单吗？");
	}
	
	// 审批（1：通过，2：驳回）
	function pay(url, status, msg) {
		var pay_remark = $("#pay_remark").val();
		if ($.trim(pay_remark) === "") {
			$("#pay_remark").tips("请输入付款备注").focus();
			return;
		}
		Dialog.confirm(msg, function() {
			$.ajax({  
			    url: url,  
			    data: {
			    	ids: "${ids}",
			    	status: status,
			    	pay_remark: pay_remark,
			    	pay_method: $.trim($("#pay_method").val())
			    },
			    dataType: "json",  
			    type: "POST",  
			    traditional: true,  
			    success: function (json) {  
					Dialog.alert(json.msg, function() {
						$.isFunction(window.parent.search) && window.parent.search();
					});
			    }  
			});  
		});
	}
</script>
</body>
</html>

