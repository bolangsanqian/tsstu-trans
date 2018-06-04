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
		
		//复选框全选/反选控制
		$("#chk_all").on("click", function() {
			$(":checkbox[name=chk]").prop("checked", $(this).is(":checked"));
			if ($(this).is(":checked")) {
				$(this).closest("table").find('tbody > tr').addClass("active");
			} else {
				$(this).closest("table").find('tbody > tr').removeClass("active");
			}
		});
		$(":checkbox[name=chk]").on("click", function() {
			var active_class = $(this).is(":checked") ? "active" : "";
			$(this).closest("tr").toggleClass(active_class);
			$("#chk_all").prop("checked", ($(":checkbox[name=chk]").not(":checked").length > 0 ? false : true));
		});
		
		//初始化表头最新宽度
		$(".tablelist th").each(function() {
			var w = $(">label", this).width();
			$(this).css("min-width", w);
		});
		
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
		    <li><a href="javascript:void();">数据字典</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="dictionary/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入典代码、字典名字" title="典代码、字典名字"/></li>
			    <li><label>字典代码：</label><input name="dict_code" type="text" class="scinput1" value="${pageInfo.pageData.dict_code }" placeholder="这里输入字典代码" title="字典代码"/></li>
			    <li><label>字典名称：</label><input name="dict_name" type="text" class="scinput1" value="${pageInfo.pageData.dict_name }" placeholder="这里输入字典名字" title="字典名字"/></li>
	    	</ul>
		    
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="dictionary:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="dictionary:add">
			        <li class="click" onclick="toAdd('${basePath }dictionary/add', 400, 280)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="dictionary:del">
			        <li class="click" onclick="delBatch('${basePath }dictionary/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="dictionary:query">
			        <li class="click" onclick="exportExcel('${basePath }dictionary/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="dict_code"><label>字典代码<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'dict_code' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="dict_name"><label>字典名称<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'dict_name' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="remark"><label>描述<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'remark' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="dictionary:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td><a title="查看详情" style="color:blue;" href="dictionary_item/index?dict_code=${model.dict_code}">${model.dict_name }</a></td>
						        <td>${model.dict_code }</td>
						        <td>
						        	${model.remark }
						        	(<c:forEach items="${model.itemList }" var="item" varStatus="status">
						        		<c:if test="${status.index ne 0 }">,</c:if>
						        		${item.item_value }:${item.item_name }
						        	</c:forEach>)
						        </td>
						        <td>
						        	<tsstu:hasPermission sign="dictionary:del">
						        	<a href="javascript:del('${basePath }dictionary/del?id=${model.id}')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="dictionary:edit">
						        	<a href="javascript:toEdit('${basePath }dictionary/edit?id=${model.id}', 400, 280)" class="tablelink"> 修改</a>
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
								<tsstu:notPermission sign="dictionary:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="dictionary:query">没有相关数据</tsstu:hasPermission>
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
