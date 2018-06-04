<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tsstu" uri="http://www.tsstu.com/wp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
<script type="text/javascript" src="http://www.bootcss.com/p/buttons/js/buttons.js?t=<%= new Date().getTime()%>"></script>
<link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</style>
<script type="text/javascript">
	$(function($){
		//初始化下拉框
		$(".select3").uedSelect({
			width : 172
		});
		
		$('#register_time').dateRangePicker({language:'cn'});
	});
</script>
</head>
<body  style="min-width: 900px;">
	<!-- 当前位置 -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="javascript:void();">客户管理</a></li>
		    <li><a href="javascript:void();">客户信息</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="customer/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入是客户编号、手机号码、昵称、分享码" title="客户编号、手机号码、昵称、分享码"/></li>
			    <li><label>手机号码：</label><input name="mobile" type="text" class="scinput1" value="${pageInfo.pageData.mobile }" placeholder="这里输入手机号码" title="手机号码"/></li>
			    <li><label>客户编号：</label><input name="cust_no" type="text" class="scinput1" value="${pageInfo.pageData.cust_no }" placeholder="这里输入客户编号" title="客户编号"/></li>
			    <li><label>经纪人手机：</label><input name="broker_mobile" type="text" class="scinput1" value="${pageInfo.pageData.broker_mobile }" placeholder="这里输入经纪人手机" title="经纪人手机"/></li>
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
			    <li>
			    	<label>经纪人?：</label>
			    	<div class="vocation">
			    		<select id="is_borker" name="is_borker" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.is_borker eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>状态：</label>
			    	<div class="vocation">
			    		<select id="status" name="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_CUSTOMER_STATUS }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>激活?：</label>
			    	<div class="vocation">
			    		<select id="active_status" name="active_status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_CUSTOMER_ACTIVE_STATUS }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.active_status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>充值?：</label>
			    	<div class="vocation">
			    		<select id="recharge_status" name="recharge_status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_ALLOW_STATUS }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.recharge_status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>提现?：</label>
			    	<div class="vocation">
			    		<select id="withdraw_status" name="withdraw_status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_ALLOW_STATUS }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.withdraw_status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>登录?：</label>
			    	<div class="vocation">
			    		<select id="login_status" name="login_status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_ALLOW_STATUS }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.login_status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>交易?：</label>
			    	<div class="vocation">
			    		<select id="trade_status" name="trade_status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_ALLOW_STATUS }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.trade_status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li><label>注册日期：</label><input id="register_time" name="register_time" type="text" class="scinput1" value="${pageInfo.pageData.register_time }" placeholder="这里输入注册日期" title="注册日期"/></li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="customer:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
			        <tsstu:hasPermission sign="customer:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }customer/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>客户编号</label></th>
				        <th><label>手机号码</label></th>
				        <th class="orderBy" attr-name="balance"><label>余额<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'balance' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="frozen_money"><label>冻结资金<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'frozen_money' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>昵称</label></th>
				        <th><label>是否为经纪人</label></th>
				        <th class="orderBy" attr-name="status"><label>状态<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'status' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="register_time"><label>注册日期<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'register_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="last_login_time"><label>最后登录时间<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'last_login_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>分享码</label></th>
				        <th style="width: 360px">操作</th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="customer:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>
    								<tsstu:hasPermission sign="customer:query"><a class="link-a" href="javascript:void(0);" onclick="toDetail('${basePath}/customer/detail?id=${model.id }')">${model.cust_no }</a></tsstu:hasPermission>
	    							<tsstu:notPermission sign="customer:query">${model.cust_no }</tsstu:notPermission>
	    						</td>
    							<td>${model.mobile }</td>
    							<td>${model.balance }</td>
    							<td>${model.frozen_money }</td>
    							<td>${model.nick_name }</td>
								<td>
									<c:forEach items="${D_TRUE_FALSE }" var="dict" varStatus="dict_status">
										<c:if test="${model.is_borker eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${D_CUSTOMER_STATUS }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td><fmt:formatDate value="${model.register_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><fmt:formatDate value="${model.last_login_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    							<td>${model.share_code }</td>
    							<td>
    								<c:if test="${model.status ne 2 }">
	    								<!-- 禁用、启用 -->
	    								<tsstu:hasPermission sign="customer:disable_account">
	    								<c:if test="${model.status eq 1 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/disable_account?id=${model.id }', '禁用账号')" class="tablelink"> 禁用账号</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	<tsstu:hasPermission sign="customer:enable_account">
							        	<c:if test="${model.status eq 0 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/enable_account?id=${model.id }', '启用账号')" class="tablelink"> 启用账号</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	
							        	<!-- 交易 -->
							        	<tsstu:hasPermission sign="customer:stop_trade">
							        	<c:if test="${model.trade_status eq 1 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/stop_trade?id=${model.id }', '禁止交易')" class="tablelink"> 禁止交易</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	<tsstu:hasPermission sign="customer:allow_trade">
							        	<c:if test="${model.trade_status eq 0 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/allow_trade?id=${model.id }', '容许交易')" class="tablelink"> 容许交易</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	
							        	<!-- 充值 -->
							        	<tsstu:hasPermission sign="customer:stop_recharge">
							        	<c:if test="${model.recharge_status eq 1 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/stop_recharge?id=${model.id }', '禁止充值')" class="tablelink"> 禁止充值</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	<tsstu:hasPermission sign="customer:allow_recharge">
							        	<c:if test="${model.recharge_status eq 0 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/allow_recharge?id=${model.id }', '容许充值')" class="tablelink"> 容许充值</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	
							        	<!-- 提现 -->
							        	<tsstu:hasPermission sign="customer:stop_withdraw">
							        	<c:if test="${model.withdraw_status eq 1 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/stop_withdraw?id=${model.id }', '禁止提现')" class="tablelink"> 禁止提现</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	<tsstu:hasPermission sign="customer:allow_withdraw">
							        	<c:if test="${model.withdraw_status eq 0 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/allow_withdraw?id=${model.id }', '容许提现')" class="tablelink"> 容许提现</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	
							        	<!-- 登录 -->
							        	<tsstu:hasPermission sign="customer:stop_login">
							        	<c:if test="${model.login_status eq 1 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/stop_login?id=${model.id }', '禁止登录')" class="tablelink"> 禁止登录</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	<tsstu:hasPermission sign="customer:allow_login">
							        	<c:if test="${model.login_status eq 0 }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/allow_login?id=${model.id }', '容许登录')" class="tablelink"> 容许登录</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	
							        	<!-- 解绑微信 -->
							        	<tsstu:hasPermission sign="customer:unbind_wx">
							        	<c:if test="${not empty model.wx_openid }">
							        	<a href="javascript:ajax_refresh('${basePath }customer/unbind_wx?id=${model.id }', '解绑微信')" class="tablelink"> 解绑微信</a>
							        	</c:if>
							        	</tsstu:hasPermission>
							        	
							        	<!-- 注销账户-->
	    								<tsstu:hasPermission sign="customer:cancel_account">
							        	<a href="javascript:ajax_refresh('${basePath }customer/cancel_account?id=${model.id }', '注销账户')" class="tablelink"> 注销账户</a>
							        	</tsstu:hasPermission>
						        	</c:if>
    							</td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="customer:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="customer:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="customer:query">没有相关数据</tsstu:hasPermission>
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
