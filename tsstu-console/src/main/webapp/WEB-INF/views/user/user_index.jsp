<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tsstu" uri="http://www.tsstu.com/wp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
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
		    <li><a href="javascript:void();">用户管理</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="user/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>用户名称：</label><input name="username" type="text" class="scinput1" value="${pageInfo.pageData.username }" placeholder="这里输入用户名称" title="用户名称"/></li>
			    <li>
			    	<label>用户类型：</label>
			    	<div class="vocation">
			    		<select name="user_type" id="user_type" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_CONSOLE_USER_TYPE }" var="dict" varStatus="vs_dict">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.user_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>状态：</label>
			    	<div class="vocation">
			    		<select name="status" id="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="vs_dict">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
	    	</ul>
		    
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<ul class="toolbar1">
		    		<tsstu:hasPermission sign="user:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="user:add">
			        <li class="click" onclick="toAdd('${basePath }user/add', 420, 400)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="user:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }user/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="u.username"><label>用户名<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'u.username' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="u.user_type"><label>用户类型<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'u.user_type' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>角色</label></th>
				        <th class="orderBy" attr-name="u.status"><label>状态<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'u.status' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="u.allow_del"><label>删除<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'u.allow_del' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>创建人</label></th>
				        <th class="orderBy" attr-name="u.create_time"><label>创建时间<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'u.create_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>最后登录IP</label></th>
				        <th class="orderBy" attr-name="u.last_login_time"><label>最后登录时间<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'u.last_login_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="user:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td>${model.username }</td>
						        <td>
						        	<c:forEach items="${D_CONSOLE_USER_TYPE}" var="dict" varStatus="vs_dict">
										<c:if test="${model.user_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:choose>
						        		<c:when test="${empty model.roleList }">无</c:when>
						        		<c:otherwise>
						        			<c:forEach items="${model.roleList}" var="role" varStatus="role_status">
												<c:if test="${role_status.index ne 0 }">,</c:if>${role.name }
											</c:forEach>
						        		</c:otherwise>
						        	</c:choose>
						        </td>
						        <td>
						        	<c:forEach items="${D_ENABLE_DISABLE}" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq  dict.item_value}">${dict.item_name }</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_ALLOW_STATUS}" var="dict" varStatus="dict_status">
										<c:if test="${model.allow_del eq  dict.item_value}">${dict.item_name }</c:if>
									</c:forEach>
						        </td>
						        <td>${model.create_by_username }</td>
						       	<td><fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						       	<td>${model.last_login_ip }</td>
						       	<td><fmt:formatDate value="${model.last_login_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<tsstu:hasPermission sign="user:del">
						        	<a href="javascript:del('${basePath }user/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="user:edit">
						        	<a href="javascript:toEdit('${basePath }user/edit?id=${model.id }', 420, 400)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="user:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="user:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="user:query">没有相关数据</tsstu:hasPermission>
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
