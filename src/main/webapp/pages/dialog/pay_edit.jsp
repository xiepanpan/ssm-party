<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<link href="${cp}/pages/js/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
<script src="${cp}/pages/js/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="${cp}/pages/js/plugins/bootstrap-select/js/lang/defaults-zh_CN.min.js"></script>
		<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">编辑党费信息</h4>
        </div>
		<div class="modal-body">
          <form method="post" action="" class="form-horizontal" role="form" id="pay_form"  style="margin: 20px;">
          <input type="hidden" name ="payId" value="${payInfo.payId}">
          	<div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">用户所在支部</label>
              <div class="col-sm-6"> 
                   	<select id="payBr" name="payBrName" value="${payInfo.payBrName}" required class="form-control" data-live-search="true"  placeholder="请选择用户所在支部"></select>
             	 <span id="span2"></span>
              </div>
            </div>
          	<div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">用户名称</label>
              <div class="col-sm-6"> 
                  <select id="payNa" name="payTel" value="${payInfo.payName}" required class=" form-control" data-live-search="true"  placeholder="请选择用户姓名"></select>
             	 <span id="span2"></span>
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">用户手机</label>
              <div class="col-sm-6"> 
                   	<select id="payT" name="payName" value="${payInfo.payTel}" required class="form-control" data-live-search="true"  placeholder="请选择用户手机号"></select>
             	 <span id="span2"></span>
              </div>
            </div>
           
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">当前年月日</label>
              <div class="col-sm-6">
                <input  class="laydate-icon form-control layer-date" name="payMonth" id="paydate" value="<fmt:formatDate value="${payInfo.payMonth}"  pattern="yyyy-MM-dd" type="date" dateStyle="long"/>" >
             	 <span id="span1"></span>
              </div>
            </div>
           <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">当前应缴费用</label>
              <div class="col-sm-6">
              <input type="text" class="form-control" name="payFees"  value="${payInfo.payFees}" id="" placeholder="">
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">缴费状态</label>
              <div class="col-sm-6">
           		<label class="checkbox-inline">
                  <input type="radio" name="payStatus" <c:if test="${payInfo.payStatus eq 1}">checked</c:if> value="1" >已缴
                </label>
                <label class="checkbox-inline">
                  <input type="radio" name="payStatus" <c:if test="${payInfo.payStatus eq 0}">checked</c:if> value="0" >未缴
                </label>
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
    $('#pay_form').bootstrapValidator({
		message: '校验失败',
		feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
           },
        fields: {
        	payTel: {
                   validators: {
                       notEmpty: {
                           message: '不能为空'
                       }, 
                   }
               },
               payName:{
            	   validators: {
                       notEmpty: {
                           message: '不能为空'
                       },   
            	   }
               },
              payMonth: {
            	  validators: {
                      notEmpty: {
                          message: '当前年月不能为空'
                      },
                  }
            },
            payFees:{
            	 validators: {
                     notEmpty: {
                         message: '应缴党费不能为空'
                     }, 
                 }
            }
		}
    }).on("success.form.bv",function(e){console.log(e)
    	e.preventDefault();
    	valiadate();
    	});
});

var paydate={elem:"#paydate",format:"YYYY-MM-DD",max:"2099-06",istime:true,istoday:true,choose:function(datas){$('#pay_form').data('bootstrapValidator').updateStatus('payMonth', 'NOT_VALIDATED',null).validateField('payMonth');}};
laydate(paydate);
</script>
<script>
function valiadate(){
	
	 $.ajax({
		 url:"${cp}/pay/editPayInfo.htm",
		 data:$("#pay_form").serialize(),
         dataType:"json",
         async: true,
         type:"post",
         success:function(data){
        	 if(data>0){
        	   bootboxDialog("编辑成功",'small',function(){$("#editModal").modal('hide');window.location.reload();});
           }else{
        	   bootboxDialog("编辑失败",'small',function(){$("#editModal").modal('hide')});
           }
        }
	 })
 }
