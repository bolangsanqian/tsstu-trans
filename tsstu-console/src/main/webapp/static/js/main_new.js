$(document).ready(function(){

	$('.nav-tabs').NavTab({});

	$(window).resize(function(){
		var tempHeight = $('.hideHead').hasClass('hide') ? 0 : $('#hd').height();
		$('.leftMenu iframe').height($(window).height() - tempHeight - 70);
		$('.content-wrap iframe').height($(window).height() - tempHeight - 90);

		$('.nav-tabs').NavTab('setContentHeight', $(window).height() - tempHeight - 90);
	}).resize();

	$('.hideHead').toggle(function(){
		$(this).addClass('hide').attr('title' ,'展开头部');
		$('#hd').hide();
		$(window).resize();
	}, function(){
		$(this).removeClass('hide').attr('title' ,'收起头部');
		$('#hd').show();
		$(window).resize();
	});


	$('.fixedLeft').toggle(function(e){
		e.stopPropagation();
		//$(this).addClass('fixed').closest('#bd').addClass('hideleft');
		$(this).closest('#bd').stop().animate({'paddingLeft': 0}, 300, function(){
			$(this).addClass('fixed hideleft');
		});

	},function(e){
		//e.stopPropagation();
		$(this).closest('#bd').stop().animate({'paddingLeft': 187}, 300, function(){
			$(this).removeClass('fixed hideleft');
		});
	});

	$('.leftMenu').mouseleave(function(){
		if(!!$(this).closest('.hideleft').size()){
			$(this).closest('#bd').stop().animate({'paddingLeft': 0});
		}
	});

	

	$('.fixedLeft,.fiexdMouseover').mouseenter(function(){
		if(!!$(this).closest('.hideleft').size()){
			$(this).closest('#bd').stop().animate({'paddingLeft': 187});
		}
	});


	$('.user').click(function(){
		$('.profile').toggle();
	});


	$('.overlay').click(function(){
		$(this).parent().hide();
	});



	

});


// 主页面公共接口

var frame = {
	openTab: function(data){
		$('.nav-tabs').NavTab('openTab', data);
	},

	closeTab: function(name){
		if(typeof name === 'object'){
			name = name.name;
		}

		$('.nav-tabs').NavTab('closeTab', data);
	},

	reloadCurrentFrame: function(){
		$('.nav-tabs').NavTab('reloadCurrentFrame');
	},

	closeCurrent: function(){
		$('.nav-tabs').NavTab('closeCurrent');
	}
}