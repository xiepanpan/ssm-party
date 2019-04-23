<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>湖北经济学院党建门户网</title>
    <%@include file="common.jsp"%>
</head>

<body>
<div class="ind_body" id="container">



    <%@include file="head.jsp"%>

<div class="ind_container">
<!--头条-->
<c:forEach items="${result}" var="item" varStatus="status">
<c:if test="${status.index==0}">
<div class="ind_ttcon"><span class="tit"><img src="${cp}/pages/webpages/images/ind_tttit.png" width="52" height="52" alt="头条"></span>
    <ul class="dbt clearfix">
        <li class="odd">
        <h1 class="dbt"><a href='${cp}/frontNews/lookNewsById.htm?tId=${item.tid}' target="_blank" title="${item.title}">
                       <span style='color:;'>
                       <c:choose>
	                         <c:when test="${fn:length(item.title) > 35}"> 
	                             <c:out value="${fn:substring(item.title, 0, 35)}..." />
	                         </c:when>
	                        <c:otherwise>
	                           <c:out value="${item.title}" />
	                         </c:otherwise>
		               </c:choose>
                       </span>
    </a></h1>
    <div class="hotinfo">
    	<span class="toutiaotext"></span>
    	<span class="toutiao" style="display:none;">${item.newsContext}</span>
    
    <a href='${cp}/frontNews/lookNewsById.htm?tId=${item.tid}' target="_blank">[查看详细]</a></div>
    </li>
    </ul>
</div>
</c:if>
</c:forEach>
    <!-- 轮播广告 开始 -->
<div id="banner_tabs" class="flexslider mt20">
			
			<ul class="slides">
			
               <%--  <li><a title="" target="_blank" href="${cp}/frontNews/lookNewsById.htm?tId=${lunbo[0].tid}"> <img width="1160" height="291" alt="" style="background: url('${pageContext.request.contextPath}/Images/${imgList[0].imageUrl}') no-repeat center;" src="${pageContext.request.contextPath}/Images/${imgList[0].imageUrl}"></a></li>
                <li><a title="" target="_blank" href="${cp}/frontNews/lookNewsById.htm?tId=${lunbo[1].tid}"> <img width="1160" height="291" alt="" style="background: url('${pageContext.request.contextPath}/Images/${imgList[1].imageUrl}') no-repeat center;" src="${pageContext.request.contextPath}/Images/${imgList[1].imageUrl}"></a></li>
                <li><a title="" target="_blank" href="${cp}/frontNews/lookNewsById.htm?tId=${lunbo[2].tid}"> <img width="1160" height="291" alt="" style="background: url('${pageContext.request.contextPath}/Images/${imgList[2].imageUrl}') no-repeat center;" src="${pageContext.request.contextPath}/Images/${imgList[2].imageUrl}"></a></li> --%>
                <li><a title="" target="_blank" href="${cp}/frontNews/lookNewsById.htm?tId=${lunbo[0].tid}"> <img width="1160" height="291" alt="" style="background: url('${parentUrl}${imgList[0].imageUrl}') no-repeat center;" src="${parentUrl}${imgList[0].imageUrl}"></a></li>
                <li><a title="" target="_blank" href="${cp}/frontNews/lookNewsById.htm?tId=${lunbo[1].tid}"> <img width="1160" height="291" alt="" style="background: url('${parentUrl}${imgList[1].imageUrl}') no-repeat center;" src="${parentUrl}${imgList[1].imageUrl}"></a></li>
                <li><a title="" target="_blank" href="${cp}/frontNews/lookNewsById.htm?tId=${lunbo[2].tid}"> <img width="1160" height="291" alt="" style="background: url('${parentUrl}${imgList[2].imageUrl}') no-repeat center;" src="${parentUrl}${imgList[2].imageUrl}"></a></li>
            
            </ul>
            <ul class="flex-direction-nav">
                <li><a class="flex-prev" href="javascript:;">Previous</a></li>
                <li><a class="flex-next" href="javascript:;">Next</a></li>
            </ul>
            <ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
                
                <li class="active"><a>1</a></li>
                <li><a>2</a></li>
                <li><a>3</a></li>
            </ol>
        </div>
        <!-- 轮播广告 结束 -->
    <div class="clear"></div>

