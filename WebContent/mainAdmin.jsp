<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.LoginDao"%>
<%@page import="dao.AllDao"%>
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
	<%
		String adminStatus = "";
		adminStatus = (String) session.getAttribute("userAdminRight");
	
			if (adminStatus.equals("")) {
				response.sendRedirect("welcome.jsp");
			} 
	%>


	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">Tables</h1>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800"></h1>
		</div>
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">SuperAdmin Table</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>Sr.No</th>
							<th>Username</th>
							<th>Password</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr.No</th>
							<th>Username</th>
							<th>Password</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>

						<%
							try {
								int i = 1, j = 1;
								LoginDao loginDao = new LoginDao();
								ResultSet rs = loginDao.getSuperAdmin();
								while (rs.next()) {
						%>

						<tr>
							<td><%=i%></td>
							<td id="username_row<%=i%>"><%=rs.getString(2)%></td>
							<td id="password_row<%=i%>"><%=rs.getString(3)%></td>
							<td style="display: none;" id="editId<%=i%>"><%=rs.getString(1)%></td>
							<td><a class="btn btn-danger btn-circle btn-sm delete "
								onclick="delete_row(<%=i%>)" id="btn_delete<%=i%>"> <i
									class="fas fa-trash"></i>
							</a> <a class="btn btn-primary btn-circle btn-sm" id="btn_edit<%=i%>"
								onclick="edit_row(<%=i%>)"> <i class="fas fa-edit"></i>
							</a> <a class="btn btn-success btn-circle btn-sm"
								style="display: none;" id="btn_save<%=i%>"
								onclick="save_row(<%=i%>)"> <i class="fas fa-trash"></i>
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
	<script type="text/javascript">
	
	
	
	function edit_row(no)
	{
	 document.getElementById("btn_edit"+no).style.display="none";
	 document.getElementById("btn_save"+no).style.display="block";
	 document.getElementById("btn_delete"+no).style.display="none";
	 var username=document.getElementById("username_row"+no);
	 var password=document.getElementById("password_row"+no);
		
	 var username_data=username.innerHTML;
	 var password_data=password.innerHTML;
		
	 username.innerHTML="<input type='text' id='username_text"+no+"' value='"+username_data+"'>";
	 password.innerHTML="<input type='text' id='password_text"+no+"' value='"+password_data+"'>";
	}
	
	function save_row(no)
	{
	 var username_val=document.getElementById("username_text"+no).value;
	 var password_val=document.getElementById("password_text"+no).value;
	 var id=document.getElementById("editId"+no).innerHTML;

		var result = confirm("want to save?");
		var task = "updateSuperAdmin";
		if (result) {
			$.ajax({
				type : "POST",
				url : "User",
				data : {
					username : username_val,
					password:  password_val,
					id : id,
					task : task
				},
				success : function(data) {
					location.reload();
				}
			});
			return false;
		}
	 
	 document.getElementById("edit_button"+no).style.display="block";
	 document.getElementById("save_button"+no).style.display="none";
	}
	
	</script>

</body>
</html>