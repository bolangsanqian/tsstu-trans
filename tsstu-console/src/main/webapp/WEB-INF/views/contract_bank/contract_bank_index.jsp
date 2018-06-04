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
		
		$('#create_time').dateRangePicker({language:'cn'});
		
	});
</script>
</head>
<body  style="min-width: 900px;">
	<!-- 当前位置 -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="javascript:void();">客户管理</a></li>
		    <li><a href="javascript:void();">签约管理</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="contract_bank/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入真实姓名、客户昵称、证件号、身份证、银行卡号" title="真实姓名、客户昵称、证件号、身份证、银行卡号"/></li>
			    <li><label>手机号码：</label><input name="mobile" type="text" class="scinput1" value="${pageInfo.pageData.mobile }" placeholder="这里输入手机号码" title="手机号码"/></li>
			    <li>
			    	<label>省份：</label>
			    	<div class="vocation">
			    		<select id="province_code" name="province_code" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${provRegionList }" var="dict" varStatus="dict_status">
								<option value="${dict.code}" <c:if test="${pageInfo.pageData.province_code eq dict.code}">selected</c:if>>${dict.name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>银行：</label>
			    	<div class="vocation">
			    		<select id="bank_id" name="bank_id" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${DICT_CONTRACT_BANK }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.bank_no eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>签约状态：</label>
			    	<div class="vocation">
			    		<select id="status" name="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_CONTRACT_BANK_STATUS }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
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
			    <li><label>创建时间：</label><input id="create_time" name="create_time" type="text" class="scinput1" value="${pageInfo.pageData.create_time }" placeholder="这里输入创建时间" title="创建时间"/></li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="contract_bank:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="contract_bank:add">
			        <li class="click" onclick="toAdd('${basePath }contract_bank/add', 600, 310)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="contract_bank:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }contract_bank/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>手机号码</label></th>
				        <th><label>客户昵称</label></th>
				        <th><label>真实姓名</label></th>
				        <th><label>身份证</label></th>
				        <th><label>银行名称</label></th>
				        <th><label>银行卡号</label></th>
				        <th><label>省份</label></th>
				        <th><label>城市</label></th>
				        <th><label>银行支行</label></th>
				        <th><label>签约状态</label></th>
				        <th><label>创建时间</label></th>
				        <th><label>最后修改时间</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="contract_bank:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>
    								<tsstu:hasPermission sign="contract_bank:query"><a class="link-a" href="javascript:void(0);" onclick="toDetail('${basePath}/contract_bank/detail?id=${model.id }')">${model.mobile }</a></tsstu:hasPermission>
	    							<tsstu:notPermission sign="contract_bank:query">${model.mobile }</tsstu:notPermission>
	    						</td>
    							<td>${model.nick_name }</td>
    							<td>${model.real_name }</td>
    							<td>${model.identity }</td>
								<td>
									<c:forEach items="${DICT_CONTRACT_BANK }" var="dict" varStatus="dict_status">
										<c:if test="${model.bank_no eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.bankcard_no }</td>
								<td>
									<c:forEach items="${provRegionList }" var="dict" varStatus="dict_status">
										<c:if test="${model.province_code eq dict.code}">${dict.name}</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${regionList }" var="dict" varStatus="dict_status">
										<c:if test="${model.city_code eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.bank_branch }</td>
								<td>
									<c:forEach items="${D_CONTRACT_BANK_STATUS }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td><fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><fmt:formatDate value="${model.last_update_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<tsstu:hasPermission sign="contract_bank:edit">
						        	<a href="javascript:toEdit('${basePath }contract_bank/edit?id=${model.id }', 600, 310)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        	<c:if test="${model.status eq 0 or model.status eq 1}">
						        	<tsstu:hasPermission sign="contract_bank:review">
						        	<a href="javascript:review('${basePath }contract_bank/review?id=${model.id }')" class="tablelink"> 审批</a>
						        	</tsstu:hasPermission>
						        	</c:if>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="contract_bank:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="contract_bank:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="contract_bank:query">没有相关数据</tsstu:hasPermission>
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
