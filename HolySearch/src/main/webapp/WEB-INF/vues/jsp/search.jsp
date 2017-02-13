<%@page
	import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/styleForm.css">
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="formoid_files/formoid1/formoid-solid-light-green.css"
	type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body id="site-container">

	<div style= "height:500px;">
	
	<div style= "height:100px; position: relative; top: 30%; width: 70%; margin-left: 17%;">

			<form:form action="rechercher" method="get"
				modelAttribute="searchForm" class="inline-form">
				<div class="row" style="margin-left: 10%;">
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
							<input type="search" class="input-sm form-control"
								placeholder="Rechercher vos destinations de vacances"
								name="objetSearch">
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3" style="margin-left: 1%;">
						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-sm"
								value="Effectuer la recherche" style="height: 42px;" />
						</div>
					</div>
				</div>

			</form:form>
		
		</div>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="bootstrap/dist/vendors.js"></script>
	<script src="bootstrap/dist/app.js"></script>
	<script src="bootstrap/dist/custom.js"></script>
</body>
</html>