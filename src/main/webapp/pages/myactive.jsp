<%@page import="com.baobao.common.service.MemRoleService"%>
<%@page import="com.baobao.common.model.MemRoleModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>支部管理</title>
    <%@include file="common.jsp"%>    
</head>
<style type="text/css">
.th-inner{
	text-align:center;
}
tr td{
	text-align:center;
}
</style>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <!-- Panel Other -->
        <input type="hidden" value="${cmd.newsType}" id="pageType">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
            	<div id="toolbar" class="">            	            	
                	<form action="" method="post">               	                               	
                   		<div class="btn-group">
                   	 		<input type="hidden" value="${branchFatherId }" id="branchFatherId"> 
				 			<button id="addbut" class="btn btn-primary " type="button"><i class="fa fa-plus"></i>&nbsp;添加</button>  
				 			<button id="editbut" class="btn btn-success " type="button"><i class="fa fa-pencil"></i>&nbsp;修改</button>
                     <!--        <button id="delbut" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</butto -->                 
                   			<button id="delbatch" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>                  
                   		</div>
                   	<!-- 	<div class="columns columns-right pull-right">
                   			<button class="btn btn-default btn-outline" type="submit"><i class="fa fa-search"></i></button>
                   		</div>
                   		<div class="pull-right search"><input class="form-control" type="text" placeholder="活动名称" style="width:270px;" name="searchValue">&nbsp;</div> -->
                   	</form>
            	</div>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <!-- Example Pagination -->
                        <div class="example-wrap">
                            <div class="example">
                                 <table id="exampleTablePagination" data-url="" data-query-params="queryParams" data-show-pagination-switch="false" data-pagination="true" data-only-info-pagination="true"  data-page-list="[15,30]"  data-toggle="table" data-pagination="true" data-page-size="15" data-striped="true">
                                    <thead>
                                        <tr>
                                        	<th data-field="state" data-checkbox="true"></th>
                                        	<th data-field="activityId"  data-visible="false">活动ID</th>
                                            <th data-field="activityTitle">活动名称</th>
                                            <th data-field="activityPlace">活动地点</th>
                                            <th>活动状态</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                	<c:forEach items="${result}" var="item" varStatus="loop">
                                        <tr>
                                            <td data-checkbox="true"></td>
                                          	<td>${item.activityId}</td>
                                            <td>${item.activityTitle}</td>
                                            <td>${item.activityPlace}</td>
                                            <td>
                                            	${status[loop.count-1]}
                                            </td>
                                            <td><a class="btn-xs btn-primary" href="${cp}/activity/LookActDetails.htm?actId=${item.activityId}">查看详情</a></td>
                                         </tr>
                                     </c:forEach>
                                    </tbody>
                                </table>                               
                            </div>
                        </div>
                        <!-- End Example Pagination -->
                    </div>

                </div>
            </div>
        </div>
        <!-- End Panel Other -->
    </div>
  <input type="hidden" id="actest"> 
<!-- 修改弹框内容 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
<div class="modal-dialog" style="width:810px;" >
<div class="modal-content">

</div>
</div>
</div>
<!-- 修改弹框内容 -->


    <%@include file="common_js.jsp"%>

	<script>
    $("#editbut").click(function(){
		var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		 if(a.length<=0 || a.length>1){
			 bootboxDialog("请选中一条数据进行修改！");
		}else{
			$("#editModal").modal({
		        remote: "${cp}/actPush/openEditPage.htm?actId="+a[0].activityId,
		        show: false
		    });
		    $("#editModal").modal("toggle");
 		} 		
	});
    
    $("#delbut").click(function(){
		var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		 if(a.length<=0 || a.length>1){
			 bootboxDialog("请选中一条数据进行删除！");
		}else{
			successDialog("提醒",'是否确认删除',function(){
				 $.ajax({
					 url:"${cp}/activity/deleteActivity.htm?actId="+a[0].activityId,
					 dataType:"json",
			         async: true,
			         type:"post",
			         success:function(data){
			           if(data>=1){
			        	   bootboxDialog("删除成功！",'small',function(){window.location.reload();});
			           }else{
			        	   bootboxDialog("删除失败！");
			           }
			        }
				 })
			});
		}
	});
    $("#delbatch").click(function(){
		var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		
		 if(a.length<=0){
			 bootboxDialog("请选中至少一条数据进行删除！");
		}else{
			successDialog("提醒",'是否确认删除',function(){
				var str =[];
				for(var i=0;i<a.length;i++){
					str.push(a[i].activityId);// 收集选中项
				}
				$.ajax({
					 url:"${cp}/activity/deleteActivityBatch.htm?Ids="+str,
					 dataType:"json",
			         async: true,
			         type:"post",
			         success:function(data){
			           if(data>=1){
			        	   bootboxDialog("删除成功！",'small',function(){window.location.reload();});
			           }else{
			        	   bootboxDialog("删除失败！");
			           }
			        }
				 })
			});
		}
	})
	
    $("#addbut").click(function(){
		$("#editModal").modal({
	        remote: "${cp}/activity/openPushAct.htm",
	        show: false
	    });		
	    $("#editModal").modal("toggle");			
	});
    

    
    $("#editModal").on("hidden.bs.modal", function() {
    	$(this).removeData("bs.modal");
 	}); 
</script>
    <script type="text/javascript">
	// 百度地图API功能
   // var map = new BMap.Map("allmap");
	function G(id) {
		 	return document.getElementById(id);
		}
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
					{"input" : "actest"
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
		});
	
		var myValue;
		ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
			myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			setPlace();
		});
	
		function setPlace(){
			function myFun(){
				var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			}
		}
</script>
</body>
</html>