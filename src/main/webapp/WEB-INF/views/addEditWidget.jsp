<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/layouts/include.jsp" %>
<%@ include file="/WEB-INF/layouts/head.jsp" %>

<body id="demo-body">
	<style>
body {
	background-image:
		url("https://images.pexels.com/photos/2224665/pexels-photo-2224665.jpeg?cs=srgb&dl=aerial-view-of-buildings-2224665.jpg&fm=jpg");
	background-repeat: no-repeat;
	background-size: cover;
	background-attachment: fixed;
}

h1 {
	color: rgb(255, 255, 255);
}

#widgetForm.form-group {
	padding-left: 0px !important;
}

#waitMessage {
	color: rgb(255, 255, 255);
}
</style>
	<div id="demo-main-div" class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1>${title}</h1>
				<%@ include file="/WEB-INF/layouts/message.jsp" %>

				<%-- 
					The Add/Edit Widget Form
					We are using ${pageContext.request.contextPath} to reference the context path.
					The order of parsing does not allow us to use <%=request.getContextPath() %>
				--%>

				<form:form method="post" id="widgetForm" modelAttribute="Address"
					action="${pageContext.request.contextPath}/widgets/addEditWidget">

					<div class="form-group col-sm-3">
						<form:hidden class="form-control" path="id" />
						<div class="form-group">
							<form:input type="text" class="form-control" path="firstName" placeholder="First Name" />
						</div>
						<div class="form-group">
							<form:input type="text" class="form-control" path="lastName" placeholder="Last Name" />
						</div>
						<div class="form-group">
							<form:input type="text" class="form-control" path="street" placeholder="Street" />
						</div>
						<div class="form-group">
							<form:input type="text" class="form-control" path="city" placeholder="City" />
						</div>
						<div class="form-group">
							<form:input type="text" class="form-control" path="state" placeholder="State" />
						</div>
						<div class="form-group">
							<form:input type="number" class="form-control" path="zip" placeholder="Zip Code" />
						</div>

						<%-- 
							The submit button. Notice that the type is "button" and not submit. We do 
							not want to submit the form when the button is clicked. Instead we want to
							validate the form (client-side), and then send the form to the server. 
						--%>

						<div>
							<div id="waitMessage" class="d-none">Please wait...</div>
							<button class="btn btn-primary m-3" type="button" id="submitBtn">
							<img id="spinnerImage" class="d-none" height="16px" src="<c:url value='/resources/img/spinner2.gif' />" /> Submit Address</button>
							<button type="reset" class="btn btn-danger m-3">Reset Form</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script>
		var btn;
		var spinnerImage;
		var waitMessage;

		// Client Side Validation
		window.addEventListener("DOMContentLoaded", (event) => {
			btn = document.getElementById("submitBtn")
			spinnerImage = document.getElementById("spinnerImage");
			waitMessage = document.getElementById("waitMessage");

			setTimeout(function () { }, 2000);

			// Get access to submit button element 
			let submitBtn = document.getElementById("submitBtn")

			// Handle/Process Submit Button Click
			submitBtn.addEventListener("click", (event) => {
				// Stop old versions of IE from processing the form 
				// even though the button type is button
				event.preventDefault();

				// Variables
				zipCode = document.getElementById("zip").value;
				form = document.getElementById("widgetForm");
				firstName = document.getElementById("firstName").value;
				lastName = document.getElementById("lastName").value;
				street = document.getElementById("street").value;
				city = document.getElementById("city").value;
				state = document.getElementById("state").value;

				
				
				//Start of validation of the inputs to form fields.
				// Using else if so the user does not get multiple alert notifications if more than one field is not filled out correctly.
				if (firstName == "") {
					alert("Please provide a first name.");
					firstName.focus();		// Using .focus() to bring the cursor to the offending form field.
				}
				else if (lastName == "") {
					alert("Please provide a last name.");
					lastName.focus();
				}
				else if (street == "") {
					alert("Please provide a street.");
					street.focus();
				}
				else if (city == "") {
					alert("Please provide a city.");
					document.getElementById("city").focus();
				}
				else if (state == "") {
					alert("Please provide a state.");
					state.focus();
				}
				else if (zipCode == "" || isNaN(document.getElementById("zip").value) || zipCode.length != 5) {
					alert("Please provide a zip code in the format #####.");
					zipCode.focus();
				}
				else {
					// Instead of using form.submit(); let's make an AJAX call using "fetch"
					//form.submit();

					try {
						// Disable the button and start the spinner
						disableButton();

						// Start building the object to send to the AJAX (fetch) request.
						let data = {};
						data.firstName = firstName;
						data.lastName = lastName;
						data.street = street;
						data.city = city;
						data.state = state;
						data.zipCode = zipCode;

						// Convert to a JSON String.
						let Widget = JSON.stringify(data);
						//console.log(dataStr);

						// Make AJAX call to the server along with the data
						fetch("<c:url value='/widgets/xAddEditWidget' />", {
							method: "POST",
							body: Widget,
							headers: {
								"Content-Type": "application/json"
							}
						}).then(function (response) {
							if (response.ok) {
								console.log("response.status=", response.status);
								return response;
							} else {
								throw new Error('Internal Error');
							}
						}).then(function (response) {
							return response.text();
						}).then(function (text) {
							enableButton();
							text = ((text == null || text == undefined || text.length == 0) ? "Sorry, Internal Error." : text);
							alert(text);
						}).catch(function (error) {
							enableButton();
							console.log('There was a problem with your fetch operation: ', error.message);
						});
					} 
					catch (err) {
						enableButton();
						alert(err);
					}
				}
			});
		});

		function disableButton() {
			// TODO Add a disable for the inputs of the form here.
			btn.disabled = true;
			spinnerImage.classList.remove("d-none");
			waitMessage.classList.remove("d-none");
			spinnerImage.classList.add("d-inline");
			waitMessage.classList.add("d-block");
		}

		function enableButton() {
			btn.disabled = false;
			spinnerImage.classList.add("d-none");
			waitMessage.classList.add("d-none");
			spinnerImage.classList.remove("d-inline");
			waitMessage.classList.remove("d-block");
		}
	</script>
</body>

</html>