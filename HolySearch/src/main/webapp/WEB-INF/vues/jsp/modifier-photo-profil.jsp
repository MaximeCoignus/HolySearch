<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Modification de votre photo de profil</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/styleForm.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="formoid_files/formoid1/formoid-solid-light-green.css"
	type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body id="site-container">
	<div class="row centered-form">
		<div
			class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-3"
			style="width: 51%;">

			<div class="panel panel-default">
				<div class="panel-heading">
					<form:form action="enregistrer-photo-profil" method="post"
						modelAttribute="avatarForm" enctype="multipart/form-data">
						<h2>
							Modifier votre photo de profil
							<c:out value="${sessionScope.sessionUtilisateur}" />
						</h2>
						<hr class="colorgraph">
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
									<br /> <br />
									<c:if test="${empty avatarUser}">
										<img src="images/ico_profil.png" id="userAvatarImage"
											style="margin-left: 23%; width: 190px; height: 205px;" />
									</c:if>
									<c:if test="${not empty avatarUser}">
										<img src="data:image/jpg;base64, ${avatarUser}"
											id="userAvatarImage"
											style="margin-left: 23%; width: 190px; height: 205px;" />
									</c:if>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
									<br /> <br /> <br /> <br /> <br />

									<div id="wrapper">
										<div class="text-center">
											<input id="fileUpload" class="form-control input-lg"
												name="userAvatarFile" type="file" accept=".png, .jpg, .jpeg" /><br />

											<script>
												$("#fileUpload")
														.on(
																'change',
																function() {

																	if (typeof (FileReader) != "undefined") {

																		var image_holder = $("#userAvatarFile");
																		image_holder
																				.empty();

																		var reader = new FileReader();
																		reader.onload = function(
																				e) {
																			$(
																					"#userAvatarImage")
																					.attr(
																							'src',
																							e.target.result);
																		}
																		image_holder
																				.show();
																		reader
																				.readAsDataURL($(this)[0].files[0]);
																	} else {
																		alert("This browser does not support FileReader.");
																	}
																});
											</script>
											<c:if test="${not empty errors}">
												<p style="color: red;">${errors}.</p>
											</c:if>
										</div>
									</div>


								</div>
							</div>
						</div>
						<hr class="colorgraph">
						<br />
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<input type="submit"
									value="Enregistrer ma nouvelle photo de profil"
									class="btn btn-primary btn-block btn-lg" tabindex="8">
							</div>
							<div class="col-xs-12 col-md-6">
								<a href=annuler-modification-photo-profil
									class="btn btn-success btn-block btn-lg">Annuler la
									modification</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="bootstrap/dist/vendors.js"></script>
	<script src="bootstrap/dist/app.js"></script>
	<script src="bootstrap/dist/custom.js"></script>
</body>
</html>