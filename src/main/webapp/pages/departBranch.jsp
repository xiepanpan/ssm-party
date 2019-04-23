<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>院系支部页面</title>
 <%@include file="common.jsp"%>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

        <!-- Panel Other -->
        <input type="hidden" value="${cmd.newsType}" id="pageType">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                                 <div id="toolbar" class="">
                                 <form action="${cp}/partyBranch/partyShow.htm" method="post">
                                 <div class="btn-group">
                                 <button id="addbut" class="btn btn-primary " type="button"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                 <button id="editbut" class="btn btn-success " type="button"><i class="fa fa-pencil"></i>&nbsp;修改</button>
                                <button id="delbut" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除3</button>
								<!-- <button id="importbut" class="btn btn-primary" type="button"><i class="fa fa-pencil"></i>&nbsp;导入党支部信息</button> -->
                                 </div>
                                 <div class="columns columns-right pull-right">
                                 <button class="btn btn-default btn-outline" type="submit"><i class="fa fa-search"></i></button>
                                 </div>
                                 <div class="pull-right search"><input class="form-control" type="text" placeholder="支部名称，书记，组织委员，宣传委员" style="width:270px;" name="searchValue">&nbsp;</div>
                                 </form>
                                 </div>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">

                    <div class="col-sm-12">
                        <!-- Example Pagination -->
                        <div class="example-wrap">
                            <div class="example">
                                <table id="exampleTablePagination" data-url="" data-query-params="queryParams" data-show-pagination-switch="false" data-pagination="true" data-only-info-pagination="true" data-toggle="table" data-pagination="true" data-page-size="15" data-striped="true">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="partyId">支部ID</th>
                                            <th data-field="partyBranchName">支部名称</th>
                                            <th data-field="partyBranchSecretary">支部书记</th>
                                            <th data-field="partyBranchOrganization">支部组织委员</th>
                                            <th data-field="partyBranchPublicity">支部宣传委员</th>
                       					</tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${result}" var="item">
                                        <tr>
                                            <td data-checkbox="true"></td>
                                            <td>${item.partyId}</td>
                                            <td>${item.partyBranchName}</td>
                                            <td>${item.partyBranchSecretary}</td>
                                            <td>${item.partyBranchOrganization}</td>
                                            <td>${item.partyBranchPublicity}</td>
                                          </tr>
                                     </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                       
                    </div>

                </div>
            </div>
        </div>
        
    </div>
  
<!-- 修改弹框内容 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
<div class="modal-dialog" style="width:660px;">
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
		        remote: "${cp}/branch/showEditPage.htm?branchId="+a[0].branchId,
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
					 url:"${cp}/branch/deleteById.htm?branchId="+a[0].branchId,
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
	});

    
    $("#addbut").click(function(){
			$("#editModal").modal({
		        remote: "${cp}/pages/dialog/branch_add.jsp",
		        show: false
		    });
		
		    $("#editModal").modal("toggle");
			
	});
    
    $("#importbut").click(function(){
		$("#editModal").modal({
			 remote: "${cp}/pages/dialog/importInfo.jsp",
	        show:false
	    });
	 $("#editModal").modal("toggle");
});
    
    $("#editModal").on("hidden.bs.modal", function() {
    	$(this).removeData("bs.modal");
 	}); 
    </script>  
</body>
</html>