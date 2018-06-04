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
		    <li><a href="javascript:void();">代码生成器</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="coder/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入用户名、手机号码、邮箱" title="用户名、手机号码、邮箱"/></li>
			    <li><label>模块名称：</label><input name="name" type="text" class="scinput1" value="${pageInfo.pageData.name }" placeholder="这里输入用户名" title="用户名"/></li>
			    <li><label>类名：</label><input name="class_name" type="text" class="scinput1" value="${pageInfo.pageData.class_name }" placeholder="这里输入类名" title="类名"/></li>
			    <li><label>表名：</label><input name="table_name" type="text" class="scinput1" value="${pageInfo.pageData.table_name }" placeholder="这里输入表名" title="表名"/></li>
	    	</ul>
		    
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<ul class="toolbar1">
		    		<tsstu:hasPermission sign="coder:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="coder:add">
			        <li class="click" onclick="toAdd('${basePath }coder/add')"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="coder:del">
			        <li class="click" onclick="delBatch('${basePath }coder/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <li class="click" onclick="generate()"><span><img src="static/images/icon/icon_export.png" /></span>生成</li>
			        <tsstu:hasPermission sign="coder:query">
			        <li class="click" onclick="exportExcel('${basePath }coder/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><input id="chk_all" name="chk_all" type="checkbox"/></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="name"><label>模块名称<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'name' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="package_name"><label>包名<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'package_name' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="class_name"><label>类名<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'class_name' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="table_name"><label>表名<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'table_name' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="add_button"><label>添加按钮<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'add_button' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="del_button"><label>删除按钮<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'del_button' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="edit_button"><label>修改按钮<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'edit_button' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="query_button"><label>查询按钮<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'export_button' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="export_button"><label>导出按钮<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'export_button' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="import_button"><label>导入按钮<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'import_button' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="permission"><label>权限<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'permission' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="create_time"><label>创建时间<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'create_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="coder:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td><a title="查看详情" style="color:blue;" href="coder_attribute/index?coder_id=${model.id}">${model.name }</a></td>
						        <td>${model.package_name }</td>
						        <td>${model.class_name }</td>
						        <td>${model.table_name }</td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.add_button eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.del_button eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.edit_button eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.query_button eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.export_button eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.import_button eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td>
						        	<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="vs_dict">
										<c:if test="${model.permission eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
						        </td>
						        <td><fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<tsstu:hasPermission sign="coder:del">
						        	<a href="javascript:del('${basePath }coder/del?id=${model.id}')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="coder:edit">
						        	<a href="javascript:toEdit('${basePath }coder/edit?id=${model.id}', 600, 400)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="coder:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="coder:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="coder:query">没有相关数据</tsstu:hasPermission>
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
	//生成代码
	function generate() {
		var ids = ""
		var i = 0;
		var chks = $("#pageForm .tablelist input[name='chk']:checked").each(function() {
			ids += "," + $(this).val();
			i++;
		});
		if (ids == ""){
			Dialog.alert("请选择要生成的模块");
			return;
		}
		ids = ids.substring(1);
		window.location.href='${basePath }coder/generateCode?idstr=' + ids;
	}
</script>
</body>
</html>
