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
		    <li><a href="javascript:void();">会员管理</a></li>
		    <li><a href="javascript:void();">会员资金流水</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="member_fund_flow/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入类型、" title="类型、"/></li>
			    <li><label>流水号：</label><input name="flow_no" type="text" class="scinput1" value="${pageInfo.pageData.flow_no }" placeholder="这里输入流水号" title="流水号"/></li>
			    <li><label>交易单号：</label><input name="trans_no" type="text" class="scinput1" value="${pageInfo.pageData.trans_no }" placeholder="这里输入交易单号" title="交易单号"/></li>
			    <li>
			    	<label>类型：</label>
			    	<div class="vocation">
			    		<select id="change_type" name="change_type" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_MEMBER_FUND_FLOW_TYPE }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.change_type eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			     <li>
			    	<label>运营中心：</label>
			    	<div class="vocation">
			    		<select id="operation_member_id" name="operation_member_id" class="select3" onchange="getMemberList()">
					    	<option value="">请选择</option>
						    <c:forEach items="${DICT_OPERATION }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.operation_member_id eq item.item_value}">selected</c:if>>${item.item_value} - ${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    </c:if>
			    <c:if test="${userInfo.user_type > 2 }">
			    <li>
			    	<label>所属会员：</label>
			    	<div class="vocation">
			    		<select id="member_id" name="member_id" class="select3" onchange="getAgentMemberList()">
					    	<option value="">请选择</option>
						    <c:forEach items="${DICT_MEMBER }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.member_id eq item.item_value}">selected</c:if>>${item.item_value} - ${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    </c:if>
			    <li><label>交易时间：</label><input name="trans_time" type="text" class="scinput1" value="${pageInfo.pageData.trans_time }" placeholder="这里输入交易时间" title="交易时间"/></li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="member_fund_flow:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
			        <tsstu:hasPermission sign="member_fund_flow:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }member_fund_flow/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>流水号</label></th>
				        <th><label>交易单号</label></th>
				        <th><label>会员</label></th>
				        <th class="orderBy" attr-name="balance"><label>余额<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'balance' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="frozen_money"><label>占用资金<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'frozen_money' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>类型</label></th>
				        <th><label>交易时间</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="member_fund_flow:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>${model.flow_no }</td>
    							<td>${model.trans_no }</td>
    							<td>
									<c:forEach items="${memberList }" var="item" varStatus="status">
										<c:if test="${model.member_id eq item.id}">${item.id} - ${item.name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.amount }</td>
    							<td>${model.frozen_money }</td>
								<td>
									<c:forEach items="${D_MEMBER_FUND_FLOW_TYPE }" var="dict" varStatus="dict_status">
										<c:if test="${model.change_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td><fmt:formatDate value="${model.trans_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="member_fund_flow:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="member_fund_flow:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="member_fund_flow:query">没有相关数据</tsstu:hasPermission>
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
