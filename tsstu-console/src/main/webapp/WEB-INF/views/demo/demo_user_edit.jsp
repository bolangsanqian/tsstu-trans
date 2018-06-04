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
    	<form id="form1">
		    <table class="form_table">
		    	<input id="id" name="id" value="${demoUser.id }" 
				<tr>
					<td class="right"><span class="must">*</span>用户名：</td>
					<td><input type="text" name="username" id="username" value="${demoUser.username }" maxlength="32" placeholder="这里输入用户名" title="会员名称" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>用户名：</td>
					<td><input type="password" name="password" id="password" value="${demoUser.password }" maxlength="32" placeholder="这里输入密码" title="密码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>手机号码：</td>
					<td><input type="text" name="mobile" id="mobile"  value="${demoUser.mobile }"  maxlength="32" placeholder="这里输入手机号码" title="手机号码" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>邮箱：</td>
					<td><input type="text" name="email" id="email" value="${demoUser.email }" maxlength="32" placeholder="这里输入邮箱" title="邮箱" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>性别：</td>
					<td><input type="text" name="sex" id="sex"  value="${demoUser.sex }"  maxlength="32" placeholder="这里输入性别" title="性别" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>生日：</td>
					<td><input type="text" name="birthday" id="birthday" value="${demoUser.birthday }" maxlength="32" placeholder="这里输入生日" title="生日" style="width:98%;"/></td>
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
	function save() {
		if ($("#id").val()=="") {
			add();
		} else {
			edit();
		}
	}
	
	//输入验证
	function inputCheck() {
		
	}
	
	//添加
	function add() {
		var url = "${basePath }demo_user/add";
		alert(url)
		var data = $("#form1").serialize();
		$.ajax({
			url: url,
			data: data,
			type: "post",
			dataType: "json",
			success: function(json) {
				if (json.ok) {
					Dialog.alert("添加成功！", function() {
						window.parent.search();
					});
				}else {
					Dialog.alert(json.msg);
				}
				
			},
			complete: function() {
			}
		});
	}
	
	//修改
	function edit() {
		var url = "${basePath }demo_user/edit";
		var data = $("#form1").serialize();
		$.ajax({
			url: url,
			data: data,
			type: "post",
			dataType: "json",
			success: function(json) {
				if (json.ok) {
					Dialog.alert("修改成功！", function() {
						window.parent.search();
					});
				}else {
					Dialog.alert(json.msg);
				}
				
			},
			complete: function() {
			}
		});
	}
	
</script>
</body>
</html>

