<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}"  var="cp"></c:set>
<title>轮播图片添加</title>
</head>
<body>
	<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">添加图片信息</h4>
        </div>
    	<div class="modal-body">
          <form method="post" action="" class="form-horizontal" role="form" id="form_data"  enctype="multipart/form-data"   style="margin: 20px;">
             
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">所属类型</label>
              <div class="col-sm-10">
                <select class="form-control layer-date" name="imageType" id="nation">
                	<option value="1">轮播</option>
                	<option value="2">其他</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">图片上传</label>
              <div class="col-sm-10">
                <input type="file" class="form-control" style="width:240px" name="myfile"  id="title" accept="image/*"  placeholder="">
                <span style="color:red;">注：上传图片的宽为1160像素、高为291像素！</span>
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
		 url:'${cp}/pic/insertPic.htm',
		 data:formData,
         async: true,
         contentType:false,
         async: false,
         cache: false,
         processData: false,
         type:"post",
         success:function(data){
           if(data==1){
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