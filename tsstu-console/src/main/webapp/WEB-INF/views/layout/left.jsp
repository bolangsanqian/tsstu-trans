<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="static/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function($){	
	//导航切换
	$(".menuson .header").click(function(e){
		e.stopPropagation();
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		$parent.addClass("active");
		if(!!$(this).next('.sub-menus').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.sub-menus').hide();
			}else{
				$parent.addClass("open").find('.sub-menus').show();	
			}
		}
	});

    // 二级菜单点击
    $('.menuson li').click(function(e) {
    	if ($(this).hasClass("active")) {
			top.window.frame.reloadCurrentFrame();
			return;
    	}
		//$(".menuson li.active").removeClass("active")
        var name = $(this).find('a').eq(0).attr('attr-name');
        if(!name){
           name = $(this).find('a').eq(0).text()
        }
        var menu_id = $(this).find('a').eq(0).attr('attr-menu_id');
        top.window.frame.openTab({
        	menu_id: menu_id,
            name: name,
            label: $(this).find('a').eq(0).text(),
            url: $(this).find('a').eq(0).attr('attr-href')
        });
        $(this).addClass("active");
        return false;
    });
	
	//三级菜单点击
	/*
	$('.sub-menus li').click(function(e) {
        $(".sub-menus li.active").removeClass("active")
	 	$(this).addClass("active");
    });*/
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});

    function toValidId(_id){
        _id = _id + '';
        var result = '';
        if(_id && _id.length){
            for(var i = 0, len = _id.length; i < len; i++){
                result += _id.charAt(i).charCodeAt().toString(36)+'_';
            }
            _id = result;
            return result;
        }
    }

    $.each($('li'), function (argument) {
        var menu_id = $(this).find('a').eq(0).attr('attr-menu_id');
        var id = toValidId(menu_id);
        $(this)[0].id = id;
    })
})	
</script>
</head>
<body style="background:#f0f9fd;">
	<dl class="leftmenu">
		<c:forEach items="${menuList }" var="menu_1" varStatus="status_1">
			<dd>
				<div class="title">
					<span><img src="static/images/leftico02.png" /></span>${menu_1.name }
				</div>
				<c:if test="${not empty menu_1.list }">
					<ul class="menuson">
					<c:forEach items="${menu_1.list }" var="menu_2" varStatus="status_2">
						<li>
							<div>
								<cite></cite><a attr-href="${menu_2.url }" attr-menu_id="${menu_2.id }" target="rightFrame">${menu_2.name }</a><i></i>
							</div>
							<c:if test="${not empty menu_2.list }">
								<ul class="sub-menus">
								<c:forEach items="${menu_2.list }" var="menu_3" varStatus="status_3">
									<li><a href="javascript:;" attr-href="${menu_3.url }" attr-menu_id="${menu_3.id }">${menu_3.name }</a></li>
								</c:forEach>
								</ul>
							</c:if>
						</li>
					</c:forEach>
					</ul>
				</c:if>
			</dd>
		</c:forEach>
		<%-- <dd>
			<div class="title">
				<span><img src="static/images/leftico02.png" /></span>功能演示
			</div>
			<ul class="menuson">
				<li><cite></cite><a attr-href="demo/table" target="rightFrame">表格</a><i></i></li>
				<li><cite></cite><a attr-href="demo/table_tag" target="rightFrame">分页自定义标签</a><i></i></li>
				<li><cite></cite><a attr-href="demo/form" target="rightFrame">表单</a><i></i></li>
				<li><cite></cite><a attr-href="demo_user/index" target="rightFrame">用户管理演示</a><i></i></li>
			</ul>
		</dd> --%>
	</dl>

</body>
</html>
