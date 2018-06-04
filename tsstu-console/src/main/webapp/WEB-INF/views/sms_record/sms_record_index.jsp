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
		    <li><a href="javascript:void();">短信记录</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="sms_record/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入短信类型、" title="短信类型、"/></li>
			    <li><label>手机号码：</label><input name="mobile" type="text" class="scinput1" value="${pageInfo.pageData.mobile }" placeholder="这里输入手机号码" title="手机号码"/></li>
			    <li>
			    	<label>短信类型：</label>
			    	<div class="vocation">
			    		<select id="sms_type" name="sms_type" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_SMS_SEND_TYPE }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.sms_type eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li><label>发送时间：</label><input name="send_time" type="text" class="scinput1" value="${pageInfo.pageData.send_time }" placeholder="这里输入发送时间" title="发送时间"/></li>
			    <li><label>IP地址：</label><input name="ip" type="text" class="scinput1" value="${pageInfo.pageData.ip }" placeholder="这里输入IP地址" title="IP地址"/></li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="sms_record:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
			        <tsstu:hasPermission sign="sms_record:del">
			        <li class="click" onclick="delBatch('${basePath }sms_record/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="sms_record:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }sms_record/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th class="orderBy" attr-name="mobile"><label>手机号码<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'mobile' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="sms_type"><label>短信类型<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'sms_type' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>短信内容</label></th>
				        <th class="orderBy" attr-name="send_time"><label>发送时间<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'send_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="ip"><label>IP地址<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'ip' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="sms_record:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>${model.mobile }</td>
								<td>
									<c:forEach items="${D_SMS_SEND_TYPE }" var="dict" varStatus="dict_status">
										<c:if test="${model.sms_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.sms_content }</td>
								<td><fmt:formatDate value="${model.send_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    							<td>${model.ip }</td>
						        <td>
						        	<tsstu:hasPermission sign="sms_record:del">
						        	<a href="javascript:del('${basePath }sms_record/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="sms_record:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="sms_record:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="sms_record:query">没有相关数据</tsstu:hasPermission>
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
