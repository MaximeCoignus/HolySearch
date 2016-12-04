<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Réinitialiser mon mot de passe</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="formoid_files/formoid1/formoid-solid-light-green.css"
	type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/styleForm.css">
</head>
<body id="site-container">
	<div class="row centered-form">
		<div
			class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-3"
			style="width: 51%;">
			<div class="panel panel-default">
				<div class="panel-heading">
					<form:form action="reinitialiserPassword" method="get"
						modelAttribute="reinitialiserPasswordForm">
						<h2>Reinitialiser votre mot de passe</h2>
						<hr class="colorgraph">

						<div class="form-group">
							<input type="email" name="email" id="email"
								class="form-control input-lg" placeholder="Adresse Email"
								tabindex="1" required>
						</div>
						<br />
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="password" name="password" id="password"
										class="form-control input-lg" placeholder="Nouveau mot de passe"
										tabindex="2" required>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="password" name="confirmPassword"
										id="confirmPassword" class="form-control input-lg"
										placeholder="Confirmer le nouveau mot de passe" tabindex="3" required>
								</div>
							</div>
						</div>
						<hr class="colorgraph">
						<br />
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<input type="submit" value="Réinitialiser le mot de passe"
									class="btn btn-primary btn-block btn-lg" tabindex="4">
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