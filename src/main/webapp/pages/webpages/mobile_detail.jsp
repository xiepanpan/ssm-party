<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${cp}/pages/webpages/js/jquery-min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<title>湖北经济学院党建门户网-新闻详情</title>
<link rel="stylesheet" type="text/css" href="${cp}/pages/webpages/css/mobile.css">
</head>

<body>
<div class="ind_body" id="container">
<%-- <div class="mlogodiv logo"><img alt="湖北经济学院" src="${cp}/pages/webpages/images/logo.jpg"></div> --%>
<div class="lmy_container">
  <div class="secnr" id="color_printsssss">
    <div id="wenzhang">
        <h1 class="wztit" style="color:;">${newsInfo.title }</h1>
        <div class="wzfbxx">日期：<fmt:formatDate value="${newsInfo.releasetime}"  pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" />&emsp;点击：${newsInfo.readcount }</div>
        <div class="wzcon">
        <p>${newsInfo.newsContext }</p>
        <div class="clear"></div>
        </div>
        <div><br></div>
   </div>
  </div>
</div>

</div>
</body>
</html>