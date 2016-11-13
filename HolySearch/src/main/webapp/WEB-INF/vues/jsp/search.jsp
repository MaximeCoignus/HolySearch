<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="formoid_files/formoid1/formoid-solid-light-green.css"
	type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body id="site-container">
	<div class="container voffset5 background-white">
		<div class="container">
			<c:if test="${not empty identifiant}">
				<form:form action="deconnexion" method="get"
					style="text-align:right;">
					<input type="submit" value="Se déconnecter">
				</form:form>
			</c:if>
			<form:form action="rechercher" method="get"
				modelAttribute="searchForm">
				<div style="text-align: center;">
					<a href="/HolySearch/search"><img src="images/logo1.gif"
						style="text-align: center;" /></a>
				</div>
				<div>
					<table style="margin-left: 30%;">
						<div style="text-align: center;">
							<tr>
								<td></td>
								<td><input type="text" name="objetSearch"
									placeholder="Search" style="width: 300px;" /></td>
								<td><input type="submit" value="Effectuer la recherche"
									style="margin-left: 20%;" /></td>
							</tr>
						</div>
					</table>
				</div>

			</form:form>

		</div>
	</div>
</body>
</html>