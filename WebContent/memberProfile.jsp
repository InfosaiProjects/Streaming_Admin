<%@page import="dao.FeedDao"%>
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
<style type="text/css">
body {
	background-color: #ecf2f5;
	margin-top: 20px;
}

.card {
	box-shadow: 0 0 2px 0 rgb(0 0 0/ 10%);
	margin-bottom: 24px;
}

.card {
	position: relative;
	display: flex;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 0 solid #ecf2f5;
	border-radius: .25rem;
}

.avatar-lg {
	height: 4.5rem;
	width: 4.5rem;
}

.rounded-circle {
	border-radius: 50% !important;
}

.img-thumbnail {
	padding: .25rem;
	background-color: #ecf2f5;
	border: 1px solid #dee2e6;
	border-radius: .25rem;
	max-width: 100%;
	height: auto;
}

.avatar-sm {
	height: 2.25rem;
	width: 2.25rem;
}

.rounded-circle {
	border-radius: 50% !important;
}

.me-2 {
	margin-right: .75rem !important;
}

.avatar-md {
	height: 3.5rem;
	width: 3.5rem;
}

.rounded-circle {
	border-radius: 50% !important;
}

.bg-transparent { -
	-bs-bg-opacity: 1;
	background-color: transparent !important;
}

.post-user-comment-box {
	background-color: #f2f8fb;
	margin: 0 -.75rem;
	padding: 1rem;
	margin-top: 20px;
}

.simplebar-wrapper {
	overflow: hidden;
	width: inherit;
	height: inherit;
	max-width: inherit;
	max-height: inherit;
}

.simplebar-height-auto-observer-wrapper {
	box-sizing: inherit !important;
	height: 100%;
	width: 100%;
	max-width: 1px;
	position: relative;
	float: left;
	max-height: 1px;
	overflow: hidden;
	z-index: -1;
	padding: 0;
	margin: 0;
	pointer-events: none;
	flex-grow: inherit;
	flex-shrink: 0;
	flex-basis: 0;
}

.font-13 {
	font-size: 13px !important;
}

.btn-soft-info {
	color: #45bbe0;
	background-color: rgba(69, 187, 224, .18);
	border-color: rgba(69, 187, 224, .12);
}

.social-list-item {
	height: 2rem;
	width: 2rem;
	line-height: calc(2rem - 2px);
	display: block;
	border: 2px solid #adb5bd;
	border-radius: 50%;
	color: #adb5bd;
}

.comment-area-box .comment-area-btn {
	background-color: #f2f8fb;
	padding: 10px;
	border: 1px solid #dee2e6;
	border-top: none;
	border-radius: 0 0 .2rem .2rem;
}
</style>

