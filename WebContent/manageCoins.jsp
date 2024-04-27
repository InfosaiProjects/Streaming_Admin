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
			<button
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
				data-toggle="modal" data-target="#addCoinsPlans">
				<i class="fas fa-plus fa-sm text-white-50"></i> Add Coins Plans
			</button>
		</div>
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Coins Table</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>Sr.No</th>
							<th>Coins</th>
							<th>RS</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Sr.No</th>
							<th>Coins</th>
							<th>RS</th>
							<th style="display: none;">id</th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>

						<%
							try {
								int i = 1, j = 1;
								CoinsDao coinsDao = new CoinsDao();
								ResultSet rs = coinsDao.getAllCoins();
								while (rs.next()) {
						%>

						<tr>
							<td><%=i%></td>
							<td id="coins_row<%=i%>"><%=rs.getString(2)%></td>
							<td id="rs_row<%=i%>"><%=rs.getString(3)%></td>
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
	<div class="modal fade" id="addCoinsPlans" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">Add Coins
						Plans</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="Coins" method="post">
						<div class="form-group row">
							<label for="coins" class="col-sm-3 col-form-label">Coins</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="coinsAdd"
									name="coinsAdd">
							</div>
						</div>
						<div class="form-group row">
							<label for="rs" class="col-sm-3 col-form-label">Ruppes <i
								class="fas fa-rupee-sign"></i></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="rsAdd" name="rsAdd">
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
	 
	 var coins=document.getElementById("coins_row"+no);
	 var rs=document.getElementById("rs_row"+no);
		
	 var coins_data=coins.innerHTML;
	 var rs_data=rs.innerHTML;
		
	 coins.innerHTML="<input type='text' id='coins_text"+no+"' value='"+coins_data+"'>";
	 rs.innerHTML="<input type='text' id='rs_text"+no+"' value='"+rs_data+"'>";
	}
	
	function save_row(no)
	{
	 var coins_val=document.getElementById("coins_text"+no).value;
	 var rs_val=document.getElementById("rs_text"+no).value;
	 var id=document.getElementById("editId"+no).innerHTML;

		var result = confirm("want to save?");
		var task = "update";
		if (result) {
			$.ajax({
				type : "POST",
				url : "Coins",
				data : {
					coins : coins_val,
					rs	:  rs_val,
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
						url : "Coins",
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
		 var coinsAdd=document.getElementById("coinsAdd").value;
		 var rsAdd=document.getElementById("rsAdd").value;
		var task = "add";
			$.ajax({
				type : "POST",
				url : "Coins",
				data : {
					coinsAdd : coinsAdd,
					rsAdd : rsAdd,
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