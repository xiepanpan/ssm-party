<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cp"></c:set>
<!DOCTYPE html>
<html>

<head>
    <title>详情页面</title>
    <%@include file="common.jsp"%>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                 <c:forEach items="${result}" var="item">
                    <div class="ibox-content">
                        <div class="pull-left">
                        <c:if test="${item.newsType==21||item.newsType==22||item.newsType==31||item.newsType==32}">
                            <button class="btn btn-white btn-xs" type="button">分类：
                                <c:if test="${item.newsType==21}">
	                                                                                 系列讲话
	                            </c:if>
	                            <c:if test="${item.newsType==22}">
	                                                                                规章制度
	                            </c:if>
                                <c:if test="${item.newsType==31}">
	                               	国内时政
	                            </c:if>
	                            <c:if test="${item.newsType==32}">
	                                                                                省内时政
	                          	</c:if>
                            
                            </button>
                        </c:if>
                            <button class="btn btn-white btn-xs" type="button"><fmt:formatDate value="${item.releasetime}"  pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></button>
                        </div>
                        <div class="text-center article-title">
                            <h2>${item.title }</h2>
                        </div>
                        <p>　　${item.newsContext }</p>
<!--                         <p>　　会议指出，央企是国民经济的骨干，在国企改革中具有引领和示范作用。按照党中央、国务院部署，央企贯彻新发展理念，持续深化改革，深入推进供给侧结构性改革，在化解淘汰过剩落后产能、防控债务风险、推进提质增效、提高核心竞争力等方面取得积极成效，经营状况显著改善，今年前7个月央企利润总额由去年同期的同比下降3.7%转为同比增长16.4%，资产负债率比年初下降0.2个百分点，提质增效攻坚战取得阶段性成果，成绩来之不易。当前要抓住央企效益转降回升的有利时机，把国企降杠杆作为“去杠杆”的重中之重，做好降低央企负债率工作。一要建立严格的分行业负债率警戒线管控制度。对高于警戒线的企业在年度经营业绩考核中增加资产负债率的权重，并严格把关主业投资、严控非主业投资、禁止安排推高负债率的投资项目。二要建立多渠道降低企业债务的机制。对效益增长较快的企业要督促其在确保正常生产经营前提下利用部分利润降低债务。对负债率低于警戒线或盈利能力较强的企业，鼓励其主动化解历史遗留问题，并在当期损益中消化去产能等改革成本。研究出台钢铁煤炭行业化解过剩产能国有资产处置有关财务处理办法，探索建立国有资本补充机制，妥善解决企业改革发展、转型升级所需资本。三要积极稳妥推进市场化法治化债转股，督促已签订的框架协议抓紧落实。推动发展前景好、有转股意向的央企创新模式，加快推进债转股。加大央企兼并重组力度，稳妥实施混合所有制改革。支持债转股实施机构多渠道筹资。鼓励国有资本投资、运营公司及有条件的央企基金采取各种市场化方式参与债转股。四要强化问责，对负债率持续攀升的企业要约谈提醒，对造成重大损失或不良影响的严肃追责。与地方合作的项目要严防增加地方政府隐性债务。五要在推动新旧动能接续转换中实现产业转型升级和降杠杆。采取有效措施确保完成今年化解钢铁煤炭过剩产能、处置“僵尸企业”和治理特困企业等工作任务。推动火电、电解铝、建材等行业开展减量减产，严控新增产能。深入实施“中国制造2025”和“互联网+”，积极发展新产业新业态新模式，改造提升传统动能，加快向数字化、网络化、智能化转型，增强发展后劲。会议要求制定进一步积极稳妥推进央企降杠杆减负债的指导意见，推动央企资产负债率总体持续稳中有降，促进高负债企业负债率逐步回归合理水平。</p>
 -->                        <hr>

                        <div class="row">
                            <div class="col-lg-12" style="text-align:center;">
                            <a onClick="backcheck()" class="btn btn-info btn-rounded" href="javascript:;">返回</a>
                            </div>
                        </div>


                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
    
    <%@include file="common_js.jsp"%>
    <script type="text/javascript">
	$("video").attr('preload',"auto");
	</script>
</body>

</html>
