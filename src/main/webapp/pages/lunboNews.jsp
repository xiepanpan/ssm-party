<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>新闻管理</title>
    <%@include file="common.jsp"%>
</head>
<style type="css/text">
.article .ibox-content{
	padding:0px;
}
</style>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

        <!-- Panel Other -->
        <input type="hidden" value="${cmd.newsType}" id="pageType">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                                 <div id="toolbar" class="">
                                 <form  action="${cp}/newsController/showNews.htm" method="post">
                                 <div class="btn-group">
                                 <button id="addbut" class="btn btn-primary " type="button"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                 <button id="editbut" class="btn btn-success " type="button"><i class="fa fa-pencil"></i>&nbsp;修改</button>
                                 <button id="delbatch" class="btn btn-danger " type="button"><i class="fa fa-remove"></i>&nbsp;删除</button>
                                 </div>
                                 <input type="hidden" value="3" name="newsType">
                                 <input type="hidden" value="1" name="isFlag"> 
                                 </form>
                                 </div>
                                 
            </div>
            <div class="ibox-content">
                <div class="row row-lg">

                    <div class="col-sm-12">
                        <!-- Example Pagination -->
                        <div class="example-wrap">
                            <div class="example">
                                <table id="exampleTablePagination" data-url="" data-query-params="queryParams" data-page-list="[15,30]" data-only-info-pagination="true" data-toggle="table" data-pagination="true" data-page-size="15" data-striped="true">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="tid">新闻ID</th>
                                            <th data-field="title">新闻标题</th>
                                            <th data-field="releasetime">发布时间</th>
                                           <!--  <th data-field="heatNum">热度</th> -->
                       						<th data-field="imgUrl">轮播图片</th>
                                            <th data-field="but">操作</th>
                          			   </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${result}" var="item">
                                        <tr>
                                            <td data-checkbox="true"></td>
                                            <td>${item.tid}</td>
                                            <td>${item.title}</td>
                                            <td><fmt:formatDate value="${item.releasetime}"  pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></td>
                                           <%--  <td>${item.heatNum}</td> --%>
                                            <td><a target="_blank" href="${parentUrl}${item.imgUrl}">${item.imgUrl}</a></td>
                                             <td><a href="${cp}/newsController/showNews.htm?isFlag=2&tid=${item.tid}" class="btn-primary btn-xs">查看详情</a></td>
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
		        remote: "${cp}/newsController/showEditPage.htm?showType=1&tId="+a[0].tid,
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
					 url:"${cp}/newsController/deleteById.htm?tId="+a[0].tid,
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

    $("#delbatch").click(function(){
		var a= $("#exampleTablePagination").bootstrapTable('getSelections');
		if(a.length<=0){
			 bootboxDialog("请选中至少一条数据进行删除！");
		}else{
			successDialog("提醒",'是否确认删除',function(){
				var str =[];
				for(var i=0;i<a.length;i++){
					str.push(a[i].tid);// 收集选中项
				}
				$.ajax({
					 url:"${cp}/newsController/deleteBatch.htm?newsIds="+str,
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
			var type = $("#pageType").val();
			$("#editModal").modal({
		        remote: "${cp}/newsController/showInsertPage.htm?ptype="+type,
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