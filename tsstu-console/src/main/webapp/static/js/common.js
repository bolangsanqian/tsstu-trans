$(function($){
	//回车事件
	$(document).keyup(function(event) {
		if (event.keyCode == 13) {
			$.isFunction(search) && search();
		}
	});
});

/**
 * 弹出添加窗口
 * @param url 请求地址
 * @param width 窗口宽度
 * @param height 窗口高度
 * @param title 标题
 */
function toAdd(url, width, height, title) {
	var diag = new Dialog();
	diag.Width = width ? width : 600;
	diag.Height = height ? height : 390;
	diag.Title = title ? title : "添加";
	diag.URL = url
	diag.show();
}

/**
 * 添加
 * @param url 请求地址
 * @param formId 表单id
 * @param successMsg 添加成功提示
 */
function add(url, formId, successMsg) {
	formId = formId ? formId : "form1";
	successMsg = successMsg ? successMsg : "添加成功！";
	var data = $("#" + formId).serialize();
	$.ajax({
		url: url,
		data: data,
		type: "post",
		dataType: "json",
		success: function(json) {
			if (json.ok) {
				Dialog.alert(successMsg, function() {
					$.isFunction(window.parent.search) && window.parent.search();
				});
			}else {
				Dialog.alert(json.msg);
			}
		},
		complete: function() {
		}
	});
}

/**
 * 弹出修改窗口
 * @param url 请求地址
 * @param width 窗口宽度
 * @param height 窗口高度
 * @param title 标题
 */
function toEdit(url, width, height, title) {
	var diag = new Dialog();
	diag.Width = width ? width : 600;
	diag.Height = height ? height : 390;
	diag.Title = title ? title : "修改";
	diag.URL = url
	diag.show();
}

/**
 * 修改
 * @param url 请求地址
 * @param formId 表单id
 * @param successMsg 修改成功提示
 */
function edit(url, formId, successMsg) {
	formId = formId ? formId : "form1"; 
	successMsg = successMsg ? successMsg : "修改成功！";
	var data = $("#form1").serialize();
	$.ajax({
		url: url,
		data: data,
		type: "post",
		dataType: "json",
		success: function(json) {
			if (json.ok) {
				Dialog.alert(successMsg, function() {
					$.isFunction(window.parent.search) && window.parent.search();
				});
			}else {
				Dialog.alert(json.msg);
			}
			
		},
		complete: function() {
		}
	});
}

/**
 * 弹出详情窗口
 * @param url 请求地址
 * @param width 窗口宽度
 * @param height 窗口高度
 * @param title 标题
 */
function toDetail(url, width, height, title) {
	var diag = new Dialog();
	diag.Width = width ? width : 420;
	diag.Height = height ? height : 500;
	diag.Title = title ? title : "详情";
	diag.URL = url
	diag.show();
}

/**
 * 审批窗口
 * @param url 请求地址
 * @param width 窗口宽度
 * @param height 窗口高度
 * @param title 标题
 */
function review(url, width, height, title) {
	var diag = new Dialog();
	diag.Width = width ? width : 340;
	diag.Height = height ? height : 260;
	diag.Title = title ? title : "审批";
	diag.URL = url;
	diag.show();
}

/**
 * 保存
 * @returns {Boolean}
 */
function save() {
	if (!inputCheck()) {
		return false;
	}
	if ($.trim($("#id").val())=="") {
		add();
	} else {
		edit();
	}
}

/**
 * 删除
 * @param url 请求地址
 */
function del(url) {
	Dialog.confirm('确定要删除该条记录？', function() {
		$.get(url, function(data) {
			if (data.ok) {
				search();		
			} else {
				Dialog.alert(data.msg);
			}
		})
	});
}

/**
 * 批量删除
 * @param url 请求地址
 * @param formId 表单id
 */
