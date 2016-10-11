<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Bienvenue sur Word360</title>
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
	<div class="container voffset5 background-white">
		<div class="container">
			<br />
			<br />
			<br />
			<br />
			<br />
			<div id="site-content" class="row">
				<div id="slider" class="col-lg-6 col-md-6">
					<div id="slideshow">
						<ul id="fade" class="crossfade random transition-crossfade">
							<li id="s3"><a href="images/image1.jpg" class="loadimage"></a>
								<div class="caption"></div></li>
							<li id="s4"><a href="images/image2.jpg" class="loadimage"></a>
								<div class="caption"></div></li>
							<li id="s5"><a href="images/image3.jpg" class="loadimage"></a>
								<div class="caption"></div></li>
						</ul>
						<script type="text/javascript"
							src="scriptaculous/lib/prototype.js"></script>
						<script type="text/javascript" src="scriptaculous/src/effects.js"></script>
						<script type="text/javascript" src="js/fastinit.js"></script>
						<script type="text/javascript" src="js/crossfade.js"></script>
						<script>
							Crossfade.setup({
								autoLoad : false
							});

							var cf2 = new Crossfade('fade');
						</script>
					</div>
				</div>
				<div id="connexion" class="col-lg-6 col-md-6">
					<div class="row">
						<br />
						<br />
						<div id="connexion-form"
							class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<form:form modelAttribute="connexionForm" class="formoid-solid-light-green"
								style="background-color: #FFFFFF; font-size: 14px; font-family: 'Trebuchet MS', Helvetica, sans-serif; color: #000; max-width: 500px; min-width: 150px"
								method="post" action="connexion">
								<div class="title">
									<h2>Connectez-vous</h2>
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span> <input
										class="form-control" type="text" name='login'
										placeholder="Identifiant" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-lock"></i></span> <input
										class="form-control" type="password" name='password'
										placeholder="Mot de passe" />
								</div>
								<div class="submit">
									<a id="forgot-psw" href="reinitialiserPassword">Mot de passe oublié?</a> <input
										type="submit" value="Connexion" />
								</div>
							</form:form>
						</div>
					</div>
					<div class="row">
						<div id="sign-up" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p>
								C'est votre première fois sur ce site?<a href="inscription">
									Inscrivez-vous</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
