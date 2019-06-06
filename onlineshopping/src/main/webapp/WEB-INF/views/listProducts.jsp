<div class="container">
	<div class="row">
		<!-- Div used to display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>
		<!-- Div used to display main content -->
		<div class="col-md-d">
			<!-- Added breadcrumb component -->
			<div class="col-lg-12">
				<c:if test="${userClicksAllProducts == true}">
					<ol class="breadcrumb">
						<li><a href="${contextRoot}/home">Home / </a></li>
						<li class="active">All Products</li>
					</ol>
				</c:if>
				<c:if test="${userClicksCategoryProducts == true}">
					<ol class="breadcrumb">
						<li><a href="${contextRoot}/home">Home / </a></li>
						<li class="active">Category / </li>
						<li class="active">${category.name}</li>
					</ol>
				</c:if>
			</div>
		</div>
	</div>
</div>