</head>
<body>

	<jsp:include page="header.jsp" />

	<%
	Object object[];
	String memberId = request.getParameter("memberId");
	ProfileDao profileDao = new ProfileDao();
	object = profileDao.getProfileDetails(memberId);
	%>

	<div class="container">
		<div class="row">
			<div class="col-xl-5">
				<div class="card">
					<div class="card-body">
						<div class="d-flex align-items-start">
							<img src="https://bootdey.com/img/Content/avatar/avatar1.png"
								class="rounded-circle avatar-lg img-thumbnail"
								alt="profile-image">
							<div class="w-100 ms-3">
								<h4 class="my-0">
									<%=object[1]%></h4>
								<p class="text-muted"></p>
							</div>
						</div>

						<div class="mt-3">
							<h4 class="font-13 text-uppercase">About Me :</h4>
							<p class="text-muted font-13 mb-3">
								<%=object[4]%>
							</p>
							<p class="text-muted mb-2 font-13">
								<strong>Full Name :</strong> <span class="ms-2"><%=object[1]%></span>
							</p>

							<p class="text-muted mb-1 font-13">
								<strong>Location :</strong> <span class="ms-2"><%=object[3]%></span>
							</p>
						</div>

					</div>
				</div>
				<!-- end card -->

				<div class="card">
					<div class="card-body text-center">
						<div class="row">
							<div class="col-4 border-end border-light">
								<h5 class="text-muted mt-1 mb-2 fw-normal">Diamonds</h5>
								<h2 class="mb-0 fw-bold"><%=object[5]%></h2>
							</div>
							<div class="col-4 border-end border-light">
								<h5 class="text-muted mt-1 mb-2 fw-normal">Followers</h5>
								<h2 class="mb-0 fw-bold"><%=object[6]%></h2>
							</div>
							<div class="col-4">
								<h5 class="text-muted mt-1 mb-2 fw-normal">Following</h5>
								<h2 class="mb-0 fw-bold"><%=object[7]%></h2>
							</div>
						</div>
					</div>
				</div>

				<div class="card">
					<div class="card-body">
						<h4 class="header-title mb-3">Inbox</h4>

						<div class="inbox-widget" data-simplebar="init"
							style="max-height: 350px;">
							<div class="simplebar-wrapper" style="margin: 0px;">
								<div class="simplebar-height-auto-observer-wrapper">
									<div class="simplebar-height-auto-observer"></div>
								</div>
								<div class="simplebar-mask">
									<div class="simplebar-offset" style="right: 0px; bottom: 0px;">
										<div class="simplebar-content-wrapper"
											style="height: auto; overflow: hidden scroll;">
											<div class="simplebar-content" style="padding: 0px;">
												<div class="d-flex align-items-center pb-1"
													id="tooltips-container">
													<img
														src="https://bootdey.com/img/Content/avatar/avatar2.png"
														class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
														alt="">
													<div class="w-100 ms-3">
														<h5 class="mb-1">Tomaslau</h5>
														<p class="mb-0 font-13">I've finished it! See you
															so...</p>
													</div>
													<a href="javascript:(0);"
														class="btn btn-sm btn-soft-info font-13"
														data-bs-container="#tooltips-container"
														data-bs-toggle="tooltip" data-bs-placement="left" title=""
														data-bs-original-title="Reply"> <i
														class="mdi mdi-reply"></i>
													</a>
												</div>
												<div class="d-flex align-items-center py-1"
													id="tooltips-container1">
													<img
														src="https://bootdey.com/img/Content/avatar/avatar3.png"
														class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
														alt="">
													<div class="w-100 ms-3">
														<h5 class="mb-1">Stillnotdavid</h5>
														<p class="mb-0 font-13">This theme is awesome!</p>
													</div>
													<a href="javascript:(0);"
														class="btn btn-sm btn-soft-info font-13"
														data-bs-container="#tooltips-container1"
														data-bs-toggle="tooltip" data-bs-placement="left" title=""
														data-bs-original-title="Reply"> <i
														class="mdi mdi-reply"></i>
													</a>
												</div>
												<div class="d-flex align-items-center py-1"
													id="tooltips-container2">
													<img
														src="https://bootdey.com/img/Content/avatar/avatar4.png"
														class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
														alt="">
													<div class="w-100 ms-3">
														<h5 class="mb-1">Shahedk</h5>
														<p class="mb-0 font-13">Hey! there I'm available...</p>
													</div>
													<a href="javascript:(0);"
														class="btn btn-sm btn-soft-info font-13"
														data-bs-container="#tooltips-container2"
														data-bs-toggle="tooltip" data-bs-placement="left" title=""
														data-bs-original-title="Reply"> <i
														class="mdi mdi-reply"></i>
													</a>
												</div>
												<div class="d-flex align-items-center py-1"
													id="tooltips-container3">
													<img
														src="https://bootdey.com/img/Content/avatar/avatar5.png"
														class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
														alt="">
													<div class="w-100 ms-3">
														<h5 class="mb-1">Kurafire</h5>
														<p class="mb-0 font-13">Nice to meet you</p>
													</div>
													<a href="javascript:(0);"
														class="btn btn-sm btn-soft-info font-13"
														data-bs-container="#tooltips-container3"
														data-bs-toggle="tooltip" data-bs-placement="left" title=""
														data-bs-original-title="Reply"> <i
														class="mdi mdi-reply"></i>
													</a>
												</div>
												<div class="d-flex align-items-center py-1"
													id="tooltips-container4">
													<img
														src="https://bootdey.com/img/Content/avatar/avatar6.png"
														class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
														alt="">
													<div class="w-100 ms-3">
														<h5 class="mb-1">Adhamdannaway</h5>
														<p class="mb-0 font-13">This theme is awesome!</p>
													</div>
													<a href="javascript:(0);"
														class="btn btn-sm btn-soft-info font-13"
														data-bs-container="#tooltips-container4"
														data-bs-toggle="tooltip" data-bs-placement="left" title=""
														data-bs-original-title="Reply"> <i
														class="mdi mdi-reply"></i>
													</a>
												</div>
												<div class="d-flex align-items-center py-1"
													id="tooltips-container5">
													<img
														src="https://bootdey.com/img/Content/avatar/avatar7.png"
														class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
														alt="">
													<div class="w-100 ms-3">
														<h5 class="mb-1">Tomaslau</h5>
														<p class="mb-0 font-13">I've finished it! See you
															so...</p>
													</div>
													<a href="javascript:(0);"
														class="btn btn-sm btn-soft-info font-13"
														data-bs-container="#tooltips-container5"
														data-bs-toggle="tooltip" data-bs-placement="left" title=""
														data-bs-original-title="Reply"> <i
														class="mdi mdi-reply"></i>
													</a>
												</div>
												<div class="d-flex align-items-center py-1"
													id="tooltips-container6">
													<img
														src="https://bootdey.com/img/Content/avatar/avatar8.png"
														class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
														alt="">
													<div class="w-100 ms-3">
														<h5 class="mb-1">Shahedk</h5>
														<p class="mb-0 font-13">Hey! there I'm available...</p>
													</div>
													<a href="javascript:(0);"
														class="btn btn-sm btn-soft-info font-13"
														data-bs-container="#tooltips-container6"
														data-bs-toggle="tooltip" data-bs-placement="left" title=""
														data-bs-original-title="Reply"> <i
														class="mdi mdi-reply"></i>
													</a>
												</div>
												<div class="d-flex align-items-center pt-1"
													id="tooltips-container7">
													<img
														src="https://bootdey.com/img/Content/avatar/avatar5.png"
														class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
														alt="">
													<div class="w-100 ms-3">
														<h5 class="mb-1">Stillnotdavid</h5>
														<p class="mb-0 font-13">This theme is awesome!</p>
													</div>
													<a href="javascript:(0);"
														class="btn btn-sm btn-soft-info font-13"
														data-bs-container="#tooltips-container7"
														data-bs-toggle="tooltip" data-bs-placement="left" title=""
														data-bs-original-title="Reply"> <i
														class="mdi mdi-reply"></i>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="simplebar-placeholder"
									style="width: auto; height: 532px;"></div>
							</div>
							<div class="simplebar-track simplebar-horizontal"
								style="visibility: hidden;">
								<div class="simplebar-scrollbar"
									style="width: 0px; display: none;"></div>
							</div>
							<div class="simplebar-track simplebar-vertical"
								style="visibility: visible;">
								<div class="simplebar-scrollbar"
									style="height: 230px; transform: translate3d(0px, 0px, 0px); display: block;"></div>
							</div>
						</div>
						<!-- end inbox-widget -->
					</div>
				</div>
				<!-- end card-->

			</div>
			<!-- end col-->

			<div class="col-xl-7">
				<div class="card">
					<div class="card-body">
						<!-- Story Box-->
						<div class="border border-light p-2 mb-3">

							<%
							FeedDao feedDao = new FeedDao();
							ResultSet rs = feedDao.getFeedByMember(memberId);
							while (rs.next()) {
							%>

							<div class="d-flex align-items-start">
								<img class="me-2 avatar-sm rounded-circle"
									src="https://bootdey.com/img/Content/avatar/avatar4.png"
									alt="Generic placeholder image">
								<div class="w-100">
									<h5 class=""><%=rs.getString("profile.name")%>
										 &emsp; <small class="text-muted">  <%=rs.getString(5)%></small>
									</h5>
									<div class=""><%=rs.getString(3)%><br> <br> 
									</div>
								</div>
							</div>

							<%
							}
							%>

						</div>
					</div>
				</div>
				<!-- end card-->

			</div>
			<!-- end col -->
		</div>
		<!-- end row-->

	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>