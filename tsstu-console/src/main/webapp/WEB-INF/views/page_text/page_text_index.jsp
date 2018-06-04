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
		    <li><a href="javascript:void();">XXX管理</a></li>
		    <li><a href="javascript:void();">文案管理</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="page_text/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input id="keywords" name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入代码、说明、文案" title="代码、说明、文案"/></li>
			    <li><label>代码：</label><input id="code" name="code" type="text" class="scinput1" value="${pageInfo.pageData.code }" placeholder="这里输入代码" title="代码"/></li>
			    <li>
			    	<label>交易模式：</label>
			    	<div class="vocation">
			    		<select id="trade_mode" name="trade_mode" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_TRADE_MODE }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.trade_mode eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="page_text:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="page_text:add">
			        <li class="click" onclick="toAdd('${basePath }page_text/add', 380, 360)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="page_text:del">
			        <li class="click" onclick="delBatch('${basePath }page_text/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="page_text:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }page_text/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>代码</label></th>
				        <th><label>说明</label></th>
				        <th><label>交易模式</label></th>
				        <th><label>文案</label></th>
				        <th><label>排序</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="page_text:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>${model.code }</td>
    							<td>${model.name }</td>
								<td>
									<c:forEach items="${D_TRADE_MODE }" var="dict" varStatus="dict_status">
										<c:if test="${model.trade_mode eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.text }</td>
    							<td>${model.sort }</td>
						        <td>
						        	<tsstu:hasPermission sign="page_text:del">
						        	<a href="javascript:del('${basePath }page_text/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="page_text:edit">
						        	<a href="javascript:toEdit('${basePath }page_text/edit?id=${model.id }', 380, 360)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="page_text:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="page_text:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="page_text:query">没有相关数据</tsstu:hasPermission>
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
