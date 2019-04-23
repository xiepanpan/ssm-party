<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
		<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">导入党员信息</h4>
        </div>
		<div class="modal-body">
          <form method="post" action="" class="form-horizontal" role="form" id="branch_form"  style="margin: 20px;">
          <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">选择文件</label>
              <div class="col-sm-10">
                <input type="file" class="form-control" name="myfile"  id="title" placeholder="">
               	<a href="${cp}/excel/member.xlsx" style="color:red;font-size: 14px;">*下载模板</a>
              </div>
           </div>
           <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label"></label>
           </div>
         <div class="infor_containor"><p id="infor" class="hidden"></p></div>
	 	<div class="modal-footer">
          <button id="submit1" type="button" class="btn btn-primary" onclick="valiadate()">提交</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
        </form>
	 </div>
<script>
function valiadate(){
	 var formData = new FormData(document.getElementById("branch_form"));
	 $.ajax({
		 url:"${cp}/memeber/importMemberExcel.htm",
		 data:$("#branch_form").serialize(),
		 data:formData,
         async: true,
         contentType:false,
         async: false,
         cache: false,
         processData: false,
         type:"post",
         success:function(data){
           var result = $.parseJSON(data);
           if(result.resultType==0){
        	   $("#submit1").attr("disabled",true);
        	   bootboxDialog("导入成功",'small',function(){$("#editModal").modal('hide');window.location.reload();});
           }else if(result.resultType==1 || result.resultType ==2){
        	   $('#infor').empty().html("导入失败! "+result.message).removeClass('hidden');
           }
        }
	 })
 }
 </script>

