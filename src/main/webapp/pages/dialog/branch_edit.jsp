<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<link href="${cp}/pages/js/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
<script src="${cp}/pages/js/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="${cp}/pages/js/plugins/bootstrap-select/js/lang/defaults-zh_CN.min.js"></script>
		<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">编辑支部信息</h4>
        </div>
		<div class="modal-body">
          <form method="post" action="" class="form-horizontal" role="form" id="branch_edit_form"  style="margin: 20px;">
          <input type="hidden" name="branchId" id="brIds" value="${branchInfo.branchId}">
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">支部名称</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="branchName" value="${branchInfo.branchName}"  readonly="readonly">
              </div>
            </div>
           <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">支部书记</label>
              <div class="col-sm-6">
                <select id="branch1" name="brSeId" class="selectpicker form-control" data-live-search="true"  placeholder="请选择支部书记"></select>
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">支部组织委员</label>
              <div class="col-sm-6">
              	<select id="branch2" name="brOrgId" class="selectpicker form-control" data-live-search="true"  placeholder="请选择支部书记"></select>
              </div>
            </div>
 			<div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">支部宣传委员</label>
              <div class="col-sm-6">
               <select id="branch3" name="brXcId" class="selectpicker form-control" data-live-search="true"  placeholder="请选择支部书记"></select>
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">备注</label>
              <div class="col-sm-6">
              	<input type="text" class="form-control" name="branchRemark"  value="${branchInfo.branchRemark}"  placeholder="">
              </div>
            </div>
	 	<div class="modal-footer">
          <button type="submit" class="btn btn-primary">提交</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
        </form>
	 </div>
	 

<script>
$(function () {
    $('#branch_edit_form').bootstrapValidator({
		message: '校验失败',
		feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
           },
        fields: {
        	branchName: {
                validators: {
                    notEmpty: {
                        message: '支部名称不能为空'
                    }
                }
            }
		}
    }).on("success.form.bv",function(e){console.log(e)
    	e.preventDefault();
    	valiadate();
    	});
});
</script>
 
<script>
function valiadate(){
	 var $addPara={}
	 if($("#branch1").val()!=-1){
		 $addPara['branchSecretary']=$("#branch1 option[value='"+$("#branch1").val()+"']").text();	
	 }else{
		 bootboxDialog("没有选择支部书记！",'small',function(){});
		 $('.modal-footer button[type="submit"]').attr("disabled",false);
		 return false;
	 }
	 if($("#branch2").val()!=-1){
		 $addPara['branchOrganizationCommittee']=$("#branch2 option[value='"+$("#branch2").val()+"']").text();
	 }else{
		 bootboxDialog("没有选择支部委员！",'small',function(){});
		 $('.modal-footer button[type="submit"]').attr("disabled",false);
		 return false;
	 }
	 if($("#branch3").val()!=-1){
		 $addPara['branchPublicityCommittee']=$("#branch3 option[value='"+$("#branch3").val()+"']").text();
	 }else{
		 bootboxDialog("没有选择支部宣传委员！",'small',function(){});
		 $('.modal-footer button[type="submit"]').attr("disabled",false);
		 return false;
	 }
	 $.ajax({
		 url:"${cp}/branch/updateBranch.htm",
		 data:$.param($addPara)+"&"+$("#branch_edit_form").serialize(),
         dataType:"json",
         async: true,
         type:"post",
         success:function(data){
           if(data==1){
        	   bootboxDialog("编辑成功",'small',function(){$("#editModal").modal('hide');window.location.reload();});
           }else{
        	   bootboxDialog("编辑失败！",'small',function(){$("#editModal").modal('hide')});
           }
        }
	 })
 }
$(function(){
	var branch1='${branchInfo.brSeId==null?-1:branchInfo.brSeId}';
	console.log(branch1);
	var branch2='${branchInfo.brOrgId==null?-1:branchInfo.brOrgId}';
	var branch3='${branchInfo.brXcId==null?-1:branchInfo.brXcId}';
	var id = document.getElementById("brIds").value;
	//支部名称列表
	$.ajax({
		url:'${cp}/branch/getMemberName.htm',
		dataType:'json',
		data:{brId:id},
		success : function(data){
			
			$('#branch1').append("<option value=\"-1\">请选择支部书记</option>");
			$('#branch2').append("<option value=\"-1\">请选择支部委员</option>");
			$('#branch3').append("<option value=\"-1\">请选择支部宣传委员</option>");
			$(data).each(function(i){
				$('#branch1').append("<option value=\""+data[i].memberId+"\">"+data[i].memberName+"</option>");
				$('#branch2').append("<option value=\""+data[i].memberId+"\">"+data[i].memberName+"</option>");
				$('#branch3').append("<option value=\""+data[i].memberId+"\">"+data[i].memberName+"</option>");
			})
			 $('.selectpicker').selectpicker({  
			        'selectedText': 'cat'  
			    });  
			$('#branch1').append("<option value=${branchInfo.brSeId}>${branchInfo.branchSecretary}</option>");
			if(branch2!=-1){
				$('#branch2').append("<option value=${branchInfo.brOrgId}>${branchInfo.branchOrganizationCommittee}</option>");
			}
			if(branch3!=-1){
				$('#branch3').append("<option value=${branchInfo.brXcId}>${branchInfo.branchPublicityCommittee}</option>");
			}
			$('#branch1').selectpicker('val',branch1);
			$('#branch2').selectpicker('val',branch2);
			$('#branch3').selectpicker('val',branch3);

		},
		error : function(data){
			console.log("获取支部名称列表错误")
		}
	});
})
 </script>

