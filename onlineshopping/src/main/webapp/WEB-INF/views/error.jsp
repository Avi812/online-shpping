<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<spring:url var="css" value="/resources/css" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Online Shopping - ${title}</title>
<!-- Bootstrap Readable Theme CSS -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="${vendorcss}/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">
</head>
<body>
	<div class="wrapper">
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
			    <!-- Brand and toggle get grouped for mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
				</div>
			</div>
		</nav>
	</div>
	<!-- Loading the page content -->
	<div class="content">
		<div class="container">
		    <div class="row">
		        <div class="col-xs-12">
		            <div class="jumbotron">
		                <h1>${errorTitle}</h1>
		                <hr/>
		                <blockquote style="word-wrap:break-word">${errorDescription}</blockquote>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- /.container -->
	<!-- Footer comes here -->
	<div style="width: 100%; position: absolute; height: 60px; margin-bottom: 0px;">
		<%@include file="./shared/footer.jsp"%>
	</div>
</body>
</html>