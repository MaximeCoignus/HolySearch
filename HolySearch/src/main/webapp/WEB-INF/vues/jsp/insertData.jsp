<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="UTF-8">

<title>HolySearch, Insert Beaches</title>

<link rel="shortcut icon" href="favicon.ico">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>

<link href="css/carousel.css" rel="stylesheet">
</head>
<body id="site-container">
	<div style="height: 400px;">

		<div
			style="height: 100px; position: relative; top: 30%; width: 70%; margin-left: 17%;">

			<form:form modelAttribute="insertDataForm" method="post"
				action="insertData" class="inline-form">
				<div class="row" style="margin-left: 10%;">

					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
							<span>Type de data à ajouter en base : </span><br /><br />
							<select class="form-control" name="objet">
								<option value="Continent">Continent</option>
								<option value="Country">Country</option>
								<option value="City">City</option>
								<option value="Destination">Destination</option>
							</select>
						</div>
					</div>
				</div>
				<br />
				<div class="row" style="margin-left: 10%;">
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
							<input type="search" class="input-sm form-control"
								placeholder="URL de la data" name="url">
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3" style="margin-left: 1%;">
						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-sm"
								value="Inserer les datas à partir de l'URL"
								style="height: 42px;" />
						</div>
					</div>
				</div>
				<c:if test="${not empty resultatInsertionData}">
					<p style="color: green;">${resultatInsertionData}.</p>
				</c:if>
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