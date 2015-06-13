<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/item.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/item.js"></script>
    <script type="text/javascript">
        $(function(){
            var old=$('#old');
            var buy=$('#buy');
            $('#old,#buy').hover(function() {
                $(this).css({
                    cursor: 'pointer',
                    background: '#1579AD'
                });
            }, function() {
                $(this).css('background', '#3cb8f8');
            });
            $('.content  input[type="submit"]').hover(function(){
                $(this).css({background:'url(../images/search4.png)'})
            },function(){
                $(this).css({background:'url(../images/search3.png)'})
            }) 
             $('.show ul>li').find('img').load(function(){
                var bi=$(this).width()/$(this).height();
                if(bi>1){
                    $(this).width(512);
                    $(this).height($(this).width()*(1/bi));
                }else{
                    $(this).height(512);
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
    <form name="form2" method="post" action="<%=path%>/searchCommon/searchCommonAction_searchCommon.do">
        <input type="text" name="searchName" placeholder="输入你想搜的商品">
        <input type="submit" value="搜二手" id="old" onclick="document.form2.hidden.value='used'">
        <input type="submit" value="搜求购" id="buy" onclick="document.form2.hidden.value='buy'">
        <input type="hidden" name="hidden" >
    </form>
    <div class="show">
        <div class="pre"></div>

        <ul>
            <s:if test="#request.pre==\"g\"">
            <s:if test="#request.goodsList[0].gimg1!=null">
            <li><a href=""><img src="${session.goodsImage[0]}" ></a> </li>
            </s:if>
            <s:if test="#request.goodsList[0].gimg2!=null">
            <li><a href=""><img src="${session.goodsImage[1]}" ></a> </li>
            </s:if>
            <s:if test="#request.goodsList[0].gimg3!=null">
            <li><a href=""><img src="${session.goodsImage[2]}" ></a> </li>
            </s:if>
            <s:if test="#request.goodsList[0].gimg4!=null">
            <li><a href=""><img src="${session.goodsImage[3]}" ></a> </li>
            </s:if>
            <s:if test="#request.goodsList[0].gimg5!=null">
            <li><a href=""><img src="${session.goodsImage[4]}" ></a> </li>
            </s:if>
            <s:if test="#request.goodsList[0].gimg6!=null">
            <li><a href=""><img src="${session.goodsImage[5]}" ></a> </li>
            </s:if>
            <ol>
                <s:if test="#request.goodsList[0].gimg1!=null">
                <li>1</li>
                </s:if>
                <s:if test="#request.goodsList[0].gimg2!=null">
                <li>2</li>
                </s:if>
                <s:if test="#request.goodsList[0].gimg3!=null">
                <li>3</li>
                </s:if>
                <s:if test="#request.goodsList[0].gimg4!=null">
                <li>4</li>
                </s:if>
                <s:if test="#request.goodsList[0].gimg5!=null">
                <li>5</li>
                </s:if>
                <s:if test="#request.goodsList[0].gimg6!=null">
                <li>6</li>
                </s:if>
            </ol>
        </s:if>
        <s:if test="#request.pre==\"n\"">
            <li><a href=""><img src="<%=path%>/images/needsDefault.png"></a> </li>
        </s:if>
        </ul>
        <div class="next"></div>
    </div>
    <s:if test="#request.pre==\"g\"">
    <div class="introduce">
        <p class="large">${request.goodsList[0].gname}</h1>
        <p>转售价：<span class="price">${request.goodsList[0].gprice}</span><span>元</span></p>
        <p>原售价：<span>${request.goodsList[0].gprePrice}</span><span>元</span></p>
        <div class="line"></div>
        <p>成色：<span>${request.goodsList[0].gdegree}</span><span></span></p>
        <p>所在校区：<span>${request.goodsList[0].gplace}</span></p>
        <p>所属类别：<span>${request.goodsList[0].gcategory}</span></p>
        <s:if test="#request.goodsList[0].gphone==''">
        <p>联系电话：<span></span></p>
        </s:if>
        <s:else>
	        <s:if test="#session.session_isLogined=='true'">
	        <p>联系电话：<span>${request.goodsList[0].gphone}</span></p>
	        </s:if>
	        <s:else>
	        <p>联系电话：<span><a href="<%=path%>/login.jsp">${fn:substring(request.goodsList[0].gphone, 0, 3)}****${fn:substring(request.goodsList[0].gphone, 7, -1)}</a></span></p>
	        </s:else>
        </s:else>
        <p>卖家：<span><a href="<%=path%>/toHomePage/toHomePageAction_toHomePage.do?host=${request.goodsList[0].user.uname}">${request.goodsList[0].user.uname}</a> </span></p>
        <s:if test="!#request.goodsList[0].gvalid">
        <br>
        <p style="color:red">该物品已关闭</p>
        </s:if>
        <div class="save fl">
            <s:if test="#request.everCollected!=true">
            <span>收藏宝贝</span>

            <form action="<%=path%>/collect/collectAction_collect.do" method="post">
                <input type="hidden" name="pre" value="${pre }"/>
                <input type="hidden" name="id" value="${id }"/>
                <input type="hidden" name="collect" value=" " id="saveBtn"/>
                <input type="hidden" name="delete" value=" " id="deleteBtn"/>
            </form>
            </s:if>
            <s:else>
            <span>取消收藏</span>

            <form action="<%=path%>/unCollect/unCollectAction_unCollect.do" method="post">
                <input type="hidden" name="pre" value="${pre }"/>
                <input type="hidden" name="id" value="${id }"/>
                <input type="hidden" name="unCollect" value=" " id="saveBtn"/>
                <input type="hidden" name="delete" value=" " id="deleteBtn"/>
            </form>
            </s:else>
        </div>
        <s:if test="%{#request.goodsList[0].user.uname==#session.session_user.uname}">
        <div class="delete fl">
            <span>删除宝贝</span>
        </div>
        </s:if>
        <div class="share fl"></div>

    </div>
    </s:if>
     <s:if test="#request.pre==\"n\"">
    <div class="introduce">
        <h1>${request.needsList[0].nname}</h1>
        <p>转售价：<span class="price">${request.needsList[0].nhighesPricet}</span><span>元</span></p>
        <p>原售价：<span>${request.needsList[0].nlessPrice}</span><span>元</span></p>
        <div class="line"></div>
        <p>成色：<span>${request.needsList[0].ndegree}</span><span></span></p>
        <p>所在校区：<span>${request.needsList[0].nplace}</span></p>
        <p>所属类别：<span>${request.needsList[0].ncategory}</span></p>
        <s:if test="#request.needsList[0].nphone==''">
        <p>联系电话：<span></span></p>
        </s:if>
        <s:else>
	        <s:if test="#session.session_isLogined=='true'">
	        <p>联系电话：<span>${request.needsList[0].nphone}</span></p>
	        </s:if>
	        <s:else>
	        <p>联系电话：<span><a href="<%=path%>/login.jsp">${fn:substring(request.needsList[0].nphone, 0, 3)}****${fn:substring(request.needsList[0].nphone, 7, -1)}</a></span></p>
	        </s:else>
        </s:else>
        <p>卖家：<span><a href="<%=path%>/toHomePage/toHomePageAction_toHomePage.do?host=${request.needsList[0].user.uname}">${request.needsList[0].user.uname}</a> </span></p>
        <s:if test="!#request.needsList[0].nvalid">
        <br>
        <p style="color:red">该物品已关闭</p>
        </s:if>
        <!-- <div class="save fl">
            <span>求购不能收藏，等待修复</span>

            <form action="<%=path%>/collect/collectAction_collect.do" method="post">
                <input type="hidden" name="pre" value="${pre }"/>
                <input type="hidden" name="id" value="${id }"/>
                <input type="hidden" name="collect" value=" " id="saveBtn"/>
                <input type="hidden" name="delete" value=" " id="deleteBtn"/>
            </form>
        </div> -->
        <s:if test="%{#request.needsList[0].user.uname==#session.session_user.uname}">
        
        <br/>
      
        <form name="form6" action="<%=path%>/collect/collectAction_collect.do" method="post">
             <input type="hidden" name="pre" value="${pre }"/>
             <input type="hidden" name="id" value="${id }"/>
             <input type="hidden" name="delete" value="1" id="deleteBtn"/>
             <input type="submit" value="删除宝贝" style="width:100px;height:40px; color:#3c3c3c ;font-size:20px" id="super" />
        </form>
       
        </s:if>
        <div class="share fl"></div>

    </div>
    </s:if>
    <div class="line1"></div>
    <s:if test="#request.pre==\"g\"">
    <div class="liuyan-right">
        <s:iterator value="#request.gCommentList" status="st">
        <div class="exchange">
                 <div class="leave">
                     <div class="leave-content">${gcMsg}
                         <span class="fr innerByer">${request.gCommentFromUsersList[st.index].uname}</span>

                     </div>

                 </div>
        </div>
        </s:iterator>
    </div>
    </s:if>
    <s:if test="#request.pre==\"n\"">
    <div class="liuyan-right">
        <s:iterator value="#request.nCommentList" status="st">
        <div class="exchange">
                 <div class="leave">
                     <div class="leave-content">${ncMsg}
                         <span class="fr innerByer">${request.nCommentFromUsersList[st.index].uname}</span>

                     </div>

                 </div>
        </div>
        </s:iterator>
    </div>
    </s:if>
    <div class=" mb20 liuyan-left">
        <s:if test="#request.pre==\"g\"">
        <p>${request.goodsList[0].gdescription}</p>
        <form id="form5" name="form5" action="<%=path%>/comment/commentAction_comment.do" method="post" id="lyForm">
            <textarea id="str" onfocus="this.innerHTML=' '">最多输入60字</textarea><br>
            <input type="hidden" name="msg" value="" id="msg"/>
            <input type="hidden" name="id" value="${request.id}"/>
            <input type="hidden" name="pre" value="${request.pre}"/>
            <input type="button" value="提交" id="liuyan" class="fr">
        </form>
        </s:if>
        <s:if test="#request.pre==\"n\"">
        <p>${request.needsList[0].ndescription}</p>
        <form action="<%=path%>/comment/commentAction_comment.do" method="post" id="lyForm">
            <textarea >最多输入60字</textarea><br>
            <input type="hidden" name="msg" value="" id="msg"/>
            <input type="hidden" name="id" value="${request.id}"/>
            <input type="hidden" name="pre" value="${request.pre}"/>
            <input type="button" value="提交" id="liuyan" class="fr">

        </form>
        </s:if>
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

<!--
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"2","bdPos":"right","bdTop":"100"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
-->
</body>
</html>