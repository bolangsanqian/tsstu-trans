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
		    <li><a href="javascript:void();">加减币管理(会员)</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="member_fund_change/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input id="keywords" name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入加减币流水号、申请原因、申请时间、状态、审核备注" title="加减币流水号、申请原因、申请时间、状态、审核备注"/></li>
			    <li><label>流水号：</label><input id="flow_no" name="flow_no" type="text" class="scinput1" value="${pageInfo.pageData.flow_no }" placeholder="这里输入加减币流水号" title="加减币流水号"/></li>
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
			    <li>
			    	<label>状态：</label>
			    	<div class="vocation">
			    		<select id="change_type" name="change_type" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_FUND_CHANGE_STATUS }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.change_type eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li>
			    	<label>变动类型：</label>
			    	<div class="vocation">
			    		<select id="status" name="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_MEMBER_FUND_CHANGE_TYPE }" var="item" varStatus="status">
								<option value="${item.item_value}" <c:if test="${pageInfo.pageData.status eq item.item_value}">selected</c:if>>${item.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
			    <li><label>申请时间：</label><input id="apply_time" name="apply_time" type="text" class="scinput1" value="${pageInfo.pageData.apply_time }" placeholder="这里输入申请时间" title="申请时间"/></li>
			    
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		        	<tsstu:hasPermission sign="member_fund_change:add">
			        <li class="click" onclick="toAdd('${basePath }member_fund_change/add', 350, 310)"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
			        </tsstu:hasPermission>
		    		<tsstu:hasPermission sign="member_fund_change:add">
			        <li class="click" onclick="toAdd('${basePath }member_fund_change/add', 350, 310)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="member_fund_change:del">
			        <li class="click" onclick="delBatch('${basePath }member_fund_change/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="member_fund_change:review">
			        <li class="click" onclick="review('${basePath }member_fund_change/review')"><span><img src="static/images/icon/icon_add.png" /></span>审批</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="member_fund_change:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }member_fund_change/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
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
				        <th><label>变动类型</label></th>
				        <th><label>变动金额</label></th>
				        <th><label>申请人</label></th>
				        <th><label>申请原因</label></th>
				        <th><label>申请时间</label></th>
				        <th><label>状态</label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="member_fund_change:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>
    								<tsstu:hasPermission sign="member_fund_change:query"><a class="link-a" href="javascript:void(0);" onclick="toDetail('${basePath}/member_fund_change/detail?id=${model.id }')">${model.flow_no }</a></tsstu:hasPermission>
	    							<tsstu:notPermission sign="member_fund_change:query">${model.flow_no }</tsstu:notPermission>
	    						</td>
								<td>
									<c:forEach items="${D_FUND_CHANGE_TYPE }" var="dict" varStatus="dict_status">
										<c:if test="${model.change_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.amount }</td>
    							<td>${model.apply_id }</td>
    							<td>${model.apply_reason }</td>
								<td><fmt:formatDate value="${model.apply_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<c:forEach items="${D_FUND_CHANGE_STATUS }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
						        <td>
						        	<c:if test="${model.status eq 0 }">
							        	<tsstu:hasPermission sign="member_fund_change:del">
							        	<a href="javascript:del('${basePath }member_fund_change/del?id=${model.id }')" class="tablelink"> 删除</a>
							        	</tsstu:hasPermission>
							        	<tsstu:hasPermission sign="member_fund_change:edit">
							        	<a href="javascript:toEdit('${basePath }member_fund_change/edit?id=${model.id }', 600, 310)" class="tablelink"> 修改</a>
							        	</tsstu:hasPermission>
						        	</c:if>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="member_fund_change:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="member_fund_change:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="member_fund_change:query">没有相关数据</tsstu:hasPermission>
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
	//审批页面
	function review(url) {
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
		var diag = new Dialog();
		diag.Width = 340;
		diag.Height = 260;
		diag.Title = "审批";
		diag.URL = "${basePath}member_fund_change/review?ids=" + ids;
		diag.show();
	}
</script>
</body>
</html>
