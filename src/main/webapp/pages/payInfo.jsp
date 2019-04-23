 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                                <!--  <button id="delbut" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button> -->
                                    <button id="delbatch" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>
                                 </div>
                                 <!--<div class="columns columns-right pull-right">
                                 <button class="btn btn-default btn-outline" type="submit"><i class="fa fa-search"></i></button>
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
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="payId"  data-visible="false">缴费记录id</th>
                                            <th data-field="payName">用户姓名</th>
                                         	<th data-field="payBrName">所在支部</th>
                                            <th data-field="payTel">用户手机</th>
                                            <th data-field="payMonth">当前年月</th>
                                       		<th data-field="payFees">当前应缴费用</th>
											<th data-field="payStatus">缴费状态</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${payList}" var="item">
                                    <tr>
                                        <td data-checkbox="true"></td>
                                    	<td>${item.payId}</td>
                                    	<td>${item.payName}</td>
                                    	<td>${item.payBrName}</td>
                                    	<td>${item.payTel}</td>
                                        <td><fmt:formatDate value="${item.payMonth}"  pattern="yyyy-MM" type="date" dateStyle="long" /></td>
                                        <td><fmt:formatNumber type="number" value="${item.payFees}" pattern="0.00" maxFractionDigits="2"/></td>
                                        <td>
                                        <c:if test="${item.payStatus==1}">
                                        	已缴费
                                        </c:if>
                                         <c:if test="${item.payStatus==0}">
                                        	未缴费
                                        </c:if>
                                        </td>
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
<div class="modal-dialog" style="width:560px;">
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
		        remote: "${cp}/pay/openPayPage.htm?payId="+a[0].payId,
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
					str.push(a[i].payId);// 收集选中项
				}
				$.ajax({
					 url:"${cp}/pay/deleteByBatch.htm?Ids="+str,
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
					 url:"${cp}/pay/deleteById.htm?payId="+a[0].payId,
					 dataType:"json",
			         async: true,
			         type:"post",
			         success:function(data){
			           if(data>0){
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
				remote: "${cp}/pages/dialog/pay_add.jsp",
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