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
		
		//复选框全选/反选控制
		$("#chk_all").on("click", function() {
			$(":checkbox[name=chk]").prop("checked", $(this).is(":checked"));
			if ($(this).is(":checked")) {
				$(this).closest("table").find('tbody > tr').addClass("active");
			} else {
				$(this).closest("table").find('tbody > tr').removeClass("active");
			}
		});
		$(":checkbox[name=chk]").on("click", function() {
			var active_class = $(this).is(":checked") ? "active" : "";
			$(this).closest("tr").toggleClass(active_class);
			$("#chk_all").prop("checked", ($(":checkbox[name=chk]").not(":checked").length > 0 ? false : true));
		});
		
		//初始化表头最新宽度
		$(".tablelist th").each(function() {
			var w = $(">label", this).width();
			$(this).css("min-width", w);
		});
		
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
		    <li><a href="#">首页</a></li>
		    <li><a href="#">数据表</a></li>
	    </ul>
    </div>
	    
    <div class="rightinfo">
    	<form id="pageForm" action="demo_user/index" method="post">
	    	<div class="formtitle1">
		    	<span>查询条件</span>
	    	</div>
	    	<ul class="seachform1">
			    <li><label>模糊查询：</label><input name="keywords" type="text" class="scinput1" value="${pageInfo.pageData.keywords }" placeholder="这里输入用户名、手机号码、邮箱" title="用户名、手机号码、邮箱"/></li>
			    <li><label>用户名：</label><input name="username" type="text" class="scinput1" value="${pageInfo.pageData.username }" placeholder="这里输入用户名" title="用户名"/></li>
			    <li><label>手机号码：</label><input name="mobile" type="text" class="scinput1" value="${pageInfo.pageData.mobile }" placeholder="这里输入手机号码" title="手机号码"/></li>
			    <li><label>邮箱：</label><input name="email" type="text" class="scinput1" value="${pageInfo.pageData.email }" placeholder="这里输入邮箱" title="邮箱"/></li>
			    <li><label>性别：</label>  
				    <div class="vocation">
					    <select name="sex" class="select3">
						    <option>全部</option>
						    <option>男</option>
						    <option>女</option>
					    </select>
				    </div>
			    </li>
	    	</ul>
		    
			<!-- 操作按钮 -->
		    <div class="tools">
		    	<ul class="toolbar1">
		    		<li class="click" onclick="search()"><span><img src="static/images/icon/icon_query.png" /></span>查询</li>
			        <li class="click" onclick="toAdd()"><span><img src="static/images/icon/icon_add.png" /></span>添加</li>
			        <li class="click" onclick="delBatch()"><span><img src="static/images/icon/icon_del.png" /></span>删除</li>
			        <li class="click" onclick="exportExcel()"><span><img src="static/images/icon/icon_export.png" /></span>导出</li>
		        </ul>
		    </div>
		    
		    <!-- 表格列表 -->
		    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
				        <th class="orderBy" attr-name="id"><label>编号<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'id' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="username"><label>用户名<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'username' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="mobile"><label>手机号码<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'mobile' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="email"><label>邮箱<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'email' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="sex"><label>性别<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'sex' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="birthday"><label>生日<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'birthday' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy" attr-name="register_time"><label>注册日期<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'register_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy"><label>注册IP</label></th>
				        <th class="orderBy" attr-name="last_login_time"><label>最后登录日期<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'last_login_time' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th class="orderBy"><label>最后登录IP</label></th>
				        <th class="orderBy right" attr-name="integral"><label>积分<i class="sort"><img src="static/images/icon/<c:choose><c:when test="${pageInfo.orderBy ne 'integral' }">default</c:when><c:otherwise>${pageInfo.sort_direction}</c:otherwise></c:choose>.png" /></i></label></th>
				        <th><label>操作</label></th>
			        </tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${not empty pageInfo.result}">
			        	<c:forEach items="${pageInfo.result }" var="model" varStatus="status">
			        		<tr>
						        <td><input name="chk" type="checkbox" value="${model.id }" /></td>
						        <td>${model.id }</td>
						        <td>${model.username }</td>
						        <td>${model.mobile }</td>
						        <td>${model.email }</td>
						        <td>${model.sex }</td>
						        <td><fmt:formatDate value="${model.birthday }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td><fmt:formatDate value="${model.register_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>${model.register_ip }</td>
						        <td><fmt:formatDate value="${model.last_login_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						        <td>${model.last_login_ip }</td>
						        <td class="right">${model.integral }</td>
						        <td>
						        	<a href="javascript:del(${model.id})" class="tablelink"> 删除</a>
						        </td>
					        </tr> 
			        	</c:forEach>
			        </c:when>
		        	<c:otherwise>
			        	<tr>
							<td colspan="100" align="center">没有相关数据</td>
						</tr>
		        	</c:otherwise>
		        </c:choose>
		        </tbody>
		    </table>
		    <tsstu:page form="pageForm" />
		</form>
	</div>
<script type="text/javascript">

	//添加页面
	function toAdd() {
		var diag = new Dialog();
		diag.Width = 430;
		diag.Height = 390;
		diag.Title = "添加";
		diag.URL = "${basePath }demo_user/add";
		diag.CancelEvent = function(){
			search();
		}
		diag.show();
	}
	
	//修改页面
	function toEdit() {
		var diag = new Dialog();
		diag.Width = 430;
		diag.Height = 390;
		diag.Title = "修改";
		diag.URL = "${basePath }demo_user/edit";
		diag.show();
	}
	
	//删除
	function del(id) {
		Dialog.confirm('确定要删除该条记录？', function() {
			var url = "${basePath }demo_user/del?id=" + id;
			$.get(url, function(data) {
				if (data.ok) {
					search();		
				}
			})
		});
	}
	
	//批量删除
	function delBatch() {
		var ids = [];
		var i = 0;
		var chks = $(".tablelist input[name='chk']:checked").each(function() {
			ids[i] = $(this).val();
			i++;
		});
		if (ids.length < 1){
			Dialog.alert("请选择要删除的记录");
			return;
		}
		Dialog.confirm('确定要删除该条记录？', function() {
			var url = "${basePath }demo_user/delBatch";
			$.ajax({  
			    url: url,  
			    data: {"ids": ids },  
			    dataType: "json",  
			    type: "POST",  
			    traditional: true,  
			    success: function (data) {  
			    	if (data.ok) {
						search();		
					} 
			    }  
			});  
		});
	}
	
	function exportExcel() {
		location.href = "${basePath }demo_user/exportExcel";
	}

</script>
</body>
</html>
