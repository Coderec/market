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
           var submit=$('#registration-submit');
           var user_reg=/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9])+$/;
           var btn=1;
           submit.click(function(){

                if(btn==5){
                    $('form').submit()
                }
            })

           user.blur(function(event) {
               var str=$(this).val();
               if(!user_reg.test(str)){
                   $('.userTip').css({color:'red'});

               }else{
                   $('.userTip').css({color:'#757575'});
                    btn++;
               }
            });
           psw.blur(function(event) {
               var str=$(this).val();
               if(str.length<6){
                   $('.psdTip').css({color:'red'});

               }else{
                   $('.psdTip').css({color:'#757575'})
                   btn++;
               }


           });
            $('#passwordand').blur(function(){
                var str=$(this).val();
                if(str.length<6){
                    $('.psdresTip').css({color:'red'});

                }else{

                    if(psw.val()!=str){
                        $('.psdresTip').text('前后密码不一样，请重新输入').css({color:'red'});
                    }else{
                        $('.psdresTip').text('前后密码一致').css({color:'#757575'});
                        btn++;
                    }

                }
            })
           phone.blur(function(){
                var str=$(this).val();
                if(!isEmail(str)){
                    $('.emailTip').css({color:'red'})

                }else{
                    $('.emailTip').css({color:'#757575'})
                    btn++;
                }
           })
            function isEmail(str){
                var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
                return reg.test(str);
            }
            submit.hover(function(event) {
               $(this).css({
                   cursor: 'pointer',
                   background: '#1276AA'
               });
           },function(){
                $(this).css('background', '#2db3f8');
           });

               
        })
       
  
    	
    </script>
</head>
<body>
    <div class="content">
        <div class="header">
            <a href="<%=path%>/index.jsp"><img src="<%=path%>/images/login-reg-logo.png"> </a>
        </div>
        <div class="form">
            <img src="<%=path%>/images/login-reg-bg.png" id="form-bg">
            <div id="registration-form">
            	
                <form action="<%=path%>/registration/registrationAction_registration.do" method="post" class="demoform">
					<span style="color:red">${request.errorMsg}</span>
                    <div>
                        <div class="beforeImg fl"><img src="<%=path%>/images/userReg.png"> </div>
                        <input type="text" name="account" class="fl" autofocus required nullmsg="不能为空" id="username">
                    </div>
                    <span class="userTip">请输入3至15个字符组成的用户名</span>
                    <div>
                        <div class="beforeImg fl"><img src="<%=path%>/images/psdReg.png"> </div>
                        <input type="password" name="pwd"  required id="password">
                    </div>
                    <span class="psdTip">请输入大于6位数的密码</span>
                    <div>
                        <div class="beforeImg fl"><img src="<%=path%>/images/psdRES.png"> </div>
                        <input type="password" name="pwd1" required id="passwordand">
                    </div>
                    <span class="psdresTip">请再次输入你的密码</span>
                    <div>
                        <div class="beforeImg fl"><img src="<%=path%>/images/email.png"> </div>
                        <input type="text" name="email"  required id="phone"  name="email">
                    </div>
                    <span class="emailTip">请输入正确的邮箱</span>

                    <input type="button" value="注册" id="registration-submit" >
                </form>
            </div>

        </div>

    </div>
    <div class="footer">
        <p>2014&nbsp<a href="http://online.cumt.edu.cn/">学生在线</a> &nbsp&nbsp<a href="http://online.cumt.edu.cn/FlyingStudio2014">翔工作室</a>&nbsp版权所有 </p>
    </div>
</body>
</html>