<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<!DOCTYPE html>
<html>
<head>
    <title>详情页面</title>
    <%@include file="common.jsp"%>
<style type="text/css">
	.lpinline{
		padding-top:3px;
	}
	.btnmar{
	   margin-bottom:5px;
	}
</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
               		<div class="ibox-content">
                        <div class="text-center article-title">
                            <h2>${result.actModel.activityTitle}</h2>
                        </div>
                     <div class="form-horizontal">
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">活动地点</label>
							<div class="col-sm-9">
								<p id="acplace" name="activityPlace" class="lpinline">${result.actModel.activityPlace}</p>
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">活动时间</label>
							<div class="col-sm-9">
						    <div class="input-group">
						    	<input id="dateStart" class="form-control" name="activityStarttime" value="<fmt:formatDate value='${result.actModel.activityStarttime}'  pattern='yyyy-MM-dd HH:mm'/>"  readonly>
						      	<div class="input-group-addon">至</div>
						     	<input id="dateEnd" class="form-control" name="activityEndtime" value="<fmt:formatDate value='${result.actModel.activityEndtime}'  pattern='yyyy-MM-dd HH:mm'/>" readonly>
							</div>
							</div>						
						</div>	
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">活动内容</label>
							<div class="col-sm-9">
								<p  name="activityPlace" class="lpinline">${result.actModel.activityContext}</p>
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="lastname" class="col-sm-2 control-label">参与支部</label>
							
							<div class="col-sm-9 lpinline"> 
							<c:forEach items="${result.partList}" var="item">
								<div class="col-sm-2 btnmar">
									<button class="btn btn-xs btn-primary ">${item.brName}</button>
								</div>
							</c:forEach>	
				 			</div>
				 			<br/>
						</div>
					</div>
                       	<hr/>
                       	
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
