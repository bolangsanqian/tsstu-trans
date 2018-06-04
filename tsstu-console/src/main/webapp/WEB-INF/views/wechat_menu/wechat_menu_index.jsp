<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tsstu" uri="http://www.tsstu.com/wp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
</style>
<script type="text/javascript">
	$(function($){
		//初始化下拉框
		$(".select3").uedSelect({
			width : 172
		});
		
 
	});
</script>
</head>
<body  style="min-width: 900px;">
	<!-- 当前位置 -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="javascript:void();">微信管理</a></li>
		    <li><a href="javascript:void();">菜单管理(微信)</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="wechat_menu/index" method="post">
    		<input type="hidden" id="pid" name="pid" value="${pageInfo.pageData.pid}" />
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input id="keywords" name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入父菜单id、名称、菜单类型、关键字、跳转地址、排序、状态、" title="菜单类型、状态、"/></li>
			    <li>
			    	<label>菜单类型：</label>
			    	<div class="vocation">
			    		<select id="menu_type" name="menu_type" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_WXMENU_TYPE }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.menu_type eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>状态：</label>
			    	<div class="vocation">
			    		<select id="status" name="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_ENABLE_DISABLE }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.status eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<c:if test="${pageInfo.pageData.pid gt 0}">
		    	<ul class="toolbar">
		    		<li class="click" onclick="goBack(${pageInfo.pageData.id})"><span><img src="static/images/icon/icon_back.png" /></span>返回</li>
		        </ul>
		        </c:if>
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="wechat_menu:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="wechat_menu:add">
			        <li class="click" onclick="toAdd('${basePath }wechat_menu/add?pid=${pageInfo.pageData.pid}', 420, 330)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="wechat_menu:del">
			        <li class="click" onclick="delBatch('${basePath }wechat_menu/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="wechat_menu:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }wechat_menu/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>名称</label></th>
				        <th><label>菜单类型</label></th>
				        <th><label>关键字</label></th>
				        <th><label>跳转地址</label></th>
				        <th><label>状态</label></th>
				        <th><label>创建时间</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="wechat_menu:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td><a title="查看详情" style="color:blue;" href="${bashPath }wechat_menu/index?pid=${model.id}&id=${pageInfo.pageData.pid }">${model.name }</a></td>
								<td>
									<c:forEach items="${D_WXMENU_TYPE }" var="dict" varStatus="dict_status">
										<c:if test="${model.menu_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.keyword }</td>
    							<td>${model.url }</td>
								<td>
									<c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td><fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<tsstu:hasPermission sign="wechat_menu:del">
						        	<a href="javascript:del('${basePath }wechat_menu/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="wechat_menu:edit">
						        	<a href="javascript:toEdit('${basePath }wechat_menu/edit?id=${model.id }', 420, 330)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="wechat_menu:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="wechat_menu:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="wechat_menu:query">没有相关数据</tsstu:hasPermission>
							</td>
						</tr>
		        	</c:otherwise>
		        </c:choose>
		        </tbody>
		    </table>
		    <tsstu:page form="pageForm" />
		</form>
	</div>
<script type="text/javascript">
	function goBack(pid) {
		location.href = "${basePath }wechat_menu/index?pid=" + pid;
	}
</script>
</body>
</html>
