<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>后台管理 - 主页</title>
    <%@include file="common.jsp"%>
</head>
<style type="text/css">
.nav>li>a i {
width: 19px;
margin-top: 2px;
}
.nav>li>a i {
margin-right: 6px;
}
.fanew {
display: block;
float: left;
font: normal normal normal 14px/1 FontAwesome;
font-size: inherit;
text-rendering: auto;
-webkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
}
.faarrow {
display: inline-block;
font: normal normal normal 14px/1 FontAwesome;
font-size: inherit;
text-rendering: auto;
-webkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
}
</style>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" width="60" height="60" class="img-circle" src="${cp}/pages/img/profile_small.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${sessionScope.userName }</strong></span>
                                <span class="text-muted text-xs block">${name }<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                               <%--  <li>
                                	<a class="J_menuItem" href="form_avatar.jsp">修改头像</a>
                                </li>
                                <li>
                                	<a class="J_menuItem" href="profile.jsp">个人资料</a>
                                </li>
                                <li>
                                	<a class="J_menuItem" href="mailbox.jsp">信箱</a>
                                </li> 
                                <li class="divider"></li>--%>
                                <li>
                                <a href="javascript:outLogin();">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element"><i class="fa fa-expand"></i></div>
                    </li>
                    <%-- <li>
                       <a class="J_menuItem" href="${cp}/pages/home.jsp"><i class="fa fa-home"></i><span class="nav-label">主页</span></a>
                    </li> --%>

                    <%-- <li>
                        <a class="J_menuItem" href="${cp}/pages/star.jsp"><i class="fa fa-star"></i><span class="nav-label">光辉历程</span></a>
                    </li> --%>
					<li>
                        <a class="J_menuItem" href="${cp}/memeber/showMembers.htm"><i class="fanew fa-user"></i><span class="nav-label">党员管理</span></a>
                    </li>
                    
                    <li>
                        <a href="#">
                            <i class="fanew fa-list-ul"></i>
                            <span class="nav-label">新闻管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${cp}/newsController/showNews.htm?newsType=1&isFlag=1">理论学习</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${cp}/newsController/showNews.htm?newsType=2&isFlag=1">时政学习</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${cp}/newsController/showNews.htm?newsType=3&isFlag=1">轮播新闻</a>
                            </li>
                        </ul>
                    </li>
                    
				
                    	
                    <li id="obshowul">
                    	<a id="obshow" href="${cp}/branch/viewBranch.htm">
                            <i class="fanew fa-list-ul"></i>
                            <span class="nav-label">支部管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level" style="display:none;">
		                    <li>
		                        <a class="J_menuItem" href="${cp}/branch/viewBranchByCondition.htm">院系分党委</a>
		                    </li>
	                    </ul>
                    	<c:if test="${roleId!=2 }">
                        <c:forEach  items="${result}" var="item">
	                    <ul data-id="${item.partyId}" class="nav nav-second-level">
		                    <li>
		                        <a class="J_menuItem" href="${cp}/branch/viewBranchByCondition.htm?branchFatherId=${item.partyId}">${item.partyBranchName}</a>
		                    </li>
	                    </ul>
	                    </c:forEach>
	                    </c:if>
                    </li>
                    <li>
                       <a class="J_menuItem" href="${cp}/helpMananger/showHelp.htm?flag=1"><i class="fanew fa-question"></i><span class="nav-label">帮助管理</span></a>
                    </li>
                    <li>
                       <a class="J_menuItem" href="${cp}/pay/showPay.htm"><i class="fanew fa-money"></i><span class="nav-label">缴费管理</span></a>
                    </li>  
                    <li>
                        <a class="J_menuItem" href="${cp}/pic/showPicManager.htm"><i class="fanew fa-file-image-o"></i><span class="nav-label">图片管理</span></a>
                    </li> 
                    <li>
                        <a class="J_menuItem" href="${cp}/act/getActTypeHtml.htm"><i class="fanew fa-file-image-o"></i><span class="nav-label">活动分类管理</span></a>
                    </li> 
                    <li>
                        <a class="J_menuItem" href="${cp}/opinion/getOpinion.htm"><i class="fanew fa-file-image-o"></i><span class="nav-label">建言献策</span></a>
                    </li> 
                    <c:if test="${roleId!=0}">
					<li>
                        <a class="J_menuItem" href="${cp}/actPush/showActivities.htm">
                            <i class="fanew fa-th-large"></i><span class="nav-label">我的活动</span></a>
                        </a>
                    </li>
                    </c:if>
                    <%-- 
                    
                    <li>
                        <a href="#">
                            <i class="fa fa fa-list-ul"></i>
                            <span class="nav-label">我的活动</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${cp}/pages/active_w.jsp">未开始</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="active_w.jsp">进行中</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="active_w.jsp">已结束</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="#">
                            <i class="fa fa fa-calendar-check-o"></i>
                            <span class="nav-label">我的任务</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="tasks.jsp">待做的任务</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="tasks.jsp">发起的任务</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="mailbox.jsp"><i class="fa fa-envelope"></i> <span class="nav-label">信箱 </span><span class="label label-warning pull-right">16</span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="mailbox.jsp">收件箱</a>
                            </li>
                            <li><a class="J_menuItem" href="mail_detail.jsp">查看邮件</a>
                            </li>
                            <li><a class="J_menuItem" href="mail_compose.jsp">写信</a>
                            </li>
                        </ul>
                    </li>
                    
                    <li>
                        <a class="J_menuItem" href="faq.jsp"><i class="fa fa-question-circle"></i><span class="nav-label">常见问题</span></a>
                    </li> 
                    
                    --%>


                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row content-tabs">
                <button class="leftmenu-hide roll-nav roll-left J_tabLeft"><i class="fa fa-bars"></i>
                </button>
                <button class="roll-nav J_tabLeft rollleftone"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${cp}/memeber/showMembers.htm">党员管理 <i class="fa fa-times-circle"></i></a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="javascript:outLogin();" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
 <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${cp}/memeber/showMembers.htm" frameborder="0" data-id="${cp}/memeber/showMembers.htm" seamless></iframe>

            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2017-2018 <a href="http://www.cptec.cn" target="_blank">武汉亿维达-技术支持</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>

    <%@include file="common_js.jsp"%>
    <script src="${cp}/pages/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${cp}/pages/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${cp}/pages/js/plugins/layer/layer.min.js"></script>
    <script src="${cp}/pages/js/hplus.min.js"></script>
    <script src="${cp}/pages/js/contabs.min.js" type="text/javascript"></script>
    <script src="${cp}/pages/js/plugins/pace/pace.min.js"></script>
