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
		    <li><a href="javascript:toUrl('${basePath }/cycle_product/index');">周期产品</a></li>
		    <li><a href="javascript:void();">持单时间</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="hold_time/index" method="post">
    		<input type="hidden" id="cycle_product_id" name="cycle_product_id" value="${pageInfo.pageData.cycle_product_id }"
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<ul class="toolbar">
		    		<li class="click" onclick="toUrl('${basePath }/cycle_product/index')"><span><img src="static/images/icon/icon_back.png" /></span>返回</li>
		        </ul>
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="hold_time:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="hold_time:add">
			        <li class="click" onclick="toAdd('${basePath }hold_time/add?cycle_product_id=${pageInfo.pageData.cycle_product_id }', 450, 350)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="hold_time:del">
			        <li class="click" onclick="delBatch('${basePath }hold_time/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="hold_time:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }hold_time/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>持仓时间</label></th>
				        <th><label>时间单位</label></th>
				        <th><label>时间段列表</label></th>
				        <th><label>金额列表</label></th>
				        <th><label>排序</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="hold_time:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td>${model.hold_time }</td>
    							<td>${model.time_unit }</td>
    							<td>${model.time_list }</td>
    							<td>${model.amount_list }</td>
						        <td>${model.sort }</td>
						        <td>
						        	<tsstu:hasPermission sign="hold_time:del">
						        	<a href="javascript:del('${basePath }hold_time/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="hold_time:edit">
						        	<a href="javascript:toEdit('${basePath }hold_time/edit?id=${model.id }', 600, 310)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="hold_time:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="hold_time:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="hold_time:query">没有相关数据</tsstu:hasPermission>
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
