<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<title>湖北经济学院党建门户网-帮助列表</title>
<link rel="stylesheet" type="text/css" href="${cp}/pages/webpages/css/mobile.css">
</head>

<body>
<div>
<%-- <div class="mlogodiv logo"><img alt="湖北经济学院" src="${cp}/pages/webpages/images/logo.jpg"></div> --%>
<div>
  <!-- <div class="tbox"><div class="htitle">帮助中心</div></div> -->
  <div class="helpcontener">
  <c:forEach items="${result}" var="item">
    <div class="hlist bottom_line">• <a href="${cp}/helpMananger/showHelpInfo.htm?id=${item.helpId}">${item.helpQuesition}</a></div>
  <!--   <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div>
    <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div>
    <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div>
    <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div>
    <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div>
    <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div>
    <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div>
    <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div>
    <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div>
    <div class="hlist bottom_line">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div> -->
   <!--  <div class="hlist">• <a href="#">对违法犯罪党员给予纪律处分应注意哪些问题...</a></div> -->
  </c:forEach>
  </div>
</div>

</div>
</body>
</html>