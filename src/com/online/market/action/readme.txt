如果要实现分页功能，则继承PageAction
反之则继承BaseAction

SearchAction					主页搜索框使用
SearchCommonAction				其他页面的搜索框共同使用 
LoginAction						登录用
Registration					注册用
ToHomePage						跳到homepage之前经过的action
ToItemDetails					跳到itemDetails之前经过的action
ToClassifiedDetails				跳到classifiedDetails之前经过的action
Index							跳到index之前经过的action					为了完成随机显示类别下的物品
Post							post点击发布后跳到的action


GoodsAction原本是提供给与goods相关操作的action的超类，但是，在开发中并没有用到。
该类提供了与goods相关的操作方法，看源码即可。

ImageServlet用来生成验证码