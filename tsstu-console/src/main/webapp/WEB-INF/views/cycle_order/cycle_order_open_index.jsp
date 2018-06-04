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
		    <li><a href="javascript:void();">周期模式</a></li>
		    <li><a href="javascript:void();">持仓订单</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="cycle_order/open_index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入客户昵称、手机号码" title="客户昵称、手机号码"/></li>
			    <li><label>订单编号：</label><input name="flow_no" type="text" class="scinput1" value="${pageInfo.pageData.flow_no }" placeholder="这里输入订单编号" title="订单编号"/></li>
			    <li><label>手机号码：</label><input name="mobile" type="text" class="scinput1" value="${pageInfo.pageData.mobile }" placeholder="这里输入手机号码" title="手机号码"/></li>
			    <li><label>经纪人：</label><input name="broker_mobile" type="text" class="scinput1" value="${pageInfo.pageData.broker_mobile }" placeholder="这里输入经纪人手机号码" title="手机号码"/></li>
			    <li>
			    	<label>交易商品：</label>
			    	<div class="vocation">
			    		<select id="cycle_product_id" name="cycle_product_id" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${DICT_CYCLE_PRODUCT }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.product_code eq item.item_value}">selected</c:if>>${item.item_value} - ${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>做单方向：</label>
			    	<div class="vocation">
			    		<select id="direction" name="direction" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_TRADING_DIRECTION }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.direction eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>持仓时间：</label>
			    	<div class="vocation">
			    		<select id="hold_time" name="hold_time" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${DICT_HOLD_TIME }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.hold_time eq item.item_value}">selected</c:if>>
									${item.item_value}
									<c:forEach items="${D_HOLD_TIME_UNIT }" var="dict" varStatus="dict_status">
										<c:if test="${item.item_name eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>	
								</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <c:if test="${userInfo.user_type > 3 }">
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
						    <c:forEach items="${DICT_COMMON_MEMBER }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.member_id eq item.item_value}">selected</c:if>>${item.item_value} - ${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    </c:if>
			    <c:if test="${userInfo.user_type > 1 }">
			    <li>
			    	<label>代理会员：</label>
			    	<div class="vocation">
			    		<select id="agent_member_id" name="agent_member_id" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${DICT_AGENT_MEMBER }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.agent_member_id eq item.item_value}">selected</c:if>>${item.item_value} - ${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    </c:if>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="cycle_order:open_query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
			        <tsstu:hasPermission sign="cycle_order:open_exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }cycle_order/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>订单编号</label></th>
				        <th><label>手机号码</label></th>
				        <th><label>客户昵称</label></th>
				        <th><label>交易商品</label></th>
				        <th class="right"><label>金额</label></th>
				        <th class="right"><label>手续费</label></th>
				        <th><label>持单时间</label></th>
				        <th><label>做单方向</label></th>
				        <th><label>建仓价格</label></th>
				        <th><label>建仓时间</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="cycle_order:open_query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>
    								<tsstu:hasPermission sign="cycle_order:open_query"><a class="link-a" href="javascript:void(0);" onclick="toDetail('${basePath}/cycle_order/open_detail?id=${model.id }')">${model.flow_no }</a></tsstu:hasPermission>
	    							<tsstu:notPermission sign="cycle_order:open_query">${model.flow_no }</tsstu:notPermission>
	    						</td>
    							<td>${model.mobile }</td>
    							<td>${model.nick_name }</td>
    							<td>${model.product_name }</td>
    							<td class="right">${model.amount }</td>
    							<td class="right">${model.fee }</td>
    							<td>${model.hold_time }
	    							<c:forEach items="${D_HOLD_TIME_UNIT }" var="dict" varStatus="dict_status">
										<c:if test="${model.time_unit eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
    							</td>
    							<td>
									<c:forEach items="${D_TRADING_DIRECTION }" var="dict" varStatus="dict_status">
										<c:if test="${model.direction eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td><fmt:formatNumber value="${model.open_price}" type="currency" groupingUsed="false" minFractionDigits="0" maxFractionDigits="5" currencySymbol=""/></td>
								<td><fmt:formatDate value="${model.open_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="cycle_order:open_query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="cycle_order:open_query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="cycle_order:open_query">没有相关数据</tsstu:hasPermission>
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
