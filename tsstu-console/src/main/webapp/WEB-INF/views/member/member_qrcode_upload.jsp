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
					<td class="right">名称：</td>
					<td>${model.name }</td>
				</tr>
				<tr style="height: 180px;">
					<td colspan="2" align="center">
						<div class="control-group js_uploadBox">
						    <div class="btn-upload">
						    	<a href="javascript:void(0);" class="upload-button" <c:if test="${not empty model.wx_qrcode_url }">style="display: none;"</c:if>>请选择公众号二维码图片
									<input type="file" name="wx_qrcode_url" id="wx_qrcode_url" value="${model.wx_qrcode_url }" title="请选择公众号二维码图片"/>
								</a>
						    </div>
						    <div class="js_showBox "><img class="js_logoBox" onerror="javascript:imgLoadError()" src="${empty model.wx_qrcode_url ? '' : model.wx_qrcode_url }" <c:if test="${empty model.wx_qrcode_url }">style="display: none;"</c:if> width="180px" height="180px"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="center" colspan="4">
			        	<a class="button button-primary button-rounded button-small" onclick="upload_wx_qrcode('${basePath }member/upload_wx_qrcode');">保存</a>
			        	<a class="button button-caution button-rounded button-small" onclick="del_wx_qrcode('${basePath }member/del_wx_qrcode?id=${model.id}');">删除</a>
			        	<a class="button button-action button-rounded button-small" onclick="parentDialog.close()">关闭</a>
					</td>
				</tr>
			</table>
		</form>
    </div>
<script type="text/javascript">

	function imgLoadError() {
		$('.js_logoBox').hide();
		$(".upload-button").show();
	}

	//图片预览
	$("#wx_qrcode_url").uploadView({
		uploadBox: '.js_uploadBox',//设置上传框容器
		showBox : '.js_showBox',//设置显示预览图片的容器
		width : 180, //预览图片的宽度，单位px
		height : 180, //预览图片的高度，单位px
		allowType: ["gif", "jpeg", "jpg", "bmp", "png"], //允许上传图片的类型
		maxSize :2, //允许上传图片的最大尺寸，单位M
		success:function(e){
			$(".upload-button").hide();
		}
	});
</script>
</body>
</html>

