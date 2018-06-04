<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../public/header.jsp"%>
<link rel="stylesheet" href="static/css/theme/${parameter.CONFIG_SYS_THEME}/public/error.css" />
</head>

<body>
<div id="nav">
<span class="biaoti">${empty errTitle ? '温馨提示' : errTitle}</span>
<span class="zi011"></span></div>
<div class="tiao">
	<img src="static/image/cuowu.png">
    <span class="zi01">${msg}</span> 
    <span class="bt" onclick="toUrl('${empty backUrl ? '' : backUrl }')">${empty btnText ? '返回' : btnText}</span>
</div>
</div>

</html>