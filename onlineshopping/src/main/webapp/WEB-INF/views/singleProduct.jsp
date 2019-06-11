<div class="container">
	<!-- Breadcrumb component -->
	<div class="row">
		<!-- to display the product details -->
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home / </a></li>
				<li><a href="${contextRoot}/show/all/products">Products / </a></li>
				<li class="active">${product.name}</li>
			</ol>
		</div>
	</div>
	<div class="row">
		<!-- to display the product image -->
		<div class="col-xs-12 col-sm-6">
			<div class="thumbnail">
			    <img src="${images}/${product.code}.jpg" class="img img-responsive"/>
			</div>
		</div>
		<!-- to display the product description -->
		<div class="col-xs-12 col-sm-6">
			<h3>${product.name}</h3>
			<hr/>
			<p>${product.description}</p>
			<hr/>
			<h4>Price: <strong>&#8377; ${product.unitPrice} /-</strong></h4>
			<hr/>
			<c:choose>
			     <c:when test="${product.quantity < 1}">
		             <h6>Quantity Available: <span style="color: red">Product is Out of Stock!</span></h6>
			         <a href="javascript:void(0)" class="btn btn-success disabled"><strike>
						<span class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</strike>
					</a>
			     </c:when>
			     <c:otherwise>
		            <h6>Quantity Available: ${product.quantity}</h6>
					<a href="${contextRoot}/cart/add/${product.id}/product"	class="btn btn-success">
						<span class="glyphicon glyphicon-shopping-cart"></span>Add to Cart
					</a>
				</c:otherwise>
			</c:choose>
			<a href="${contextRoot}/show/all/products" class="btn btn-success">Back</a>
		</div>
	</div>
</div>