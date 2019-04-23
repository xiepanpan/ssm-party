<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>湖北经济学院党建门户网-帮助中心</title>
    <%@include file="common.jsp"%>
</head>

<body>
<div class="ind_body" id="container">

    <%@include file="head.jsp"%>

<div class="lmy_container">
  <div class="lmy_position"><a href="${cp}/frontNews/index.html">首页</a> &gt; <a title="帮助中心" href="">帮助中心</a> &gt; <a title="问题解答" href="">问题解答</a></div>
  <div class="secnr" id="color_printsssss">
    <div id="wenzhang">
        <h1 class="help_wztit" style="color:;"><span class="t_color">问:${helpInfo.helpQuesition}</span></h1>
        <div class="wzcon_h" style="font-family:'微软雅黑'">
			${helpInfo.helpAnswer}
			<p style="text-indent:2em;"><br></p>
		<div class="clear"></div>
        </div>
        <div class="wzbot">
          <ul class="fxd_close"><a href="javascript:void(0)" onclick="javascript:window.print()" class="print_btn">打印本页</a><a href="javascript:window.close();" class="close_btn">关闭窗口</a></ul>
       </div>
   </div>
  </div>
</div>

    <%@include file="foot.jsp"%>
</div>
</body>
</html>