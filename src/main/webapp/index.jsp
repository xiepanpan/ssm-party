<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${cp}/pages/js/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<input type="hidden" src="${cp}/pages/img/a1.png">
	<script type="text/javascript">
	 $(function(){
		window.location.href="${cp}/frontNews/index.html";
	})
	</script>
</body>
</html>