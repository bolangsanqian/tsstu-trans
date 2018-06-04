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
			width : 189
		});
	});
	
</script>

</head>
<body>
    <div class="formbody">
    	<form id="form1">
		    <table class="form_table">
				<tr>
					<td><textarea rows="8" cols="4" name="review_remark" id="review_remark" maxlength="32" placeholder="这里输入审批备注" title="审批备注" style="width:98%;">${model.review_remark }</textarea></td>
				</tr>
				<tr>
					<td class="center" colspan="4">
			        	<a class="button button-primary button-rounded button-small" onclick="review('${bashPath}member_fund_change/review_pass', 1);">通过</a>
			        	<a class="button button-primary button-rounded button-small" onclick="review('${bashPath}member_fund_change/review_reject', 2);">驳回</a>
			        	<a class="button button-action button-rounded button-small" onclick="parentDialog.close()">取消</a>
					</td>
				</tr>
			</table>
		</form>
    </div>
<script type="text/javascript">
	// 审批（1：通过，2：驳回）
	function review(url, review_status) {
		var review_remark = $("#review_remark").val();
		if ($.trim(review_remark) == "") {
			review_remark = review_status == 2 ? "驳回！" : "通过！";
		}
		var msg = review_status == 2 ? "确定要驳回订单吗？" : "确定要审批通过订单吗？";
		Dialog.confirm(msg, function() {
			$.ajax({  
			    url: url,  
			    data: {
			    	ids: "${ids}",
			    	review_status: review_status,
			    	review_remark: review_remark
			    },
			    dataType: "json",  
			    type: "POST",  
			    traditional: true,  
			    success: function (json) {  
			    	if (json.ok) {
						Dialog.alert(json.msg, function() {
							$.isFunction(window.parent.search) && window.parent.search();
						});
					}else {
						Dialog.alert(json.msg);
					}
			    }  
			});  
		});
	}
</script>
</body>
</html>

