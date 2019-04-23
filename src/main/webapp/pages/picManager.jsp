<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>图片管理</title>
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
                                            <th class="col-sm-1" data-field="imageId" data-visible="false">ID</th>
											<th class="col-sm-1" data-field="imageUrl">图片类别</th>
                                            <th data-field="imgurl">图片链接</th>
                                            <th>图片大小</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${result}" var="item" varStatus="loop">
                                        <tr>
                                            <td data-radio="true"></td>
                                            <td>${item.imageId}</td>
                                            <td><c:if test="${item.imageType == 1}">轮播</c:if>
                                            	<c:if test="${item.imageType != 1}">其他</c:if>
                                            </td>
                                            <td><a href="${parentUrl}${item.imageUrl}"  target="_blank"/>${item.imageUrl}</a></td>
                                         	<td>${sizeList[loop.count-1]}</td>
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
		        remote: "${cp}/pic/updatePicPage.htm?id="+a[0].imageId,
		        show: false
		    });
		    $("#editModal").modal("toggle");
		}
		

	});

    
    $("#addbut").click(function(){
			$("#editModal").modal({
				remote: "${cp}/pages/dialog/pic_add.jsp",
				 show: false
		    });
		
		    $("#editModal").modal("toggle");
			
	});
    $("#delbut").click(function(){
    	var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		 if(a.length<=0 || a.length>1){
			 bootboxDialog("请选中一条数据进行删除！");
		}else{
			successDialog("提醒",'是否确认删除',function(){
				 $.ajax({
					 url:"${cp}/pic/deleteById.htm?id="+a[0].imageId,
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
    
    $("#editModal").on("hidden.bs.modal", function() {
 	    $(this).removeData("bs.modal");
 	}); 
    </script>  
</body>
</html>