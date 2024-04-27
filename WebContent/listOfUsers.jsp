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
			<h6 class="m-0 font-weight-bold text-primary">Profile Table</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>Sr.No</th>
							<th>Profile Pic</th>
							<th>Name</th>
							<th>Country</th>
							<th>Description</th>
							<th>Diamonds</th>
							<th>Followers</th>
							<th>Following</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr.No</th>
							<th>Profile Pic</th>
							<th>Name</th>
							<th>Country</th>
							<th>Description</th>
							<th>Diamonds</th>
							<th>Followers</th>
							<th>Following</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>

						<%String adminId = request.getParameter("adminId");
						System.out.println("adminId>>>>" + adminId);
							try {
								int i = 1, j = 1;
								ProfileDao profileDao = new ProfileDao();
								ResultSet rs = profileDao.getAllProfileDetailsAsPerAgency(adminId);
								while (rs.next()) {
						%>

						<tr>
							<td><%=i%></td>
							<td id="proPic_row<%=i%>"><img
								class="img-profile rounded-circle" src="img/undraw_profile.svg"></td>
							<td id="name_row<%=i%>"><%=rs.getString(3)%></td>
							<td id="country_row<%=i%>"><%=rs.getString(4)%></td>
							<td id="desc_row<%=i%>"><%=rs.getString(5)%></td>
							<td id="diamonds_row<%=i%>"><%=rs.getString(6)%></td>
							<td id="followers_row<%=i%>"><%=rs.getString(7)%></td>
							<td id="following_row<%=i%>"><%=rs.getString(8)%></td>
							<td style="display: none;" id="editId<%=i%>"><%=rs.getString(1)%></td>
							<td><a class="btn btn-danger btn-circle btn-sm delete"
								onclick="delete_row(<%=i%>)" id="btn_delete<%=i%>"> <i
									class="fas fa-trash"></i>
							</a> <a class="btn btn-primary btn-circle btn-sm" id="btn_edit<%=i%>"
								onclick="edit_row(<%=i%>)"> <i class="fas fa-edit"></i>
							</a> <a class="btn btn-success btn-circle btn-sm"
								style="display: none;" id="btn_save<%=i%>"
								onclick="save_row(<%=i%>)"> <i class="fas fa-trash"></i>
							</a> <a class="btn btn-warning btn-circle btn-sm" id="btn_save<%=i%>"
								href="memberProfile.jsp?memberId=<%=rs.getString(2)%>"> <i
									class="fa fa-user" aria-hidden="true"></i>

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
	 
	 var proPic=document.getElementById("proPic_row"+no);
	 var name=document.getElementById("name_row"+no);
	 var country=document.getElementById("country_row"+no);
	 var desc=document.getElementById("desc_row"+no);
	 
	 var proPic_data=proPic.innerHTML;
	 var name_data=name.innerHTML;
	 var country_data=country.innerHTML;
	 var desc_data=desc.innerHTML;
	 
	 proPic.innerHTML="<input type='file' id='proPic_text"+no+"' value='"+proPic_data+"'>";
	 name.innerHTML="<input type='text' id='name_text"+no+"' value='"+name_data+"'>";
	 country.innerHTML="<input type='text' id='country_text"+no+"' value='"+country_data+"'>";
	 desc.innerHTML="<input type='text' id='desc_text"+no+"' value='"+desc_data+"'>";
	}
	
	function save_row(no)
	{
	 var proPic_val=document.getElementById("proPic_text"+no).value;
	 var name_val=document.getElementById("name_text"+no).value;
	 var country_val=document.getElementById("country_text"+no).value;
	 var desc_val=document.getElementById("desc_text"+no).value;
	 var id=document.getElementById("editId"+no).innerHTML;

		var result = confirm("want to save?");
		var task = "update";
		if (result) {
			$.ajax({
				type : "POST",
				url : "Profile",
				data : {
					proPic : proPic_val,
					name	:  name_val,
					country : country_val,
					desc	:  desc_val,
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
	
	
	function delete_row(no){
				var result = confirm("want to delete?");
				var result2=confirm("Sure!! The Profile will permanantly get deleted.");
				 var id=document.getElementById("editId"+no).innerHTML;
				var task = "delete"
				if (result) {
					if (result2) {
					$.ajax({
						type : "POST",
						url : "Profile",
						data : {
							id : id,
							task : task
						},
						success : function(data) {
							location.reload();
						}
						
					});
					return false;
				}
				}else{
					window.location.replace("404.html");
				}

			}
	
	</script>

</body>
</html>