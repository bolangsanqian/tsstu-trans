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
		    <li><a href="javascript:history.go(-1);">数据字典</a></li>
		    <li><a href="javascript:void();">数据字典项</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="dictionary_item/index" method="post">
    		<input type="hidden" name="dict_code" value="${pageInfo.pageData.dict_code }">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入项名称" title="项名称"/></li>
			    <li><label>项名称：</label><input name="item_name" type="text" class="scinput1" value="${pageInfo.pageData.item_name }" placeholder="这里输入项名称" title="类名"/></li>、
			    <li><label>项名值：</label><input name="item_value" type="text" class="scinput1" value="${pageInfo.pageData.item_value }" placeholder="这里输入项名值" title="项名值"/></li>
	    	</ul>
		    
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<ul class="toolbar">
		    		<li class="click" onclick="goBack()"><span><img src="static/images/icon/icon_back.png" /></span>返回</li>
		        </ul>
		    	<ul class="toolbar1">
		    		<tsstu:hasPermission sign="dictionary:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="dictionary:add">
			        <li class="click" onclick="toAdd('${basePath }dictionary_item/add?dict_code=${pageInfo.pageData.dict_code }', 400, 250)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="dictionary:del">
			        <li class="click" onclick="delBatch('${basePath }dictionary_item/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="dictionary:query">
			        <li class="click" onclick="exportExcel('${basePath }dictionary_item/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="di.item_name"><label>项名称<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'di.item_name' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="di.item_value"><label>项名值<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'di.item_value' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="di.sort"><label>排序<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'di.sort' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td>${model.item_name }</td>
						        <td>${model.item_value }</td>
						        <td>${model.sort }</td>
						        <td>
						        	<tsstu:hasPermission sign="dictionary:del">
						        	<a href="javascript:del('${basePath }dictionary_item/del?id=${model.id}')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="dictionary:edit">
						        	<a href="javascript:toEdit('${basePath }dictionary_item/edit?id=${model.id}&dict_code=${pageInfo.pageData.dict_code }', 400, 250)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">没有相关数据</td>
						</tr>
		        	</c:otherwise>
		        </c:choose>
		        </tbody>
		    </table>
		    <tsstu:page form="pageForm" />
		</form>
	</div>
<script type="text/javascript">
	function goBack() {
		location.href = "${basePath }dictionary/index";
	}
</script>
</body>
</html>
