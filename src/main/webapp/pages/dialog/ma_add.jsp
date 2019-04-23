<%@page import="com.baobao.common.service.MemRoleService"%>
<%@page import="com.baobao.common.model.MemRoleModel"%>
<%@page import="com.baobao.common.service.MemberService"%>
<%@page import="com.baobao.common.model.MemberInfoModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<link
	href="${cp}/pages/js/plugins/bootstrap-select/css/bootstrap-select.min.css"
	rel="stylesheet">

<script
	src="${cp}/pages/js/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
<script
	src="${cp}/pages/js/plugins/bootstrap-select/js/lang/defaults-zh_CN.min.js"></script>
	<link
	href="${cp}/pages/plugins/btdaterangepicker/daterangepicker.css"
	rel="stylesheet">
	<script type="text/javascript" src="${cp}/pages/plugins/btdaterangepicker/moment.js"></script>
    <script type="text/javascript" src="${cp}/pages/plugins/btdaterangepicker/daterangepicker.js"></script>
   
<%
	Integer userId=(Integer) request.getSession().getAttribute("userId");
%>
<style type="text/css">
input[type=checkbox]{
	margin-top:2px!important;
    line-height: normal;
}
.tangram-suggestion-main {  
    position: absolute; 
  	z-index: 12345;  
    left: 542px;  
    top: 128px;  
    width: 300px;
}
</style>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">×</button>
	<h4 class="modal-title" id="myModalLabel">添加活动信息</h4>
</div>
<div class="modal-body">
	<form method="post" action="" class="form-horizontal" role="form"
		id="branch_form" style="margin: 20px;">
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动名称</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="activityTitle"
					id="acName" placeholder="请输入活动名称"> <span id="span1"></span>
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动地点</label>
			<div class="col-sm-9">
				<input id="acplace" name="activityPlace"  class="form-control"   placeholder="请输入活动地点">
				<div id="searchResultPanel" style="display: none" ></div>
			</div>
			<input id="latitude"  name="latitude" type="hidden">
			<input id="longitude"  name="longitude" type="hidden">
		</div>
		<div id="allmap" style="width: 640px;height: 340px;overflow: hidden;margin-bottom:20px;font-family:'微软雅黑';left:30px;display: none"></div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动时间</label>
			<div class="col-sm-9">
		    <div class="input-group">
		    	<input id="dateStart" class="laydate-icon form-control layer-date" name="activityStarttime" placeholder="请点击选择活动开始时间" readonly>
		      	<div class="input-group-addon">至</div>
		     	<input id="dateEnd" class="laydate-icon form-control layer-date" name="activityEndtime" placeholder="请点击选择活动结束时间" readonly>
			</div>
			</div>						
		</div>	
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动内容</label>
			<div class="col-sm-9">
				<textarea id="branch3" name="activityContext"  class="form-control" data-live-search="true" style="resize:none;height:300px;"></textarea>
			</div>
		</div>
		<div class="form-group has-feedback">
			<label for="lastname" class="col-sm-2 control-label">参与支部</label>
			<div class="col-sm-9"> 
				<c:forEach items="${result}" var="item">
				 <div class="checkbox col-sm-3">
				    <label><input type="checkbox" name="brList" value="${item.brId}">${item.brName}</label>
				  </div>
				</c:forEach> 
 			</div>
 			<br/><div id="mulckerr" class="col-sm-9 col-sm-offset-2"></div>	 
		</div>
		<div class="modal-footer">
			<button id="submit" type="submit" class="btn btn-primary">提交</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
		
	</form>
</div>
<script>

