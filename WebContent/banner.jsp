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

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">Banner</h1>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800"></h1>
			<a href="#"
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
				data-toggle="modal" data-target="#addBanner"><i
				class="fas fa-plus fa-sm text-white-50"></i> Add Banner</a>
		</div>
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary"></h6>
		</div>
		<div class="card-body">
			<div id="carouselExampleCaptions" class="carousel slide"
				data-ride="carousel" style="height: 400px;">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleCaptions" data-slide-to="0"
						class="active"></li>
					<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
					<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
				</ol>

				<div class="carousel-inner" style="height: 400px;">
					<div class="carousel-item active">

						<%
						try {
							AllDao allDao = new AllDao();
							ResultSet rs = allDao.getFirstImage(1);
							if (rs.next()) {
								System.out.println(rs.getString(1));
						%>
						<img src="images/bannerImg/<%=rs.getString(3)%>"
							class="d-block w-100" style="height: 400px;" alt="...">

						<div class="carousel-caption d-none d-md-block">
							<h5><%=rs.getString(5)%></h5>
							<p><%=rs.getString(6)%></p>
						</div>

						<%
						}
						} catch (Exception ex) {
						System.out.print("ex1> " + ex);
						}
						%>

					</div>
					<div class="carousel-item">

						<%
						try {
							AllDao allDao = new AllDao();
							ResultSet rs = allDao.getFirstImage(2);
							if (rs.next()) {
								System.out.println(rs.getString(2));
						%>

						<img src="images/bannerImg/<%=rs.getString(3)%>"
							class="d-block w-100" style="height: 400px;" alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h5><%=rs.getString(5)%></h5>
							<p><%=rs.getString(6)%></p>
						</div>

						<%
						}
						} catch (Exception ex) {
						System.out.print("ex1> " + ex);
						}
						%>
					</div>
					<div class="carousel-item">
						<%
						try {
							AllDao allDao = new AllDao();
							ResultSet rs = allDao.getFirstImage(3);
							if (rs.next()) {
								System.out.println(rs.getString(3));
						%>



						<img src="images/bannerImg/<%=rs.getString(3)%>"
							class="d-block w-100" style="height: 400px;" alt="...">

						<div class="carousel-caption d-none d-md-block">
							<h5><%=rs.getString(5)%></h5>
							<p><%=rs.getString(6)%></p>
						</div>

						<%
						}
						} catch (Exception ex) {
						System.out.print("ex1> " + ex);
						}
						%>
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-target="#carouselExampleCaptions" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-target="#carouselExampleCaptions" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</button>
			</div>
		</div>
	</div>

	<!-- /.container-fluid -->


	<div class="modal fade" id="addBanner" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog  modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add Banner</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="Banner" method="post" enctype="multipart/form-data"
						id="">
						<input type="text" style="display: none;" name="task" value="add">
						<div class="form-group row">
							<label for="" class="col-sm-4 col-form-label">Select
								Banner</label>
							<div class="col-sm-8">
								<select id="bannerId" name="bannerId" class="form-control">
									<option value="1">First Banner</option>
									<option value="2">Second Banner</option>
									<option value="3">Third Banner</option>
								</select>
							</div>
						</div>
						<img id="output" width="300" />
						<div class="form-group row">
							<label for="" class="col-sm-4 col-form-label">Image</label>
							<div class="col-sm-8">
								<input type="file" name="image" id="image" required
									onchange="readURL(this)" class="form-control form-control-sm">
							</div>
							<div class="col-sm-4">

								<input type="text" name="imageBase64" id="imageBase64"
									style="display: none;">
							</div>
						</div>
						<div class="form-group row">
							<label for="" class="col-sm-4 col-form-label">Headline</label>
							<div class="col-sm-8">
								<textarea name="headline" id="headline" class="form-control" required></textarea>
							</div>
						</div>

						<div class="form-group row">
							<label for="" class="col-sm-4 col-form-label">Main
								Content</label>
							<div class="col-sm-8">
								<textarea name="mainContent" id="mainContent"
									class="form-control" required></textarea>
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save/Add</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

	<script type="text/javascript">
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					document.querySelector("#output").setAttribute("src",
							e.target.result);
				};
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>



</body>
</html>