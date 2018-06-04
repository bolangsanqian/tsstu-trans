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
		
		//创建时间
		$('#create_time').dateRangePicker({language:'cn'});
 
	});
</script>
</head>
<body  style="min-width: 900px;">
	<!-- 当前位置 -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="javascript:void();">会员管理</a></li>
		    <li><a href="javascript:void();">签约管理(会员)</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="member_contract_bank/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input id="keywords" name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入真实姓名、身份证、银行编号、银行卡号、银行支行、审批备注" title="真实姓名、身份证、银行编号、银行卡号、银行支行、审批备注"/></li>
			    <li>
			    	<label>所属会员：</label>
			    	<div class="vocation">
			    		<select id="member_id" name="member_id" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${DICT_MEMBER }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.member_id eq item.item_value}">selected</c:if>>${item.item_value} - ${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li><label>身份证：</label><input id="identity" name="identity" type="text" class="scinput1" value="${pageInfo.pageData.identity }" placeholder="这里输入身份证" title="身份证"/></li>
			    <li><label>银行卡号：</label><input id="bankcard_no" name="bankcard_no" type="text" class="scinput1" value="${pageInfo.pageData.bankcard_no }" placeholder="这里输入银行卡号" title="银行卡号"/></li>
			    <li>
			    	<label>所属银行：</label>
			    	<div class="vocation">
			    		<select id="bank_no" name="bank_no" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${DICT_CONTRACT_BANK }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.bank_no eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>省份：</label>
			    	<div class="vocation">
			    		<select id="province_code" name="province_code" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${provRegionList }" var="item" varStatus="status">
								<option value="${item.code}" <c:if test="${pageInfo.pageData.province_code eq item.code}">selected</c:if>>${item.name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>状态：</label>
			    	<div class="vocation">
			    		<select id="status" name="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_CONTRACT_BANK_STATUS }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.status eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li><label>创建时间：</label><input id="create_time" name="create_time" type="text" class="scinput1" value="${pageInfo.pageData.create_time }" placeholder="这里输入创建时间" title="创建时间"/></li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="member_contract_bank:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
			        <tsstu:hasPermission sign="member_contract_bank:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }member_contract_bank/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>会员名称</label></th>
				        <th><label>真实姓名</label></th>
				        <th><label>身份证</label></th>
				        <th><label>银行编号</label></th>
				        <th><label>银行卡号</label></th>
				        <th><label>银行支行</label></th>
				        <th><label>状态</label></th>
				        <th><label>创建时间</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="member_contract_bank:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>
    								<tsstu:hasPermission sign="member_contract_bank:query"><a class="link-a" href="javascript:void(0);" onclick="toDetail('${basePath}/member_contract_bank/detail?id=${model.id }')">
    									<c:forEach items="${DICT_MEMBER }" var="item" varStatus="status">
											<c:if test="${model.member_id eq item.item_value}">${item.item_name}</c:if>
										</c:forEach>
    								</a></tsstu:hasPermission>
	    							<tsstu:notPermission sign="member_contract_bank:query">
	    								<c:forEach items="${DICT_MEMBER }" var="item" varStatus="status">
											<c:if test="${model.member_id eq item.item_value}">${item.item_name}</c:if>
										</c:forEach>
	    							</tsstu:notPermission>
	    						</td>
	    						<td>${model.real_name }</td>
    							<td>${model.identity }</td>
								<td>
									<c:forEach items="${DICT_CONTRACT_BANK }" var="dict" varStatus="dict_status">
										<c:if test="${model.bank_no eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.bankcard_no }</td>
    							<td>${model.bank_branch }</td>
								<td>
									<c:forEach items="${D_CONTRACT_BANK_STATUS }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td><fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<c:if test="${model.status eq 0 or model.status eq 1}">
						        	<tsstu:hasPermission sign="member_contract_bank:review">
						        	<a href="javascript:review('${basePath }member_contract_bank/review?id=${model.id }')" class="tablelink"> 审批</a>
						        	</tsstu:hasPermission>
						        	</c:if>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="member_contract_bank:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="member_contract_bank:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="member_contract_bank:query">没有相关数据</tsstu:hasPermission>
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