$(function () {
    $('#branch_form').bootstrapValidator({
		message: '校验失败',
		feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
           },
        fields: {
        	activityTitle: {
                   validators: {
                       notEmpty: {
                           message: '活动名称不能为空'
                       }
                   }
               },
            activityPlace: {
                validators: {
                    notEmpty: {
                        message: '活动地点不能为空'
                    },
                    
                }
            },
            activityStarttime:{
            	validators: {
					notEmpty: {
						message: '活动开始时间不能为空'
					},
					date: {
						message: '时间格式错误',
						format: 'YYYY-MM-DD hh:mm'
					},
					callback:{
						message:'活动开始时间不得大于等于活动结束时间',
						callback:function(value,validator){
							if(comptime(value,$('#dateEnd').val())){
								return true; 
							}else{
								$("#dateStart").val(null);
								return false;		
							}
						}
					}
				}
            },
            activityEndtime:{
            	validators: {
					notEmpty: {
						message: '活动结束时间不能为空'
					},
					date: {
						message: '时间格式错误',
						format: 'YYYY-MM-DD hh:mm'
					},
					callback:{
						message:'活动结束时间不得小于等于活动开始时间',
						callback:function(value,validator){
							if(comptime($('#dateStart').val(),value)){
								return true;
							}else{
								$("#dateEnd").val(null);
								return false;
							}
							
						}
					}
				}
            },
            activityContext: {
                validators: {
                    notEmpty: {
                        message: '活动内容不能为空'
                    }
                }
            },
            "brList":{
                validators: {
                    choice: {
                        min: 1,
                        message: '至少要选择%s个作为参与者'
                    }
                },
        		container:'#mulckerr'
            }
		}
    }).on("success.form.bv",function(e){console.log(e)
    	e.preventDefault();
    	valiadate();
    });
});
</script>
<script>
var indate={elem:"#dateStart",format:"YYYY-MM-DD hh:mm",max:"2099-06-16 00:00",istime:true,istoday:true,choose:function(datas){$('#branch_form').data('bootstrapValidator').updateStatus('activityStarttime', 'NOT_VALIDATED',null).validateField('activityStarttime');}};
var newdate={elem:"#dateEnd",format:"YYYY-MM-DD hh:mm",max:"2099-06-16 00:00",istime:true,istoday:true,choose:function(datas){$('#branch_form').data('bootstrapValidator').updateStatus('activityEndtime', 'NOT_VALIDATED',null).validateField('activityEndtime');}};
laydate(indate);
laydate(newdate);
</script>
<script>
function valiadate(){
  	 $.ajax({
		 url:"${cp}/actPush/publishActivity.htm",
		 data:$("#branch_form").serialize(),
	     dataType:"json",
	     async: true,
	     type:"post",
	     success:function(data){
	       if(data>=1){
	    	   bootboxDialog("添加成功",'small',function(){$("#editModal").modal('hide');window.location.reload();});
	       }else if(data==-1){
	    	   bootboxDialog("输入的地址无效，请重新输入！");
	       }else{
	    	   bootboxDialog("添加失败！",'small',function(){$("#editModal").modal('hide');});
	       }
	    }
	})	
}

/* function valiadate(){
	 console.log($("#dateStart").val());
	 if($("#dateStart").val()=='' || $("#dateEnd").val()==''){
		 alert("未选择活动日期");
		 $("#submit").attr('disabled',false);
		 return false;
	 }else{
		 dsArr=($("#dateStart").val()).split(/-| |:/);
		 deArr=($("#dateEnd").val()).split(/-| |:/);
 		 sdate=new Date(dsArr[0],dsArr[1],dsArr[2],dsArr[3],dsArr[4],dsArr[5]);    		
		 edate=new Date(deArr[0],deArr[1],deArr[2],deArr[3],deArr[4],deArr[5]);
		 if(compare(sdate,edate)){
			 submit();
		 }else{
			 console.log("日期范围不正确");
			 $("#submit").attr('disabled',false);
			 return false;
		 }
	 }
} */

$(function(){
		//获取院系列表
		$.ajax({
			url:'${cp}/branch/getMemberName.htm',
			dataType:'json',
			success : function(data){
				console.log(data);
 
			},
			error : function(data){

			}
		});
	})
function checkPlace(){
	var place = $('#acplace').val();
	$.ajax({
		 url: '${cp}/actPush/isValidPalce.htm?activityPlace='+place,
		 type:"post",
	     success:function(data){
	    	//alert(data)
	       return data;
	    }
	})
	
}
 </script>
<script type="text/javascript">
   // var map = new BMap.Map("allmap");
	function G(id) {
		 	return document.getElementById(id);
		}
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
					{"input" : "acplace"
				});
		ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
			var _value = e.fromitem.value;
			var value = "";
			if (e.fromitem.index > -1) {
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
			
			value = "";
			if (e.toitem.index > -1) {
				_value = e.toitem.value;
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
			G("searchResultPanel").innerHTML = str;
		});
	
		var myValue;
		ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
			myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
			
			setPlace();
		});
	
		function setPlace(){
			//map.clearOverlays();    //清除地图上所有覆盖物
			function myFun(){
				var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
				//map.centerAndZoom(pp, 18);
				//map.addOverlay(new BMap.Marker(pp));//添加标注
				document.getElementById("latitude").value=pp.lat;
				document.getElementById("longitude").value=pp.lng;
			}
		/* 	var local = new BMap.LocalSearch(map,{
				onSearchComplete:myFun
			})
			local.search(myValue); */
		}
    </script>