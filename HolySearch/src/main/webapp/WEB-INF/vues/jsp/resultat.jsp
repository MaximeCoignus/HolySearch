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

			<br /> <br />
			<br /> <br />
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
			<br /> <br /> Résultat de la recherche effectuée avec l'API Lucene
			<br /> <br />



			<c:if test="${fn:length(listeResultatForm) > 0}">
				<table>
					<c:forEach var="resultat" items="${listeResultatForm}">
						<tr>
							<td><label><spring:message code="resultat.nomPlage" />:</label>
								<c:out value="${resultat.beachName}" /></td>
							<td><label><spring:message
										code="resultat.latitudePlage" />:</label> <c:out
									value="${resultat.latitude}" /></td>
							<td><label><spring:message
										code="resultat.longitudePlage" />:</label> <c:out
									value="${resultat.longitude}" /></td>
						</tr>
					</c:forEach>
				</table>

			</c:if>

			<c:if test="${fn:length(listeResultatForm) == 0}"> 
				Aucun résultat pour la recherche de : <c:out
					value="${searchForm.objetSearch}" />
			</c:if>



		</div>
	</div>
	<br /> <br />
			<br /> <br />
</body>
</html>