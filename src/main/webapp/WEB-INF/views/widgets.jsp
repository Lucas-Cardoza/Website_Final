<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/layouts/include.jsp"%>
<%@ include file="/WEB-INF/layouts/head.jsp"%>

<body id="demo-body">
	<style>
		body {
			background-image: url("https://static9.depositphotos.com/1625039/1110/i/950/depositphotos_11105895-stock-photo-neighborhood.jpg");
			background-repeat: no-repeat;
			background-size: cover;
			background-attachment: fixed;
		}

		#table1 {
			background-color: rgba(255, 255, 255, 0.9);
			color: black;
		}

		#button #editBtn {
			display: none;
		}

		#button:hover #edit {
			display: block;
		}
	</style>
	<div id="demo-main-div" class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1>${title}</h1>

				<%-- User Message --%>
				<%@ include file="/WEB-INF/layouts/message.jsp"%>

				<%-- Add Address Button --%>
				<div class="row mt-2">
					<div class="col-3">
						<!-- The button to add a widget -->
						<a type="button" class="btn btn-info m-3"
							href="<%=request.getContextPath()%>/widgets/addEditWidget"><span><i
									class="fas fa-plus"></i></span> Address</a>
					</div>

					<%-- Bootstrap Table to hold widgets --%>
					<table id="table1" class='table table-bordered table-striped table-hover'>
						<tr>
							<th>Action</th>
							<th>ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Street</th>
							<th>City</th>
							<th>State</th>
							<th>Zip Code</th>
						</tr>

						<%-- Loop through the database and fill in the table --%>
		
						<c:if test="${empty widgetList}">
							<tr>
								<td colspan="3">No data found</td>
							<tr>
						</c:if>
						<c:if test="${not empty widgetList}">
							<c:forEach var="address" items="${widgetList}">
								<tr>
									<th><a type="button" id="editBtn" class="btn btn-primary"
											href="<%=request.getContextPath() %>/widgets/addEditWidget?id=${address.id}">
											<i id="edit_address_${address.id}" class="fas fa-pencil-alt"></i>
										</a>
										<a type="button" id="deleteBtn" class="btn btn-danger"
											href="<%=request.getContextPath() %>/widgets/deleteWidget?id=${address.id}">
											<i id="delete_address_${address.id}" class="fas fa-times"></i>
										</a></th>
									<td>
										${address.id}
									</td>
									<td>
										${address.firstName}
									</td>
									<td>
										${address.lastName}
									</td>
									<td>
										${address.street}
									</td>
									<td>
										${address.city}
									</td>
									<td>
										${address.state}
									</td>
									<td>
										${address.zip}
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>