<%@page import="com.baobao.common.service.MemRoleService"%>
<%@page import="com.baobao.common.model.MemRoleModel"%>
<%@page import="com.baobao.common.service.MemberService"%>
<%@page import="com.baobao.common.model.MemberInfoModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<link
	href="${cp}/pages/js/plugins/bootstrap-select/css/bootstrap-select.min.css"
	rel="stylesheet">
<script
	src="${cp}/pages/js/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
<script
	src="${cp}/pages/js/plugins/bootstrap-select/js/lang/defaults-zh_CN.min.js"></script>
<script src="${cp}/pages/js/plugins/layerk/layer.js"></script>
<%
	Integer userId=(Integer) request.getSession().getAttribute("userId");
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">×</button>
	<h4 class="modal-title" id="myModalLabel">添加组织信息</h4>
</div>
<div class="modal-body">
	<form method="post" action="" class="form-horizontal" role="form"
		id="branch_form" style="margin: 20px;">
		<input name="brInId" type="hidden" id="brInId">
		<div class="form-group">
			<label for="lastname" class="col-sm-4 control-label">组织名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="partyBranchName"
					id="orName" placeholder="请输入组织名称"> <span id="span1"></span>
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-4 control-label">组织书记</label>
			<div class="col-sm-6">
				<select id="branch1" name="partyBranchSecretaryId" required
					class="selectpicker form-control" data-live-search="true"></select>
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-4 control-label">组织委员</label>
			<div class="col-sm-6">
				<select id="branch2" name="partyBranchOrganizationId" required
					class="selectpicker form-control" data-live-search="true"></select>
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-4 control-label">组织宣传委员</label>
			<div class="col-sm-6">
				<select id="branch3" name="partyBranchPublicityId" required
					class="selectpicker form-control" data-live-search="true"></select>
			</div>
		</div>
		<c:if test="${result.memberInbranchid==-1}">
			<div class="form-group">
				<label for="lastname" class="col-sm-4 control-label">组织类型</label>
				<div class="col-sm-6">

					<select id="branch4" name="partyBranchType" required
						class="selectpicker form-control" data-live-search="true">
						<option value="-1">请选择组织类型</option>
						<option value="0">校党委</option>
						<option value="1">院党委</option>
					</select>
				</div>
			</div>
		</c:if>
		<c:if test="${result.memberInbranchid!=-1}">
			<input name="partyBranchType" type="hidden" value="1">
		</c:if>
		<div class="modal-footer">
			<button id="submit" type="button" class="btn btn-primary">提交</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
	</form>
