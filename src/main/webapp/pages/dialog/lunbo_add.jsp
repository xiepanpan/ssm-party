<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
		<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">添加轮播新闻信息</h4>
        </div>
        <input type="hidden"  value="${ptype}" id="addType">
        <div class="modal-body">
          <form method="post" action="" class="form-horizontal" role="form" id="form_data"  style="margin: 20px;">
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">标题</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="title" value="" id="title" placeholder="">
              </div>
            </div>
           <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">发布时间</label>
              <div class="col-sm-10">
                 <input placeholder="" class="laydate-icon form-control layer-date" name="releasetime" id="birthday">
              </div>
            </div>
           <!--  <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">热度</label>
              <div class="col-sm-10">
                <input type="number" class="form-control layer-date" name="heatNum" value="" placeholder="数字越大显示越靠前">
              </div>
            </div> -->
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">图片上传</label>
              <div class="col-sm-10">
                <input type="file" class="form-control" style="width:240px" name="myfile"  accept="image/*" id="title" placeholder="">
                <span style="color:red;">注：上传图片的宽高比为2.25 : 1，否则app显示会变形！</span>
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">新闻内容</label>
              <div class="col-sm-10">
                <!-- 加载编辑器的容器 -->
    			<script id="container" name="newsContext" type="text/plain" style="height:200px"></script>
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
        	title: {
                validators: {
                    notEmpty: {
                        message: '标题不能为空'
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
            },
            myfile:{
                validators: {
                	notEmpty: {
                        message: '图片不能为空'
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
 function valiadate(){
	 var formData = new FormData(document.getElementById("form_data"));
	 $.ajax({
		 url:"${cp}/newsController/insertLunboNews.htm",
		 data:$("#form_data").serialize(),
		 data:formData,
         async: true,
         contentType:false,
         async: false,
         cache: false,
         processData: false,
         type:"post",
         success:function(data){
           if(data==1){
        	   bootboxDialog("添加成功",'small',function(){$("#editModal").modal('hide');window.location.reload();});
        	   
           }else{
        	   bootboxDialog("添加失败！",'small',function(){$("#editModal").modal('hide')});
           }
        }
	 })
 }
 </script>