<!---->
<div class="ind_mainer mt20">
  <div class="fl ind_lbox">
    <div class="ind_gktop2">
     <a class="tit" href="">新闻总汇</a>
    </div>
    <div class="ind_gktop3">
     <a class="more" href="${cp}/frontNews/lookAlltypes.htm" target="_blank"><img src="${cp}/pages/webpages/images/more.gif" width="18" height="18"></a>
    </div>
    <div class="fl ind_tzbkbox mt20">
      <div class="ind_xwtop">
        <ul class="clearfix">
          <li class="active"><a href="${cp}/frontNews/lookAlltypes.htm?newsType=1#gtab1" data-toggle="mouse">系列讲话</a></li>
          <li><a href="${cp}/frontNews/lookAlltypes.htm?newsType=2#gtab2" data-toggle="mouse">规章制度</a></li>
        </ul>
      </div>
     
      <div class="ind_tzlist ind_tzbklist">
        <ul class="active" id="gtab1">
       <c:forEach items="${result}" var="item">
        <c:if test="${item.newsType==21}">
        <li class="odd">
        <a href='${cp}/frontNews/lookNewsById.htm?tId=${item.tid}' target="_blank" title="${item.title}" class="left">
               <span style='color:;'>
               <c:choose>  
	                         <c:when test="${fn:length(item.title) > 20}">  
	                             <c:out value="${fn:substring(item.title, 0, 20)}..." />  
	                         </c:when>  
	                        <c:otherwise>  
	                           <c:out value="${item.title}" />  
	                         </c:otherwise>  
		                   </c:choose>
               </span>
    	</a>
       <span class="right date"><fmt:formatDate value="${item.releasetime}"  pattern="MM-dd"/></span>
       </li>
    	</c:if>
        </c:forEach>
     	</ul>
        <ul id="gtab2">
      	<c:forEach items="${result}" var="item">
        <c:if test="${item.newsType==22}">
        <li class="odd">
        <a href='${cp}/frontNews/lookNewsById.htm?tId=${item.tid}' target="_blank" title="${item.title}" class="left">
                          <span style='color:;'>
                          <c:choose>  
	                         <c:when test="${fn:length(item.title) > 20}">  
	                             <c:out value="${fn:substring(item.title, 0, 20)}..." />  
	                         </c:when>  
	                        <c:otherwise>  
	                           <c:out value="${item.title}" />  
	                         </c:otherwise>  
		                   </c:choose>
                          </span>
    	</a>
                    <span class="right date"><fmt:formatDate value="${item.releasetime}"  pattern="MM-dd"/></span>
   		 </li>
   		 </c:if>
   		 </c:forEach>
   		</ul>
      </div>
       
    </div>  
    <div class="fl ind_tzbkbox ml20 mt20">
      <div class="ind_xwtop">
        <ul class="clearfix">
          <li class="active"><a href="${cp}/frontNews/lookAlltypes.htm?newsType=3#htab1" data-toggle="mouse">国内时政</a></li>
          <li><a href="${cp}/frontNews/lookAlltypes.htm?newsType=4#htab2" data-toggle="mouse">省内时政</a></li>
        </ul>
      </div>
      <div class="ind_tzlist ind_tzbklist">
        <ul class="active" id="htab1">
        <c:forEach items="${result}" var="item">
        <c:if test="${item.newsType==31}">
        <li class="odd">
        <a href='${cp}/frontNews/lookNewsById.htm?tId=${item.tid}' target="_blank" title="${item.title}" class="left">
                        <span style='color:;'>
                        <c:choose>  
	                         <c:when test="${fn:length(item.title) > 20}">  
	                             <c:out value="${fn:substring(item.title, 0, 20)}..." />  
	                         </c:when>  
	                        <c:otherwise>  
	                           <c:out value="${item.title}" />  
	                         </c:otherwise>  
		                   </c:choose>
                        </span>
	    </a>
	                    <span class="right date"><fmt:formatDate value="${item.releasetime}"  pattern="MM-dd"/></span>
	    </li>
        </c:if>
        </c:forEach>
        </ul>
        <ul id="htab2">
          <c:forEach items="${result}" var="item">
        <c:if test="${item.newsType==32}">
        <li class="odd">
        <a href='${cp}/frontNews/lookNewsById.htm?tId=${item.tid}' target="_blank" title="${item.title}" class="left">
                        <span style='color:;'>
                        <c:choose>  
	                         <c:when test="${fn:length(item.title) > 20}">  
	                             <c:out value="${fn:substring(item.title, 0, 20)}..." />  
	                         </c:when>  
	                        <c:otherwise>  
	                           <c:out value="${item.title}" />  
	                         </c:otherwise>  
		                   </c:choose>
                        </span>
	    </a>
	                    <span class="right date"><fmt:formatDate value="${item.releasetime}"  pattern="MM-dd"/></span>
	    </li>
        </c:if>
        </c:forEach>
    	</ul>
      </div>
    </div>
  </div>
  <div class="fr ind_rbox">
     <a href="http://139.224.60.3/app/HbuePartyDev.apk"><img src="${cp}/pages/webpages/images/app.jpg" alt="app下载"></a>
    <div class="ind_tzptbox mt20" style="text-align: center;">
    <img src="${cp}/pages/webpages/images/xsmTest.png" alt="">
    </div>
  </div>
  <div class="clear"></div>
</div>
<!--链接-->
<div class="botlink mt20 myLinks">
  <div class="hd">
    <ul>
      <li class="linktitle">网站导航：</li>
      <li onclick="window.open('http://www.hbue.edu.cn/structure/index.psp')" class="linkid">湖北经济学院</li>
      <li onclick="window.open('http://jwc.hbue.edu.cn/')" class="linkid">湖经教务处</li>
      <li onclick="window.open('http://kyc.hbue.edu.cn/')" class="linkid">湖经科研处</li>
      <li onclick="window.open('http://rsc.hbue.edu.cn/')" class="linkid">湖经人事处</li>
      <li onclick="window.open('http://ie.hbue.edu.cn/')" class="linkid">信息工程学院</li>
      <li onclick="window.open('http://xgxy.hbue.edu.cn/')" class="last linkid">大数据学院</li>
    </ul>
   </div>
</div>
</div>
    <%@include file="foot.jsp"%>
</div>


    <script type="text/javascript">
        //banner动画
        $(function() {
            var bannerSlider = new Slider($('#banner_tabs'), {
                time : 3000,
                delay : 2000,
                event : 'hover',
                auto : true,
                mode : 'fade',
                controller : $('#bannerCtrl'),
                activeControllerCls : 'active'
            });
            $('#banner_tabs .flex-prev').click(function() {
                bannerSlider.prev()
            });
            $('#banner_tabs .flex-next').click(function() {
                bannerSlider.next()
            });
            
            
            //新闻头条
            var htext = $('.toutiao').text();
            $('.toutiaotext').html(htext.substring(0,150)+'...');
        })
    </script>
</body>
</html>