</div>
<script>
function legVali() {
    $('#branch_form').bootstrapValidator({
		message: '校验失败',
		feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
           },
        fields: {
        	partyBranchName: {
                   validators: {
                       notEmpty: {
                           message: '支部名字不能为空'
                       },
                       threshold :  1 , 
                       remote: {
                           url: '${cp}/branch/checkBranchName.htm',
                           message: '支部ID已存在',
                           type: 'POST',//请求方式
                           data: function(validator) {
                                 return {
                                	 brId: $('[name="branchId"]').val()
                                 };
                              }
                            
                       }
                   }
               },
           partyBranchType: {
                validators: {
                    notEmpty: {
                        message: '支部名称不能为空'
                    },
                    threshold :  1 , 
                    remote: {
                        url: '${cp}/branch/checkBranchName.htm',
                        message: '支部名称已存在',
                        type: 'POST',//请求方式
                        data: function(validator) {
                              return {
                            	  brname: $('[name="branchName"]').val()
                              };
                           }
                         
                    }
                }
            }
		}
    })
}
</script>
<script>
function submit(){
	 var $addPara={}
	 /* if($("#branch1").val()!=-1){
		 $addPara['partyBranchSecretary']=$("#branch1 option[value='"+$("#branch1").val()+"']").text();
	 }else{
		 bootboxDialog("没有选择组织书记！",'small',function(){});
		 return false;
	 }
	 if($("#branch2").val()!=-1){		 
		 $addPara['partyBranchOrganization']=$("#branch2 option[value='"+$("#branch2").val()+"']").text();
	 }else{
		 bootboxDialog("没有选择组织委员！",'small',function(){});
		 return false;
	 }
	 if($("#branch3").val()!=-1){
		 $addPara['partyBranchPublicity']=$("#branch3 option[value='"+$("#branch3").val()+"']").text();
	 }else{
		 bootboxDialog("没有选择组织宣传委员！",'small',function(){});
		 return false;
	 }
	 console.log($.param($addPara)); */
  	 $.ajax({
 		 url:"${cp}/partyBranch/editPartyBranch.htm",
 		 data:$.param($addPara)+"&"+$("#branch_form").serialize(),
 		 data:$("#branch_form").serialize(),
         dataType:"json",
         async: true,
         type:"post",
         success:function(data){
        	 console.log(data);
        	if(data.data>=1){
         	   	bootboxDialog("添加成功",'small',function(){$("#editModal").modal('hide');
         	   	var ulNode=document.createElement('ul');
         	   	var ulList=window.parent.document.getElementById("obshowul").getElementsByTagName('ul');
         	   	ulNode.className=ulList[ulList.length-1].getAttribute("class");
         	   	ulNode.setAttribute('data-id',data.id);
         	   	console.log(ulList[ulList.length-1].getAttribute("aria-expanded"));
         	   	ulNode.setAttribute('aria-expanded',ulList[ulList.length-1].getAttribute("aria-expanded"));  
         	   	ulNode.innerHTML='<li><a class="J_menuItem" href="/hbjiandang/branch/viewBranchByCondition.htm?branchFatherId='+data.id+'" data-index="13">'+data.name+'</a></li>';
/*          		window.parent.document.getElementById("obshowul").appendChild('<ul data-id="'+data.id+'" class="nav nav-second-level collapse in" aria-expanded="true" style=""><li>'
                       + '<a class="J_menuItem" href="/hbjiandang/branch/viewBranchByCondition.htm?branchFatherId='+data.id+ ' data-index="13">'+data.name+'</a>'
                       +'</li></ul>');  */
                window.parent.document.getElementById("obshowul").appendChild(ulNode);
                window.parent.callback()
         	   	window.location.reload();});
            }else{
            	if(data.data=1){
            		bootboxDialog(data.message,'small',function(){$("#editModal").modal('hide');});
            	}else{
            		bootboxDialog("添加失败！",'small',function(){$("#editModal").modal('hide');});
            	}
            }
         }
 	})	
}
$("#submit").click(function(){
/* 	console.log($("#orName").val()); */
	if($("#orName").val()==""){
		bootboxDialog("组织名称不能为空！",'small',function(){});
        return false;
	}else if($("#branch4").val()==-1){
		bootboxDialog("未选择组织类型！",'small',function(){});
		return false;
	}
	submit();
});

 $(function(){
		//支部名称列表
		$.ajax({
			url:'${cp}/branch/getMemberName.htm',
			dataType:'json',
			success : function(data){
				$('#branch1').append("<option value=\"-1\">请选择组织书记</option>");
				$('#branch2').append("<option value=\"-1\">请选择组织委员</option>");
				$('#branch3').append("<option value=\"-1\">请选择组织宣传委员</option>");
				$(data).each(function(i){
					$('#branch1').append("<option value=\""+data[i].memberId+"\">"+data[i].memberName+"</option>");
					$('#branch2').append("<option value=\""+data[i].memberId+"\">"+data[i].memberName+"</option>");
					$('#branch3').append("<option value=\""+data[i].memberId+"\">"+data[i].memberName+"</option>");
				})
				
				 $('.selectpicker').selectpicker({  
				        'selectedText': 'cat'  
				    });  
			},
			error : function(data){
				console.log("获取支部名称列表错误")
			}
		});
	})

 </script>