</body>
<script type="text/javascript">

// 如果当前session没有登录信息,则跳转至登陆界面,有登录信息,就在iframe中打开数据展示
/* $(".J_menuItem").click(function(){
	var userId = "";
	$.ajax({
		url:"${cp}/login/getUserIdFromSession.htm",
		dataType:'json',
		success: function(data){
			var result = data;
			if(result.status==true){
				userId = result.userId;
			}
			if(userId=""||userId==null){
				window.location.href="${cp}/login.jsp";
			}
		}
	})
}) */
$(document).on("click",".J_menuItem",function() {
	var userId = "";
	$.ajax({
		url:"${cp}/login/getUserIdFromSession.htm",
		dataType:'json',
		success: function(data){
			var result = data;
			if(result.status==true){
				userId = result.userId;
			}
			if(userId=""||userId==null){
				window.location.href="${cp}/login.jsp";
			}
		}
	})
})

function outLogin(){
	successDialog("提示","是否确认退出登录？",function(){
		$.ajax({
			url:"${cp}/login/signOut.app",
			data:null,
			dataType:'json',
			success: function(data){
				if(data.status==true){
					window.location.href="${cp}/login.jsp";
				}
			}
		})
	});
	
}
$(document).on('click',"#obshow",function(){
	console.log("点击党支部");
	console.log($("#obshowul").find("ul").eq(0).find('a'));
	$("#obshowul").find("ul").eq(0).find('a').eq(0).click();
});






