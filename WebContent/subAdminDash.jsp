<%@page import="dao.subAdminDao"%>
<%@page import="dao.UserDao"%>
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

	<jsp:include page="subAdminHeader.jsp" />
	
<%
		int no1 = 0;
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		String subadminUsername = (String) session.getAttribute("subadminUsername");
		String subadminPassword = (String) session.getAttribute("subadminPassword");
		if (subadminPassword.equals(null) || subadminUsername.equals(null)) {
			String message = "Please Login First";
			response.sendRedirect("login.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}
	%>
		<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">User List</h1>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800"></h1>
		</div>
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Table</h6>
			
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>Sr.No</th>
							<th>MemberId</th>
							<th>Name</th>
							<th>Country</th>
							<th>Diamonds</th>
							<th>Followers</th>
							<th>Following</th>
							<th style="display: none;">id</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr.No</th>
							<th>MemberId</th>
							<th>Name</th>
							<th>Country</th>
							<th>Diamonds</th>
							<th>Followers</th>
							<th>Following</th>
							<th style="display: none;">id</th>
						</tr>
					</tfoot>
					<tbody>
					
					<%
							try {
								int i = 1, j = 1;
								subAdminDao subAdminDao = new subAdminDao();
								Object[] object=subAdminDao.getSubAdminRechargeRight(subadminUsername, subadminPassword);
								
								
								ResultSet rs = subAdminDao.getAllMembersAserSubAdmin(object[0]);
								while (rs.next()) {
						%>

						<tr>
							<td><%=i%></td>
							<td id="coins_row<%=i%>"><%=rs.getString(2)%></td>
							<td id="rs_row<%=i%>"><%=rs.getString(3)%></td>
							<td id="rs_row<%=i%>"><%=rs.getString(4)%></td>
							<td id="rs_row<%=i%>"><%=rs.getString(6)%></td>
							<td id="rs_row<%=i%>"><%=rs.getString(7)%></td>
							<td id="rs_row<%=i%>"><%=rs.getString(8)%></td>
						</tr>
						<%
							++i;
								}
							} catch (Exception e) {
								System.out.print(e);
							}
						%>
					</tbody>
				</table>
			</div>
			
			
			
			
		</div>
		<div class="card-body">
			<div class="table-responsive">
				
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>