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
			width : 163
		});
		
	});
</script>
</head>
<body  style="min-width: 900px;">
	<!-- 当前位置 -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="javascript:void();">产品管理</a></li>
		    <li><a href="javascript:void();">产品分类</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="product_category/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入产品代码、分类名称" title="产品代码、分类名称"/></li>
			    <li>
			    	<label>状态：</label>
			    	<div class="vocation">
			    		<select id="status" name="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>数据源：</label>
			    	<div class="vocation">
			    		<select id="datasource_code" name="datasource_code" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${dsList }" var="item" varStatus="item_status">
								<option value="${item.code}" <c:if test="${pageInfo.pageData.datasource_code eq item.code}">selected</c:if>>${item.code} - ${item.name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li><label>创建时间：</label><input name="create_time" type="text" class="scinput1" value="${pageInfo.pageData.create_time }" placeholder="这里输入创建时间" title="创建时间"/></li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="product_category:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="product_category:add">
			        <li class="click" onclick="toAdd('${basePath }product_category/add', 600, 300)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="product_category:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }product_category/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>分类名称</label></th>
				        <th><label>产品代码</label></th>
				        <th><label>数据源</label></th>
				        <th><label>汇率类型</label></th>
				        <th><label>汇率</label></th>
				        <th><label>状态</label></th>
				        <th class="orderBy" attr-name="sort"><label>排序<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'sort' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="product_category:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>${model.name }</td>
    							<td>${model.code }</td>
    							<td>
									<c:forEach items="${dsList }" var="item" varStatus="status">
										<c:if test="${model.datasource_code eq item.code}">${item.name}</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${D_FOREIGN_RATE_TYPE }" var="dict" varStatus="dict_status">
										<c:if test="${model.rate_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.rate }</td>
								<td>
									<c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.sort }</td>
						        <td>
						        	<tsstu:hasPermission sign="product_category:del">
						        	<a href="javascript:del('${basePath }product_category/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="product_category:edit">
						        	<a href="javascript:toEdit('${basePath }product_category/edit?id=${model.id }', 600, 300)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="trading_day:query">
						        	<a href="${basePath }trading_day/index?product_category_id=${model.id }&from=category" class="tablelink"> 交易日</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="product_category:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="product_category:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="product_category:query">没有相关数据</tsstu:hasPermission>
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
