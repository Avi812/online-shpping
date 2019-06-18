<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Sign Up - Address</h4>
				</div>
				<div class="panel-body">
					<sf:form class="form-horizontal" id="billingForm" method="POST" modelAttribute="billing">
						<div class="form-group">
							<label class="control-label col-md-4" for="addressLineOne">Address Line One</label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineOne" placeholder="Enter Address Line One" class="form-control" />
								<sf:errors path="addressLineOne" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="addressLineTwo">Address Line Two</label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineTwo" placeholder="Enter Address Line Two" class="form-control" />
								<sf:errors path="addressLineTwo" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="city">City</label>
							<div class="col-md-8">
								<sf:input type="text" path="city" placeholder="Enter City Name" class="form-control" />
								<sf:errors path="city" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="postalCode">Postal Code</label>
							<div class="col-md-8">
								<sf:input type="text" path="postalCode" placeholder="xxxxxxxxxx" class="form-control" />
								<sf:errors path="postalCode" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="state">State</label>
							<div class="col-md-8">
								<sf:input type="text" path="state" placeholder="Enter State Name" class="form-control" />
								<sf:errors path="state" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="country">Country</label>
							<div class="col-md-8">
								<sf:input type="text" path="country" placeholder="Enter Country Name" class="form-control" />
								<sf:errors path="country" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
							    <!-- Submit button for moving to personal -->
								<button type="submit" name="_eventId_personal" class="btn btn-primary">
								    <span class="glyphicon glyphicon-chevron-left"></span>Previous - Personal
								</button>
							    <!-- Submit button for moving to confirm -->
								<button type="submit" name="_eventId_confirm" class="btn btn-primary">
								    Next - Confirm <span class="glyphicon glyphicon-chevron-right"></span>
								</button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../shared/flows-footer.jsp"%>