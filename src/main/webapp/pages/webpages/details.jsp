<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>湖北经济学院党建门户网-新闻详情</title>
    <%@include file="common.jsp"%>
</head>

<body>
<div class="ind_body" id="container">

    <%@include file="head.jsp"%>

<div class="lmy_container">
  <div class="lmy_position">
  <c:if test="${newsparent == '轮播'}"><a href="${cp}/frontNews/index.html">首页</a> &gt; <a title="${newsparent}" href="">${newsparent}</a></c:if>
  <c:if test="${newsparent != '轮播'}">
  <a href="${cp}/frontNews/index.html">首页</a> &gt; <a title="${newsparent}" href="">${newsparent}</a> &gt; <a title="${newslist}" href="">${newslist}</a>
  </c:if>
  </div>
  <div class="secnr" id="color_printsssss">
    <div id="wenzhang">
        <h1 class="wztit" style="color:;   ">${item.title}</h1>
                <div class="wzfbxx">
          <div class="fl">日期：<fmt:formatDate value="${item.releasetime}"  pattern="yyyy-MM-dd"/>&emsp;点击：${item.readcount}</div>
          <div class="wz_bj"><input type="button" style="background-color:#EDF0F5;" onclick="document.all('color_printsssss').style.backgroundColor='#EDF0F5';"> <input type="button" style="background-color:#FAFBE6;" onclick="document.all('color_printsssss').style.backgroundColor='#FAFBE6';"> <input type="button" style="background-color:#FFF2E2;" onclick="document.all('color_printsssss').style.backgroundColor='#FFF2E2';"> <input type="button" style="background-color:#FDE6E0;" onclick="document.all('color_printsssss').style.backgroundColor='#FDE6E0';"> <input type="button" style="background-color:#F3FFE1;" onclick="document.all('color_printsssss').style.backgroundColor='#F3FFE1';"> <input type="button" style="background-color:#DAFAF3;" onclick="document.all('color_printsssss').style.backgroundColor='#DAFAF3';"> <input type="button" style="background-color:#EAEAEF;" onclick="document.all('color_printsssss').style.backgroundColor='#EAEAEF';"> <input type="button" style="background-color:#FFFFFF;" onclick="document.all('color_printsssss').style.backgroundColor='#FFFFFF';"></div>
        </div>
        <div class="wzcon" style="font-family:'微软雅黑'">
			${item.newsContext}
			<p style="text-indent:2em;">
			    <br>
			</p>
		<div class="clear"></div>
        </div>
        <div class="wzbot">
          <ul class="fxd_close"><a href="javascript:void(0)" onclick="javascript:window.print()" class="print_btn">打印本页</a><a href="javascript:window.close();" class="close_btn">关闭窗口</a></ul>
       </div>
   </div>
  </div>
</div>
    <%@include file="foot.jsp"%>
</div>
</body>
</html>