<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<style type="text/css">
textarea{
	resize:none;
}
.form-control{
    border: 1px solid #C3C3C3;
}
.btn:hover{
    background-color: #337ab7;
    border-color: #2e6da4;
}
</style>
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">党员信息修改</h4>
        </div>
        <div class="modal-body">
          <form method="post" action="" class="form-horizontal" role="form" id="form_data"  style="margin: 20px;">
            <input type="hidden" name="memberId" value="${result.memberId}">
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label"><font style="color: red;font-size: 14px;">*</font>姓名</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="memberName" value="${result.memberName }" id="title" placeholder="">
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label"><font style="color: red;font-size: 14px;">*</font>手机号</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="memberTel" value="${result.memberTel }" disabled="disabled">
                <span id="span1"></span>
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">性别</label>
              <div class="col-sm-6">
                <label class="checkbox-inline">
                  <input type="radio" name="memberSex" <c:if test="${result.memberSex eq '男'}">checked</c:if> value="男" >男
                </label>
                <label class="checkbox-inline">
                  <input type="radio" name="memberSex" <c:if test="${result.memberSex eq '女'}">checked</c:if> value="女" >女
                </label>
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">民族</label>
              <div class="col-sm-6">
                <select class="form-control" name="memberNational" id="nation"></select>
              </div>
            </div>
 
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label"><font style="color: red;font-size: 14px;">*</font>出生日期</label>
              <div class="col-sm-6">
                <input placeholder="" name="memberBirth" class="laydate-icon form-control layer-date" value="<fmt:formatDate value='${result.memberBirth }'  pattern='yyyy-MM-dd'/>" id="birthday">
              </div>
            </div>
            
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">籍贯</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="memberNative" value="${result.memberNative }" placeholder="">
              </div>
            </div>
            
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label"><font style="color: red;font-size: 14px;">*</font>入党时间</label>
              <div class="col-sm-6">
                <input placeholder="" name="memberRddate" class="laydate-icon form-control layer-date" value="<fmt:formatDate value='${result.memberRddate }'  pattern='yyyy-MM-dd'/>" id="indate">
              </div>
            </div>
            
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label"><font style="color: red;font-size: 14px;">*</font>转正时间</label>
              <div class="col-sm-6">
                <input placeholder="" name="memberZzdate" class="laydate-icon form-control layer-date" value="<fmt:formatDate value='${result.memberZzdate }'  pattern='yyyy-MM-dd'/>" id="newdate">
              </div>
            </div>
            
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">工作单位</label>
              <div class="col-sm-6">
                <textarea class="form-control" name="memberWorkunit" placeholder="">${result.memberWorkunit }</textarea>
              </div>
            </div>
            
          <%--   <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">职务</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="memberJob" value="${result.memberJob }">
              </div>
            </div> --%>
            
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">身份证号码</label>
              <div class="col-sm-6">
                <input type="text" id="Idcard" class="form-control" name="memberIdcard" value="${result.memberIdcard }" onchange="isCardNo(this.value)">
              </div>
            </div>
            
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label"><font style="color: red;font-size: 14px;">*</font>所在支部</label>
              <div class="col-sm-6">
               	<select id="branch" name="memberInbranchid" class="branchName form-control" placeholder="请选择支部" required="required"></select>
              </div>
            </div>
            
            <div class="form-group">
              <label for="lastname" class="col-sm-4 control-label">所属类型</label>
              <div class="col-sm-6">
                <label class="checkbox-inline">
                  <input type="radio" <c:if test="${result.memberType eq '积极分子'}">checked</c:if> name="memberType" checked value="积极分子" >积极分子
                </label>
                 <label class="checkbox-inline">
                  <input type="radio" <c:if test="${result.memberType eq '发展对象'}">checked</c:if> name="memberType" value="发展对象" >发展对象
                </label>
                <label class="checkbox-inline">
                  <input type="radio" <c:if test="${result.memberType eq '预备党员'}">checked</c:if> name="memberType" value="预备党员" >预备党员
                </label>
                <label class="checkbox-inline">
                  <input type="radio" <c:if test="${result.memberType eq '正式党员'}">checked</c:if> name="memberType" value="正式党员" >正式党员
                </label>
              </div>
            </div>
            
            <div class="form-group">
              <label for="remark" class="col-sm-4 control-label">备注</label>
              <div class="col-sm-6">
                <textarea class="form-control" name="memberRemark" value="" placeholder="">${result.memberRemark }</textarea>
              </div>
            </div>
         
      
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary" id="subbut">提交</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
      </form>
      </div>


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
        	message: '用户名验证失败',
        	memberName: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    }
                }
            },
            memberBirth: {
				validators: {
					notEmpty: {
						message: '出生日期不能为空'
					},
					date: {
						message: '时间格式错误',
						format: 'YYYY-MM-DD'
					}
				}
			},
			/* memberNative: {
                validators: {
                	notEmpty: {
                        message: '籍贯不能为空'
                    }
                }
            }, */
            memberRddate: {
				validators: {
					notEmpty: {
						message: '入党日期不能为空'
					},
					date: {
						message: '时间格式错误',
						format: 'YYYY-MM-DD'
					},
					callback:{
						message:'入党日期不得大于等于转正日期',
						callback:function(value,validator){
							if(duibi(value,$('#newdate').val())){
								return true;
							}else{
								$('#indate').val(null);
								return false;
							}
							
						}
					}
				}
			},
			 memberZzdate: {
				validators: {
					notEmpty: {
						message: '转正日期不能为空'
					},
					date: {
						message: '时间格式错误',
						format: 'YYYY-MM-DD'
					},
					callback:{
						message:'转正日期不得小于等于入党日期',
						callback:function(value,validator){
							if(duibi($('#indate').val(),value)){
								return true;
							}else{
								$('#newdate').val(null);
								return false;
							}
							
						}
					}
				}
			},
			/* memberWorkunit: {
                validators: {
                	notEmpty: {
                        message: '工作单位不能为空'
                    }
                }
            }, */
         /*    memberJob: {
                validators: {
                	notEmpty: {
                        message: '职位不能为空'
                    }
                }
            }, */
            branchName: {
                validators: {
                	notEmpty: {
                        message: '所在支部不能为空'
                    }
                }
            },
             memberIdcard: {
                validators: {
                	shengfenzheng: {
                        message: '身份证格式不正确'
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
            memberRemark:{
            	validators:{
            		stringLength:{
            			max: 100,
            			message:'备注的长度在100以内'
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
var birthday={elem:"#birthday",format:"YYYY-MM-DD",max:"2099-06-16",istime:true,istoday:true,choose:function(datas){$('#form_data').data('bootstrapValidator').updateStatus('memberBirth', 'NOT_VALIDATED',null).validateField('memberBirth');}};
var indate={elem:"#indate",format:"YYYY-MM-DD",max:"2099-06-16",istime:true,istoday:true,choose:function(datas){$('#form_data').data('bootstrapValidator').updateStatus('memberRddate', 'NOT_VALIDATED',null).validateField('memberRddate');}};
var newdate={elem:"#newdate",format:"YYYY-MM-DD",max:"2099-06-16",istime:true,istoday:true,choose:function(datas){$('#form_data').data('bootstrapValidator').updateStatus('memberZzdate', 'NOT_VALIDATED',null).validateField('memberZzdate');}};
 laydate(birthday);
 laydate(indate);
 laydate(newdate);
 
 
//名族数据
var memberNational = '${result.memberNational}';
 var nations = ["汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族","壮族","布依族","朝鲜族","满族","侗族","瑶族","白族","土家族",  
                "哈尼族","哈萨克族","傣族","黎族","傈僳族","佤族","畲族","高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族",  
                "土族","达斡尔族","仫佬族","羌族","布朗族","撒拉族","毛南族","仡佬族","锡伯族","阿昌族","普米族","塔吉克族","怒族", "乌孜别克族",  
               "俄罗斯族","鄂温克族","德昂族","保安族","裕固族","京族","塔塔尔族","独龙族","鄂伦春族","赫哲族","门巴族","珞巴族","基诺族"];  
 $(function(){
 	var nation = document.getElementById("nation");  
     if(nation){
     	for ( var i in nations) {  
             if(memberNational==i){
            	 nation.add(new Option(nations[i],i,true,true));  
             }else{
            	 nation.add(new Option(nations[i],i));  
             }
         }  
     }
 })

  $(function(){
	  var  memberInbranchid = '${result.memberInbranchid}';
		//支部名称列表
		$.ajax({
			url:'${cp}/memeber/getbranchName.htm',
			dataType:'json',
			success : function(data){
				$('#branch').append("<option value=\"\">请选择支部</option>");
				$(data).each(function(i){
					var branch = document.getElementById("branch");
					if(parseFloat(memberInbranchid)==parseFloat(data[i].branchId)){
						branch.add(new Option(data[i].branchName,data[i].branchId,true,true));  
		             }else{
		            	 branch.add(new Option(data[i].branchName,data[i].branchId));  
		             }
				})
				
			},
			error : function(data){
				console.log("获取支部名称列表错误")
			}
		});
	})
	
/*  function isCardNo(card)  
 {  
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
    if(reg.test(card) === false)  
    {  
    	 bootboxDialog("身份证输入不合法");
        document.getElementById("Idcard").value="";
        return  false;  
    }  
 } */
 function valiadate(){
	 $.ajax({
		 url:"${cp}/memeber/updateMember.htm",
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

