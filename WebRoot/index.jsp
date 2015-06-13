<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!doctype html>
<html lang="en">
<head>
    <% 
        if(request.getAttribute("agf")==null){
          response.sendRedirect(path+"/index/indexAction_index.do");
        }
    %>
	<meta charset="UTF-8">
	<title>跳蚤市场</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css">

	<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/base.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/switch.js"></script>
	<script src="Scripts/swfobject_modified.js" type="text/javascript"></script>
	<script type="text/javascript">
	
			var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
					: " http://");
			document
					.write(unescape("%3Cscript src='"
							+ _bdhmProtocol
							+ "hm.baidu.com/h.js%3F252df6bcd449d899c97af170df08db1b' type='text/javascript'%3E%3C/script%3E"));
		
			
	</script>
</head>
<body>
	<div id="header_fixed">
		<div class="header">
			<a href="http://online.cumt.edu.cn" id="logo"><img src="<%=path%>/images/online.png"></a>
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
			<div class="navigation">
				<div class="search_box">
					<img src="<%=path%>/images/tiaozao.png" style="margin-top:21px;width:183px;margin-left:50px;margin-bottom:15px;"/>
					
					<form action="<%=path%>/search/searchAction_search.do" method="post" name="form1">
						<span class="used" ></span><span >搜二手</span>
					  <span class="buy"></span><span>搜求购</span><br>
						<input type="text" name="searchName" id="search"><input type="image"  src="<%=path%>/images/search.png" alt="Submit" class="search_button fl"/>
						<div class="line"></div>
						<div class="goods_submit fl" data='post'><p>发布宝贝</p></div>
						<div class="buy_submit fl" data='buy'><p>发布求购</p></div>
                        <input type="hidden" name="hide" value="used" id="hide"/>
                        <input type="hidden" name="hideButton" value=" " id="hideButton">

					</form>
				</div>
				<div class="classification ">
					<div class="box">
						<div class="bgDiv"></div>
						<p >闲置数码</p>
						<span>手机</span><span>相机</span><span>电脑</span>
					</div >
					<div class="box">
						<div class="bgDiv"></div>
						<p >书籍杂志</p>
						<span>教程</span><span>辅导</span><span>杂志</span>
					</div>
					<div class="box"> 
						<div class="bgDiv"></div>
						<p >家具日用</p>
						<span>日杂</span><span>化妆品</span><span>电器</span>
					</div >
					<div class="box ">
						<div class="bgDiv"></div>
						<p >服鞋配饰</p>
						<span>服装</span><span>鞋子</span><span>配饰</span>
					</div>
                    <div class="box ">
                        <div class="bgDiv"></div>
                        <p >竹苑小市场</p>

                    </div>

                    <form id="formLinks" action="<%=path%>/toClassifiedDetails/toClassifiedDetailsAction_toClassifiedDetails.do" method="post" >
                        <input type="hidden" name="className" value="" id="links">
                    </form>

				</div>

				<div class="banner">
					<ul class="show">
						<li><a href="<%=path%>/toClassifiedDetails/toClassifiedDetailsAction_toClassifiedDetails.do?className=闲置数码"><img src="<%=path%>/images/banner1.jpg"></a></li>
						<li><a href="<%=path%>/toClassifiedDetails/toClassifiedDetailsAction_toClassifiedDetails.do?className=书籍杂志"><img src="<%=path%>/images/banner2.jpg"></a></li>
						<li><a href="<%=path%>/toClassifiedDetails/toClassifiedDetailsAction_toClassifiedDetails.do?className=家具日用"><img src="<%=path%>/images/banner3.jpg"></a></li>
						<li><a href="<%=path%>/toClassifiedDetails/toClassifiedDetailsAction_toClassifiedDetails.do?className=服鞋配饰"><img src="<%=path%>/images/banner4.jpg"></a></li>
					</ul>
                    <ol class="number">
                        <li class="active"></li>
                        <li></li>
                        <li></li>
                        <li></li>

                    </ol>

				</div>

			</div>
			<div class="first_session">
				<span class="title1" data='idle'>全新闲置</span><span data='love' >猜你喜欢</span>
                <input type="hidden" name="" value="idle" id="change">
				<ul class="slide one">
					<s:iterator value="#request.newestGoodsList" status="st" var="goods">
                    <s:if test="#st.index!=4">
                        <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.newestImagesList[st.index]}" ></a>
                        <p class="p1">${goods.gname}</p>
                        <p class="p2">￥${goods.gprice}</p>
                        </li>
                    </s:if>
                    <s:else>
                        <li class="mr0"><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.newestImagesList[st.index]}" ></a>
                        <p class="p1">${goods.gname}</p>
                        <p class="p2">￥${goods.gprice}</p>
                        </li>
                    </s:else>
                    </s:iterator>
                </ul>
                <ul class="slide hidden two">
                    <s:iterator value="#request.favorGoodsList" status="st" var="goods">
                    <s:if test="#st.index!=4">
                        <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.favorImagesList[st.index]}" ></a>
                        <p class="p1">${goods.gname}</p>
                        <p class="p2">￥${goods.gprice}</p>
                        </li>
                    </s:if>
                    <s:else>
                        <li class="mr0"><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.favorImagesList[st.index]}" ></a>
                        <p class="p1">${goods.gname}</p>
                        <p class="p2">￥${goods.gprice}</p>
                        </li>
                    </s:else>
                    </s:iterator>
                </ul>
			</div>
            <div class="tip"><div class="green"></div><span>闲置数码</span></div>
			<div class="second_session">

				<ul class="slide2">
					<s:iterator value="#request.goodsList1" var="goods" status="st">
                        <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.goodses1ImagesList[st.index]}" ></a>
                        <p class="p1">${goods.gname}</p>
                        <p class="p2">￥${goods.gprice}</p>
                        </li>
                    </s:iterator>
					
				</ul>
			</div>
            <div class="tip"><div class="green"></div><span>书籍杂志</span></div>
            <div class="second_session">
                <ul class="slide2">
                    <s:iterator value="#request.goodsList2" var="goods" status="st">
                        <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.goodses2ImagesList[st.index]}" ></a>
						<p class="p1">${goods.gname}</p>
                        <p class="p2">￥${goods.gprice}</p>
                        </li>
                    </s:iterator>
                </ul>
            </div>
            <div class="tip"><div class="green"></div><span>家具日用</span></div>
            <div class="second_session">
                <ul class="slide2">
                    <s:iterator value="#request.goodsList3" var="goods" status="st">
                        <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.goodses3ImagesList[st.index]}" ></a>
                        <p class="p1">${goods.gname}</p>
                        <p class="p2">￥${goods.gprice}</p>
                        </li>
                    </s:iterator>
                </ul>
            </div>
            <div class="tip"><div class="green"></div><span>服鞋配饰</span></div>
            <div class="second_session">
                <ul class="slide2">
                    <s:iterator value="#request.goodsList4" var="goods" status="st">
                        <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.goodses4ImagesList[st.index]}" ></a>
                        <p class="p1">${goods.gname}</p>
                        <p class="p2">￥${goods.gprice}</p>
                        </li>
                    </s:iterator>
                </ul>
            </div>
            <div class="tip"><div class="green"></div><span>竹苑小市场</span></div>
            <div class="second_session">
                <ul class="slide2">
                    <s:iterator value="#request.goodsList5" var="goods" status="st">
                        <li><a href="<%=path%>/toItemDetails/toItemDetailsAction_toItemDetails.do?pre=g&id=${goods.gid}"><img src="${session.goodses5ImagesList[st.index]}" ></a>
						<p class="p1">${goods.gname}</p>
                        <p class="p2">￥${goods.gprice}</p>
                        </li>
                    </s:iterator>
                </ul>
            </div>
	</div>
	<div class="footer">
		<p>2014&nbsp<a href="http://online.cumt.edu.cn/">学生在线</a> &nbsp&nbsp<a href="http://online.cumt.edu.cn/FlyingStudio2014">翔工作室</a>&nbsp版权所有 </p>
	</div>

</body>
</html>