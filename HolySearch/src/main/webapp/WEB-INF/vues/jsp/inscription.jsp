<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
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
					<form:form action="createUserAccount" method="post"
						modelAttribute="userForm">
						<h2>Création de votre compte</h2>
						<hr class="colorgraph">
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" name="userNom" id="userNom"
										class="form-control input-lg" placeholder="Nom" tabindex="1"
										required>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" name="userPrenom" id="userPrenom"
										class="form-control input-lg" placeholder="Prénom"
										tabindex="2">
								</div>
							</div>
						</div>
						<br />
						<div class="form-group">
							<input type="date" name="userBirthday" id="userBirthday"
								class="form-control input-lg" placeholder="Date de naissance"
								tabindex="3">
						</div>
						<br />
						<div class="form-group">
							<input type="text" name="userLogin" id="userLogin"
								class="form-control input-lg" placeholder="Identifiant"
								tabindex="4" required>
						</div>
						<br />
						<div class="form-group">
							<input type="email" name="userEmail" id="userEmail"
								class="form-control input-lg" placeholder="Addresse Email"
								tabindex="5" required>
						</div>
						<br />
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="password" name="userPassword" id="userPassword"
										class="form-control input-lg" placeholder="Mot de passe"
										tabindex="6" required>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="password" name="userConfirmPassword"
										id="userConfirmPassword" class="form-control input-lg"
										placeholder="Confirmer le mot de passe" tabindex="7" required>
								</div>
							</div>
						</div>
						<hr class="colorgraph">
						<br />
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<input type="submit" value="S'inscrire"
									class="btn btn-primary btn-block btn-lg" tabindex="8">
							</div>
							<div class="col-xs-12 col-md-6">
								<a href="connexionHoly" class="btn btn-success btn-block btn-lg">Se
									connecter</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>