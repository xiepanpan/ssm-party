<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>湖北经济学院党建门户网-帮助中心</title>
    <%@include file="common.jsp"%>
</head>

<body>
<div class="ind_body" id="container">

    <%@include file="head.jsp"%>

<div class="lmy_container">
  <div class="lmy_position"><a href="${cp}/frontNews/index.html">首页</a> &gt; <a title="理论学习" href="javascript:;">帮助中心</a></div>
  <div class="secnr" id="color_printsssss">
    <div id="wenzhang">

            
        <div class="news_box">
        <div class="list list_1 list_2" style="padding-bottom: 0px; border-bottom: none;">
             <ul>
 
                 
				 <c:forEach items="${result}" var="item">
              	 <li class="line">
         	问：<a href="${cp}/helpMananger/showHelpInfoById.htm?helpId=${item.helpId}&flag=2" target="_blank">${item.helpQuesition}</a>
               	 </li>
                 </c:forEach>


             </ul>
        </div> 
                        
   </div>


   </div>
  </div>
</div>

    <%@include file="foot.jsp"%>
</div>
</body>
</html>