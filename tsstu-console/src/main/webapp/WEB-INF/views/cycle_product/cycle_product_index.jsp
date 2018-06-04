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
		    <li><a href="javascript:void();">时限产品</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
    	<form id="pageForm" action="cycle_product/index" method="post">
    		<input type="hidden" id="cycle_product_id" name="cycle_product_id" value="${pageInfo.pageData.cycle_product_id }">
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入status" title="status"/></li>
			    <li><label>产品代码：</label><input name="code" type="text" class="scinput1" value="${pageInfo.pageData.code }" placeholder="这里输入产品代码" title="产品代码"/></li>
			    <li><label>产品名称：</label><input name="name" type="text" class="scinput1" value="${pageInfo.pageData.name }" placeholder="这里输入产品名称" title="产品名称"/></li>
			    <li>
			    	<label>产品分类：</label>
			    	<div class="vocation">
			    		<select id="product_category_id" name="product_category_id" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${categoryList }" var="item" varStatus="dict_status">
								<option value="${item.id}" <c:if test="${pageInfo.pageData.product_category_id eq item.id}">selected</c:if>>${item.code} - ${item.name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
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
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="cycle_product:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="cycle_product:add">
			        <li class="click" onclick="toAdd('${basePath }cycle_product/add', 600, 310)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="cycle_product:del">
			        <li class="click" onclick="delBatch('${basePath }cycle_product/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="cycle_product:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }cycle_product/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="code"><label>产品代码<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'code' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>产品名称</label></th>
				        <th><label>单位</label></th>
				        <th><label>产品分类</label></th>
				        <th><label>最大持仓金额</label></th>
				        <th><label>最小建仓金额</label></th>
				        <th><label>手续费公式</label></th>
				        <th class="orderBy" attr-name="status"><label>状态<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'status' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>排序</label></th>
				        <th class="orderBy" attr-name="create_time"><label>创建时间<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'create_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="cycle_product:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>${model.code }</td>
    							<td>${model.name }</td>
    							<td>${model.unit }</td>
    							<td>
									<c:forEach items="${categoryList }" var="item" varStatus="status">
										<c:if test="${model.product_category_id eq item.id}">${item.name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.max_hold_amount }</td>
    							<td>${model.min_create_amount }</td>
    							<td>${model.fee_formula }</td>
								<td>
									<c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.sort }</td>
								<td><fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<tsstu:hasPermission sign="cycle_product:del">
						        	<a href="javascript:del('${basePath }cycle_product/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="cycle_product:edit">
						        	<a href="javascript:toEdit('${basePath }cycle_product/edit?id=${model.id }', 600, 310)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="cycle_product:query">
						        	<a href="javascript:toUrl('${basePath }hold_time/index?cycle_product_id=${model.id }')" class="tablelink"> 持仓时间</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="cycle_product:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="cycle_product:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="cycle_product:query">没有相关数据</tsstu:hasPermission>
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