function delBatch(url, formId) {
	var ids = [];
	var i = 0;
	formId = formId ? formId : "pageForm"; 
	var chks = $("#" + formId + " .tablelist input[name='chk']:checked").each(function() {
		ids[i] = $(this).val();
		i++;
	});
	if (ids.length < 1){
		Dialog.alert("请选择要删除的记录");
		return;
	}
	Dialog.confirm('确定要删除该条记录？', function() {
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

/**
 * 导出
 * @param url
 */
function exportExcel(url) {
	location.href = url;
}

/**
 * 跳转到指定url
 * @param url
 */
function toUrl(url) {
	location.href = url;
}

/**
 * 执行ajax操作
 * @param url
 */
function ajax(url) {
	$.post(url, function(data) {
		if (data.ok) {
			Dialog.alert("操作成功！", function() {
				parentDialog.close();
			});
		} else {
			Dialog.alert(data.msg);
		}
	});
}

/**
 * 执行ajax并且刷新
 * @param url
 */
function ajax_refresh(url, confirmMsg) {
	var msg = confirmMsg ? "您确认要【" + confirmMsg + "】吗？" : "您确认要执行该操作吗？";
	Dialog.confirm(msg, function(){
		$.post(url, function(data) {
			if (data.ok) {
				msg = confirmMsg ? "【" + confirmMsg + "】操作成功！" : "操作成功！";
				Dialog.alert(msg, function() {
					search();
					parentDialog.close();
				});
			} else {
				Dialog.alert(data.msg);
			}
		});
	});
}

/**
 * 公众号二维码上传页面
 * @param url 请求地址
 */
function to_upload_wx_qrcode(url, isExchange) {
	if (!isExchange) {
		var chk = $(":checkbox[name=chk]:checked");
		if (chk.length <= 0) {
			Dialog.alert("请选择选中一条记录");
			return;
		}
		if (chk.length > 1) {
			Dialog.alert("只容许选择一条记录");
			return;
		}
		url += "?id=" + chk.val();
	} 
	
	var diag = new Dialog();
	diag.Width = 360;
	diag.Height =  310;
	diag.Title = "公众号二维码上传";
	diag.URL = url;
	diag.show();
}

/**
 * 更新二维码图片
 * @param url 请求地址
 */
function upload_wx_qrcode(url) {
	$("#form1").ajaxSubmit({  
        type: 'post',  
        url: url,   
        contentType: "application/x-www-form-urlencoded; charset=utf-8",   
		success: function(json) {
			Dialog.alert(json.msg, function() {
				$.isFunction(window.parent.search) && window.parent.search();
			});
		},
		complete: function() {
		}
	});
}

/**
 * 删除公众号二维码
 * @param url 请求地址
 */
function del_wx_qrcode(url) {
	$.get(url, function(data) {
		if (data.ok) {
			Dialog.alert("删除成功！", function() {
				parentDialog.close();
			});
		} else {
			Dialog.alert(data.msg);
		}
	});
}

//选择运营中心的时候，查询其下面的会员，填充到会员下拉列表中
function getMemberList(){
	var operation_member_id = $("#operation_member_id").val();
	var url = "member/getMemberList?operation_member_id="+operation_member_id;
	$.ajax({
		url: url,
		type: "post",
		success: function(arr) {
			$("#member_id").empty();
			var html = "<option value='' selected>请选择</option>";
			for(var i=0; i<arr.length; i++){ 
				var data = arr[i];
				html += "<option value='" + data.item_value + "'>" + data.item_value + " - " + data.item_name + "</option>";
			} 
			$("#member_id").append(html);
			$("#member_id").uedSelect("setOption");
		}
	});
	getAgentMemberList();
}

//选择会员的时候，查询其下面的代理会员，填充到代理会员下拉列表中
function getAgentMemberList(){
	var operation_member_id = $("#operation_member_id").val();
	var member_id = $("#member_id").val();
	var url = "agent_member/getAgentMemberList?member_id=" + member_id + "&operation_member_id=" + operation_member_id;
	$.ajax({
		url: url,
		type: "post",
		success: function(arr) {
			$("#agent_member_id").empty();
			var html = "<option value='' selected>请选择</option>";
			for(var i=0; i<arr.length; i++){ 
				var data = arr[i];
				html += "<option value='" + data.item_value + "'>" + data.item_value + " - " + data.item_name + "</option>";
			} 
			$("#agent_member_id").append(html);
			$("#agent_member_id").uedSelect("setOption");
		}
	});
}

//自动转大写
function toUpperCase(obj) {
	obj.value = obj.value.toUpperCase()
}
