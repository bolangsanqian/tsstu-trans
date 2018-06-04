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
		    <li><a href="javascript:void();">系统配置</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="config/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入配置说明、配置值" title="配置说明、配置值"/></li>
			    <li><label>配置代码：</label><input name="config_code" type="text" class="scinput1" value="${pageInfo.pageData.config_code }" placeholder="这里输入配置代码" title="配置代码"/></li>
			    <li><label>配置分组：</label>  
				    <div class="vocation">
					    <select name="config_group" id="config_group" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_SYS_CONFIG_GROUP }" var="dict" varStatus="vs_dict">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.config_group eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
				    </div>
			    </li>
	    	</ul>
		    
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<ul class="toolbar1">
		    		<tsstu:hasPermission sign="config:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="config:add">
			        <li class="click" onclick="toAdd('${basePath }config/add', 600, 310)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="config:del">
			        <li class="click" onclick="delBatch('${basePath }config/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="config:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }config/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="config_name"><label>配置说明</label></th>
				        <th class="orderBy" attr-name="config_code"><label>配置代码<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'param_code' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="config_value"><label>配置值</i></label></th>
				        <th class="orderBy" attr-name="config_unit"></label>配置单位</th>
				        <th class="orderBy" attr-name="config_group"><label>所属分组<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'config_group' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="effective_way"><label>生效方式<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'table_name' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="sort"><label>排序<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'sort' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="config:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td>${model.config_name }</td>
						        <td>${model.config_code }</td>
						        <td>${model.config_value }</td>
						        <td>${model.config_unit }</td>
						        <td>
						        	<c:forEach items="${D_SYS_CONFIG_GROUP }" var="dict" varStatus="dict_status">
										<c:if test="${model.config_group eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_EFFECTIVE_WAY }" var="dict" varStatus="dict_status">
										<c:if test="${model.effective_way eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>${model.sort }</td>
						        <td>
						        	<tsstu:hasPermission sign="config:del">
						        	<a href="javascript:del('${basePath }config/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="config:edit">
						        	<a href="javascript:toEdit('${basePath }config/edit?id=${model.id }', 600, 310)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="config:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="config:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="config:query">没有相关数据</tsstu:hasPermission>
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
