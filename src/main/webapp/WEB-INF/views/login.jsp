<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<meta charset="UTF-8">
<!--  style>
body {
	background: #9CECFB;
	/* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #0052D4, #65C7F7, #9CECFB);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #0052D4, #65C7F7, #9CECFB);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
</style-->
<%@include file="./shared/homeprop.jsp"%>
<%@include file="./shared/homecss.jsp" %>
<title>20 Minute Genius | Login</title>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<a class="navbar-brand" href="#myPage"
				style="padding: 15px; font-size: 15px;"> <!-- <img src="images/logo.jpg" alt="20 Minute Genius"> -->20
				Minute Genius
			</a>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/educational/">Home</a></li>
					<li><a href="/educational/login">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div id="loginbox" style="margin-top: 200px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">

					<div class="panel-title">Log In</div>
					<!-- div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="#">Forgot password?</a>
					</div-->
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<c:if test="${!empty error}">
						<div id="login-alert" class="alert alert-danger col-sm-12">
							${error}</div>

					</c:if>
					<c:if test="${!empty msg}">
						<div id="logout-alert" class="alert alert-success col-sm-12">
							${msg}</div>

					</c:if>

					<form id="loginform" class="form-horizontal" role="form"
						method="post" action="/educational/login/">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="text" class="form-control" name="username" value=""
								placeholder="username">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								type="password" class="form-control" name="password"
								placeholder="password">
						</div>



						<div class="input-group">
							<div class="checkbox">
								<label> <input id="login-remember" type="checkbox"
									name="remember" value="1"> Remember me
								</label>
							</div>
						</div>


						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls" align="center">
								<input type="submit" value="Login" id="btn-login"
									class="btn btn-success" />
							</div>
						</div>


						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Don't have an account! <a href="/educational/register"
										onClick="$('#loginbox').hide(); $('#signupbox').show()">
										Register Here </a>
								</div>
							</div>
						</div>
					</form>



				</div>
			</div>
		</div>
	</div>
	<div>
		<%@include file="./shared/contact.jsp"%>
	</div>

</body>
</html>