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
		    <li><a href="javascript:void();">产品管理</a></li>
		    <c:if test="${pageInfo.pageData.from eq 'category' }">
		    <li><a href="${bashPath }product_category/index">产品分类</a></li>
		    </c:if>
		    <li><a href="javascript:void();">交易日管理</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="trading_day/index" method="post">
    		<c:if test="${pageInfo.pageData.from eq 'category' }">
    		<input type="hidden" id="product_category_id" name="product_category_id" value="${pageInfo.pageData.product_category_id }">
    		<input type="hidden" id="from" name="from" value="${pageInfo.pageData.from }">
    		</c:if>
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li>
			    	<label>产品分类：</label>
			    	<div class="vocation">
			    		<select id="product_category_id" name="product_category_id" class="select3" onchange="search()" <c:if test="${pageInfo.pageData.from eq 'category' }">disabled="disabled"</c:if>>
					    	<option value="">请选择</option>
						    <c:forEach items="${categoryList }" var="item" varStatus="status">
								<option value="${item.id}" <c:if test="${pageInfo.pageData.product_category_id eq item.id}">selected</c:if>>${item.code} - ${item.name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<c:if test="${pageInfo.pageData.from eq 'category' }">
		    	<ul class="toolbar">
		    		<li class="click" onclick="toUrl('${bashPath }product_category/index')"><span><img src="static/images/icon/icon_back.png" /></span>返回</li>
		        </ul>
		        </c:if>
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="trading_day:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
			         <tsstu:hasPermission sign="trading_day:reset">
			        <li class="click" onclick="reset()"><span><img src="static/images/icon/icon_export.png" /></span>重置</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="trading_day:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }trading_day/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="product_category_id"><label>产品分类<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'product_category_id' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="week"><label>星期<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'week' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>开盘时间</label></th>
				        <th><label>收盘时间</label></th>
				        <th><label>收盘日期</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="trading_day:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
								<td>
									<c:forEach items="${categoryList }" var="item" varStatus="status">
										<c:if test="${model.product_category_id eq item.id}">${item.name }</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${D_WEEK }" var="dict" varStatus="dict_status">
										<c:if test="${model.week eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.opening_time }</td>
    							<td>${model.closing_time }</td>
								<td>
									<c:forEach items="${CLOSING_DAY }" var="dict" varStatus="dict_status">
										<c:if test="${model.closing_day eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
						        <td>
						        	<tsstu:hasPermission sign="trading_day:edit">
						        	<a href="javascript:toEdit('${basePath }trading_day/edit?id=${model.id }', 360, 360)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="trading_day:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="trading_day:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="trading_day:query">没有相关数据</tsstu:hasPermission>
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
	/**
	 * 重置交易日
	 */
	function reset() {
		$.ajax({
			url: "${bathPath }trading_day/reset",
			data: {product_category_id: "${model.id }"},
			type: "post",
			dataType: "json",
			success: function(json) {
				if (json.ok) {
					Dialog.alert(successMsg, function() {
						$.isFunction(window.parent.search) && window.parent.search();
					});
				}else {
					Dialog.alert(json.msg);
				}
			},
			complete: function() {
			}
		});
	}
</script>
</body>
</html>
