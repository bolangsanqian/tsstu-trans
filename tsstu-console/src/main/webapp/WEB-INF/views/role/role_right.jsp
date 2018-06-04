<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tsstu" uri="http://www.tsstu.com/wp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
<link rel="stylesheet" href="static/plugins/zTree/3.5/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="static/plugins/zTree/3.5/jquery.ztree.core.js"></script>
<script type="text/javascript" src="static/plugins/zTree/3.5//jquery.ztree.excheck.js"></script>
<script type="text/javascript">
var zTree;
$(function($) {
	var setting = {
	    showLine: true,
	    checkable: true,
	    check: {
			enable: true
		},
	    data: {
	    	key: {
	    		checked: "hasPermission"
	    	},
	    	simpleData: {
				enable: true,
				pIdKey: "pid"
			}
	    }
	};
	var zTreeNodes = ${permissionList};
	$.fn.zTree.init($("#tree"), setting, zTreeNodes);
});
</script>
<style type="text/css">
.ztree:before {
		border: 0px;
	}
</style>
</head>
<body>
	<div class="formbody">
		<input type="hidden" name="roleId" id="roleId" value="${roleId }">
		<table class="form_table">
			<tr>
				<td>
					<div style="overflow: auto;height:400px;width: 100%;">
						<ul id="tree" class="ztree" style="overflow:auto;border: 0px!important;"></ul>
					</div>
				</td>
			</tr>
			<tr>
				<td class="center" colspan="4">
		        	<a class="button button-primary button-rounded button-small" onclick="save();">保存</a>
		        	<a class="button button-action button-rounded button-small" onclick="parentDialog.close()">取消</a>
				</td>
			</tr>
		</table>
    </form>
<script type="text/javascript">
//保存
function save(){
	var roleId = $("#roleId").val();
	var zTree = $.fn.zTree.getZTreeObj("tree");
	var nodes = zTree.getCheckedNodes(true);
	var ids = [];
	for(var i=0; i<nodes.length; i++){
		ids[i] = nodes[i].id;
	}
	$.ajax({
		url: "${basePath }role/editPermission",
		data: {ids: ids, roleId: roleId},
		type: "post",
		dataType: "json",
		traditional: true, 
		success: function(json) {
			if (json.ok) {
				Dialog.alert("修改成功！", function() {
					parentDialog.close();
					window.parent.search();
				});
			} else {
				Dialog.alert(json.msg);
			}
		}
	});
}
</script>
</body>
</html>
