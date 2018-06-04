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
		
		$('#recharge_time').dateRangePicker({language:'cn'});
		
		
	});
</script>
</head>
<body  style="min-width: 900px;">
	<!-- 当前位置 -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="javascript:void();">XXX管理</a></li>
		    <li><a href="javascript:void();">会员充值订单</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="member_recharge_order/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入支付单号、支付IP、备注" title="支付单号、支付IP、备注"/></li>
			    <li><label>充值流水号：</label><input name="flow_no" type="text" class="scinput1" value="${pageInfo.pageData.flow_no }" placeholder="这里输入充值流水号" title="充值流水号"/></li>
			    <li>
			    	<label>所属会员：</label>
			    	<div class="vocation">
			    		<select id="member_id" name="member_id" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${DICT_MEMBER }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.member_id eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>支付方式：</label>
			    	<div class="vocation">
			    		<select id="payment_method" name="payment_method" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_MEMBER_PAYMENT_METHOD }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.payment_method eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>状态：</label>
			    	<div class="vocation">
			    		<select id="status" name="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_MEMBER_RECHARGE_STATUS }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.status eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li><label>充值时间：</label><input id="recharge_time" name="recharge_time" type="text" class="scinput1" value="${pageInfo.pageData.recharge_time }" placeholder="这里输入充值时间" title="充值时间"/></li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		        	<tsstu:hasPermission sign="member_recharge_order:exportExcel">
			        <li class="click" onclick="search('${basePath }member_recharge_order/query')"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="member_recharge_order:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }member_recharge_order/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>充值流水号</label></th>
				        <th><label>所属会员</label></th>
				        <th><label>充值金额</label></th>
				        <th><label>手续费</label></th>
				        <th><label>支付方式</label></th>
				        <th><label>状态</label></th>
				        <th><label>充值时间</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="member_recharge_order:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>
    								<tsstu:hasPermission sign="fund_flow:query"><a class="link-a" href="javascript:void(0);" onclick="toDetail('${basePath}/member_recharge_order/detail?id=${model.id }')">${model.flow_no }</a></tsstu:hasPermission>
	    							<tsstu:notPermission sign="fund_flow:query">${model.flow_no }</tsstu:notPermission>
	    						</td>
								<td>
									<c:forEach items="${DICT_MEMBER }" var="dict" varStatus="dict_status">
										<c:if test="${model.member_id eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.amount }</td>
    							<td>${model.fee }</td>
								<td>
									<c:forEach items="${D_MEMBER_PAYMENT_METHOD }" var="dict" varStatus="dict_status">
										<c:if test="${model.payment_method eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${D_MEMBER_RECHARGE_STATUS }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td><fmt:formatDate value="${model.recharge_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="member_recharge_order:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="member_recharge_order:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="member_recharge_order:query">没有相关数据</tsstu:hasPermission>
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
