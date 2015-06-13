<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/details.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>

    <script type="text/javascript">
        $(function(){
            var old=$('#old');
            var buy=$('#buy');
            $('.content  input[type="submit"]').hover(function(){
                $(this).css({cursor:'pointer',background:'url(../images/search4.png)'})
            },function(){
                $(this).css({background:'url(../images/search3.png)'})
            })
            $('.details-content img').load(function(){
		        var bi=$(this).width()/$(this).height();
		        if(bi>1){
		            $(this).width(180);
		            $(this).height($(this).width()*(1/bi));
		        }else{
		            $(this).height(180);
		            $(this).width($(this).height()*bi);
		        }
		    })
        })
    </script>
</head>
<body>
<div id="header_fixed">
    <div class="header">
        <a href="<%=path%>/index.jsp" id="logo"><img src="<%=path%>/images/detail.png"></a>
        <ul>
            <s:if test="#session.session_isLogined=='true'">
                <li class="message"><a href="<%=path%>/toHomePage/toHomePageAction_toHomePage.do?host=${session.session_user['uname']}"> 消息&nbsp<span>${session.session_userMsg}</span></a></li>
                <li class="favorites"><a href="<%=path%>/toHomePage/toHomePageAction_toHomePage.do?host=${session.session_user['uname']}"> 收藏夹&nbsp<span>${session.session_userCollect}</span></a></li>
                <li><a href="<%=path%>/exit/exitAction_exit.do">退出</a></li>
                <li>用户名：<span><a href="<%=path%>/toHomePage/toHomePageAction_toHomePage.do?host=${session.session_user['uname']}">${session.session_user.uname}</a></span></li>
            </s:if>
            <s:else>
                 <li class="register_button"><a href="<%=path%>/registration.jsp">注册</a></li>
                <li class="login_button"><a href="<%=path%>/login.jsp">登录</a></li>
            </s:else>

        </ul>
    </div>


</div>
<div class="content">
    <form name="form3" method="post" action="<%=path%>/searchCommon/searchCommonAction_searchCommon.do">
        <input type="text" name="searchName" placeholder="输入你想搜的商品">
        <input type="submit" value="搜二手" id="old" onclick="document.form3.hidden.value='used'">
        <input type="submit" value="搜求购" id="buy" onclick="document.form3.hidden.value='buy'">
        <input type="hidden" name="hidden" >
    </form>
    <div class="details">
        <div class="title">
            <div></div>
            <h2>${className}</h2>
            <s:if test="#request.className=='闲置数码'">
            <h5>手机</h5>
            <h5>相机</h5>
            <h5>电脑</h5>
            </s:if>
            <s:if test="#request.className=='书籍杂志'">
            <h5>教程</h5>
            <h5>辅导</h5>
            <h5>杂志</h5>
            </s:if>
            <s:if test="#request.className=='家居日用'">
            <h5>日杂</h5>
            <h5>化妆品</h5>
            <h5>电器</h5>
            </s:if>
            <s:if test="#request.className=='服鞋配饰'">
            <h5>服装</h5>
            <h5>鞋子</h5>
            <h5>配饰</h5>
            </s:if>
        </div>
        <div class="details-content">
            <ul>
                <s:iterator value="#request.goodsList.pageElements" var="goods" status="st">
                    <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.classifiedImages[st.index]}" > </a>
                        <p>${goods.gname}</p>
                    </li>
                </s:iterator>
                
            </ul>
            <div class="pagination">
                <ul>
                    <li class="preNext"><a href="<%=path%>/classifiedDetailsChangePage/classifiedDetailsChangePageAction_classifiedDetailsChangePage.do?className=${request.className}&pageNum=${request.goodsList.previousPageNumber}">上一页</a> </li>
                    <li class="iNow">当前第&nbsp<span>${goodsList.currPageNumber}</span>/<span><fmt:formatNumber type="number" value="${(goodsList.totalCount-goodsList.totalCount%12)/12+1}"/>&nbsp页</span></li>
                    <li class="preNext"><a href="<%=path%>/classifiedDetailsChangePage/classifiedDetailsChangePageAction_classifiedDetailsChangePage.do?className=${request.className}&pageNum=${request.goodsList.nextPageNumber}">下一页</a> </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p>2014&nbsp<a href="http://online.cumt.edu.cn/">学生在线</a> &nbsp&nbsp<a href="http://online.cumt.edu.cn/FlyingStudio2014">翔工作室</a>&nbsp版权所有 </p>
</div>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"2","bdPos":"right","bdTop":"100"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>

</body>
</html>