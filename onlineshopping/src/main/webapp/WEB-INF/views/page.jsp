<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="vendorcss" value="/resources/vendor/bootstrap/css" />
<spring:url var="vendorjq" value="/resources/vendor/jquery" />
<spring:url var="vendorjs" value="/resources/vendor/bootstrap/js" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Online Shopping - ${title}</title>
<script>
	window.menu = '${title}';
</script>
<!-- Bootstrap Readable Theme CSS -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="${vendorcss}/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<%@include file="./shared/navbar.jsp"%>
	<!-- Loading the page content -->
	<div class="content">
		<c:if test="${userClicksHome == true}">
			<%@include file="content.jsp"%>
		</c:if>
		<!-- Loads only when user clicks About -->
		<c:if test="${userClicksAbout == true}">
			<%@include file="about.jsp"%>
		</c:if>
		<!-- Loads only when user clicks All Products -->
		<c:if test="${userClicksAllProducts == true or userClicksCategoryProducts == true}">
			<%@include file="listProducts.jsp"%>
		</c:if>
		<!-- Loads only when user clicks Contact -->
		<c:if test="${userClicksContact == true}">
			<%@include file="contact.jsp"%>
		</c:if>
	</div>
	<!-- /.container -->
	<!-- Footer comes here -->
	<div style="width: 100%;position:absolute;height:60px;margin-bottom:0px;">
		<%@include file="./shared/footer.jsp"%>
	</div>
	<!-- /.container -->
	<!-- Bootstrap core JavaScript -->
	<script src="${vendorjq}/jquery.min.js"></script>
	<script src="${vendorjs}/bootstrap.bundle.min.js"></script>
	<!-- Self coded Javascript -->
	<script src="${js}/myapp.js"></script>
</body>
</html>