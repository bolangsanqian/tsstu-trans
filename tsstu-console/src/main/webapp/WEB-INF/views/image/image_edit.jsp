<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
<link type="text/css" rel="stylesheet" href="static/css/upload_button.css?t=<%= new Date().getTime()%>"/>
<script type="text/javascript" src="static/js/jquery.uploadView.js"></script>
<script type="text/javascript" src="static/js/jquery-form.js"></script>
<style type="text/css">
	.form_table td.right {
		width: 100px;
	}
</style>
<script type="text/javascript">
	$(function($){
		//初始化下拉框
		$(".select3").uedSelect({
			width : 240
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
					<td class="right"><span class="must">*</span>说明：</td>
					<td><input type="text" name="title" id="title" value="${model.title }" maxlength="32" placeholder="这里输入说明" title="说明" style="width:98%;"/></td>
				</tr>
				<tr style="height: 120px;">
					<td class="right"><span class="must">*</span>图片：</td>
					<td class="center">
						<div class="control-group js_uploadBox">
						    <div class="btn-upload">
						    	<a id="image_url_a" href="javascript:void(0);" class="upload-button" <c:if test="${not empty model.image_url }">style="display: none;"</c:if>>请选择一张图片
									<input type="file" name="img" id="image_url" value="${model.image_url }" title="请选择一张图片"/>
								</a>
						    </div>
						    <div class="js_showBox "><img class="js_logoBox" onerror="javascript:imgLoadError()" src="${empty model.image_url ? '' : model.image_url }" <c:if test="${empty model.image_url }">style="display: none;"</c:if> width="100px" height="100px"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>图片分组：</td>
					<td>
						<div class="vocation">
						    <select id="image_group" name="image_group" class="select3">
						    	<option value="">请选择</option>
							    <c:forEach items="${D_IMAGE_GROUP }" var="dict" varStatus="dict_status">
									<option value="${dict.item_value}" <c:if test="${model.image_group eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
								</c:forEach>
						    </select>
					    </div>
					 </td>
				</tr>
				<tr>
					<td class="right"><span class="must">*</span>排序：</td>
					<td><input type="number" name="sort" id="sort" value="${model.sort }" maxlength="32" placeholder="这里输入排序" title="排序" style="width:98%;"/></td>
				</tr>
				<tr>
					<td class="center" colspan="4">
			        	<a class="button button-primary button-rounded button-small" onclick="save();">保存</a>
			        	<a class="button button-primary button-rounded button-small" onclick="imgLoadError();">删除图片</a>
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
			upload_image("${basePath}image/add");
		} else {
			upload_image("${basePath}image/edit");
		}
	}
	
	//输入验证
	function inputCheck() {
		if ($.trim($("#title").val())=="") {
			$("#title").tips("请输入说明").focus();
			return;
		}
		if ($.trim($("#image_url").val())=="") {
			$("#image_url_a").tips("请输入图片地址").focus();
			return;
		}
		if ($.trim($("#image_group").val())=="") {
			$("#image_group").tips("请选择图片分组").focus();
			return;
		}
		if ($.trim($("#sort").val())=="") {
			$("#sort").tips("请输入排序").focus();
			return;
		}
		return true;
	}
	
	function imgLoadError() {
		$('.js_logoBox').hide();
		$(".upload-button").show();
	}

	//图片预览
	$("#image_url").uploadView({
		uploadBox: '.js_uploadBox',//设置上传框容器
		showBox : '.js_showBox',//设置显示预览图片的容器
		width : 100, //预览图片的宽度，单位px
		height : 100, //预览图片的高度，单位px
		allowType: ["gif", "jpeg", "jpg", "bmp", "png"], //允许上传图片的类型
		maxSize :2, //允许上传图片的最大尺寸，单位M
		success:function(e){
			$(".upload-button").hide();
		}
	});
	
	/**
	 * 上传轮播图片
	 * @param url 请求地址
	 */
	function upload_image(url) {
		var data = $("#form1").serialize();
		$("#form1").ajaxSubmit({  
	        type: 'post',  
	        url: url,
	        data: data,
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",   
			success: function(json) {
				Dialog.alert(json.msg, function() {
					$.isFunction(window.parent.search) && window.parent.search();
				});
			},
			complete: function() {
			}
		});
	}
	
</script>
</body>
</html>

