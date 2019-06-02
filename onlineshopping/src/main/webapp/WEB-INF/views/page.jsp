<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="vendorcss" value="/resources/vendor/bootstrap/css" />
<spring:url var="vendorjq" value="/resources/vendor/jquery" />
<spring:url var="vendorjs" value="/resources/vendor/bootstrap/js" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
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
	<div class="wrapper">
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>
		<!-- Page Content -->
		<div class="content">
			<c:if test="${userClicksHome == true}">
				<%@include file="./shared/pageContent.jsp"%>
			</c:if>
			<!-- Loads only when user clicks About -->
			<c:if test="${userClicksAbout == true}">
				<%@include file="about.jsp"%>
			</c:if>
			<!-- Loads only when user clicks Contact -->
			<c:if test="${userClicksContact == true}">
				<%@include file="contact.jsp"%>
			</c:if>
		</div>
		<!-- Footer starts here -->
		<div class="footer" style="width: 100%">
			<%@include file="./shared/footer.jsp"%>
		</div>
		<!-- Bootstrap core JavaScript -->
		<script src="${vendorjq}/jquery.min.js"></script>
		<script src="${vendorjs}/bootstrap.bundle.min.js"></script>
		<!-- Self coded Javascript -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>
</html>