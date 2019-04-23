<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>党员管理</title>
  <%@include file="common.jsp"%>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                                 <div id="toolbar" class="">
                                 <form action="${cp}/memeber/showMembers.htm" method="post">
                                 <div class="btn-group">
                                 <button id="addbut" class="btn btn-primary " type="button"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                 <button id="editbut" class="btn btn-success " type="button"><i class="fa fa-pencil"></i>&nbsp;修改</button>
                                <!--  <button id="delbut" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button> -->
                                 <button id="resetbut" class="btn btn-primary " type="button"><i class="fa fa-pencil"></i>&nbsp;重置密码</button>
                                 <button id="importbut" class="btn btn-success" type="button"><i class="fa fa-pencil"></i>&nbsp;导入党员信息</button>
                                 <button id="exportbut" class="btn btn-primary" type="button"><i class="fa fa-pencil"></i>&nbsp;导出党员信息</button>
                                 <button id="exportall" class="btn btn-success" type="button"><i class="fa fa-pencil"></i>&nbsp;一键导出所有</button>
                                 <button id="delbatch" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>
                                 </div>
	                                 <div class="columns columns-right pull-right">
	                                 <button class="btn btn-default btn-outline" type="submit" id="sea-btn" style="height:33px;"><i class="fa fa-search"></i></button>
	                                 </div>
	                                 <div class="pull-right search"><input class="form-control" type="text" name="searchValue" value="${cmd.searchValue }" placeholder="姓名/职务/所在支部/所属类型" style="width:250px;">&nbsp;</div>
                                 </form>
                                 </div>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">

                    <div class="col-sm-12">
                        <!-- Example Pagination -->
                        <div class="example-wrap">
                            <div class="example">
                                <table id="exampleTablePagination" data-url="" data-maintain-selected="true" data-query-params="queryParams" data-only-info-pagination="true" data-page-list="[15,30]" data-toggle="table" data-pagination="true" data-page-size="15" data-striped="true">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="memberId">党员ID</th>
                                            <th data-field="memberName">姓名</th>
                                            <th data-field="memberSex">性别</th>
                                         	<th data-field="memberRddate">入党时间</th>
                                            <th data-field="memberZzdate">转正时间</th>
                                            <th data-field="memberJob">职务</th>
                                           	<th data-field ="memberTel">手机号</th>
                                            <th data-field="memberInbranchid">所在支部</th>
                                            <th data-field="memberType">当前所属类型</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${result}" var="item">
                                        <tr>
                                            <td data-checkbox="true"></td>
                                            <td>${item.memberId}</td>
                                            <td>${item.memberName}</td>
                                         	<td>${item.memberSex }</td>
                                          	<td><fmt:formatDate value="${item.memberRddate}"  pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
                                            <td><fmt:formatDate value="${item.memberZzdate}"  pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
                                         	<td>${item.memberJob}</td>
                                         	<td>${item.memberTel}</td>
                                            <td>${item.branchName}</td>
                                            <td>${item.memberType}</td>
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
<div class="modal-dialog">
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
		        remote: "${cp}/memeber/jumpEditPage.htm?memberId="+a[0].memberId,
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
					str.push(a[i].memberId);// 收集选中项
				}
				$.ajax({
					 url:"${cp}/memeber/deleteMemberByIds.htm?memberIsDelete=2&memberId="+str,
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
  /*   $("#delbut").click(function(){
		var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		 if(a.length<=0 || a.length>1){
			 bootboxDialog("请选中一条数据进行删除！");
		}else{
			successDialog("提醒",'是否确认删除',function(){
				 $.ajax({
					 url:"${cp}/memeber/deleteMemberById.htm?memberIsDelete=2&memberId="+a[0].memberId,
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
		        remote: "${cp}/memeber/jumpAddMember.htm",
		        show: false
		    });
		    
			$("#editModal").modal("toggle");
			
	});
    $("#resetbut").click(function(){
    	var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		 if(a.length<=0 || a.length>1){
			 bootboxDialog("请选中一条数据进行操作！");
		}else{
			successDialog("提醒",'是否确认重置密码',function(){
				 $.ajax({
					 url:"${cp}/memeber/resetPass.htm?memberId="+a[0].memberId,
					 dataType:"json",
			         async: true,
			         type:"post",
			         success:function(data){
			           if(data>=1){
			        	   bootboxDialog("重置成功！密码为123456",'small',function(){window.location.reload();});
			           }else{
			        	   bootboxDialog("重置失败！");
			           }
			        }
				 })
			});
		}
    })
    $("#importbut").click(function(){
		$("#editModal").modal({
			remote: "${cp}/pages/dialog/importMemberExcel.jsp",
	        show:false
	    });
	 $("#editModal").modal("toggle");
	});
    $("#exportbut").click(function(){
    	var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		if(a.length<=0){
			 bootboxDialog("请选中至少一条数据进行导出！");
		}else{
			successDialog("提醒",'是否确认导出',function(){
				var str =[];
				for(var i=0;i<a.length;i++){
					str.push(a[i].memberId);// 收集选中项
				}
				window.location.href="${cp}/memeber/exportExcel.htm?Ids="+str
			});
		}
    })
    $("#exportall").click(function(){
    	successDialog("提醒",'是否确定导出',function(){
    		window.location.href="${cp}/memeber/exportExcelAll.htm"
    	})
    })
    $("#editModal").on("hidden.bs.modal", function() {
 	    $(this).removeData("bs.modal");
 	});
    </script>  
</body>
</html>