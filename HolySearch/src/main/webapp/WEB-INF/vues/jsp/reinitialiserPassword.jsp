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
	<br />
	<br />
	<br />
	<div class="container voffset5 background-white">
		<div class="container" >
			
			<p><h4>Réinitialiser son mot de passe :</h4>
			</p>
			<br />
			<form:form action="reinitialiserPassword" method="get"
				modelAttribute="reinitialiserPasswordForm" >
				<fieldset style="padding:0.05%;border:1px #8EA2C6 solid;margin: 2% 0%;">
				<p style="padding:2%;margin: 0% 7%;">
					<label><spring:message
										code="reinitialiser.email" />*</span> </label> <input
						class="large" type="email" name="email" value=""
										placeholder="Email" size="80"
						maxlength="80"   /><span
						class="icon-place"></span> <br /> <br /> 
					
					<label><spring:message
											code="reinitialiser.password" /> <span class="requis">*</span> </label> <input
						type="password" name='password'
									placeholder="Mot de passe" size="80"
						maxlength="80"   /><span
						class="icon-place"></span><br /> <br /> 
						
					<label><spring:message
											code="reinitialiser.confirmerPassword" /></label>
					<input type="password" name='confirmPassword'
									placeholder="Confirmer le mot de passe"
						size="80"
						maxlength="80"   /><span class="icon-place"></span><br /> 
					<p style="margin:2% 34%;">
						<input type="submit" value="Réinitialiser son mot de passe" />
						<input type="reset"
						value="Remettre à zéro" /> <br />
					</p>
					</p>
					
				</fieldset>	
			</form:form>
		</div>
	</div>
</body>
</html>