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
		    <li><a href="javascript:void();">系统日志</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="log/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入用户ID、资源名称、IP地址、请求参数、资源地址" title="用户ID、资源名称、IP地址、请求参数、资源地址"/></li>
			    <li><label>用户ID：</label><input name="user_id" type="text" class="scinput1" value="${pageInfo.pageData.user_id }" placeholder="这里输入用户ID" title="用户ID"/></li>
			    <li><label>资源名称：</label><input name="name" type="text" class="scinput1" value="${pageInfo.pageData.name }" placeholder="这里输入资源名称" title="资源名称"/></li>
			    <li><label>IP地址：</label><input name="ip" type="text" class="scinput1" value="${pageInfo.pageData.ip }" placeholder="这里输入IP地址" title="IP地址"/></li>
			    <li>
			    	<label>操作类型：</label>
			    	<div class="vocation">
			    		<select name="operate_type" id="operate_type" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_LOG_TYPE }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.operate_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
	    	</ul>
		    
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="log:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
			        <tsstu:hasPermission sign="log:del">
			        <li class="click" onclick="delBatch()"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="log:exportExcel">
			        <li class="click" onclick="exportExcel()"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="user_id"><label>用户id<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'user_id' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="name"><label>资源名称</label></th>
				        <th class="orderBy" attr-name="url"><label>资源地址<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'url' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="param"><label>请求参数</label></th>
				        <th class="orderBy" attr-name="operate_type"><label>操作类型<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'operate_type' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ip"><label>IP<i class="status"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ip' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="create_time"><label>创建时间<i class="status"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'create_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="log:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td>${model.user_id }</td>
						        <td>${model.name }</td>
						        <td style="max-width: 150px;">${model.url }</td>
						        <td  style="max-width: 100px;">${model.param }</td>
						        <td>
						        	<c:forEach items="${D_LOG_TYPE }" var="dict" varStatus="dict_status">
										<c:if test="${model.operate_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>${model.ip }</td>
						        <td><fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<tsstu:hasPermission sign="log:del">
						        	<a href="javascript:del(${model.id})" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="dictionary:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="log:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="log:query">没有相关数据</tsstu:hasPermission>
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

	//添加页面
	function toAdd() {
		var diag = new Dialog();
		diag.Width = 460;
		if (pid > 0) {
			diag.Height = 490;
		} else {
			diag.Height = 470;
		}
		diag.Title = "添加";
		diag.URL = "${basePath }log/add";
		diag.show();
	}
	
	//修改页面
	function toEdit(id) {
		var diag = new Dialog();
		diag.Width = 460;
		if (pid > 0) {
			diag.Height = 490;
		} else {
			diag.Height = 470;
		}
		diag.Title = "修改";
		diag.URL = "${basePath }log/edit?id=" + id;
		diag.show();
	}
	
	//删除
	function del(id) {
		Dialog.confirm('确定要删除该条记录？', function() {
			var url = "${basePath }log/del?id=" + id;
			$.get(url, function(data) {
				if (data.ok) {
					search();		
				}
			})
		});
	}
	
	//批量删除
	function delBatch() {
		var ids = [];
		var i = 0;
		var chks = $(".tablelist input[name='chk']:checked").each(function() {
			ids[i] = $(this).val();
			i++;
		});
		if (ids.length < 1){
			Dialog.alert("请选择要删除的记录");
			return;
		}
		Dialog.confirm('确定要删除该条记录？', function() {
			var url = "${basePath }log/delBatch";
			$.ajax({  
			    url: url,  
			    data: {"ids": ids },  
			    dataType: "json",  
			    type: "POST",  
			    traditional: true,  
			    success: function (data) {  
			    	if (data.ok) {
						search();		
					} 
			    }  
			});  
		});
	}
	
	function exportExcel() {
		location.href = "${basePath }log/exportExcel";
	}

</script>
</body>
</html>
