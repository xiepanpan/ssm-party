<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
		<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">编辑新闻信息</h4>
        </div>
        <div class="modal-body">
          <form method="post" action="" class="form-horizontal" role="form" id="form_data"  style="margin: 20px;">
          <input type="hidden" class="form-control" name="tid" value="${newsInfo.tid}">
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">标题</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="title" value="${newsInfo.title}" id="title" placeholder="">
              </div>
            </div>
           <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">发布时间</label>
              <div class="col-sm-10">
                 <input placeholder="" class="laydate-icon form-control layer-date" name="releasetime" id="birthday" value="<fmt:formatDate value='${newsInfo.releasetime}'  pattern='yyyy-MM-dd HH:mm'/>" >
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">所属类型</label>
              <div class="col-sm-10">
              <select name="newsType" class="form-control layer-date">
	              <c:if test="${newsInfo.newsType eq 21 || newsInfo.newsType eq 22}">
					<option <c:if test="${newsInfo.newsType eq 21}">selected</c:if> value="21">系列讲话</option>
					<option <c:if test="${newsInfo.newsType eq 22}">selected</c:if> value="22">规章制度</option>
				  </c:if>
				  <c:if test="${newsInfo.newsType eq 31 || newsInfo.newsType eq 32}">
					<option <c:if test="${newsInfo.newsType eq 31}">selected</c:if> value="31">国内时政</option>
					<option <c:if test="${newsInfo.newsType eq 32}">selected</c:if> value="32">省内时政</option>
				  </c:if>
			  </select>
                <%-- <select class="form-control" name="newsType" value="${newsInfo.newsType}" id="nation"><lect> --%>
			</div>
            </div>
 			<%-- <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">热度(0-99)</label>
              <div class="col-sm-10">
                <input type="number" class="form-control layer-date" name="heatNum" value="${newsInfo.heatNum}" placeholder="数字越大显示越靠前">
              </div>
            </div> --%>
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">新闻内容</label>
              <div class="col-sm-10">
                <script id="container" name="newsContext" type="text/plain" style="height:200px">${newsInfo.newsContext}</script>
              </div>
            </div>

          
        <div class="modal-footer">

          <button type="submit" class="btn btn-primary" onclick="">提交</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
        </form>
	 </div>
	 
 
    <!-- 配置文件 -->
    <script type="text/javascript" src="${cp}/pages/js/plugins/UEditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${cp}/pages/js/plugins/UEditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="${cp}/pages/js/plugins/UEditor/lang/zh-cn/zh-cn.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container',{
            initialFrameHeight: 300
        });


    </script>

<script>
$(function () {
    $('#form_data').bootstrapValidator({
		message: '校验失败',
		feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
           },
        fields: {
        	title: {
                validators: {
                    notEmpty: {
                        message: '标题不能为空'
                    },
                    stringLength:{
            			max: 100,
            			message:'备注的长度在100以内'
            		}
                }
            },
			releasetime: {
				validators: {
					notEmpty: {
						message: '时间不能为空'
					},
					date: {
						message: '时间格式错误',
						format: 'YYYY-MM-DD hh:mm'
					}
				}
			},
			heatNum: {
                validators: {
                	integer: {
                        message: '只能填写整数'
                    }
                }
            },
            newsContext: {
                validators: {
                	notEmpty: {
                        message: '内容不能为空'
                    }
                }
            }
		}
    }).on("success.form.bv",function(e){console.log(e)
    	e.preventDefault();
    	valiadate();
    	});
});
</script>


<script>
var birthday={elem:"#birthday",format:"YYYY-MM-DD hh:mm",max:"2099-06-16 00:00",istime:true,istoday:true,choose:function(datas){$('#form_data').data('bootstrapValidator').updateStatus('releasetime', 'NOT_VALIDATED',null).validateField('releasetime');}};
laydate(birthday);
 var nations = ["系列讲话","规章制度"];  
 $(function(){
 	var nation = document.getElementById("nation");  
     if(nation){
     	for (var i in nations) {  
             nation.add(new Option(nations[i], i+1));  
         }  
     }
 })
 function valiadate(){
	 $.ajax({
		 url:"${cp}/newsController/updateNews.htm",
		 data:$("#form_data").serialize(),
         dataType:"json",
         async: true,
         type:"post",
         success:function(data){
           if(data==1){
        	   bootboxDialog("修改成功",'small',function(){$("#editModal").modal('hide');window.location.reload();});
           }else{
        	   bootboxDialog("修改失败",'small',function(){$("#editModal").modal('hide')});
           }
        }
	 })
 }
 </script>