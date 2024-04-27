<%@page import="dao.ProfileDao"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- favicon -->
<link rel="shortcut icon" href="assets/images/favicon1.png">
<!-- favicon -->
</head>
<body>

	<jsp:include page="header.jsp" />
	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">Tables</h1>
	<div class="row">

		<!-- DataTales Example -->
		<div class="card shadow mb-5 ml-5 mr-5" style="width: 40%">
			<div
				class="d-sm-flex align-items-center justify-content-between mb-4">
				<h1 class="h3 mb-0 text-gray-800"></h1>
			</div>
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Statistics Table</h6>
			</div>
			<div class="card-body">
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Select
						User</label> <select class="form-control" id="userClass"
						onchange="getUserData()">
						<option>Select Option</option>
						<option value="agency">Agency</option>
						<option value="user">User</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="" class="form-label" id="userSelectId">Agencies/Users</label>

					<select name="country" class="form-control" id="country">
						<option value="">Please select</option>
					</select>

				</div>
				<div class="text-right">
					<button class="btn btn-primary" onclick="getAllDataAsPerUser()">Submit</button>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->


		<!-- DataTales Example -->
		<div class="card shadow mb-5 ml-5 mr-5" style="width: 40%">
			<div
				class="d-sm-flex align-items-center justify-content-between mb-4">
				<h1 class="h3 mb-0 text-gray-800"></h1>
			</div>
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Statistics Table</h6>
			</div>
			<div class="card-body">
				<table class="table table-bordered" id="statisticsDataTable"
					class="statisticsDataTable">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody id="tableBody">

					</tbody>

				</table>
			</div>
		</div>
	</div>


	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
		function getUserData() {
			var a = document.getElementById("userClass").value;

			var task = "getAllUsersByUser";
			$.ajax({
				type : "POST",
				url : "Statistics",
				data : {
					user : a,
					task : task
				},
				success : function(data) {
					showValuesofMap(data, data.user);
				}
			});
			return false;
		}

		function showValuesofMap(data, user) {
			var x = document.getElementById("country");
			x.innerHTML = "";
			var replaceData = data.replace(/{/g, "");
			var replaceData2 = replaceData.replace(/}/g, "");
			var dataLength = replaceData2.split(",").length;
			var match = replaceData2.split(', ');
			for ( var a in match) {
				var variable = match[a];
				var value1 = variable.split('=')[0];
				var value2 = variable.split('=')[1];
				option = document.createElement('option');
				option.value = value1;
				option.text = value2;
				x.add(option);
			}
		}

		function getAllDataAsPerUser() {
			var x = document.getElementById("country").value;
			var y = document.getElementById("userClass").value;
			var table = document.getElementById("statisticsDataTable");

			//Start-Remove all the data in table body
			var Parent = document.getElementById("tableBody");
			while (Parent.hasChildNodes()) {
				Parent.removeChild(Parent.firstChild);
			}
			//End-Remove all the data in table body

			var task = "getAllDataAsPerUser";
			$.ajax({
				type : "POST",
				url : "Statistics",
				data : {
					user : y,
					id : x,
					task : task
				},
				success : function(data) {
					showValuesofMapOfUser(data);
				}
			});
			return false;
		}

		function showValuesofMapOfUser(data) {
			var replaceData = data.replace(/{/g, "");
			var replaceData2 = replaceData.replace(/}/g, "");
			var dataLength = replaceData2.split(",").length;
			var match = replaceData2.split(', ');

			var table = document.getElementById("statisticsDataTable")
					.getElementsByTagName('tbody')[0];
			for ( var a in match) {
				var variable = match[a];
				var value1 = variable.split('=')[0];
				var value2 = variable.split('=')[1];

				var row = table.insertRow(0);
				row.setAttribute("id", "firstId");
				var cell1 = row.insertCell(0).innerHTML = value1;
				var cell2 = row.insertCell(1).innerHTML = value2;
				var cell3 = row.insertCell(2).innerHTML = "<a id='btnEdit' onclick='renderAsPerId()' class='form-control btn btn-primary btn-sm'>Info</a>";
			}
		}

		function renderAsPerId() {
			var id = document.getElementById("statisticsDataTable").rows[1].cells
					.item(0).innerHTML;
			var a = isNumber(id);
			if (a === true) {
				window.location.href = "statisticsAgentInfo.jsp?id=" + id + "";
			} else {
				window.location.href = "statisticsUserInfo.jsp?id=" + id + "";
			}

		}
		function isNumber(n) {
			return /^-?[\d.]+(?:e-?\d+)?$/.test(n);
		}
	</script>
</body>
</html>