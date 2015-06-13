<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<s:if test="#session.session_isLogined!='true'">
		<%
			response.sendRedirect(path+"/login.jsp");
		 %>
	</s:if>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/post.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/post.js"></script>
   
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
        <p>你有什么闲置的宝贝要出售</p>
        <div class="post-form">
            <form action="<%=path%>/post/postAction_post.do" method="post" class="demoform" enctype="multipart/form-data">
                <div class="fl side-left">
                    <input type="hidden" name="kind" value="g"/>
                    <p><label for="title">标题</label><input type="text" name="name" id="title"><span>请输入3至8个字</span></p>
                    <p><label for="item">类目</label><input type="button" name="category" id="item" value=" "><span>请选择</span></p>
                    <p><label for="usetime">新旧</label><input type="button" name="degree" id="usetime" value=" "><span>请选择</span></p>
                    <p><label for="price" >价格</label><input type="text" name="price" id="price" ><span>请输入1-20000</span></p>
                    <p><label for="original-price">原价</label><input type="text" name="pre_price" id="original-price" ><span>请输入1-20000</span></p>
                    <p><label for="phone" >电话号码</label><input type="text" name="phone" id="phone"><span>电话格式错误</span></p>
                </div>
                <div class="fl ml118">
                    <p>
                    	<label>所在地</label>
                    	<span class="nanhu"></span><span >南湖校区</span><span class="wenchang"></span><span>文昌</span>
                   	</p>
                   	
					<label class="fl" >宝贝图片</label>
                    <div class="file fl">
                   		
                        <input type="file" name="imgs" class="iptFile"><br>
                        <input type="file" name="imgs"  class="iptFile"><br>
                        <input type="file" name="imgs"  class="iptFile"><br>
                        <input type="file" name="imgs"  class="iptFile"><br>
                        <input type="file" name="imgs"  class="iptFile"><br>
                        <input type="file" name="imgs"  class="iptFile">
                    </div>
                   <div class="clear"></div>
					<label class="fl">宝贝描述</label><textarea name="description" class="fl" rows="7" cols="20" id="description"></textarea>
                </div>

                 <input type="hidden" name="place" value="nanhu" id="hidePlace">
                 <input type="hidden" name="degree" value=" " class="usetime">
                 <input type="hidden" name="category" value=" " class="item">

            </form>


        </div>
        <input type="image" src="<%=path%>/images/post-button.png"  id="submit">
    </div>
    <div class="footer">
        <p>2014&nbsp<a href="http://online.cumt.edu.cn/">学生在线</a> &nbsp&nbsp<a href="http://online.cumt.edu.cn/FlyingStudio2014">翔工作室</a>&nbsp版权所有 </p>
    </div>
</body>
</html>