function f(l){var k=0;$(l).each(function(){k+=$(this).outerWidth(true)});return k}
function g(n){var o=f($(n).prevAll()),q=f($(n).nextAll());var l=f($(".content-tabs").children().not(".J_menuTabs"));var k=$(".content-tabs").outerWidth(true)-l;var p=0;if($(".page-tabs-content").outerWidth()<k){p=0}else{if(q<=(k-$(n).outerWidth(true)-$(n).next().outerWidth(true))){if((k-$(n).next().outerWidth(true))>q){p=o;var m=n;while((p-$(m).outerWidth())>($(".page-tabs-content").outerWidth()-k)){p-=$(m).prev().outerWidth();m=$(m).prev()}}}else{if(o>(k-$(n).outerWidth(true)-$(n).prev().outerWidth(true))){p=o-$(n).prev().outerWidth(true)}}}$(".page-tabs-content").animate({marginLeft:0-p+"px"},"fast")}
function callback(){
	function a(){var o=Math.abs(parseInt($(".page-tabs-content").css("margin-left")));var l=f($(".content-tabs").children().not(".J_menuTabs"));var k=$(".content-tabs").outerWidth(true)-l;var p=0;if($(".page-tabs-content").width()<k){return false}else{var m=$(".J_menuTab:first");var n=0;while((n+$(m).outerWidth(true))<=o){n+=$(m).outerWidth(true);m=$(m).next()}n=0;if(f($(m).prevAll())>k){while((n+$(m).outerWidth(true))<(k)&&m.length>0){n+=$(m).outerWidth(true);m=$(m).prev()}p=f($(m).prevAll())}}$(".page-tabs-content").animate({marginLeft:0-p+"px"},"fast")}
	function b(){var o=Math.abs(parseInt($(".page-tabs-content").css("margin-left")));var l=f($(".content-tabs").children().not(".J_menuTabs"));var k=$(".content-tabs").outerWidth(true)-l;var p=0;if($(".page-tabs-content").width()<k){return false}else{var m=$(".J_menuTab:first");var n=0;while((n+$(m).outerWidth(true))<=o){n+=$(m).outerWidth(true);m=$(m).next()}n=0;while((n+$(m).outerWidth(true))<(k)&&m.length>0){n+=$(m).outerWidth(true);m=$(m).next()}p=f($(m).prevAll());if(p>0){$(".page-tabs-content").animate({marginLeft:0-p+"px"},"fast")}}}
	$(".J_menuItem").each(function(k){if(!$(this).attr("data-index")){$(this).attr("data-index",k)}});
	function c(){var o=$(this).attr("href"),m=$(this).data("index"),l=$.trim($(this).text()),k=true;if(o==undefined||$.trim(o).length==0){return false}$(".J_menuTab").each(function(){if($(this).data("id")==o){if(!$(this).hasClass("active")){$(this).addClass("active").siblings(".J_menuTab").removeClass("active");g(this);$(".J_mainContent .J_iframe").each(function(){if($(this).data("id")==o){$(this).show().siblings(".J_iframe").hide();return false}})}k=false;return false}});if(k){var p='<a href="javascript:;" class="active J_menuTab" data-id="'+o+'">'+l+' <i class="fa fa-times-circle"></i></a>';$(".J_menuTab").removeClass("active");var n='<iframe class="J_iframe" name="iframe'+m+'" width="100%" height="100%" src="'+o+'" frameborder="0" data-id="'+o+'" seamless></iframe>';$(".J_mainContent").find("iframe.J_iframe").hide().parents(".J_mainContent").append(n);$(".J_menuTabs .page-tabs-content").append(p);g($(".J_menuTab.active"))}return false}$(".J_menuItem").on("click",c);function h(){var m=$(this).parents(".J_menuTab").data("id");var l=$(this).parents(".J_menuTab").width();if($(this).parents(".J_menuTab").hasClass("active")){if($(this).parents(".J_menuTab").next(".J_menuTab").size()){var k=$(this).parents(".J_menuTab").next(".J_menuTab:eq(0)").data("id");$(this).parents(".J_menuTab").next(".J_menuTab:eq(0)").addClass("active");$(".J_mainContent .J_iframe").each(function(){if($(this).data("id")==k){$(this).show().siblings(".J_iframe").hide();return false}});var n=parseInt($(".page-tabs-content").css("margin-left"));if(n<0){$(".page-tabs-content").animate({marginLeft:(n+l)+"px"},"fast")}$(this).parents(".J_menuTab").remove();$(".J_mainContent .J_iframe").each(function(){if($(this).data("id")==m){$(this).remove();return false}})}if($(this).parents(".J_menuTab").prev(".J_menuTab").size()){var k=$(this).parents(".J_menuTab").prev(".J_menuTab:last").data("id");$(this).parents(".J_menuTab").prev(".J_menuTab:last").addClass("active");$(".J_mainContent .J_iframe").each(function(){if($(this).data("id")==k){$(this).show().siblings(".J_iframe").hide();return false}});$(this).parents(".J_menuTab").remove();$(".J_mainContent .J_iframe").each(function(){if($(this).data("id")==m){$(this).remove();return false}})}}else{$(this).parents(".J_menuTab").remove();$(".J_mainContent .J_iframe").each(function(){if($(this).data("id")==m){$(this).remove();return false}});g($(".J_menuTab.active"))}return false}$(".J_menuTabs").on("click",".J_menuTab i",h);function i(){$(".page-tabs-content").children("[data-id]").not(":first").not(".active").each(function(){$('.J_iframe[data-id="'+$(this).data("id")+'"]').remove();$(this).remove()});$(".page-tabs-content").css("margin-left","0")}$(".J_tabCloseOther").on("click",i);function j(){g($(".J_menuTab.active"))}$(".J_tabShowActive").on("click",j);function e(){if(!$(this).hasClass("active")){var k=$(this).data("id");$(".J_mainContent .J_iframe").each(function(){if($(this).data("id")==k){$(this).show().siblings(".J_iframe").hide();return false}});$(this).addClass("active").siblings(".J_menuTab").removeClass("active");g(this)}}$(".J_menuTabs").on("click",".J_menuTab",e);
	function d(){var l=$('.J_iframe[data-id="'+$(this).data("id")+'"]');var k=l.attr("src")}$(".J_menuTabs").on("dblclick",".J_menuTab",d);$(".J_tabLeft").on("click",a);$(".J_tabRight").on("click",b);$(".J_tabCloseAll").on("click",function(){$(".page-tabs-content").children("[data-id]").not(":first").each(function(){$('.J_iframe[data-id="'+$(this).data("id")+'"]').remove();$(this).remove()});$(".page-tabs-content").children("[data-id]:first").each(function(){$('.J_iframe[data-id="'+$(this).data("id")+'"]').show();$(this).addClass("active")});$(".page-tabs-content").css("margin-left","0")})
}
</script>
</html>
