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
							<th>UserId</th>
							<th>Name</th>
							<th>Country</th>
							<th>Description</th>
							<th>Diamonds</th>
							<th>Coins</th>
							<th>Followers</th>
							<th>Following</th>
							<th>Status</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr.No</th>
							<th>Profile Pic</th>
							<th>UserId</th>
							<th>Name</th>
							<th>Country</th>
							<th>Description</th>
							<th>Diamonds</th>
							<th>Coins</th>
							<th>Followers</th>
							<th>Following</th>
							<th>Status</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>

						<%
						try {
							int i = 1, j = 1;
							ProfileDao profileDao = new ProfileDao();
							ResultSet rs = profileDao.getAllProfileDetails();
							while (rs.next()) {
								String blockId = rs.getString(1);
								String blockStatus = rs.getString(12);
						%>
						<tr>
							<td><%=i%></td>
							<td id="proPic_row<%=i%>"><img
								class="img-profile rounded-circle" src="img/undraw_profile.svg"></td>
							<td><%=rs.getString(2)%></td>
							<td id="name_row<%=i%>"><%=rs.getString(3)%></td>
							<td id="country_row<%=i%>"><%=rs.getString(4)%></td>
							<td id="desc_row<%=i%>"><%=rs.getString(5)%></td>
							<td id="diamonds_row<%=i%>"><%=rs.getString(6)%></td>
							<td class="text-center"><p
									class="pCheckCoin<%=i%> text-danger"></p>
								<button
									class="form-control btn btn-primary btn-sm checkCoinBtn<%=i%>"
									onclick="checkCoins('<%=rs.getString(2)%>',<%=i%>)">Check</button></td>
							<td id="followers_row<%=i%>"><%=rs.getString(7)%></td>
							<td id="following_row<%=i%>"><%=rs.getString(8)%></td>
							<%
							if (blockStatus.equals("tempBlock")) {
							%>
							<td style="background-color: orange;" class="text-white"><%="Temp Block"%></td>
							<%
							}
							%>

							<%
							if (blockStatus.equals("open")) {
							%>
							<td style="background-color: green;" class="text-white"><%="Open"%></td>
							<%
							}
							%>

							<%
							if (blockStatus.equals("perBlock")) {
							%>
							<td style="background-color: red;" class="text-white"><%="Permanant Block"%></td>
							<%
							}
							%>

							<td style="display: none;" id="editId<%=i%>"><%=rs.getString(1)%></td>
							<td><a class="btn btn-danger btn-circle btn-sm delete"
								title="delete" onclick="delete_row(<%=i%>)"
								id="btn_delete<%=i%>"> <i class="fas fa-trash"></i>
							</a> <a class="btn btn-primary btn-circle btn-sm" id="btn_edit<%=i%>"
								title="edit" onclick="edit_row(<%=i%>)"> <i
									class="fas fa-edit"></i>
							</a> <a class="btn btn-success btn-circle btn-sm" title="save"
								style="display: none;" id="btn_save<%=i%>"
								onclick="save_row(<%=i%>)"> <i class="fas fa-trash"></i>
							</a> <a class="btn btn-warning btn-circle btn-sm"
								title="Member Profile"
								href="memberProfile.jsp?memberId=<%=rs.getString(2)%>"> <i
									class="fa fa-user" aria-hidden="true"></i>

							</a> <%
 if (blockStatus.equals("tempBlock")) {
 %> <a class="btn btn-danger btn-circle btn-sm" id="btn_block<%=i%>"
								title="block" data-toggle="modal" data-target="#blockModal"
								onclick="getBlockId(<%=blockId%>)"> <i class="fas fa-ban"
									aria-hidden="true"></i>

							</a> <%
 }
 %> <%
 if (blockStatus.equals("open")) {
 %> <a class="btn btn-danger btn-circle btn-sm" id="btn_block<%=i%>"
								title="block" data-toggle="modal" data-target="#blockModal"
								onclick="getBlockId(<%=blockId%>)"> <i class="fas fa-ban"
									aria-hidden="true"></i>

							</a> <%
 }
 %> <%
 if (blockStatus.equals("perBlock")) {
 %> <a class="btn btn-success btn-circle btn-sm" id="btn_unBlock<%=i%>"
								title="Unblock" onclick="unBlockUser(<%=blockId%>)"> <i
									class="fas fa-ban" aria-hidden="true"></i>

							</a> <%
 }
 %></td>
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

	<!-- Modal blockModal -->
	<div class="modal fade" id="blockModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Block User</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<label class="hide" id="blockUserId"></label>

					<div class="text-center" id="">
						<h4 class="text-danger">
							<label id="blockUserName"></label> Select time.
						</h4>
					</div>

					<form>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio"
								name="inlineRadioOptions" id="inlineRadio1" value="12">
							<label class="form-check-label" for="inlineRadio1">12hrs</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio"
								name="inlineRadioOptions" id="inlineRadio2" value="24">
							<label class="form-check-label" for="inlineRadio2">24hrs</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio"
								name="inlineRadioOptions" id="inlineRadio3" value="36">
							<label class="form-check-label" for="inlineRadio3">36hrs</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio"
								name="inlineRadioOptions" id="inlineRadio3" value="48">
							<label class="form-check-label" for="inlineRadio3">48hrs</label>
						</div>

						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio"
								name="inlineRadioOptions" id="inlineRadio3" value="99">
							<label class="form-check-label" for="inlineRadio3">Permanent</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="blockUser()">Save
						changes</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal blockModal -->


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
	
	function getBlockId(a){
		document.getElementById("blockUserId").innerHTML=a;
	}
	
	function blockUser(){
		var result = confirm("want to Block?");
		 var blockTime=document.querySelector('input[name="inlineRadioOptions"]:checked').value;
		 var id=document.getElementById("blockUserId").innerHTML;
		var task = "block"
		if (result) {
			$.ajax({
				type : "POST",
				url : "Profile",
				data : {
					id : id,
					blockTime: blockTime,
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
	
	function blockUser(){
		var result = confirm("want to Block?");
		 var blockTime=document.querySelector('input[name="inlineRadioOptions"]:checked').value;
		 var id=document.getElementById("blockUserId").innerHTML;
		var task = "block"
		if (result) {
			$.ajax({
				type : "POST",
				url : "Profile",
				data : {
					id : id,
					blockTime: blockTime,
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
	
	function unBlockUser(e){
		var result = confirm("want to UnBlock?");
		 var id=e;
		var task = "unBlock"
		if (result) {
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
		}else{
			window.location.replace("404.html");
		}
	}
	
	function checkCoins(a,b){
		 var id=a;
		var task = "getTheCoinsByMember";
			$.ajax({
				type : "POST",
				url : "Coins",
				data : {id : id,task : task},
			    success:function(data)
			        {
			    	$(".checkCoinBtn"+b).hide();
			    	$(".pCheckCoin"+b).html("<b>"+data+"</b>");
			        } 
			});
			return false;
	
	}
	</script>
</body>
</html>