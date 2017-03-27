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
<title>Résultat de votre recherche</title>
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
			<br /> <br /><br /> <br />

			<!--Première ligne-->

			<div class="row row-eq-height">

				<div class="col-md-3 ">

					<div>
						<a class="project-thumbnail-wrap" href="details" target="">
							<img alt="Image de la ville" class="img-rounded" height="200px"
							src="http://media.vanityfair.com/photos/5640e4e4ffe4c2a35a2a9bef/16:9/w_1200,h_630,c_limit/t-miami-beach-waterworld-david-kamp.jpg"
							width="100%" />
						</a>
					</div>
					<p></p>
					<div class="container-fluid img-rounded"
						style="border: 1px solid #FFDEAD; height: 250px;">
						<h3>Miami</h3>
						<h6>Floride, Etats-Unis</h6>
						<p class="text-info text-justify">Miami est une ville des
							États-Unis, centre financier et culturel de niveau international,
							située dans le sud-est de l'État de Floride. Elle est le siège du
							comté de Miami-Dade.</p>
					</div>
				</div>

				<div class="col-md-3">

					<div>
						<img alt="Image de la ville" class="img-rounded" height="200px"
							src="http://75d6c1c9155b031cbab1-d46475ba7b2b029c2fc6c2ad83051f70.r80.cf2.rackcdn.com/dev/destinations/Hawaii/Oahu/Oahu_hero.jpg"
							width="100%" />
					</div>
					<p></p>
					<div class="container-fluid img-rounded"
						style="border: 1px solid #FFDEAD; height: 250px;">
						<h3>Hawai</h3>
						<h6>Hawai, Etats-Unis</h6>
						<p class="text-info text-justify">O?ahu est la troisième île
							par la taille de l'archipel d'Hawaï et la plus peuplée des îles
							formant l'État d'Hawaï. Elle couvre une superficie de 1 545,34
							km², en incluant l'île de Ford et les îlots de la baie de Kaneohe
							et de la côte est.</p>
					</div>

				</div>

				<div class="col-md-3">

					<div>
						<img alt="Image de la ville" class="img-rounded" height="200px"
							src="http://southerncaliforniabeaches.org/img/MalibuPier.jpg"
							width="100%" />
					</div>
					<p></p>
					<div class="container-fluid img-rounded"
						style="border: 1px solid #FFDEAD; height: 250px;">
						<h3>Malibu</h3>
						<h6>Californie, Etats-Unis</h6>
						<p class="text-info text-justify">Malibu est une ville du
							comté de Los Angeles, dans l'État de Californie aux États-Unis.
							D'après le recensement de 2010, la ville comptait 12 645
							habitants.</p>
					</div>

				</div>

				<div class="col-md-3">

					<div>
						<img alt="Image de la ville" class="img-rounded" height="200px"
							src="http://frenchdistrict.com/californie-nord/files/2013/04/santa-monica-pier-at-sunset.jpg"
							width="100%" />
					</div>
					<p></p>
					<div class="container-fluid img-rounded"
						style="border: 1px solid #FFDEAD; height: 250px;">
						<h3>Santa Monica</h3>
						<h6>Californie, Etats-Unis</h6>
						<p class="text-info text-justify">Santa Monica est une ville
							côtière à l'ouest de Los Angeles, dans l'État de Californie aux
							États-Unis. Le territoire de Santa Monica est enclavé entre la
							ville de Los Angeles et l'océan Pacifique.</p>
					</div>
				</div>

			</div>

			<br> <br> <br>

			<!--Deuxième ligne-->

			<div class="row row-eq-height">

				<div class="col-md-3 ">

					<div>
						<a class="project-thumbnail-wrap" href="/project/details"
							target=""> <img alt="Image de la ville" class="img-rounded"
							height="200px"
							src="http://media.vanityfair.com/photos/5640e4e4ffe4c2a35a2a9bef/16:9/w_1200,h_630,c_limit/t-miami-beach-waterworld-david-kamp.jpg"
							width="100%" />
						</a>
					</div>
					<p></p>
					<div class="container-fluid img-rounded"
						style="border: 1px solid #FFDEAD; height: 250px;">
						<h3>Miami</h3>
						<h6>Floride, Etats-Unis</h6>
						<p class="text-info text-justify">Miami est une ville des
							États-Unis, centre financier et culturel de niveau international,
							située dans le sud-est de l'État de Floride. Elle est le siège du
							comté de Miami-Dade.</p>
					</div>
				</div>

				<div class="col-md-3">

					<div>
						<img alt="Image de la ville" class="img-rounded" height="200px"
							src="http://75d6c1c9155b031cbab1-d46475ba7b2b029c2fc6c2ad83051f70.r80.cf2.rackcdn.com/dev/destinations/Hawaii/Oahu/Oahu_hero.jpg"
							width="100%" />
					</div>
					<p></p>
					<div class="container-fluid img-rounded"
						style="border: 1px solid #FFDEAD; height: 250px;">
						<h3>Hawai</h3>
						<h6>Hawai, Etats-Unis</h6>
						<p class="text-info text-justify">O?ahu est la troisième île
							par la taille de l'archipel d'Hawaï et la plus peuplée des îles
							formant l'État d'Hawaï. Elle couvre une superficie de 1 545,34
							km², en incluant l'île de Ford et les îlots de la baie de Kaneohe
							et de la côte est.</p>
					</div>

				</div>

				<div class="col-md-3">

					<div>
						<img alt="Image de la ville" class="img-rounded" height="200px"
							src="http://southerncaliforniabeaches.org/img/MalibuPier.jpg"
							width="100%" />
					</div>
					<p></p>
					<div class="container-fluid img-rounded"
						style="border: 1px solid #FFDEAD; height: 250px;">
						<h3>Malibu</h3>
						<h6>Californie, Etats-Unis</h6>
						<p class="text-info text-justify">Malibu est une ville du
							comté de Los Angeles, dans l'État de Californie aux États-Unis.
							D'après le recensement de 2010, la ville comptait 12 645
							habitants.</p>
					</div>

				</div>

				<div class="col-md-3">

					<div>
						<img alt="Image de la ville" class="img-rounded" height="200px"
							src="http://frenchdistrict.com/californie-nord/files/2013/04/santa-monica-pier-at-sunset.jpg"
							width="100%" />
					</div>
					<p></p>
					<div class="container-fluid img-rounded"
						style="border: 1px solid #FFDEAD; height: 250px;">
						<h3>Santa Monica</h3>
						<h6>Californie, Etats-Unis</h6>
						<p class="text-info text-justify">Santa Monica est une ville
							côtière à l'ouest de Los Angeles, dans l'État de Californie aux
							États-Unis. Le territoire de Santa Monica est enclavé entre la
							ville de Los Angeles et l'océan Pacifique.</p>
					</div>
				</div>

			</div>

			<br /> <br /> Résultat de la recherche effectuée avec l'API Lucene
			<br /> <br />

			<c:if test="${fn:length(listeResultatForm) > 0}">
				<table>
					<c:forEach var="resultat" items="${listeResultatForm}">
						<tr>
							<td><label><spring:message code="resultat.nomPlage" />:</label>
								<c:out value="${resultat.destinationFrenchName}" /></td>
							<td><label><spring:message
										code="resultat.latitudePlage" />:</label> <c:out
									value="${resultat.destinationLatitude}" /></td>
							<td><label><spring:message
										code="resultat.longitudePlage" />:</label> <c:out
									value="${resultat.destinationLongitude}" /></td>
							<td><label>Wiki Description:</label> <c:out
									value="${resultat.destinationWikiDescription}" /></td>
							<td><label>Wiki picture:</label> <img
								src="${resultat.destinationWikiPicture}" /></td>
						</tr>
					</c:forEach>
				</table>

			</c:if>

			<c:if test="${fn:length(listeResultatForm) == 0}"> 
				Aucun résultat pour la recherche de : <c:out
					value="${searchForm.objetSearch}" />
			</c:if>

			<br> <br> <br>

			<!--Numero de page-->

			<ul class="pagination">
				<li class="disabled"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
			</ul>

		</div>
	</div>
</body>
</html>