<%@page import="dao.ProfileDao"%>
<%@page import="dao.subAdminDao"%>
<%@page import="dao.CoinsDao"%>
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
<link rel="shortcut icon" href="assets/images/favicon1.png">
<!-- favicon -->
</head>
<body>

	<jsp:include page="header.jsp" />


	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">Tables</h1>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800"></h1>

		</div>
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Search Users By
				Agencies</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<form>
					<div class="form-group row">
						<label for="staticEmail" class="col-sm-2 col-form-label">Agency
							Name</label>
						<div class="col-sm-6">
							<select class="form-control" id="adminId">
								<%
									
									subAdminDao subAdminDao = new subAdminDao();
									ResultSet rs = subAdminDao.getAllSubAdmin();
									while (rs.next()) {
								%>
								<option value="<%=rs.getObject(1)%>"><%=rs.getObject(4)%></option>
								<%
									}
								%>
							</select>
						</div>
						<button type="button" class="btn btn-primary col-sm-3"
							onclick="searchUsers()">Search</button>
					</div>
				</form>
				
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	function searchUsers(){
		var adminId= document.getElementById("adminId").value;
		window.location.href  = "listOfUsers.jsp?adminId="+adminId;
	}
	</script>

</body>
</html>