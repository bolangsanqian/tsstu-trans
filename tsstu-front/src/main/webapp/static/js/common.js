$(function($) {
	/*$(".tsstu-dialog,.tsstu-dialog2").on("click", ".close", function() {
		closeDialog();
		hideMask();
	});*/
	
});

/**
 * 地址调整
 * @param url
 */
function toUrl(url) {
	layer.open({
		type: 2
	});
	setTimeout(function() {
		location.href = url;
	}, 100);
}

/**
 * 显示透明遮盖层
 */
function showMask(id) {
	if (id) {
		id = id.replace("#", "")
		$(".tsstu-mask").attr("id", id + "_mask");
	}
	$(".tsstu-mask").show();
}

/**
 * 隐藏透明遮盖层
 */
function hideMask() {
	$(".tsstu-mask").attr("id", "");
	$(".tsstu-mask").hide();
}

/**
 * 显示对话框
 */
function showDialog(id) {
	if (id) {
		showMask(id);
		if (!$(id).is(":hidden")) {
			closeDialog(id);
			return;
		}
	} else {
		id = ".tsstu-dialog";
	}
	$(".tsstu-dialog, .tsstu-dialog2").hide();
	$(id).slideDown("fast");
}

/**
 * 显示对话框
 */
function closeDialog() {
	hideMask();
	$(".tsstu-dialog, .tsstu-dialog2").slideUp("fast").remove();
}

/**
 * 加载实时行情
 * @param codes 行情代码（code1,code2,....）
 */
function load_realtime_quotation(codes, minute) {
	minute = minute ? minute : 0;
	var url = "quotation/realtime";
	$.ajax({
		url: url,
		data: {
			"codes": codes,
			"minute": minute
		},
		type: "post",
		dataType: "json",
		success: function(json) {
			if (json.ok) {
				var quotation_list = json.data[0];
				var quotation_history_now = null;
				if (json.data.length > 1) {
					quotation_history_now = json.data[1];
				}
				for (var index in quotation_list) {
					var quotation = quotation_list[index];
					if (typeof refresh_price_callback === 'function') {
						refresh_price_callback(quotation, quotation_history_now);
					}
					if (typeof refresh_price_callback2 === 'function') {
						refresh_price_callback2(quotation, quotation_history_now);
					}
					if (typeof refresh_price_callback3 === 'function') {
						refresh_price_callback3(quotation, quotation_history_now);
					}
					if (typeof refresh_price_callback4 === 'function') {
						refresh_price_callback4(quotation, quotation_history_now);
					}
				}
			}
		},
		complete: function() {
		}
	});
}
setInterval(function () {
	var codes = $("#codes").val();
	var minute = $("#minute").val();
	load_realtime_quotation(codes, minute);
}, 1000);