<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}"  var="cp"></c:set>
<title>活动分类添加</title>
</head>
<body>
	<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">添加活动分类</h4>
        </div>
    	<div class="modal-body">
          <form method="post" action="" class="form-horizontal" role="form" id="form_data"  enctype="multipart/form-data"   style="margin: 20px;">
           <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">活动分类名称</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" style="width:240px" name="activityTypeName"  id="title"  placeholder="">
              </div>
            </div>
 		<div class="modal-footer">
          <button type="button" class="btn btn-primary" onclick="valiadate()">提交</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
        </form>
	 </div>
	 <script type="text/javascript">
	 function valiadate(){
	var formData = new FormData(document.getElementById("form_data"));
    $.ajax({
		 url:'${cp}/act/saveActType.htm',
		 data:formData,
         async: true,
         contentType:false,
         async: false,
         cache: false,
         processData: false,
         type:"post",
         success:function(data){
        	var dJson=eval("("+data+")");
        	 console.log(dJson);
        	 /* console.log(data.status); */
           if(dJson.status){
        	   bootboxDialog("添加成功",'small',function(){$("#editModal").modal('hide');window.location.reload();});
        	   
           }else{
        	   bootboxDialog("添加失败！",'small',function(){$("#editModal").modal('hide')});
           }
        }
	 })
 	}
	 </script>
</body>
</html>