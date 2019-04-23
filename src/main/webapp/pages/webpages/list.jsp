<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>湖北经济学院党建门户网-新闻列表</title>
    <%@include file="common.jsp"%>
</head>

<body>
<div class="ind_body" id="container">

    <%@include file="head.jsp"%>

<div class="lmy_container">
  <div class="lmy_position"><a href="${cp}/frontNews/index.html">首页</a> &gt; <a title="理论学习" href="">${newsparent}</a> &gt; <a title="系列讲话" href="">${newslist}</a></div>
  <div class="secnr" id="color_printsssss">
    <div id="wenzhang">

            
        <div class="news_box">
        <div class="list list_1 list_2" style="padding-bottom: 0px; border-bottom: none;">
             <ul>
 
				<c:forEach items="${result}" var="item" varStatus="status">
					 <li <c:if test="${status.count%6==0}">class="line" </c:if>>
	                    • <a href="${cp}/frontNews/lookNewsById.htm?tId=${item.tid}" title="${item.title}" target="_blank">${item.title}</a>
	                     <span class="date"><fmt:formatDate value="${item.releasetime}"  pattern="yyyy-MM-dd"/></span>
	                 </li>
	                 
				</c:forEach>
                 

                <!--  <li class="line">
                   
                     <a href="detail.jsp" target="_blank">· 张高丽会见沙特国王萨勒曼并主持中沙双边合作机制会议</a>
                     <span class="date">
                       2017-08-25
                     </span>
                 </li> -->


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