$("#payBr").change(function(){
	$("#payNa option").remove();
	$("#payT option").remove();
	var payBr = $("#payBr").val();
	$.ajax({
		url:'${cp}/pay/getMemberTelName.htm?Ids='+payBr,
		dataType:'json',
		success:function(data){
			$('#payNa').append("<option value=\"\">请选择姓名</option>");
			$(data).each(function(i){
				$('#pay_form').data('bootstrapValidator').updateStatus('payTel', 'NOT_VALIDATED',null).validateField('payTel');
				$('#payNa').append("<option value=\""+data[i].member_tel+"\">"+data[i].member_name+"</option>");
			});
			$('#payT').append("<option value=\"\">请选择手机号</option>");
			$(data).each(function(i){
				$('#pay_form').data('bootstrapValidator').updateStatus('payName', 'NOT_VALIDATED',null).validateField('payName');
				$('#payT').append("<option value=\""+data[i].member_name+"\">"+data[i].member_tel+"</option>");
			})
		},
		error:function(data){
			console.log("获取姓名错误")
		}
	})
})
$("#payNa").change(function(){
	var payT= $("#payNa").val();
	var select = document.getElementById("payT");
	var options = select.options;
	var selectBr = document.getElementById("payBr");
	var optionsBr = selectBr.options;
	$("#payT option").each(function (i){ 
		if(options[i].text==payT){
			select.options[i].selected = true;//动态为select赋值
			$('#pay_form').data('bootstrapValidator').updateStatus('payName', 'NOT_VALIDATED',null).validateField('payName');
			return false;//return true 相当于continue
		}
	 })
	 $.ajax({
		 url:'${cp}/pay/getBranchByTel.htm?tel='+payT,
		 dataType:'json',
		 success:function(data){
			$("#payBr option").each(function (i){
				if(optionsBr[i].value==data){
					selectBr.options[i].selected = true;//动态为select赋值
					$('#pay_form').data('bootstrapValidator').updateStatus('payBrName', 'NOT_VALIDATED',null).validateField('payBrName');
						return false;//return true 相当于continue
					}
			 })
		 }
	 })
})
$("#payT").change(function(){
	var index=document.getElementById("payT").selectedIndex;//获取当前选择项的索引.
	var payT = document.getElementById("payT").options[index].text;//获取当前选择项的值.
	var payName = $("#payT").val();
	var select = document.getElementById("payNa");
	var options = select.options;
	var selectBr = document.getElementById("payBr");
	var optionsBr = selectBr.options;
	$("#payNa option").each(function (i){  
		if(options[i].text==payName){
			select.options[i].selected = true;//动态为select赋值
			$('#pay_form').data('bootstrapValidator').updateStatus('payTel', 'NOT_VALIDATED',null).validateField('payTel');
			return false;//return true 相当于continue
		}
	 })
	  $.ajax({
		 url:'${cp}/pay/getBranchByTel.htm?tel='+payT,
		 dataType:'json',
		 success:function(data){
			 $("#payBr option").each(function (i){
				if(optionsBr[i].value==data){
					selectBr.options[i].selected = true;//动态为select赋值
					$('#pay_form').data('bootstrapValidator').updateStatus('payBrName', 'NOT_VALIDATED',null).validateField('payBrName');
						return false;//return true 相当于continue
					}
			 })
		 }
	 })
})
$(function(){
	var paytel='${payInfo.payTel}';
	var payNa = '${payInfo.payName}';
	var payBr = '${payInfo.payBrId}';
	//支部名称列表
   $.ajax({
		url:'${cp}/pay/getMemberTelName.htm',
		dataType:'json',
		success : function(data){
			$('#payT').append("<option value=\"\">请选择手机号</option>");
			$(data).each(function(i){
				if(data[i].member_tel==paytel){
					$('#payT').append("<option selected value=\""+data[i].member_name+"\">"+data[i].member_tel+"</option>");
				}else{
					$('#payT').append("<option value=\""+data[i].member_name+"\">"+data[i].member_tel+"</option>");
				}
			});
			$('#payNa').append("<option value=\"\">请选择用户姓名</option>");
			$(data).each(function(i){
				if(data[i].member_name==payNa){
					$('#payNa').append("<option selected value=\""+data[i].member_tel+"\">"+data[i].member_name+"</option>");
				}else{
					$('#payNa').append("<option value=\""+data[i].member_tel+"\">"+data[i].member_name+"</option>");
				}
			});
		},
		error : function(data){
			console.log("获取手机号列表错误")
		}
	});

 	$.ajax({
		url:'${cp}/pay/getBrName.htm',
		dataType:'json',
		success : function(data){
			$('#payBr').append("<option value=\"\">请选择支部名称</option>");
			$(data).each(function(i){
				if(data[i].branchId==payBr){
					$('#payBr').append("<option selected value=\""+data[i].branchId+"\">"+data[i].branchName+"</option>");
				}else{
				$('#payBr').append("<option value=\""+data[i].branchId+"\">"+data[i].branchName+"</option>");
				}
			})
		},
		error : function(data){
			console.log("获取支部错误")
		}
	});
	
})

 </script>

