//导航
$(function(){
	$(".menubox").find(".on").addClass("tmpc");

	$('.menubox').on('mouseenter',"dd:has(.indmenucont)",function(){
		$(".menubox").find(".on").removeClass("on");
		$(this).find(".mu").addClass('on');
		$(this).children('.indmenucont').show(0,function(){
			$('.div_scroll').scroll_absolute({arrows:true});
            $('.ld_jl .div_scroll').scroll_absolute({arrows:false}) 
		});
	}).on('mouseleave',"dd:has(.indmenucont)",function(){
		$(this).find(".mu").removeClass('on');
		$(".menubox").find(".tmpc").addClass("on");
		$(this).children('.indmenucont').hide();
	});	
});



//字体设置
$(function(){
$('#f-big').click(function(){$('.wzcon,.wzcon *,.sp_wzcon,.sp_wzcon *').css('fontSize','18px');return false;});
$('#f-normal').click(function(){$('.wzcon,.wzcon *,.sp_wzcon,.sp_wzcon *').css('fontSize','16px');return false;});
$('#f-small').click(function(){$('.wzcon,.wzcon *,.sp_wzcon,.sp_wzcon *').css('fontSize','14px');return false;});
});
