<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../public/header.jsp"%>
<link type="text/css" rel="stylesheet" href="static/plugins/error/css/error.css"/>
</script> 
</head>
<body>
	<!-- Main Wrapper. Set this to 'fixed' for fixed layout and 'fluid' for fluid layout' -->
	<div id="da-wrapper" class="fluid">
    
        <!-- Content -->
        <div id="da-content">
            
            <!-- Container -->
            <div class="da-container clearfix">
            
            	<div id="da-error-wrapper">
                	
                   	<div id="da-error-pin"></div>
                    <div id="da-error-code">
                    	error <span>${errorCode }</span>                    
                    </div>
                	<h1 class="da-error-heading">${errorText }，如有疑问，请联系管理员，谢谢！</h1>
                    <p><a href="index">点击进入首页</a></p>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <div id="da-footer">
        	<div class="da-container clearfix">
           	<p> 2017 tsstu. All Rights Reserved. <a href="http://www.tsstu.com/" target="_blank">猎云科技</a></div>
        </div>
    </div>
    
</body>
</html>