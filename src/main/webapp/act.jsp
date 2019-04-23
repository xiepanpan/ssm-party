<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/hbjiandang/act/createAct.app" method="post">
		activityName=活动&activityContent=这个是活动&activityStartTime=2018-6-10%20%2010：10：10
		&activityEndTime=2018-8-10%2010：10：10&activityAddress=湖经&typeIds=3
		&branchIds=1&userIds=211
		活动名称:<input type="text" name="activityName">
		活动内容:<input type="text" name="activityContent">
		活动开始时间:<input type="text" name="activityStartTime">
		活动结束时间:<input type="text" name="activityEndTime">
		活动地址:<input type="text" name="activityAddress">
		活动分类:<input type="text" name="typeIds">
		活动支部:<input type="text" name="branchIds">
		活动参与者:<input type="text" name="userIds">
		<input type="submit" value="tijiao">
	</form>
</body>
</html>