<%@ page language="java"
	import="java.util.*,com.online.market.model.Goods,com.online.market.model.User"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>跳蚤市场后台管理界面</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- The styles -->
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link href="<%=path%>/css/bootstrap-cerulean.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<link href="css/charisma-app.css" rel="stylesheet">
<link href="css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
<link href='css/fullcalendar.css' rel='stylesheet'>
<link href='css/fullcalendar.print.css' rel='stylesheet' media='print'>
<link href='css/chosen.css' rel='stylesheet'>
<link href='css/uniform.default.css' rel='stylesheet'>
<link href='css/colorbox.css' rel='stylesheet'>
<link href='css/jquery.cleditor.css' rel='stylesheet'>
<link href='css/jquery.noty.css' rel='stylesheet'>
<link href='css/noty_theme_default.css' rel='stylesheet'>
<link href='css/elfinder.min.css' rel='stylesheet'>
<link href='css/elfinder.theme.css' rel='stylesheet'>
<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='css/opa-icons.css' rel='stylesheet'>
<link href='css/uploadify.css' rel='stylesheet'>



<link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>

	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a>


				<!-- theme selector starts -->
				<div class="btn-group pull-right theme-container">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-tint"></i><span class="hidden-phone">更改主题</span> <span
						class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="themes">
						<li><a data-value="classic" href="#"><i
								class="icon-blank"></i> Classic</a></li>
						<li><a data-value="cerulean" href="#"><i
								class="icon-blank"></i> Cerulean</a></li>
						<li><a data-value="cyborg" href="#"><i class="icon-blank"></i>
								Cyborg</a></li>
						<li><a data-value="redy" href="#"><i class="icon-blank"></i>
								Redy</a></li>
						<li><a data-value="journal" href="#"><i
								class="icon-blank"></i> Journal</a></li>
						<li><a data-value="simplex" href="#"><i
								class="icon-blank"></i> Simplex</a></li>
						<li><a data-value="slate" href="#"><i class="icon-blank"></i>
								Slate</a></li>
						<li><a data-value="spacelab" href="#"><i
								class="icon-blank"></i> Spacelab</a></li>
						<li><a data-value="united" href="#"><i class="icon-blank"></i>
								United</a></li>
					</ul>
				</div>
				<!-- theme selector ends -->

				<!-- user dropdown starts -->
				<div class="btn-group pull-right">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone"> admin</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="AdminDetail.jsp">个人信息</a></li>
						<li class="divider"></li>
						<li><a href="AdminLogin.jsp">退出</a></li>
					</ul>
				</div>
				<!-- user dropdown ends -->

				<div class="top-nav nav-collapse">
					<ul class="nav">

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<!-- topbar ends -->
	<div class="container-fluid">
		<div class="row-fluid">

			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">Main</li>
						<li><a class="ajax-link"
							href="<%=path%>/adminIndexAction/adminIndexAction_adminIndex.do"><i
								class="icon-home"></i><span class="hidden-tablet">首页</a></li>
						<li><a class="ajax-link"
							href="<%=path%>/adminUserTable/adminUserTableAction_userTable.do"><i
								class="icon-eye-open"></i><span class="hidden-tablet">用户管理</span></a></li>
						<li><a class="ajax-link"
							href="<%=path%>/adminGoodsTable/adminGoodsTableAction_goodsTable.do"><i
								class="icon-edit"></i><span class="hidden-tablet">商品管理</span></a></li>
					</ul>

				</div>
			</div>

			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>

			<div id="content" class="span10">
				<!-- content starts -->


				<div>
					<ul class="breadcrumb">
						<li><a href="#">主页</a> <span class="divider">/</span></li>
						<li><a href="#">商品管理</a></li>
					</ul>
				</div>

				<div class="row-fluid sortable">
					<div class="box span12">
						

						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-user"></i>商品名称<s:property value="goods.gname"/>
							</h2>
						</div>
						<div class="box-content">


							<table border=1 sellpacing=0 cellpacing=0>
								<tr>
									<th></th>
									<th>评论</th>
									<th>评论者</th>
									<th>评论时间</th>
									<th></th>
								</tr>
								<br>
								<s:if test="#session.session_isLogined=='true'">

									<s:iterator value="gcomments" status="status" var="x">
								
											<tr>

												<td><s:property value="#status.count" />
												<td><s:property value="#x.gcMsg" /></td>
												<td><s:property value="#x.uid" /></td>
												<td><s:property value="#x.gcTime" /></td>
												
												<td style='width:8em' class="center">&nbsp;&nbsp; </a>
													&nbsp;&nbsp;<a class="btn btn-danger"
													href="<%=path%>/adminGoodsDetail/adminGoodsDetailAction_deleteGComment.do?gid=<s:property value="x.goods.gid"/>&gcId=<s:property value="#x.gcId"/>">
														<i class="icon-trash icon-white"></i> 删除
												</a></td>
											</tr>
									</s:iterator>

								</s:if>
							</table>

						</div>
					</div>
				</div>


				<hr>

				<div class="modal hide fade" id="myModal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Settings</h3>
					</div>
					<div class="modal-body">
						<p>Here settings can be configured...</p>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn" data-dismiss="modal">Close</a> <a href="#"
							class="btn btn-primary">Save changes</a>
					</div>
				</div>

				<footer> </footer>

			</div>
			<!--/.fluid-container-->

			<!-- external javascript
	================================================== -->
			<!-- Placed at the end of the document so the pages load faster -->

			<!-- jQuery -->
			<script src="js/jquery-1.7.2.min.js"></script>
			<!-- jQuery UI -->
			<script src="js/jquery-ui-1.8.21.custom.min.js"></script>
			<!-- transition / effect library -->
			<script src="js/bootstrap-transition.js"></script>
			<!-- alert enhancer library -->
			<script src="js/bootstrap-alert.js"></script>
			<!-- modal / dialog library -->
			<script src="js/bootstrap-modal.js"></script>
			<!-- custom dropdown library -->
			<script src="js/bootstrap-dropdown.js"></script>
			<!-- scrolspy library -->
			<script src="js/bootstrap-scrollspy.js"></script>
			<!-- library for creating tabs -->
			<script src="js/bootstrap-tab.js"></script>
			<!-- library for advanced tooltip -->
			<script src="js/bootstrap-tooltip.js"></script>
			<!-- popover effect library -->
			<script src="js/bootstrap-popover.js"></script>
			<!-- button enhancer library -->
			<script src="js/bootstrap-button.js"></script>
			<!-- accordion library (optional, not used in demo) -->
			<script src="js/bootstrap-collapse.js"></script>
			<!-- carousel slideshow library (optional, not used in demo) -->
			<script src="js/bootstrap-carousel.js"></script>
			<!-- autocomplete library -->
			<script src="js/bootstrap-typeahead.js"></script>
			<!-- tour library -->
			<script src="js/bootstrap-tour.js"></script>
			<!-- library for cookie management -->
			<script src="js/jquery.cookie.js"></script>
			<!-- calander plugin -->
			<script src='js/fullcalendar.min.js'></script>
			<!-- data table plugin -->
			<script src='js/jquery.dataTables.min.js'></script>

			<!-- chart libraries start -->
			<script src="js/excanvas.js"></script>
			<script src="js/jquery.flot.min.js"></script>
			<script src="js/jquery.flot.pie.min.js"></script>
			<script src="js/jquery.flot.stack.js"></script>
			<script src="js/jquery.flot.resize.min.js"></script>
			<!-- chart libraries end -->

			<!-- select or dropdown enhancer -->
			<script src="js/jquery.chosen.min.js"></script>
			<!-- checkbox, radio, and file input styler -->
			<script src="js/jquery.uniform.min.js"></script>
			<!-- plugin for gallery image view -->
			<script src="js/jquery.colorbox.min.js"></script>
			<!-- rich text editor library -->
			<script src="js/jquery.cleditor.min.js"></script>
			<!-- notification plugin -->
			<script src="js/jquery.noty.js"></script>
			<!-- file manager library -->
			<script src="js/jquery.elfinder.min.js"></script>
			<!-- star rating plugin -->
			<script src="js/jquery.raty.min.js"></script>
			<!-- for iOS style toggle switch -->
			<script src="js/jquery.iphone.toggle.js"></script>
			<!-- autogrowing textarea plugin -->
			<script src="js/jquery.autogrow-textarea.js"></script>
			<!-- multiple file upload plugin -->
			<script src="js/jquery.uploadify-3.1.min.js"></script>
			<!-- history.js for cross-browser state change on ajax -->
			<script src="js/jquery.history.js"></script>
			<!-- application script for Charisma demo -->
			<script src="js/charisma.js"></script>
</body>
</html>
