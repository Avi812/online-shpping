<%@include file="../shared/flows-header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Personal Details of the User</h4>
					<br/>
				</div>
				<div class="panel-body">
					<div class="text-center">
						<h4>Name: <strong>${registerModel.user.firstName} ${registerModel.user.lastName}</strong></h4>
						<h5>Email: <strong>${registerModel.user.email}</strong></h5>
						<h5>Contact Number: <strong>${registerModel.user.contactNumber}</strong></h5>
						<h5>Role: <strong>${registerModel.user.role}</strong></h5>
					</div>
				</div>
				<div class="panel-footer">
					<!-- Anchor to edit personal details -->
					<a href="${flowExecutionUrl}&_eventId_personal"	class="btn btn-primary">Edit</a>
				</div>
			</div>
		</div>
		<!-- Column to display the Address -->
		<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Billing Address</h4>
				</div>
				<div class="panel-body">
					<div class="text-center">
						<h4>${registerModel.billing.addressLineOne}</h4>
						<h4>${registerModel.billing.addressLineTwo}</h4>
						<h4>${registerModel.billing.city} - ${registerModel.billing.postalCode}</h4>
						<h4>${registerModel.billing.state} - ${registerModel.billing.country}</h4>
					</div>
				</div>
				<div class="panel-footer">
					<!-- Anchor to edit personal details -->
					<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
				</div>
			</div>
		</div>
		<!-- Confirm button after displaying the Address -->
		<div class="col-sm-4 col-sm-offset-4">
			<div class="panel panel-primary">
				<div class="text-center">
					<!-- Anchor to move to success page -->
					<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-primary">Confirm</a>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../shared/flows-footer.jsp"%>