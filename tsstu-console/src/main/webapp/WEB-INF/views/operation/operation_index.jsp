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
		    <li><a href="javascript:void();">运营中心</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="operation/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入名称、公司名称、公司领导" title="名称、公司名称、公司领导"/></li>
			    <li><label>手机号码：</label><input name="mobile" type="text" class="scinput1" value="${pageInfo.pageData.mobile }" placeholder="这里输入手机号码" title="手机号码"/></li>
			    <li>
			    	<label>状态：</label>
			    	<div class="vocation">
			    		<select id="status" name="status" class="select3">
					    	<option value="">请选择</option>
						    <c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="dict_status">
								<option value="${dict.item_value}" <c:if test="${pageInfo.pageData.status eq dict.item_value}">selected</c:if>>${dict.item_name}</option>
							</c:forEach>
					    </select>
			    	</div>
			    </li>
	    	</ul>
			<!-- 操作按钮 -->
		    <div class="tools">
		        <ul class="toolbar1">
		    		<tsstu:hasPermission sign="operation:query">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
		    		</tsstu:hasPermission>
		    		<tsstu:hasPermission sign="operation:add">
			        <li class="click" onclick="toAdd('${basePath }operation/add', 600, 310)"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="operation:upload_wx_qrcode">
			        <li class="click" onclick="to_upload_wx_qrcode('${basePath }member/upload_wx_qrcode')"><span><img src="static/images/icon/icon_add.png" /></span>公众号二维码</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="operation:del">
			        <li class="click" onclick="delBatch('${basePath }operation/delBatch')"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        </tsstu:hasPermission>
			        <tsstu:hasPermission sign="operation:exportExcel">
			        <li class="click" onclick="exportExcel('${basePath }operation/exportExcel')"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
			        </tsstu:hasPermission>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th style="width: 15px;"><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th style="width: 25px;text-align: center">序号</th>
				        <th><label>编号</label></th>
				        <th><label>名称</label></th>
				        <th><label>公司名字</label></th>
				        <th><label>公司领导</label></th>
				        <th class="orderBy" attr-name="mobile"><label>手机号码<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'mobile' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>证件类型</label></th>
				        <th><label>证件号</label></th>
				        <th class="orderBy" attr-name="balance"><label>余额<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'balance' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="frozen_money"><label>冻结资金<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'frozen_money' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>状态</label></th>
				        <th class="orderBy" attr-name="create_time"><label>创建时间<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'create_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
		        		<tsstu:hasPermission sign="operation:query">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td align="center">${pageInfo.startIndex + status.index }</td>
    							<td>${model.id }</td>
    							<td>
    								<tsstu:hasPermission sign="operation:query"><a class="link-a" href="javascript:void(0);" onclick="toDetail('${basePath}/operation/detail?id=${model.id }')">${model.name }</a></tsstu:hasPermission>
	    							<tsstu:notPermission sign="operation:query">${model.name }</tsstu:notPermission>
	    						</td>
    							<td>${model.company_name }</td>
    							<td>${model.company_leader }</td>
    							<td>${model.mobile }</td>
								<td>
									<c:forEach items="${MEMBER_IDENTITY_TYPE }" var="dict" varStatus="dict_status">
										<c:if test="${model.identity_type eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
    							<td>${model.identity }</td>
    							<td>${model.balance }</td>
    							<td>${model.frozen_money }</td>
								<td>
									<c:forEach items="${D_ENABLE_DISABLE }" var="dict" varStatus="dict_status">
										<c:if test="${model.status eq dict.item_value}">${dict.item_name}</c:if>
									</c:forEach>
								</td>
								<td><fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>
						        	<tsstu:hasPermission sign="operation:del">
						        	<a href="javascript:del('${basePath }operation/del?id=${model.id }')" class="tablelink"> 删除</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="operation:edit">
						        	<a href="javascript:toEdit('${basePath }operation/edit?id=${model.id }', 600, 310)" class="tablelink"> 修改</a>
						        	</tsstu:hasPermission>
						        	<tsstu:hasPermission sign="member:account">
						        	<c:choose>
										<c:when test="${model.user_id gt 0 }">
							        		<a href="javascript:toEdit('${basePath }member/account?id=${model.id }', 380, 280)" class="tablelink"> 查看账号</a>
							        	</c:when>
							        	<c:otherwise>
							        		<a href="javascript:toEdit('${basePath }member/account?user_type=6&id=${model.id }', 380, 280)" class="tablelink"> 创建账号</a>
							        	</c:otherwise>						        	
						        	</c:choose>
						        	</tsstu:hasPermission>
						        </td>
					        </tr> 
			        	</c:forEach>
			        	</tsstu:hasPermission>
			        	<tsstu:notPermission sign="operation:query">
		        		<tr>
							<td colspan="100" align="center">您无权查看</td>
						</tr>
			        	</tsstu:notPermission>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">
								<tsstu:notPermission sign="operation:query">您无权查看</tsstu:notPermission>
								<tsstu:hasPermission sign="operation:query">没有相关数据</tsstu:hasPermission>
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
