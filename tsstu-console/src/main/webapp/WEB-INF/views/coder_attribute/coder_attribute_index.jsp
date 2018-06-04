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
		    <li><a href="javascript:history.go(-1);">代码生成器</a></li>
		    <li><a href="javascript:void();">代码生成器明细</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="coder_attribute/index" method="post">
    		<input type="hidden" name="coder_id" value="${pageInfo.pageData.coder_id }">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入用户名、手机号码、邮箱" title="用户名、手机号码、邮箱"/></li>
			    <li><label>列名：</label><input name="name" type="text" class="scinput1" value="${pageInfo.pageData.name }" placeholder="这里输入用户名" title="用户名"/></li>
			    <li><label>备注：</label><input name="title" type="text" class="scinput1" value="${pageInfo.pageData.title }" placeholder="这里输入类名" title="类名"/></li>
	    	</ul>
		    
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<ul class="toolbar">
		    		<li class="click" onclick="toUrl('${basePath }/coder/index')"><span><img src="static/images/icon/icon_back.png" /></span>返回</li>
		        </ul>
		    	<ul class="toolbar1">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
			        <li class="click" onclick="toAdd('${basePath }coder_attribute/add?coder_id=${pageInfo.pageData.coder_id }', 600, 400)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        <li class="click" onclick="delBatch('${basePath }coder_attribute/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        <li class="click" onclick="exportExcel('${basePath }coder_attribute/export')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="ca.name"><label>字段名<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.name' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.title"><label>备注<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.title' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.java_type"><label>字段类型<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.java_type' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.data_length"><label>字段长度<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.data_length' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.is_key"><label>主键<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.is_key' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.element_type"><label>表单类型<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.element_type' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.is_search"><label>查询条件<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.is_search' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.is_input"><label>前台录入<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.is_input' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.is_show"><label>列表显示<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.is_show' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.dict_code"><label>数据字典<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.dict_code' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.sort"><label>排序<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.sort' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ca.create_time"><label>创建时间<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ca.create_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
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
						        <td>${model.name }</td>
						        <td>${model.title }</td>
						        <td>
						        	<c:forEach items="${D_FIELD_TYPE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.java_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>${model.data_length }</td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.is_key eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_ELEMENT_TYPE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.element_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.is_search eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.is_input eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.is_show eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>${model.dict_code !="" ? model.dict_code : '无' }</td>
						        <td>${model.sort }</td>
						        <td><fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<tsstu:hasPermission sign="coder:del">
						        	<a href="javascript:del('${basePath }coder_attribute/del?id=${model.id}')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="coder:edit">
						        	<a href="javascript:toEdit('${basePath }coder_attribute/edit?id=${model.id}', 600, 400)" class="tablelink"> 修改</a>
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
</body>
</html>
