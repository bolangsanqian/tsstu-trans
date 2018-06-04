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
		    <li><a href="javascript:void();">图片管理</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="image/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input id="keywords" name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入说明、图片地址、图片分组、排序、" title="图片分组、"/></li>
			    <li><label>说明：</label><input id="title" name="title" type="text" class="scinput1" value="${pageInfo.pageData.title }" placeholder="这里输入说明" title="说明"/></li>
			    <li>
			    	<label>图片分组：</label>
			    	<div class="vocation">
			    		<select id="image_group" name="image_group" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_IMAGE_GROUP }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.image_group eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="image:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="image:add">
			        <li class="click" onclick="toAdd('${basePath }image/add', 380, 360)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="image:del">
			        <li class="click" onclick="delBatch('${basePath }image/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="image:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }image/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th style="width: 80px;text-align: center"><label>图片</label></th>
				        <th><label>说明</label></th>
				        <th><label>图片地址</label></th>
				        <th><label>图片分组</label></th>
				        <th><label>排序</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="image:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td align="center"><img src="${model.image_url }" width="80" height="80" /> </td>
    							<td>${model.title }</td>
    							<td>${model.image_url }</td>
								<td>
									<c:forEach items="${D_IMAGE_GROUP }" var="dict" varStatus="dict_status">
										<c:if test="${model.image_group eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.sort }</td>
						        <td>
						        	<tsstu:hasPermission sign="image:del">
						        	<a href="javascript:del('${basePath }image/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="image:edit">
						        	<a href="javascript:toEdit('${basePath }image/edit?id=${model.id }', 380, 360)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="image:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="image:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="image:query">没有相关数据</tsstu:hasPermission>
							</td>
						</tr>
		        	</c:otherwise>
		        </c:choose>
		        </tbody>
		    </table>
		    <tsstu:page form="pageForm" />
		</form>
	</div>
</body>
</html>
