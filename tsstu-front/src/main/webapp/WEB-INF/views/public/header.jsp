<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
	request.setAttribute("time", new Date().getTime());
%>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="format-detection" content="telephone=no">
<meta name="robots" content="nofollow">
<!-- <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width"> -->
<!-- <meta name ="viewport" content ="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"> -->
<meta name="viewport" id="viewport" content="width=750, user-scalable=no, minimal-ui">

<title>猎云商城</title>
<!-- 样式 -->
<link type="text/css" rel="stylesheet" href="static/css/common.css?t=${time }"/>
<link type="text/css" rel="stylesheet" href="static/css/style.css?t=${time }"/>
<link type="text/css" rel="stylesheet" href="static/themes/${theme }/css/style.css?t=${time }"/>
<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
<link type="text/css" rel="stylesheet" href="static/plugins/layer.mobile-v2.0/need/layer.css?t=11${time }"/>
<script type="text/javascript" src="static/plugins/layer.mobile-v2.0/layer.js"></script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="static/js/jquery.qrcode.min.js"></script>   
<script type="text/javascript" src="static/js/common.js?t=${time }"></script>

