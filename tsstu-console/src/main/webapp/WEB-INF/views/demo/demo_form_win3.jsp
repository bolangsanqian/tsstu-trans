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
			width : 272
		});
	});
	
</script>

</head>
<body>
    <div class="formbody">
	    <table class="form_table">
			<tr>
				<td class="right"><span class="must">*</span>会员名称：</td>
				<td><input type="text" name="name" id="name" value="${member.name }" maxlength="32" placeholder="这里输入会员名称" title="会员名称" style="width:98%;"/></td>
			</tr>
			<tr>
				<td class="right"><span class="must">*</span>负责人：</td>
				<td><input type="text" name="company_leader" id="company_leader"  value="${member.company_leader }"  maxlength="32" placeholder="这里输入负责人" title="负责人" style="width:98%;"/></td>
			</tr>
			<tr>
				<td class="right"><span class="must">*</span>会员名称：</td>
				<td><input type="text" name="name" id="name" value="${member.name }" maxlength="32" placeholder="这里输入会员名称" title="会员名称" style="width:98%;"/></td>
			</tr>
			<tr>
				<td class="right"><span class="must">*</span>负责人：</td>
				<td><input type="text" name="company_leader" id="company_leader"  value="${member.company_leader }"  maxlength="32" placeholder="这里输入负责人" title="负责人" style="width:98%;"/></td>
			</tr>
			<tr>
				<td class="right"><span class="must">*</span>会员名称：</td>
				<td><input type="text" name="name" id="name" value="${member.name }" maxlength="32" placeholder="这里输入会员名称" title="会员名称" style="width:98%;"/></td>
			</tr>
			<tr>
				<td class="right"><span class="must">*</span>负责人：</td>
				<td><input type="text" name="company_leader" id="company_leader"  value="${member.company_leader }"  maxlength="32" placeholder="这里输入负责人" title="负责人" style="width:98%;"/></td>
			</tr><tr>
				<td class="right"><span class="must">*</span>会员名称：</td>
				<td><input type="text" name="name" id="name" value="${member.name }" maxlength="32" placeholder="这里输入会员名称" title="会员名称" style="width:98%;"/></td>
			</tr>
			<tr>
				<td class="right"><span class="must">*</span>负责人：</td>
				<td><input type="text" name="company_leader" id="company_leader"  value="${member.company_leader }"  maxlength="32" placeholder="这里输入负责人" title="负责人" style="width:98%;"/></td>
			</tr>
			<tr>
				<td class="right"><span class="must">*</span>证件类型：</td>
				<td>
					<div class="vocation">
					    <select class="select3">
						    <option>全部</option>
						    <option>其他</option>
						    <option>全部</option>
						    <option>其他</option>
						    <option>全部</option>
						    <option>其他</option>
						    <option>全部</option>
						    <option>其他</option>
						    <option>全部</option>
						    <option>其他</option>
					    </select>
				    </div>
				</td>
			</tr>
			<tr>
				<td class="center" colspan="4">
		        	<a class="button button-primary button-rounded button-small">保存</a>
		        	<a class="button button-action button-rounded button-small" onclick="parentDialog.close()">取消</a>
				</td>
			</tr>
		</table>
    </div>
</body>
</html>

