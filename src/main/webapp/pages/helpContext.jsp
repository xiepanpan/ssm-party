<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<!DOCTYPE html>
<html>

<head>
    <title>详情页面</title>
    <%@include file="common.jsp"%>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
               		<div class="ibox-content">
                        <div class="text-center article-title">
                            <h2>${helpInfo.helpQuesition}</h2>
                        </div>
                        <p>${helpInfo.helpAnswer}</p>
                       <hr>
					  <div class="row">
                            <div class="col-lg-12" style="text-align:center;">
                            <a onClick="backcheck()" class="btn btn-info btn-rounded" href="javascript:;">返回</a>
                            </div>
                     </div>
					</div>
                </div>
            </div>
        </div>
	</div>
    <%@include file="common_js.jsp"%>
</body>

</html>
