<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <title>后台管理-首页</title>
    <%@include file="common.jsp"%>

</head>

<body class="gray-bg">
    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
            <hr>
            <blockquote class="text-warning" style="font-size:14px">Hello${sessionScope.userName}~
            </blockquote>
            <hr>
        </div>
    </div>
</body>
</html>
