$(function($) {
	$(".tsstu-dialog .time .time-item").click(function() {
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
	});
	
	$(".tsstu-dialog .money .money-item").click(function() {
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
	});
});

//持仓列表
function hold_list() {
	var url = "trade/hold_dialog";
	$.ajax({
		url: url,
		data: {},
		type: "post",
		dataType: "html",
		success: function(html) {
			$("body").append(html);
			showDialog("#hold_list_dialog");
			load_hold_swiper();
		},
		complete: function() {
		}
	});
}

function trade_confirm(direction) {
	var product_id = $("#product_id").val();
	var url = "trade/confirm_dialog";
	$.ajax({
		url: url,
		data: {
			"product_id": product_id,
			"direction": direction
		},
		type: "post",
		dataType: "html",
		success: function(html) {
			$("body").append(html);
			showDialog("#trade_dialog");
			load_amount_swiper();
			load_time_swiper();
		},
		complete: function() {
		}
	});
}

function trading(order_id) {
	var product_id = $("#product_id").val();
	var url = "trade/trading_dialog";
	$.ajax({
		url: url,
		data: {
			"order_id": order_id
		},
		type: "post",
		dataType: "html",
		success: function(html) {
			$("body").append(html);
			showDialog("#trading_dialog");
		},
		complete: function() {
		}
	});
}

//倒计时
function countDown(miao) {
	var container = $('.tsstu-circle-wrap').radialIndicator({
		barColor: '#eeeeee',
		barBgColor: '#fdbe19',
        radius: 130,
        barWidth: 8,
        percentage: true,
        displayNumber: false,
        minValue: 1, //minimum value
        maxValue: miao, //maximum value
        initValue: 1,
    }).data('radialIndicator');
	var i = 1;
	var count = miao;
	var loading = setInterval(function () {
		if (count > 0) {
			container.value(++i);
			$(".miao").html(--count)
		} else {
			clearInterval(loading);
		}
	}, 1000);
}

//加载时间列表左右滑动
function load_time_swiper() {
	var swiper = new Swiper('#trade_dialog .time .swiper-container', {
        slidesPerView: 'auto',
        paginationClickable: true,
        grabCursor: true,
        spaceBetween: 20
    });
}

//加载金额列表左右滑动
var swiper = 1;
function load_amount_swiper() {
    swiper = new Swiper('#trade_dialog .money .swiper-container', {
        slidesPerView: 'auto',
        paginationClickable: true,
        grabCursor: true,
        spaceBetween: 20
    });
}

//加载持仓列表左右滑动
function load_hold_swiper() {
	var swiper = new Swiper('#hold_list_dialog .swiper-container', {
		scrollbar:'.swiper-scrollbar',
    	direction: 'vertical',
        slidesPerView: 'auto',
        paginationClickable: true,
        speed: 1000,
        grabCursor: true,
    });
}
