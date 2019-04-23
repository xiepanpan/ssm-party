<%@page import="com.baobao.common.service.MemRoleService"%>
<%@page import="com.baobao.common.model.MemRoleModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>组织管理</title>
    <%@include file="common.jsp"%>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <!-- Panel Other -->
        <input type="hidden" value="${cmd.newsType}" id="pageType">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
           		<div id="toolbar" class="">
                	<form action="${cp}/branch/viewBranchByCondition.htm" method="post">
                 		<div class="btn-group">
                 			<button id="addbut1" class="btn btn-primary " type="button"><i class="fa fa-plus"></i>&nbsp;添加</button>
                 			<button id="editbut" class="btn btn-success " type="button"><i class="fa fa-pencil"></i>&nbsp;修改</button>
                			<!-- <button id="delbut" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button> -->
                			<button id="delbatch" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>
                  		<!-- 	 <button id="importbut" class="btn btn-primary" type="button"><i class="fa fa-pencil"></i>&nbsp;导入支部信息</button> -->
                 		</div>
                 		<div class="columns columns-right pull-right">
                 			<button class="btn btn-default btn-outline" type="submit"><i class="fa fa-search"></i></button>
                 		</div>
                 		<c:if test="${branchFatherId!=null}">
                 			<input type="hidden" name="branchFatherId" value="${branchFatherId }">
                 		</c:if>
                 		
                 		<div class="pull-right search"><input class="form-control" type="text" placeholder="支部名称/书记/组织委员/宣传委员" style="width:270px;" value="${searchValue}" name="searchValue">&nbsp;</div>
                 	</form>
                 </div>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">

                    <div class="col-sm-12">
                        <!-- Example Pagination -->
                        <div class="example-wrap">
                            <div class="example">
                                <table id="exampleTablePagination" data-url="" data-query-params="queryParams" data-show-pagination-switch="false" data-page-list="[15,30]" data-pagination="true" data-only-info-pagination="true" data-toggle="table" data-pagination="true" data-page-size="15" data-striped="true">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-visible="false" data-field="partyId" >党分支Id</th>
                                            <th data-field="branchName">党分支名称</th>
                                            <th data-field="branchSecretary">党分支书记</th>
                                            <th data-field="branchOrganizationCommittee">党分支组织委员</th>
                                            <th data-field="branchPublicityCommittee">党分支宣传委员</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${result}" var="item">
                                        <tr>
                                            <td data-checkbox="true"></td>
                                            <td data-visible="false">${item.partyId}</td>
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
                        <!-- End Example Pagination -->
                    </div>

                </div>
            </div>
        </div>
        <!-- End Panel Other -->
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
		        remote: "${cp}/branch/showEditPartyPage.htm?branchId="+a[0].partyId,
		        show: false
		    });
		    $("#editModal").modal("toggle");
		}

	});
   /*  $("#delbut").click(function(){
		var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		 if(a.length<=0 || a.length>1){
			 bootboxDialog("请选中一条数据进行删除！");
		}else{
			successDialog("提醒",'是否确认删除',function(){
				 $.ajax({
	 				 url:"${cp}/partyBranch/deletePartyBranchById.htm?partyId="+a[0].partyId, 
					 dataType:"json",
			         async: true,
			         type:"post",
			         success:function(data){
			           if(data>=1){
			        	   var delParent=window.parent.document.getElementById("obshowul");
			        	   delParent.removeChild(delParent.querySelectorAll('ul[data-id="'+a[0].partyId+'"]')[0]);
			        	   bootboxDialog("删除成功！",'small',function(){window.location.reload();});
			           }else{
			        	   bootboxDialog("删除失败！");
			           }
			        }
				 })
			});
		}
	}); */
    $("#delbatch").click(function(){
		var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		if(a.length<=0){
			 bootboxDialog("请选中至少一条数据进行删除！");
		}else{
			successDialog("提醒",'是否确认删除',function(){
				var str =[];
				for(var i=0;i<a.length;i++){
					str.push(a[i].partyId);// 收集选中项
				}
				$.ajax({
					 url:"${cp}/partyBranch/deleteBatch.htm?Ids="+str,
					 dataType:"json",
			         async: true,
			         type:"post",
			         success:function(data){
			        if(data.status==true){
			        	 bootboxDialog("删除成功",'small',function(){window.location.reload();});
			           	   for(var j=0;j<a.length;j++){
			        		   var delParent=window.parent.document.getElementById("obshowul");
				        	   delParent.removeChild(delParent.querySelectorAll('ul[data-id="'+a[j].partyId+'"]')[0]);
			        	   }
			        }else{
			        	if(data.i>=1){
				        	   bootboxDialog(data.smsg+'<br>'+data.msg,'small',function(){window.location.reload();});
				           	   for(var j=0;j<a.length;j++){
				        		   var delParent=window.parent.document.getElementById("obshowul");
					        	   delParent.removeChild(delParent.querySelectorAll('ul[data-id="'+a[j].partyId+'"]')[0]);
				        	   }
				           }else{
				        	   if(data.msg!=null){
				        		   bootboxDialog(data.msg);
				        	   }else{
				        		   alert(data.msg)
				        		   bootboxDialog("删除失败！");
				        	   }
				           }
			        }
			      }
				 })
			});
		}
	})
    $("#addbut1").click(function(){
			$("#editModal").modal({
		        remote: "${cp}/branch/openAddOrg.htm",
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