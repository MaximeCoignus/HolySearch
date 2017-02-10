<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${sessionScope.sessionUtilisateur}" /> :
	Mon profil</title>
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
					<h3 style="text-align: center;">
						Bienvenue sur votre profil
						<c:out value="${sessionScope.sessionUtilisateur}" />
					</h3>
					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<br /> <br />
								<c:if test="${empty avatarUser}">
									<img src="images/ico_profil.png" id="userAvatarImage"
										style="margin-left: 23%;width: 190px; height: 205px;" />
								</c:if>
								<c:if test="${not empty avatarUser}">
									<img src="data:image/jpg;base64, ${avatarUser}" id="userAvatarImage"
										style="margin-left: 23%;width: 190px; height: 205px;" />
								</c:if>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<br /> <br /> <br /> <br /> <br /> <br />
								<div id="wrapper">
									<a href="modifier-photo-profil"> <input type="button"
										class="btn btn-primary btn-sm"
										value="Modifier ma photo de profil" style="height: 42px;" /></a>
								</div>
							</div>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<span style="margin-left: 23%;"><u>Identifiant</u> : <c:out
										value="${infoUser.userLogin}" /></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<span style="margin-left: 23%;"><u>Nom</u> : <c:out
										value="${infoUser.userNom}" /></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<span style="margin-left: 23%;"><u>Prenom</u> : <c:out
										value="${infoUser.userPrenom}" /></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<span style="margin-left: 23%;"><u>Date de naissance</u>
									: <c:out value="${infoUser.userBirthday}" /></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<span style="margin-left: 23%;"><u>Email</u> : <c:out
										value="${infoUser.userEmail}" /></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<span style="margin-left: 23%;"><u>Mot de passe</u> :
									***************</span>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<div id="wrapper">
									<a href="reinitialiserPassword"> <input type="button"
										class="btn btn-primary btn-sm"
										value="Modifier mon mot de passe" style="height: 32px;" /></a>
								</div>
							</div>
						</div>
						<br /> <br />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>