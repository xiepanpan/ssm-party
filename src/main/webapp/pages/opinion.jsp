<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>建言献策</title>
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
                                 <button id="delbut" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>
                                 </div>
                                  <!--  <div class="columns columns-right pull-right">
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
                                <table id="exampleTablePagination" data-url="" data-query-params="queryParams" data-only-info-pagination="true" data-page-list="[15,30]" data-toggle="table" data-pagination="true" data-page-size="15" data-striped="true">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-radio="true"></th>
                                            <th class="col-sm-1" data-field="opinionId" data-visible="false">ID</th>
											<th class="col-sm-2" >提议时间</th>
											<th class="col-sm-9" >提议内容</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${opinions}" var="opinion" varStatus="loop">
                                        <tr>
                                            <td data-radio="true"></td>
                                            <td>${opinion.opinionId}</td>
                                         	<td><fmt:formatDate value="${opinion.opinionTime}" pattern="yyyy-MM-dd"/></td>
                                         	<td>${opinion.opinionContent}</td>
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
   
    $("#delbut").click(function(){
    	var a= $("#exampleTablePagination").bootstrapTable('getSelections');
    	console.log(a);
		 if(a.length<=0 || a.length>1){
			 bootboxDialog("请选中一条数据进行删除！");
		}else{
			successDialog("提醒",'是否确认删除',function(){
				 $.ajax({
					 url:"${cp}/opinion/deleteOpinion.htm?id="+a[0].opinionId,
					 dataType:"json",
			         async: true,
			         type:"post",
			         success:function(data){
			           if(data.status){
			        	   bootboxDialog("删除成功！",'small',function(){window.location.reload();});
			           }else{
			        	   bootboxDialog("删除失败！");
			           }
			        }
				 })
			});
		}
	});
    
    $("#editModal").on("hidden.bs.modal", function() {
 	    $(this).removeData("bs.modal");
 	}); 
    </script>  
</body>
</html>