<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>QA PRO</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="./assets/css/bootstrap.lumen.css">
<link rel="stylesheet" href="./assets/css/main.css">

<script src="./assets/js/vendor/modernizr-2.8.3.min.js"></script>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="./assets/js/vendor/jquery-1.11.2.min.js"><\/script>')
</script>

<script src="./assets/js/vendor/bootstrap.min.js"></script>

<script src="./assets/js/main.js"></script>

</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="./">QA Pro</a>
			</div>

			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">

					<c:if test="${not empty sessionScope.memberInfo}">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">Questions
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="ListQuestion">List questions</a></li>
								<li class="divider"></li>
								<li><a href="AddQuestion">Add question</a></li>
							</ul></li>
					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">

					<c:choose>
						<c:when test="${sessionScope.memberInfo eq null}">
							<li><a href="Login">Login</a></li>
							<li><a href="Register">Register</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="Logout">Sign Out</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>
	<!-- Wrap all page content here -->
	<div id="wrap">
		<!-- Begin page content -->