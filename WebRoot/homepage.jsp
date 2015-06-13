<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/homepage.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/homepage.js"></script>
    <script type="text/javascript">
        $(function(){
            var old=$('#old');
            var buy=$('#buy');
            $('.content  input[type="submit"]').hover(function(){
                $(this).css({cursor:'pointer',background:'url(../images/search4.png)'})
            },function(){
                $(this).css({background:'url(../images/search3.png)'})
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
    <form name="form1" method="post" action="<%=path%>/searchCommon/searchCommonAction_searchCommon.do">
        <input type="text" name="searchName" placeholder="输入你想搜的商品">
        <input type="submit" value="搜二手" id="old" onclick="document.form1.hidden.value='used'">
        <input type="submit" value="搜求购" id="buy" onclick="document.form1.hidden.value='buy'">
        <input type="hidden" name="hidden" >
    </form>
    <div class="home">
        <div class="home-nav">
        	<s:if test="#request.isMe==true">
            <div data="good" class="active"><p>我的宝贝</p></div>
            <div data="collect"><p>我的收藏</p></div>
            <div data="msg"><p>我的物品评论</p></div>
            </s:if>
            <s:else>
            <div data="good" class="active"><p>Ta的宝贝</p></div>
            <div data="collect"><p>Ta的收藏</p></div>
            </s:else>
        </div>
        <div class="home-content">
            <div class="pre"></div>
            <div class="good box ">
                <ul>
                    <s:if test="#request.goodsList!=null">
                	<s:iterator var="goods" value="#request.goodsList">
	                    <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${goods.gimg1}" > </a>
	                        <p>${goods.gname}</p>
	                    </li>
					</s:iterator>
					</s:if>

                </ul>
            </div>
            <div class="collect hidden box">
                <ul>
                    <s:if test="#request.gidsList!=null">
                    <s:iterator var="goods" value="#request.gidsList">
                        <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${goods.gimg1}" > </a>
                            <p>${goods.gname}</p>
                        </li>
                    </s:iterator>
                    </s:if>


                </ul>
            </div>
            <s:if test="#request.isMe==true">
            <div class="msg box hidden">
                <ul>
                	<s:if test="#request.gCommentGoodsList!=null">
                	<s:iterator var="goods" value="#request.gCommentGoodsList" status="st">
	                    <li>
                        <div class="leave"><p><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}">${request.gCommentList[st.index].gcMsg}</a></p>
                            <p class="ml">${request.gCommentUsersList[st.index].uname}</p>
                        </div>
                    	</li>
					</s:iterator>
					</s:if>
                </ul>
            </div> 
            </s:if>
            <div class="next"></div>
        </div>
    </div>


</div>
<div class="footer">
    <p>2014&nbsp<a href="http://online.cumt.edu.cn/">学生在线</a> &nbsp&nbsp<a href="http://online.cumt.edu.cn/FlyingStudio2014">翔工作室</a>&nbsp版权所有 </p>
</div>
<!-- <div class="newBg">
    <div class="newForm">
        <form>
            <textarea></textarea><br>
            <input type="button" value="提交">
        </form>
    </div>
</div> -->
</body>
</html>