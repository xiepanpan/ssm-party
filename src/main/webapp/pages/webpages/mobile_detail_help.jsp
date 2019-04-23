<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<title>湖北经济学院党建门户网-帮助详情</title>
<link rel="stylesheet" type="text/css" href="${cp}/pages/webpages/css/mobile.css">
</head>

<body>
<div class="ind_body" id="container">
<%-- <div class="mlogodiv logo"><img alt="湖北经济学院" src="${cp}/pages/webpages/images/logo.jpg"></div> --%>
<div class="lmy_container">
  <div class="secnr_h">
    <div class="">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    	<td class="t1" align="right">问题：</td>
    	<td class="t2" align="left">${helpInfo.helpQuesition}</td>
    	</tr>
    	<tr>
    	<td class="line_h" colspan="2"></td>
    	</tr>
    	<tr>
    	<td class="t11" align="right" valign="top">回复：</td>
    	<td class="t2" align="left">${helpInfo.helpAnswer}</td>
    	</tr>
    	</table>
        <div><br></div>
   </div>
  </div>
</div>

</div>
</body>
</html>