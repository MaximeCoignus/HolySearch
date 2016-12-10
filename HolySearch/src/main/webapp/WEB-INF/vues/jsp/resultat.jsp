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
<title>Resultat de votre recherche</title>
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
	<div class="container voffset5 background-white">
		<div class="container">

			<form:form action="rechercher" method="get"
				modelAttribute="searchForm">
				<div>
					
						<div style="text-align: center;">
							
								<a href="/HolySearch/search"><img
										src="images/logo.gif"
										style="text-align: center; margin-right: 5%;" /></a>
								<input type="text" name="objetSearch"
									placeholder="Search" style="width: 300px;"
									value="${searchForm.objetSearch}" />
								<input type="submit" value="Effectuer la recherche"
									style="margin-left: 5%;" />
							
						</div>
					
				</div>
			</form:form>
			<br /> <br />
			ici on mettra le resultat
			
			<br>

			<c:if test="${not empty resultatForm}">

				<label><spring:message code="resultat.nomPlage" /></label>
				<c:out value="${resultatForm.beachName}" />

				<br>
				<br>

				<label><spring:message code="resultat.latitudePlage" /></label>
				<c:out value="${resultatForm.latitude}" />

				<br>
				<br>

				<label><spring:message code="resultat.longitudePlage" /></label>
				<c:out value="${resultatForm.longitude}" />

				<br>
				<br>
				<br>

			</c:if>
			
			<c:if test="${empty resultatForm}"> 
				Aucun résultat pour la recherche de : <c:out value="${searchForm.objetSearch}"/>
			</c:if>



		</div>
	</div>
</body>
</html>