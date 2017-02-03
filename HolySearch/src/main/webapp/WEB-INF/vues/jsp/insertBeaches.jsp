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
	<div class="container voffset5 background-white">
		<div class="container">
			<br/><br/><br/>
			<p>Inserer des nouvelles plages a partir de l'url du webservice</p>
			<form:form modelAttribute="insertBeachesForm" method="post"
				action="insertBeaches">
				<br />
				<div>
					<span><label>Url du webservice</label></span> <input type="text"
						name='url' placeholder="Url du webservice" required />
				</div>
				<br />
				<div class="submit">
					<input type="submit" value="Inserer les plages a partir de l'URL" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>