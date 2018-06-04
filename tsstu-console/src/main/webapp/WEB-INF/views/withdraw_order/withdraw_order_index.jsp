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
		    <li><a href="javascript:void();">客户管理</a></li>
		    <li><a href="javascript:void();">提现订单</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="withdraw_order/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入提现状态（0：处理中，1：提现成功，2：提现失败）、审批状态（0：未审批，1：已审批，-1：已驳回）、提现方式(0：微信提现，1：网银提现，2：融宝提现)、" title="提现状态（0：处理中，1：提现成功，2：提现失败）、审批状态（0：未审批，1：已审批，-1：已驳回）、提现方式(0：微信提现，1：网银提现，2：融宝提现)、"/></li>
			    <li><label>提现流水号：</label><input name="flow_no" type="text" class="scinput1" value="${pageInfo.pageData.flow_no }" placeholder="这里输入提现流水号" title="提现流水号"/></li>
			    <li><label>手机号码：</label><input name="mobile" type="text" class="scinput1" value="${pageInfo.pageData.mobile }" placeholder="这里输入手机号码" title="手机号码"/></li>
			    <li>
			    	<label>提现状态：</label>
			    	<div class="vocation">
			    		<select id="status" name="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_WITHDRAW_STATUS }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.status eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>审批状态：</label>
			    	<div class="vocation">
			    		<select id="review_status" name="review_status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_WITHDRAW_REVIEW_STATUS }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.review_status eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>提现方式：</label>
			    	<div class="vocation">
			    		<select id="withdraw_method" name="withdraw_method" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_WITHDRAW_METHOD }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.withdraw_method eq item.item_value}">selected</c:if>>${item.item_name}</option>
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
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="withdraw_order:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="withdraw_order:review">
		    		<li class="click" onclick="toReviewBatch('${basePath }withdraw_order/review')"><span><img src="static/images/icon/icon_review.png" /></span>审批</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="withdraw_order:review">
		    		<li class="click" onclick="toPayBatch(('${basePath }withdraw_order/review')"><span><img src="static/images/icon/icon_pay.png" /></span>付款</li>
		    		</tsstu:hasPermission>
			        <tsstu:hasPermission sign="withdraw_order:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }withdraw_order/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>提现流水号</label></th>
				        <th><label>手机号码</label></th>
				        <th><label>客户昵称</label></th>
				        <th><label>提现金额</label></th>
				        <th><label>手续费</label></th>
				        <th><label>申请时间</label></th>
				        <th><label>审批状态</label></th>
				        <th><label>提现状态</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="withdraw_order:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>
    								<tsstu:hasPermission sign="withdraw_order:query"><a class="link-a" href="javascript:void(0);" onclick="toDetail('${basePath}/withdraw_order/detail?id=${model.id }')">${model.flow_no }</a></tsstu:hasPermission>
	    							<tsstu:notPermission sign="withdraw_order:query">${model.flow_no }</tsstu:notPermission>
	    						</td>
						        <td>${model.mobile }</td>
    							<td>${model.nick_name }</td>
    							<td>${model.amount }</td>
    							<td>${model.fee }</td>
								<td><fmt:formatDate value="${model.apply_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<c:forEach items="${D_WITHDRAW_REVIEW_STATUS }" var="dict" varStatus="dict_status">
										<c:if test="${model.review_status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${D_WITHDRAW_STATUS }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
						        <td>
						        	<c:if test="${model.review_status eq 0 and model.review_sign eq 1}">
						        	<tsstu:hasPermission sign="withdraw_order:review">
						        	<a href="javascript:toReview('${basePath }withdraw_order/review?ids=${model.id }')" class="tablelink"> 审批</a>
						        	</tsstu:hasPermission>
						        	</c:if>
						        	<c:if test="${model.review_status eq 1 and model.status eq 0 }">
						        	<tsstu:hasPermission sign="withdraw_order:pay">
						        	<a href="javascript:toPay('${basePath }withdraw_order/pay?ids=${model.id }')" class="tablelink"> 付款</a>
						        	</tsstu:hasPermission>
						        	</c:if>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="withdraw_order:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="withdraw_order:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="withdraw_order:query">没有相关数据</tsstu:hasPermission>
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
	// 批量审批
	function toReviewBatch(url) {
		var ids = "";
		var i = 0;
		var chks = $("#pageForm .tablelist input[name='chk']:checked").each(function() {
			if (i > 0){
				ids += ",";		
			}
			ids += $(this).val();
			i++;
		});
		if (ids == ""){
			Dialog.alert("请选择要审批的记录");
			return;
		}
		
		url = url + "?ids=" + ids;
		toReview(url);
	}
	
	// 审批页面
	function toReview(url) {
		var diag = new Dialog();
		diag.Width = 340;
		diag.Height = 260;
		diag.Title = "审批";
		diag.URL = url;
		diag.show();
	}
	
	// 批量付款
	function toPayBatch(url) {
		var ids = "";
		var i = 0;
		var chks = $("#pageForm .tablelist input[name='chk']:checked").each(function() {
			if (i > 0){
				ids += ",";		
			}
			ids += $(this).val();
			i++;
		});
		if (ids == ""){
			Dialog.alert("请选择付款的记录");
			return;
		}
		
		url = url + "?ids=" + ids;
		toPay(url);
	}
	
	// 付款页面
	function toPay(url) {
		var diag = new Dialog();
		diag.Width = 420;
		diag.Height = 320;
		diag.Title = "付款";
		diag.URL = url;
		diag.show();
	}
	
</script>
</body>
</html>
