<%@page import="dao.ProfileDao"%>
<%@page import="dao.UserDao"%>
<%@page import="dao.subAdminDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="dao.ConnectionDao"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- favicon -->
<link rel="shortcut icon" href="/assets/images/favicon1.png">
<!-- favicon -->

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="vendor/datatables/dataTables.bootstrap4.min.css">

</head>
<body>

	<jsp:include page="header.jsp" />
	<h2>Add Coins in User</h2>
	<div class="row">

		<!-- Area Chart -->
		<div class="col-xl-12 col-lg-11">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h6 class="m-0 font-weight-bold text-primary">Add Coins</h6>
				</div>
				<!-- Card Body -->
				<div class="card-body">

					<form>
						<div class="form-group row">
							<label for="staticEmail" class="col-sm-2 col-form-label">User
								Name</label>
							<div class="col-sm-10">
								<select class="form-control" id="agencyId">
									<%
										ProfileDao profileDao=new ProfileDao();
										ResultSet rs = profileDao.getAllProfileDetails();
										while (rs.next()) {
									%>
									<option value="<%=rs.getObject(2) + "/" + rs.getObject(3)+ "/" + rs.getObject(11)+ "/" + rs.getObject(1)%>"><%=rs.getObject(3)%></option>
									<%
										}
									%>
								</select>
							</div>
						</div>

						<div class="form-group row">
							<label for="staticEmail" class="col-sm-2 col-form-label">User
								Code</label>
							<div class="col-sm-10">
								<label for="userCode" class="col-sm-2" id="userCode">
								</label>
							</div>
						</div>

						<div class="form-group row">
							<label for="staticEmail" class="col-sm-2 col-form-label">Total
								Coins</label>
							<div class="col-sm-10">
								<label for="userCode" class="col-sm-2" id="userTotalCoins">
								</label>
							</div>
						</div>


						<div class="form-group row">
							<label for="inputPassword" class="col-sm-2 col-form-label">Add
								Coins</label>

							<div class="col-sm-4">
								<div class="form-group">
									<input type="number" class="form-control" id="showRange"
										min="0" max="999999">
								</div>
							</div>

							<div class="col-sm-6">
								<div class="form-group">
									<input type="range" class="form-control-range" id="myRange"
										name="points" min="0" max="999999">
										<input type="text" class="form-control-range" style="display: none;" id="valueId"
										>
								</div>
							</div>
						</div>

						<div class="form-group">

							<div class="text-right">
								<button type="button" class="btn btn-primary"
									onclick="insertFunction()">Save/Add</button>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<script>
		var slider = document.getElementById("myRange");
		var output = document.getElementById("showRange");
		output.value = slider.value;

		slider.oninput = function() {
			output.value = this.value;

		}
		output.oninput = function() {
			slider.value = this.value;
		}

		var userCode = document.getElementById("userCode");

		agencyId.onchange = function() {
			var agencyId = document.getElementById("agencyId").value;
			var value1 = agencyId.split("/")[0];
			var value2 = agencyId.split("/")[2];
			var valueId = agencyId.split("/")[3];
			document.getElementById("valueId").value=valueId;
			userCode.innerHTML = value1;
			userTotalCoins.innerHTML = value2;
		}

		function insertFunction() {
			var userCode = document.getElementById("userCode").innerHTML;
			var showRange = document.getElementById("showRange").value;
			var valueId = document.getElementById("valueId").value;
			var task = "transferToUser";
			var result = confirm("want to transafer " + showRange
					+ " in agency account?");
			if (result) {
				$.ajax({
					type : "POST",
					url : "Profile",
					data : {
						userId : valueId,
						userCode : userCode,
						range : showRange,
						task : task
					},
					success : function(data) {
						alert("Added sucessfully")
						location.reload();
					}
				});
				return false;
			} else {
				window.location.replace("404.html");
			}
		}
	</script>

</body>
</html>