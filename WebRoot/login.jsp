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
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/login-reg.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            var user=$('#username');
            var psw=$('#password');
             var phone=$('#phone');
            var submit=$('#login-submit');

           var phone_reg=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;

            function checkStr(str){
                // [\u4E00-\uFA29]|[\uE7C7-\uE7F3]汉字编码范围
                var re1 = new RegExp("^([\u4E00-\uFA29]+|[\uE7C7-\uE7F3]+|[a-zA-Z0-9])+$");
                if (!re1.test(str)){
                    return false;
                }
                 return true;
            }
            user.blur(function(event) {
               var str=$.trim($(this).val());
               if(str.length==0){
                   
                   $('.demoForm').submit(function(){
                       return false;
                   })
               }
            });
           psw.blur(function(event) {
               var str=$(this).val();
               if(str.length<6){
                   $('.demoForm').submit(function(){
                       return false;
                   })
                }
            });
          
           submit.hover(function(event) {
               $(this).css({
                   cursor: 'pointer',
                   background: '#1276AA'
               });
           },function(){
                $(this).css('background', '#2db3f8');
           });

           
            $('#newBtn').hover(function(){
                $(this).css({background:'#508a23',cursor:'pointer'})
            },function(){
                $(this).css('background','#63a72d')
            })
            $('#newText').blur(function(){
                if($(this).val()==''||!isEmail($(this).val())){
                    $('#newBtn').click(function(){
                        return false
                    })
                }
            })
            $('#newBtn').click(function(){
                $('.newForm form').submit();
                $('.newForm').css('display','block')
            })
               
        })
        function isEmail(str){
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            return reg.test(str);
        }
      
    </script>
     <script type="text/javascript">
    function changeImage(img){
    img.src=img.src+"?"+new Date().getTime();}
    </script>
</head>
<body>

    <div class="content">
        <div class="header">
            <a href="<%=path%>/index.jsp"><img src="<%=path%>/images/login-reg-logo.png"> </a>
        </div>
        <div class="form">
            <img src="<%=path%>/images/login-reg-bg.png" id="form-bg">
            <div id="login-form">
                <form action="<%=path%>/login/loginAction_login.do" method="post" class="demoform">
                	<p style="color:red">${request.errorMsg}</p>
                    <div class="user-logo"></div> <input type="text" name="account" placeholder="请输入你的用户名" autofocus required id="username">
                    <div class="password-logo"></div><input type="password" name="pwd" placeholder="请输入你的密码" required id="password">
                    <div class="yzm-logo"></div><input type="text"  name="checkcode" id="yzm">
                    <img src="<%=path%>/servlet/ImageServlet" onclick="changeImage(this)" alt="换一张" style="cursor:hand">
					<p>忘记密码？<a href="http://online.cumt.edu.cn/discuz/forum.php">找回密码>></a> </p>
                    <input type="submit" value="登录" id="login-submit" >
                </form>
            </div>

        </div>

    </div>
    <div class="footer">
        <p>2014&nbsp<a href="http://online.cumt.edu.cn/">学生在线</a> &nbsp&nbsp<a href="http://online.cumt.edu.cn/FlyingStudio2014">翔工作室</a>&nbsp版权所有 </p>
    </div>

</body>
</html>