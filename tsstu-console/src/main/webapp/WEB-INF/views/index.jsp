<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("time", new Date().getTime());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="static/css/main_new.css?v=1">
<title>${P_SYS_CONSOLE_NAME }</title>
</head>
<body>
	<div id="hd" class="clearfix">
		<div class="topleft">
	    	<a href="main_new.html"><img src="static/images/logo.png" title="系统首页" /></a>
	    </div>
	        
	    <ul class="nav">
		    <li attr-src="default.html"><a href="javascript:;" class="selected"><img src="static/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
		    <li attr-src="imgtable.html"><a href="javascript:;"><img src="static/images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
		    <li attr-src="imglist.html"><a href="javascript:;"><img src="static/images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
		    <li attr-src="tools.html"><a href="javascript:;"><img src="static/images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
		    <li style="display: none;" attr-src="computer.html"><a href="javascript:;"><img src="static/images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
		    <li style="display: none;" attr-src="tab.html"><a href="javascript:;"><img src="static/images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
	    </ul>
	            
	    <div class="topright">    
		    <ul>
			    <li><span><img src="static/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
			    <li><a href="javascript:;" class="abult">关于</a></li>
			    <li><a href="logout" target="_parent">退出</a></li>
		    </ul>
	     
		    <div class="user">
			    <span>${userInfo.username }</span>
			    <i class="downarrow"></i>
			    <i>消息</i>
			    <b>5</b>
		    </div> 
		</div>
	</div>


	<div id="bd" class="clearfix">
		<div class="leftMenu">
			<a href="javascript:;" class="fixedLeft" title="取消固定"><i></i></a>
			<div class="lefttop"><span></span>导航菜单</div>
			<iframe src="layout/left" width="100%" height="100%" frameborder="0"></iframe>
		</div>
		<div class="content-wrap">
			<div class="nav-tabs-wrap clearfix">
				 <ul class="nav-tabs"></ul>
				 <a href="javascript:;" title="更多操作" class="more-btn"></a>
				<a href="javascript:;" class="hideHead" title="收起头部"></a>
			</div>
			<div class="content-pages">
			</div>
		</div>
		<div class="fiexdMouseover"></div>
	</div>

	<div id="ft" class="footer clearfix">
		<span>仅供学习交流，请勿用于任何商业用途</span>
    	<i>版权所有 2017 tsstu.com</i>  
	</div>

	<div class="panel profile">
		
		<div class="panel-content">
			<div class="profile-item">
				<h5>常用操作</h5>
				<ul>
					<li><a href="javascript:;">产品管理/续费</a></li>
					<li><a href="javascript:;">提交工单</a></li>
					<li><a href="javascript:;">索取售后发票</a></li>
				</ul>
			</div>
			<div class="profile-item">
				<h5>会员信息管理</h5>
				<ul>
					<li><a href="javascript:;">基本信息</a></li>
					<li><a href="javascript:;">提交工单</a></li>
				</ul>
			</div>
		</div>
		<div class="overlay"></div>
	</div>

	<div class="panel tabs-more-panel">
		
		<div class="panel-content">
			<div class="profile-item">
				<h5>标签操作</h5>
				<ul>
					<li class="closeAllTab"><a href="javascript:;">关闭全部</a></li>
					<li class="closeCurrentTab"><a href="javascript:;">关闭当前</a></li>
					<li class="closeOther"><a href="javascript:;">关闭其他</a></li>
				</ul>
			</div>
			
			<div class="profile-item opend-panel">
				<h5>已打开标签</h5>
				<ul>
					
				</ul>
			</div>
		</div>
		<div class="overlay"></div>
	</div>

	<!---弹窗示例结束，使用请删除以下内容
	<div class="exitDialog">
		<div class="dialog-content">
	    	<iframe src="http://www.taobao.com/" frameborder="0" width="100%" height="380px"></iframe>
	    </div>
	</div>
	-->

    <!---弹窗示例结束，使用请删除以上内容-->
</body>
<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="static/js/navtab.js?v=${time }"></script>
<script type="text/javascript" src="static/js/main_new.js?v=${time }"></script>
<script type="text/javascript" src="static/js/core.js?v=${time }"></script>
<script type="text/javascript" src="static/js/common.js?v=${time }"></script>
</html>
