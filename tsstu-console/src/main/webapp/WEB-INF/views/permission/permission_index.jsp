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
		    <li><a href="javascript:void();">系统管理</a></li>
		    <li><a href="javascript:void();">权限管理</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="permission/index" method="post">
    		<input type="hidden" id="pid" name="pid" value="${pageInfo.pageData.pid}" />
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入菜单名称、资源路径、权限标识" title="菜单名称、资源路径、权限标识"/></li>
			    <li><label>菜单名称：</label><input name="name" type="text" class="scinput1" value="${pageInfo.pageData.name }" placeholder="这里输入模块名称" title="模块名称"/></li>
			    <li><label>资源路径：</label><input name="url" type="text" class="scinput1" value="${pageInfo.pageData.url }" placeholder="这里输入资源路径" title="资源路径"/></li>
			    <li><label>权限标识：</label><input name="permission_sign" type="text" class="scinput1" value="${pageInfo.pageData.permission_sign }" placeholder="这里输入权限标识" title="权限标识"/></li>
	    	</ul>
		    
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<c:if test="${pageInfo.pageData.pid gt 0}">
		    	<ul class="toolbar">
		    		<li class="click" onclick="goBack(${pageInfo.pageData.id})"><span><img src="static/images/icon/icon_back.png" /></span>返回</li>
		        </ul>
		        </c:if>
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="permission:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="permission:add">
			        <li class="click" onclick="toAdd('${basePath }permission/add?pid=${pageInfo.pageData.pid}', 460, 490)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="permission:del">
			        <li class="click" onclick="delBatch('${basePath }permission/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="permission:query">
			        <li class="click" onclick="exportExcel('${basePath }permission/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="name"><label>菜单名称<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'name' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="url"><label>资源路径<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'url' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="permission_sign"><label>权限标识<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'permission_sign' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="sort"><label>序号<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'sort' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="status"><label>状态<i class="status"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'status' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="permission:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td><a title="查看详情" style="color:blue;" href="permission/index?pid=${model.id}&id=${pageInfo.pageData.pid }">${model.name }</a></td>
						        <td>${model.url }</td>
						        <td>${model.permission_sign }</td>
						        <td>${model.sort }</td>
						        <td>
						        	<c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<tsstu:hasPermission sign="permission:del">
						        	<a href="javascript:del('${basePath }permission/del?id=${model.id}')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="permission:edit">
						        	<a href="javascript:toEdit('${basePath }permission/edit?id=${model.id}&pid=${pageInfo.pageData.pid }', 460, 490)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="permission:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="permission:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="permission:query">没有相关数据</tsstu:hasPermission>
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
		location.href = "${basePath }permission/index?pid=" + pid;
	}
</script>
</body>
</html>
