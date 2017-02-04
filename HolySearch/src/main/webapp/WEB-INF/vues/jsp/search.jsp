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
	<div class="container voffset5 background-white">
		<div class="container">
			<br /> <br />
			<br /> <br />
			<br /> <br />
			
			<form:form action="rechercher" method="get"
				modelAttribute="searchForm">
				<div>
					<table style="margin-left: 28%;">
						<div style="text-align: center;">
							<tr>
								<td></td>
								<td><input type="search" placeholder="Search"
									name="objetSearch" style="padding-top: 6%; padding-bottom:6%; padding-left:10%;"></td>
								<td><input type="submit" class="o-button -large"
									value="Effectuer la recherche" style="margin-left: 20%;" /></td>
							</tr>
						</div>
					</table>
				</div>

			</form:form>
			<br /> <br /><br /> <br />
			<br /> <br />
			<br /> <br />
			<br /> <br /><br /> <br />
			<br /> <br />
			<br /> <br />
		</div>
	</div>
</body>
</html>