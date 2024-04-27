<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="dao.AllDao"%>
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
			<h6 class="m-0 font-weight-bold text-primary">All Issues Raised
				By Members</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>Sr.No</th>
							<th>Issue</th>
							<th>Description</th>
							<th>MemeberId</th>
							<th>Member Name</th>
							<th>Date</th>
							<th>Status</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr.No</th>
							<th>Issue</th>
							<th>Description</th>
							<th>MemeberId</th>
							<th>Member Name</th>
							<th>Date</th>
							<th>Status</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>

						<%
							try {
								int i = 1, j = 1;
								AllDao allDao = new AllDao();
								ResultSet rs = allDao.getAllOpenReportedIssues();
								while (rs.next()) {

									String reportDate = rs.getString(5);
									String reportStatus = rs.getString(6);
						%>

						<tr>
							<td><%=i%></td>
							<td id="rHeading_row<%=i%>"><%=rs.getString(9)%></td>
							<td id="rDesc_row<%=i%>"><%=rs.getString(3)%></td>
							<td><%=rs.getString(4)%></td>
							<td id="rDesc_row<%=i%>"><%=rs.getString(10)%></td>
							<td><%=reportDate%></td>
							<td>
								<%
									if (reportStatus.equals("closed")) {
								%> <span class="badge badge-success">Closed </span> <%
								 	} else if (reportStatus.equals("open")) {
								 %><span class="badge badge-warning">Open </span> <%
								 	}
								 %>
							</td>
							<td><a class="btn btn-warning btn-circle btn-sm"
								id="btn_save<%=i%>"
								href="memberProfile.jsp?memberId=<%=rs.getString(4)%>"
								title="View Profile"> <i class="fa fa-user"
									aria-hidden="true"></i>

							</a></td>
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
	</div>

	<!-- /.container-fluid -->
	<jsp:include page="footer.jsp" />
</body>
</html>