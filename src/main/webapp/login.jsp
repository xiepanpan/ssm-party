<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<TITLE>湖经党建后台管理系统</TITLE>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${cp}/pages/img/favicon.ico">
<link href="${cp}/pages/css/login.css" rel="stylesheet">    
<script src="${cp}/pages/js/jquery.min.js"></script>

<script>
function makeValidtionCode(length) {
var random = 0;
var code = '';
for (var i = 0; i < length; i++) {
random = (42 * Math.random()) + 48;
if (((random >= 48 && random <= 57) || (random >=65 && random <= 90))) {
		//alert(random+'黄'+String.fromCharCode(random));
		code += String.fromCharCode(random);
} else {
i--;
}
}
return code;
}
// 随机颜色生成
// code 随机码
function makeHtmlCode(code) {
var gValue = 0;
var cValue = '';
var htmlCode = '';
for (var i = 0; i < code.length; i++) {
cValue = '';
for (var j = 0; j < 3; j++) {
gValue = Math.floor(Math.random() * 255);
cValue += gValue.toString(16).toUpperCase();
}
cValue = '#' + cValue;
htmlCode += '<span style="color:' + cValue + ';padding-left:5px;" name="yzmName">' + code.charAt(i) + '</span>';
}
return htmlCode;
}

//保存每次生成的验证码字符串
var vc = '';
var ms = '';
$(function () {
vc = makeValidtionCode(4);
$('#validationCode').html(makeHtmlCode(vc));
});

function refrash(){
vc = makeValidtionCode(4);
$('#validationCode').html(makeHtmlCode(vc));
}
</script>
</HEAD>
<BODY>
	<FORM id="loginform" name="loginform" action="${cp}/login/Login.htm" method="post">
		<div class="titleText">湖经党建后台管理系统</div>
		<div class="main">
			<div class="lgbg">
				<div class="lgbox">
					<div><input id="userid" name="phone" class="username" type="text" placeholder="请输入账号"></div>
					<div class="m12"><input id="password" name="password" class="password" type="password" placeholder="请输入密码"></div>
					<div class="m12">
						<div class="eyzm"><input id="validation" name="randomcode" size="8" class="yzinput" type="text" align="center" placeholder="请输入验证码"></div>
						<div class="yzm">
							<!-- <img id="randomcode_img" src="validatecode.jsp" alt="" width="56" height="20" align='absMiddle' /><br> -->
							<div class="ma" id="validationCode" onclick="refrash()" style="width:60px;border:1px solid #666;"></div>
							<a class="frash" href="javascript:refrash()">看不清楚，换一张</a>
						</div>
					</div>
     					<div style="clear:both; height:0" ></div>
					<div class="infor_containor"><p id="infor" class="hidden"></p></div>
					<div class="m32 butbox">
						<input id="login" type="button" class="but" onclick="loginsubmit()" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" class="but" value="重置">
					</div>
				</div>
			</div>
		</div>
		<div class="line"></div>
		<div class="logo"></div>
		<div class="pic"></div>
	</FORM>
	
	
	
	
<script type="text/javascript">
//校验表单输入
function checkinput() {
	//*****************表单校验******************
	var flag=true;
	// 校验用户名:
	// 获得用户名文本框的值:
	var username = document.getElementById("userid").value;
	if(username == null || username == ''){
		$('#infor').empty().html("用户名不能为空!").removeClass('hidden');
		flag=false;
		return false;
	}
	// 校验密码:
	// 获得密码框的值:
	var password = document.getElementById("password").value;
	if(password == null || password == ''){
		$('#infor').empty().html("密码不能为空!").removeClass('hidden');
		flag=false;
		return false;
	}
	 // 校验验证码:
	// 获得当前验证码:

	var code="";
	var yzmArray=document.getElementsByName("yzmName");
	for(var i=0;i<yzmArray.length;i++){
		code+=yzmArray[i].innerHTML;
	} 

	if(code.toLowerCase()!= $('#validation').val().toLowerCase()){
		$('#infor').empty().html("验证码错误!").removeClass('hidden');
		flag=false;
		return false;
	} 
	return flag;
	//*****************表单校验******************
}

//登录提示方法
function loginsubmit(){
	if(checkinput()){
		$('#login').attr("disabled",true);
		$('#infor').attr('class','hidden');
		$('#infor').empty();
		
		var str  = $("#loginform").attr("action"); // 访问路径
		var account  =	encodeURI(encodeURI($("#userid").val())); // 账号
		var passWord =  encodeURI(encodeURI($("#password").val())); // 密码
		var info = "phone="+account+"&password="+passWord; // 账号+密码拼接的字符串
		
		// ajax访问获取登录信息
		$.ajax({
			url:$("#loginform").attr("action"),
			data:info,
			dataType:'json',
			success: function(data){
				if(data.status==true){
					/* window.location.href="pages/index.jsp"; // 登陆成功后访问的界面 */
					var htm="<form id='indexForm' name='indexForm' action='${cp}/login/goIndex.htm' method='post'></form>";
					$("#loginform").after(htm);
					$("#indexForm").submit(); 
				}else{

					$('#login').attr("disabled",false);
					$('#infor').empty().html(data.message).removeClass('hidden');
				}
			}
		})
	}	 
}

</script>


</BODY>
</HTML>
