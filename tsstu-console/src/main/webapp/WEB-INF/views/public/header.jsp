<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
	request.setAttribute("time", new Date().getTime());
%>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${P_SYS_CONSOLE_NAME }</title>
<meta name="description" content="" />
<meta name="robots" content="nofollow">

<!-- 样式 -->
<link type="text/css" rel="stylesheet" href="static/css/style.css?t=<%= new Date().getTime()%>"/>
<link type="text/css" rel="stylesheet" href="static/css/buttons.css?t=<%= new Date().getTime()%>"/>

<!-- jquery -->
<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>   

<!-- 下拉框插件 -->
<link type="text/css" rel="stylesheet" href="static/css/select.css?t=<%= new Date().getTime()%>"/>
<script type="text/javascript" src="static/js/select-ui.min.js?t=<%= new Date().getTime()%>"></script>

<!-- 弹窗插件 -->
<script type="text/javascript" src="static/plugins/zDialog/zDialog.js?t=<%= new Date().getTime()%>"></script>
<script type="text/javascript" src="static/plugins/zDialog/zDrag.js"></script>

<!-- 验证提示 -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>

<!-- 二维码插件 -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>

<!-- 日期范围选择插件 -->
<link rel="stylesheet" href="static/plugins/jquery-date-range-picker-master/dist/daterangepicker.min.css" />
<script src="static/plugins/jquery-date-range-picker-master/dist/moment.min.js" type="text/javascript"></script>
<script src="static/plugins/jquery-date-range-picker-master/dist/jquery.daterangepicker.min.js"></script>

<!-- 公共方法 -->
<script type="text/javascript" src="static/js/common.js?t=<%= new Date().getTime()%>"></script>

