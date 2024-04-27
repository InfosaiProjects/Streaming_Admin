<%@page import="dao.subAdminDao"%>
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
		ResultSet rs;
	%>

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">Tables</h1>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800"></h1>
			<a href="#"
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
				data-toggle="modal" data-target="#addSubAdmin"><i
				class="fas fa-plus fa-sm text-white-50"></i> Add Agencies</a>
		</div>
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Agencies Table</h6>
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
							<th>Agent Code</th>
							<th>Name Of Agencies</th>
							<th>EmailId</th>
							<th>Total Diamonds</th>
							<th>Total No of host</th>
							<th>Recharge Permission</th>
							<th>Commision Percentage</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr.No</th>
							<th>Username</th>
							<th>Password</th>
							<th>Agent Code</th>
							<th>Name Of Agencies</th>
							<th>EmailId</th>
							<th>Total Diamonds</th>
							<th>Total No of host</th>
							<th>Recharge Permission</th>
							<th>Commision Percentage</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>

						<%
							try {
								int i = 1, j = 1;
								String rechargePermission = "";
								subAdminDao subAdminDao = new subAdminDao();
								rs = subAdminDao.getAllSubAdmin();
								while (rs.next()) {
									rechargePermission = rs.getString(8);
						%>

						<tr>
							<td><%=i%></td>
							<td id="username_row<%=i%>"><%=rs.getString(2)%></td>
							<td id="password_row<%=i%>"><%=rs.getString(3)%></td>
							<td><%=rs.getString(1)%></td>
							<td id="agencies_row<%=i%>"><%=rs.getString(4)%></td>
							<td id="emailId_row<%=i%>"><%=rs.getString(5)%></td>
							<td id="userPermission_row<%=i%>"></td>
							<td id="totalActiveUsers_row<%=i%>"><%=rs.getString(7)%></td>
							<td id="rechargePermission_row<%=i%>">
								<%
									if (rechargePermission.equals("yes")) {
								%> <a href="#" class="btn btn-success btn-sm  btn-icon-split">
									<span class="icon text-white-50"> <i class="fas fa-flag"></i>
								</span> <span class="text">Yes</span>
							</a> <%
 	}
 			if (rechargePermission.equals("no")) {
 %> <a href="#" class="btn btn-danger btn-sm btn-icon-split"> <span
									class="icon text-white-50"> <i
										class="fas fa-exclamation-triangle"></i>
								</span> <span class="text">No</span>
							</a> <%
 	}
 %>
							</td>
							<td id="commisionPercentage_row<%=i%>"><%=rs.getString(9)%></td>
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

	<!-- Modal -->
	<div class="modal fade" id="addSubAdmin" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">Add Agencies</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="Coins" method="post">
						<div class="form-group row">
							<label for="username" class="col-sm-3 col-form-label">Username</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="usernameAdd"
									name="usernameAdd">
							</div>
						</div>
						<div class="form-group row">
							<label for="password" class="col-sm-3 col-form-label">Password
							</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="passwordAdd"
									name="passwordAdd">
							</div>
						</div>

						<div class="form-group row">
							<label for="agenciesAdd" class="col-sm-3 col-form-label">Name
								Of Agencies </label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="agenciesAdd"
									name="agenciesAdd">
							</div>
						</div>

						<div class="form-group row">
							<label for="emailIdAdd" class="col-sm-3 col-form-label">Email
								Id </label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="emailIdAdd"
									name="emailIdAdd">
							</div>
						</div>

						<div class="form-group row">
							<label for="userPermissionAdd" class="col-sm-3 col-form-label">Total
								Permission Of Users </label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="userPermissionAdd"
									name="userPermissionAdd">
							</div>
						</div>

						<div class="form-group row">
							<label for="totalActiveUsersAdd" class="col-sm-3 col-form-label">Total
								No of Host </label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="totalActiveUsersAdd"
									name="totalActiveUsersAdd">
							</div>
						</div>

						<div class="form-group row">
							<label for="totalNoOfHost" class="col-sm-3 col-form-label">Recharge
								Permission</label>
							<div class="col-sm-9">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="inlineRadio1" value="yes">
									<label class="form-check-label" for="inlineRadio1">Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="inlineRadio2" value="no">
									<label class="form-check-label" for="inlineRadio2">No</label>
								</div>
							</div>
						</div>

						<div class="form-group row">
							<label for="commisionPercentageAdd"
								class="col-sm-3 col-form-label">Commission Percentage </label>
							<div class="col-sm-9">
								<input type="text" class="form-control"
									id="commisionPercentageAdd" name="commisionPercentageAdd">
							</div>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						onclick="insertFunction()">Save/Add</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	
	function edit_row(no)
	{
	 document.getElementById("btn_edit"+no).style.display="none";
	 document.getElementById("btn_save"+no).style.display="block";
	 document.getElementById("btn_delete"+no).style.display="none";
	 var username=document.getElementById("username_row"+no);
	 var password=document.getElementById("password_row"+no);
	 
	 var agencies=document.getElementById("agencies_row"+no);
	 var emailId=document.getElementById("emailId_row"+no);
	 var userPermission=document.getElementById("userPermission_row"+no);
	 var totalActiveUsers=document.getElementById("totalActiveUsers_row"+no);
	 var rechargePermission=document.getElementById("rechargePermission_row"+no);
	 var commisionPercentage=document.getElementById("commisionPercentage_row"+no);
		
	 var username_data=username.innerHTML;
	 var password_data=password.innerHTML;
	 var agencies_data=agencies.innerHTML;
	 
	 
	 var emailId_data=emailId.innerHTML;
	 var userPermission_data=userPermission.innerHTML;
	 var totalActiveUsers_data=totalActiveUsers.innerHTML;
	 var rechargePermission_data=rechargePermission.innerHTML;
	 var commisionPercentage_data=commisionPercentage.innerHTML;
		
	 username.innerHTML="<input type='text' id='username_text"+no+"' value='"+username_data+"'>";
	 password.innerHTML="<input type='text' id='password_text"+no+"' value='"+password_data+"'>";
	 agencies.innerHTML="<input type='text' id='agencies_text"+no+"' value='"+agencies_data+"'>";
	 
	 emailId.innerHTML="<input type='email' id='emalId_text"+no+"' value='"+emailId_data+"'>";
	 userPermission.innerHTML="<input type='text' id='userPermission_text"+no+"' value='"+userPermission_data+"'>";
	 totalActiveUsers.innerHTML="<input type='text' id='totalActiveUsers_text"+no+"' value='"+totalActiveUsers_data+"'>";
// 	 rechargePermission.innerHTML="<input type='text' id='rechargePermission_text"+no+"' value='"+rechargePermission_data+"'>";
	rechargePermission.innerHTML="<div class='form-check form-check-inline'><input class='form-check-input' type='radio' name='inlineRadioOptionsForEdit"+no+"' value='yes'><label class='form-check-label' for='inlineRadio1'>Yes</label></div><div class='form-check form-check-inline'><input class='form-check-input' type='radio' name='inlineRadioOptionsForEdit"+no+"' value='no' checked><label class='form-check-label' for='inlineRadio2' >No</label></div>";
	 commisionPercentage.innerHTML="<input type='text' id='commisionPercentage_text"+no+"' value='"+commisionPercentage_data+"'>";
	}
	
	function save_row(no)
	{
	 var username_val=document.getElementById("username_text"+no).value;
	 var password_val=document.getElementById("password_text"+no).value;
	 var agencies_val=document.getElementById("agencies_text"+no).value;
	 var emailId_val=document.getElementById("emalId_text"+no).value;
	 var userPermission_val=document.getElementById("userPermission_text"+no).value;
	 var totalActiveUsers_val=document.getElementById("totalActiveUsers_text"+no).value;
	 var rechargePermission_val=document.querySelector("input[name='inlineRadioOptionsForEdit"+no+"']:checked").value;
	 var commisionPercentage_val=document.getElementById("commisionPercentage_text"+no).value;
	 var id=document.getElementById("editId"+no).innerHTML;

		var result = confirm("want to save?");
		var task = "update";
		if (result) {
			$.ajax({
				type : "POST",
				url : "SubAdmin",
				data : {
					username : username_val,
					password	:  password_val,
					agencies : agencies_val,
					emailId	:   emailId_val,
					userPermission : userPermission_val,
					totalActiveUsers : totalActiveUsers_val,
					rechargePermission : rechargePermission_val,
					commisionPercentage : commisionPercentage_val,
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
				 var id=document.getElementById("editId"+no).innerHTML;
				var task = "delete"
				if (result) {
					$.ajax({
						type : "POST",
						url : "SubAdmin",
						data : {
							id : id,
							task : task
						},
						success : function(data) {
							location.reload();
						}
						
					});
					return false;
				}else{
					window.location.replace("404.html");
				}
			}
	
	function insertFunction(){
		var usernameAdd=document.getElementById("usernameAdd").value;
		 var passwordAdd=document.getElementById("passwordAdd").value;
		 var agenciesAdd=document.getElementById("agenciesAdd").value;
		 var emailIdAdd=document.getElementById("emailIdAdd").value;
		 var userPermissionAdd=document.getElementById("userPermissionAdd").value;
		 var totalActiveUsersAdd=document.getElementById("totalActiveUsersAdd").value;
		 var rechargePermissionAdd=document.querySelector('input[name="inlineRadioOptions"]:checked').value;
		 var commisionPercentageAdd=document.getElementById("commisionPercentageAdd").value;
		 
		var task = "add";
			$.ajax({
				type : "POST",
				url : "SubAdmin",
				data : {
					username : usernameAdd,
					password : passwordAdd,
					agencies : agenciesAdd,
					emailId  : emailIdAdd,
					userPermission : userPermissionAdd,
					totalActiveUsers : totalActiveUsersAdd,
					rechargePermission : rechargePermissionAdd,
					commisionPercentage : commisionPercentageAdd,
					task : task
				},
				success : function(data) {
					location.reload();
				}
				
			});
			return false;
		
	}
	
	
	</script>

</body>
</html>