<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
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
		$(".select-ui").uedSelect({
			width : 172
		});
		$("#select_page_size").uedSelect({
			width : 58
		});
		
	});
	function moreSearch() {
		$(".seachform2").toggle();
	}
	
	function open_win() {
		var diag = new Dialog();
		diag.Id = "win_001";
		diag.Width = 680;
		diag.Height = 500;
		diag.Title = "弹窗1";
		diag.URL = "${basePath }demo/form_win";
		diag.show();
	}
	
	function open_win2() {
		var diag = new Dialog();
		diag.Id = "win_002";
		diag.Width = 680;
		diag.Height = 500;
		diag.Title = "弹窗2";
		diag.URL = "${basePath }demo/form_win2";
		diag.show();
	}
	
	function open_win3() {
		var diag = new Dialog();
		diag.Id = "win_003";
		diag.Width = 450;
		diag.Height = 500;
		diag.Title = "弹窗3";
		diag.URL = "${basePath }demo/form_win3";
		diag.show();
	}
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
    	<div class="formtitle1">
	    	<span>查询条件</span>
	    	<!-- 
			<div class="nav-search">
				<label class="input-icon">
					<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="">
					<i class="ace-icon fa fa-search nav-search-icon"></i>
				</label>
			</div>
			 -->
    	</div>
    	<ul class="seachform1">
		    <li><label>项目名称</label><input name="" type="text" class="scinput1" /></li>
		    <li><label>学校名称</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>
		    <li><label>项目状态</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>    
		    <li><label>项目状态</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>   
		    <li><label>项目状态</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>  
		    <li><label>项目状态</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>   
		    <li><label>项目状态</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>  
		    <li><label>项目状态</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>   
		    <li><label>项目状态</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>   
    	</ul>
	    <ul class="seachform2" style="height: 120px;">
		    <li><label>项目编号</label><input name="" type="text" class="scinput1" /></li>
		    <li><label>项目类型</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>
		    
		    <li><label>项目领域</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>
		    <li><label>项目领域</label>  
			    <div class="vocation">
				    <select class="select-ui select3">
					    <option>全部</option>
					    <option>其他</option>
				    </select>
			    </div>
		    </li>
	    
		    <li><label>立项部门</label><input name="" type="text" class="scinput1" /></li>
		    <li><label>经费范围</label><input name="" type="text" class="scinput1" /></li>
		    <li><label>-</label><input name="" type="text" class="scinput1" /></li>
		    <li><label>负责人</label><input name="" type="text" class="scinput1" /></li>
		    <li><label>立项时间</label><input name="" type="text" class="scinput1" /></li>
		    <li><label>-</label><input name="" type="text" class="scinput1" /></li>      
	    </ul>
	    
		<!-- 操作按钮 -->
	    <div class="tools">
	    	<ul class="toolbar1">
		        <li class="click"><span><img src="static/images/t01.png" /></span>添加</li>
		        <li class="click"><span><img src="static/images/t02.png" /></span>修改</li>
		        <li onclick="open_win()"><span><img src="static/images/t03.png" /></span>弹窗1</li>
		        <li onclick="open_win2()"><span><img src="static/images/t04.png" /></span>弹窗2</li>
		        <li onclick="open_win3()"><span><img src="static/images/t04.png" /></span>弹窗3</li>
	        </ul>
	        <ul class="toolbar1">
	        	<li onclick="moreSearch();"><span><img src="static/images/t05.png" /></span>设置</li>
	        </ul>
	    </div>
	    
	    <!-- 表格列表 -->
	    <table class="tablelist">
	    	<thead>
		    	<tr>
			        <th><label><input id="chk_all" name="chk_all" type="checkbox"/></label></th>
			        <th><label>编号<i class="sort"><img src="static/images/px.gif" /></i></label></th>
			        <th><label>标题</label></th>
			        <th><label>用户</label></th>
			        <th><label>籍贯</label></th>
			        <th><label>性别</label></th>
			        <th><label>年龄</label></th>
			        <th><label>邮箱</label></th>
			        <th><label>手机号码</label></th>
			        <th><label>身份证号码</label></th>
			        <th><label>发布时间</label></th>
			        <th><label>是否审核</label></th>
			        <th><label>操作</label></th>
		        </tr>
	        </thead>
	        <tbody>
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130908</td>
		        <td>王金平幕僚：马英九声明字字见血 人活着没意思</td>
		        <td>admin</td>
		        <td>江苏南京</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-09 15:05</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink"> 删除</a></td>
	        </tr> 
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130907</td>
		        <td>温州19名小学生中毒流鼻血续：周边部分企业关停</td>
		        <td>uimaker</td>
		        <td>山东济南</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-08 14:02</td>
		        <td>未审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130906</td>
		        <td>社科院:电子商务促进了农村经济结构和社会转型</td>
		        <td>user</td>
		        <td>江苏无锡</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-07 13:16</td>
		        <td>未审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130905</td>
		        <td>江西&quot;局长违规建豪宅&quot;：局长检讨</td>
		        <td>admin</td>
		        <td>北京市</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-06 10:36</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130904</td>
		        <td>中国2020年或迈入高收入国家行列</td>
		        <td>uimaker</td>
		        <td>江苏南京</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-05 13:25</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130903</td>
		        <td>深圳地铁车门因乘客拉闸打开 3人被挤入隧道</td>
		        <td>user</td>
		        <td>广东深圳</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-04 12:00</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130902</td>
		        <td>33次地表塌陷 村民不敢下地劳作(图)</td>
		        <td>admin</td>
		        <td>湖南长沙</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-03 00:05</td>
		        <td>未审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130901</td>
		        <td>医患关系：医生在替改革不彻底背黑锅</td>
		        <td>admin</td>
		        <td>江苏南京</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-02 15:05</td>
		        <td>未审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130900</td>
		        <td>山东章丘公车进饭店景点将自动向监控系统报警</td>
		        <td>uimaker</td>
		        <td>山东滨州</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-01 10:26</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>      
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130900</td>
		        <td>山东章丘公车进饭店景点将自动向监控系统报警</td>
		        <td>uimaker</td>
		        <td>山东滨州</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-01 10:26</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>  
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130900</td>
		        <td>山东章丘公车进饭店景点将自动向监控系统报警</td>
		        <td>uimaker</td>
		        <td>山东滨州</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-01 10:26</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>  
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130900</td>
		        <td>山东章丘公车进饭店景点将自动向监控系统报警</td>
		        <td>uimaker</td>
		        <td>山东滨州</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-01 10:26</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>  
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130900</td>
		        <td>山东章丘公车进饭店景点将自动向监控系统报警</td>
		        <td>uimaker</td>
		        <td>山东滨州</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-01 10:26</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>  
	        <tr>
		        <td><input name="chk" type="checkbox" value="" /></td>
		        <td>20130900</td>
		        <td>山东章丘公车进饭店景点将自动向监控系统报警</td>
		        <td>uimaker</td>
		        <td>山东滨州</td>
		        <td>男</td>
		        <td>26</td>
		        <td>18222222222@qq.com</td> <td>18222222222</td>
		        <td>43200125414523112</td>
		        <td>2013-09-01 10:26</td>
		        <td>已审核</td>
		        <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink">删除</a></td>
	        </tr>    
	        </tbody>
	    </table>
	    <div class="pagin">
	    	<div class="message" style="float: left;">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
	        <ul class="paginList" style="float: right;">
		        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
		        <li class="paginItem"><a href="javascript:;">首页</a></li>
		        <li class="paginItem"><a href="javascript:;">上页</a></li>
		        <li class="paginItem"><a href="javascript:;">1</a></li>
		        <li class="paginItem current"><a href="javascript:;">2</a></li>
		        <li class="paginItem"><a href="javascript:;">3</a></li>
		        <li class="paginItem"><a href="javascript:;">4</a></li>
		        <li class="paginItem"><a href="javascript:;">5</a></li>
		        <li class="paginItem more"><a href="javascript:;">...</a></li>
		        <li class="paginItem"><a href="javascript:;">10</a></li>
		        <li class="paginItem"><a href="javascript:;">下页</a></li>
		        <li class="paginItem"><a href="javascript:;">尾页</a></li>
		        <li class="paginItem"><a href="javascript:;">共10页</a></li>
		        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
	        </ul>
	    </div>
	</div>
	<div style="padding: 10px;margin-bottom: 30px;"></div>
</body>
</html>
