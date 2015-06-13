<%@ page language="java"
	import="java.util.*,com.online.market.model.Admin,com.online.market.model.User,com.online.market.model.AdminMsg"
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

<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link href="<%=path%>/css/bootstrap-cerulean.css" rel="stylesheet">
<link href="<%=path%>/css/bootstrap-responsive.css" rel="stylesheet">
<link href="<%=path%>/css/charisma-app.css" rel="stylesheet">
<link href="<%=path%>/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
<link href='<%=path%>/css/fullcalendar.css' rel='stylesheet'>
<link href='<%=path%>/css/fullcalendar.print.css' rel='stylesheet'
	media='print'>
<link href='<%=path%>/css/chosen.css' rel='stylesheet'>
<link href='<%=path%>/css/uniform.default.css' rel='stylesheet'>
<link href='<%=path%>/css/colorbox.css' rel='stylesheet'>
<link href='<%=path%>/css/jquery.cleditor.css' rel='stylesheet'>
<link href='<%=path%>/css/jquery.noty.css' rel='stylesheet'>
<link href='<%=path%>/css/noty_theme_default.css' rel='stylesheet'>
<link href='<%=path%>/css/elfinder.min.css' rel='stylesheet'>
<link href='<%=path%>/css/elfinder.theme.css' rel='stylesheet'>
<link href='<%=path%>/css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='<%=path%>/css/opa-icons.css' rel='stylesheet'>
<link href='<%=path%>/css/uploadify.css' rel='stylesheet'>


<link rel="shortcut icon" href="<%=path%>/img/favicon.ico">

</head>

<body>
	<!-- topbar starts -->
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
						<i class="icon-user"></i><span class="hidden-phone">admin</span> <span
						class="caret"></span>
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
						<li><a href="#">用户管理</a></li>
					</ul>
				</div>

				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-user"></i>所有消息
							</h2>
						</div>
						<div class="box-content">

							<tr>
								<th></th>
								<th>用户</th>
								<th>信息</th>

							</tr>
							<br>


						</div>

						<%
							List<AdminMsg> adminMsgs = (List<AdminMsg>) request
									.getAttribute("adminMsgs");
							for (AdminMsg adminMsg : adminMsgs) {

								out.println("<td>" + adminMsg.getUser().getUname() + "</td>");
								out.println("<td>" + adminMsg.getAdMsg() + "</td>"); 
								out.println("<br/>");
							}
						%>





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
								<a href="#" class="btn" data-dismiss="modal">Close</a> <a
									href="#" class="btn btn-primary">Save changes</a>
							</div>
						</div>

						<footer> </footer>

					</div>
					<!--/.fluid-container-->

					<!-- external javascript
	================================================== -->
					<!-- Placed at the end of the document so the pages load faster -->

					<!-- jQuery -->
					<script src="<%=path%>/js/jquery-1.7.2.min.js"></script>
					<!-- jQuery UI -->
					<script src="<%=path%>/js/jquery-ui-1.8.21.custom.min.js"></script>
					<!-- transition / effect library -->
					<script src="<%=path%>/js/bootstrap-transition.js"></script>
					<!-- alert enhancer library -->
					<script src="<%=path%>/js/bootstrap-alert.js"></script>
					<!-- modal / dialog library -->
					<script src="<%=path%>/js/bootstrap-modal.js"></script>
					<!-- custom dropdown library -->
					<script src="<%=path%>/js/bootstrap-dropdown.js"></script>
					<!-- scrolspy library -->
					<script src="<%=path%>/js/bootstrap-scrollspy.js"></script>
					<!-- library for creating tabs -->
					<script src="<%=path%>/js/bootstrap-tab.js"></script>
					<!-- library for advanced tooltip -->
					<script src="<%=path%>/js/bootstrap-tooltip.js"></script>
					<!-- popover effect library -->
					<script src="<%=path%>/js/bootstrap-popover.js"></script>
					<!-- button enhancer library -->
					<script src="<%=path%>/js/bootstrap-button.js"></script>
					<!-- accordion library (optional, not used in demo) -->
					<script src="<%=path%>/js/bootstrap-collapse.js"></script>
					<!-- carousel slideshow library (optional, not used in demo) -->
					<script src="<%=path%>/js/bootstrap-carousel.js"></script>
					<!-- autocomplete library -->
					<script src="<%=path%>/js/bootstrap-typeahead.js"></script>
					<!-- tour library -->
					<script src="<%=path%>/js/bootstrap-tour.js"></script>
					<!-- library for cookie management -->
					<script src="<%=path%>/js/jquery.cookie.js"></script>
					<!-- calander plugin -->
					<script src='<%=path%>/js/fullcalendar.min.js'></script>
					<!-- data table plugin -->
					<script src='<%=path%>/js/jquery.dataTables.min.js'></script>

					<!-- chart libraries start -->
					<script src="<%=path%>/js/excanvas.js"></script>
					<script src="<%=path%>/js/jquery.flot.min.js"></script>
					<script src="<%=path%>/js/jquery.flot.pie.min.js"></script>
					<script src="<%=path%>/js/jquery.flot.stack.js"></script>
					<script src="<%=path%>/js/jquery.flot.resize.min.js"></script>
					<!-- chart libraries end -->

					<!-- select or dropdown enhancer -->
					<script src="<%=path%>/js/jquery.chosen.min.js"></script>
					<!-- checkbox, radio, and file input styler -->
					<script src="<%=path%>/js/jquery.uniform.min.js"></script>
					<!-- plugin for gallery image view -->
					<script src="<%=path%>/js/jquery.colorbox.min.js"></script>
					<!-- rich text editor library -->
					<script src="<%=path%>/js/jquery.cleditor.min.js"></script>
					<!-- notification plugin -->
					<script src="<%=path%>/js/jquery.noty.js"></script>
					<!-- file manager library -->
					<script src="<%=path%>/js/jquery.elfinder.min.js"></script>
					<!-- star rating plugin -->
					<script src="<%=path%>/js/jquery.raty.min.js"></script>
					<!-- for iOS style toggle switch -->
					<script src="<%=path%>/js/jquery.iphone.toggle.js"></script>
					<!-- autogrowing textarea plugin -->
					<script src="<%=path%>/js/jquery.autogrow-textarea.js"></script>
					<!-- multiple file upload plugin -->
					<script src="<%=path%>/js/jquery.uploadify-3.1.min.js"></script>
					<!-- history.js for cross-browser state change on ajax -->
					<script src="<%=path%>/js/jquery.history.js"></script>
					<!-- application script for Charisma demo -->
					<script src="<%=path%>/js/charisma.js"></script>
</body>
</html>
