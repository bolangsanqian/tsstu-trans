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
		
		//申请时间
		$('#apply_time').dateRangePicker({language:'cn'});
 
	});
</script>
</head>
<body  style="min-width: 900px;">
	<!-- 当前位置 -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="javascript:void();">XXX管理</a></li>
		    <li><a href="javascript:void();">经纪人申请</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="broker_apply/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>用户名：</label><input id="username" name="username" type="text" class="scinput1" value="${pageInfo.pageData.username }" placeholder="这里输入用户名" title="用户名"/></li>
			    <li><label>申请时间：</label><input id="apply_time" name="apply_time" type="text" class="scinput1" value="${pageInfo.pageData.apply_time }" placeholder="这里输入申请时间" title="申请时间"/></li>
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
			    	<label>审批状态 ：</label>
			    	<div class="vocation">
			    		<select id="review_status" name="review_status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_REVIEW_STATUS }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.review_status eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="broker_apply:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="broker_apply:review">
			        <li class="click" onclick="review('${basePath }broker_apply/review', 380, 360)"><span><img src="static/images/icon/icon_add.png" /></span>审批</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="broker_apply:del">
			        <li class="click" onclick="delBatch('${basePath }broker_apply/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="broker_apply:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }broker_apply/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
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
				        <th><label>用户名</label></th>
				        <th><label>申请时间</label></th>
				        <th><lable>审批状态</lable></th>
				        <th><label>审批人</label></th>
				        <th><label>审批时间</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="broker_apply:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
						        <td>${model.mobile }</td>
    							<td>${model.nick_name }</td>
    							<td>
    								<tsstu:hasPermission sign="broker_apply:query"><a class="link-a" href="javascript:void(0);" onclick="toDetail('${basePath}/broker_apply/detail?id=${model.id }')">${model.username }</a></tsstu:hasPermission>
	    							<tsstu:notPermission sign="broker_apply:query">${model.username }</tsstu:notPermission>
	    						</td>
								<td><fmt:formatDate value="${model.apply_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<c:forEach items="${D_REVIEW_STATUS }" var="dict" varStatus="dict_status">
										<c:if test="${model.review_status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.review_by_name }</td>
								<td><fmt:formatDate value="${model.review_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<c:if test="${model.review_status eq 2 }">
						        	<tsstu:hasPermission sign="broker_apply:del">
						        	<a href="javascript:del('${basePath }broker_apply/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	</c:if>
						        	<c:if test="${model.review_status eq 0}">
						        	<tsstu:hasPermission sign="broker_apply:review">
						        	<a href="javascript:toReview('${basePath }broker_apply/review?ids=${model.id }')" class="tablelink"> 审批</a>
						        	</tsstu:hasPermission>
						        	</c:if>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="broker_apply:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="broker_apply:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="broker_apply:query">没有相关数据</tsstu:hasPermission>
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
</script>
</body>
</html>
