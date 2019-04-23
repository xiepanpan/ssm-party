<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
    <div class="topcon">
        <div class="fr top_r">
            <a class="mail" href="">app下载</a>
            <a class="oa" href="javascript:;" onclick="SetHome(this,'http://www.baidu.com');">设为首页</a>
     <a class="add winopen" href="javascript:;" onclick="addFavorite()">加入收藏</a>
        </div>
        <div class="clear"></div>
    </div>
    <div class="banner">
        <div class="logo">
            <ul class="clearfix"><li class="num1"><a title="湖北经济学院" href="javascript:void(0)" tabindex="-1"> <img src="${cp}/pages/webpages/images/logo.jpg" width="304" height="76" border="0" alt="湖北经济学院"> </a> </li></ul>
        </div>
        <div class="seccon">
            <div class="searchbox">
                <form action="" method="get" data-validator-option="">
                    <ul>
                        <li class="se_box">
                            <input class="search-keywords srkcss" name="keywords" type="text" value="" size="24" maxlength="30" placeholder="请输入需要查找的信息" data-rule="输入的信息:required"/>
                        </li>
                        <li class="sea_an">
                            <input name="input" type="submit" value="搜索" class="topsearchbtn"/>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
    <div class="menucon">
        <div class="menubox">
            <dl class="clearfix">
                <dd>
                    <div class="mu sy on"><a href="${cp}/frontNews/index.html"><img src="${cp}/pages/webpages/images/mu_ico1.png" width="22" height="20" alt="首页"/>首 页</a>
        </div>
        <div class="indmenucont dpnone"></div>
        </dd>
        <!---->
        <dd class="lang">
            <div class="mu off"><a href="${cp}/frontNews/lookAlltypes.htm?newsType=1"><img src="${cp}/pages/webpages/images/mu_ico2.png" width="23" height="20" alt="系列讲话"/>系列讲话</a></div>
    </dd>
    <!---->
    <dd>
        <div class="mu off"><a href="${cp}/frontNews/lookAlltypes.htm?newsType=2"><img src="${cp}/pages/webpages/images/mu_ico3.png" width="17" height="20" alt="规章制度"/>规章制度</a></div>
   </dd>
<!---->
<dd>
    <div class="mu off"><a href="${cp}/frontNews/lookAlltypes.htm?newsType=3"><img src="${cp}/pages/webpages/images/mu_ico4.png" width="20" height="20" alt="国内时政"/>国内时政</a></div>
</dd>
<!---->
<dd>
    <div class="mu off"><a href="${cp}/frontNews/lookAlltypes.htm?newsType=4"><img src="${cp}/pages/webpages/images/mu_ico5.png" width="21" height="20" alt="省内时政"/>省内时政</a></div>
</dd>
<!---->
<dd>
    <div class="mu off"><a href="${cp}/helpMananger/showHelp.htm?flag=2"><img src="${cp}/pages/webpages/images/mu_ico6.png" width="21" height="20" alt="帮助中心"/>帮助中心</a></div>
</dd>
</dl>
</div>
</div>
</div>

<script>
function SetHome(obj,url){
    try{
        obj.style.behavior='url(#default#homepage)';
        obj.setHomePage(url);
    }catch(e){
        if(window.netscape){
            try{
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            }catch(e){
                alert("抱歉，此操作被浏览器拒绝！\n\n请在浏览器地址栏输入“about:config”并回车然后将[signed.applets.codebase_principal_support]设置为'true'");
            }
        }else{
            alert("抱歉，您所使用的浏览器无法完成此操作。\n\n您需要手动将【"+url+"】设置为首页。");
        }
    }
}


function addFavorite() {
    var url = window.location;
    var title = document.title;
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf("360se") > -1) {
        alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
    }
    else if (ua.indexOf("msie 8") > -1) {
        window.external.AddToFavoritesBar(url, title); //IE8
    }
    else if (document.all) {
  try{
   window.external.addFavorite(url, title);
  }catch(e){
   alert('请按 Ctrl+D 手动收藏!');
  }
    }
    else if (window.sidebar) {
        window.sidebar.addPanel(title, url, "");
    }
    else {
  alert('请按 Ctrl+D 手动收藏!');
    }
}
</script>