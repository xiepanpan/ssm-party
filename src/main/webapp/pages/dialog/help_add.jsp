<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
		<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">添加帮助信息</h4>
        </div>
        <div class="modal-body">
          <form method="post" action="" class="form-horizontal" role="form" id="form_data"  style="margin: 20px;">
         	<div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">提问</label>
              <div class="col-sm-10">
                 <input placeholder=""  type="text" class="form-control" name="helpQuesition" id="birthday">
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">解答</label>
              <div class="col-sm-10">
                <!-- 加载编辑器的容器 -->
    			<script id="container" name="helpAnswer" type="text/plain" style="height:200px"></script>
              </div>
            </div>
		<div class="modal-footer">
          <button type="submit" class="btn btn-primary" id="subbut">提交</button>
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
        var ue = UE.getEditor('container');
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
        	helpQuesition: {
                validators: {
                    notEmpty: {
                        message: '提问不能为空'
                    }
                }
            },
        	helpAnswer: {
                validators: {
                    notEmpty: {
                        message: '解答不能为空'
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
function valiadate(){
	 $.ajax({
		 url:"${cp}/helpMananger/insertHelp.htm",
		 data:$("#form_data").serialize(),
         dataType:"json",
         async: true,
         type:"post",
         success:function(data){console.log(data)
           if(data==1){
        	   bootboxDialog("添加成功",'small',function(){$("#editModal").modal('hide');window.location.reload();});
        	   
           }else{
        	   bootboxDialog("添加失败！",'small',function(){$("#editModal").modal('hide')});
           }
        }
	 })
 }
 </script>

