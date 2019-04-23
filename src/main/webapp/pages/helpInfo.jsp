<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>帮助管理</title>
    <%@include file="common.jsp"%>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                                 <div id="toolbar" class="">
                                 <!-- form加这里 -->
                                 <div class="btn-group">
                                 <button id="addbut" class="btn btn-primary " type="button"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                 <button id="editbut" class="btn btn-success " type="button"><i class="fa fa-pencil"></i>&nbsp;修改</button>
                               <!--   <button id="delbut" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button> -->
                                 <button id="delbatch" class="btn btn-danger" type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>
                                 </div>
                                  <!--    <div class="columns columns-right pull-right">
                                 <button class="btn btn-default btn-outline" type="button"><i class="fa fa-search"></i></button>
                                 </div>
                             <div class="pull-right search"><input class="form-control" type="text" placeholder="搜索">&nbsp;</div> -->
                                <!-- form加这里 -->
                                 </div>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">

                    <div class="col-sm-12">
                        <!-- Example Pagination -->
                        <div class="example-wrap">
                            <div class="example">
                                <table id="exampleTablePagination" data-url="" data-query-params="queryParams" data-only-info-pagination="true" data-toggle="table" data-page-list="[15,30]" data-pagination="true" data-page-size="15" data-striped="true">
                                    <thead>
                                        <tr>
                                            <th data-field="state"data-checkbox="true"></th>
                                            <th class="col-sm-1" data-field="helpId" data-visible="false">ID</th>
                                            <th data-field="helpQuesition">提出的问题</th>
											<th class="col-sm-2" data-field="helpAnswer">操作</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${result}" var="item" varStatus="loop">
                                        <tr>
                                            <td data-checkbox="true"></td>
                                            <td data-visible="false">${item.helpId}</td>
                                          <%--   <td>${loop.count}</td> --%>
                                            <td>${item.helpQuesition}</td>
                                            <td><a href="${cp}/helpMananger/showHelpInfoById.htm?helpId=${item.helpId}&flag=1" class="btn-primary btn-xs">查看详情</a></td>
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
  
<!-- 修改弹框内容 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
<div class="modal-dialog" style="width:960px;">
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
		        remote: "${cp}/helpMananger/openHelpPage.htm?helpId="+a[0].helpId,
		        show: false
		    });
		    $("#editModal").modal("toggle");
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
					str.push(a[i].helpId);// 收集选中项
				}
				$.ajax({
					 url:"${cp}/helpMananger/deleteBatch.htm?Ids="+str,
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
   /*  $("#delbut").click(function(){
		var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		 if(a.length<=0 || a.length>1){
			 bootboxDialog("请选中一条数据进行删除！");
		}else{
			successDialog("提醒",'是否确认删除',function(){
				 $.ajax({
					 url:"${cp}/helpMananger/deleteById.htm?helpId="+a[0].helpId,
					 dataType:"json",
			         async: true,
			         type:"post",
			         success:function(data){
			           if(data==1){
			        	   bootboxDialog("删除成功！",'small',function(){window.location.reload();});
			           }else{
			        	   bootboxDialog("删除失败！");
			           }
			        }
				 })
			});
		}
	}); */
    $("#addbut").click(function(){
			$("#editModal").modal({
				remote: "${cp}/pages/dialog/help_add.jsp",
				 show: false
		    });
		
		    $("#editModal").modal("toggle");
			
	});
    $("#editModal").on("hidden.bs.modal", function() {
 	    $(this).removeData("bs.modal");
 	}); 
    </script>  
</body>
</html>