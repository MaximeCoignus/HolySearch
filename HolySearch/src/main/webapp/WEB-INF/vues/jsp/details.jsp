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
<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
<title>Détails de la recherche</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/styleForm.css">
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="formoid_files/formoid1/formoid-solid-light-green.css"
	type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDSOxjtCd31DRdYvuK0qr3LI4pjkw6muKs"></script>

<script>
			function initialisation(){
				var optionsCarte = {
					zoom: 8,
					center: new google.maps.LatLng(25.7742700, -80.1936600)
				}
				var maCarte = new google.maps.Map(document.getElementById("cityMap"), optionsCarte);
			}
			google.maps.event.addDomListener(window, 'load', initialisation);
		</script>

<style>
	#cityMap {
		height: 50%
	}
</style>

</head>
<body id="site-container">
	<div class="container voffset5 background-white">
		
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
			<br/><br />
			
			<!--Première ligne-->
			
			<div class="row row-eq-height">
			
			  <div class="col-md-9">
			  
				<div>
					<a class="project-thumbnail-wrap" target="">
						<img alt="Image de la ville" class="img-rounded" height="500px" src="http://media.vanityfair.com/photos/5640e4e4ffe4c2a35a2a9bef/16:9/w_1200,h_630,c_limit/t-miami-beach-waterworld-david-kamp.jpg" width="100%" />
					</a>
				</div>
				</div>
			<div class="col-md-3">
				<div class="container-fluid img-rounded" style="border: 1px solid #FFDEAD;">
					<h3 style="color: #E9967A;">Miami</h3>
					<h6 style="color: #90EE90;">Floride, Etats-Unis</h6>
					<p class="text-info text-justify">
						Miami est une ville des États-Unis, centre financier et culturel de niveau international, située dans le sud-est de l'État de Floride. Elle est le siège du comté de Miami-Dade.
					</p>
					<p class="text-info text-justify">
						Miami est une ville des États-Unis, centre financier et culturel de niveau international, située dans le sud-est de l'État de Floride. Elle est le siège du comté de Miami-Dade.
					</p>
					<p class="text-info text-justify">
						Miami est une ville des États-Unis, centre financier et culturel de niveau international, située dans le sud-est de l'État de Floride. Elle est le siège du comté de Miami-Dade.
					</p>
					<p class="text-info text-justify">
						Miami est une ville des États-Unis, centre financier et culturel de niveau international, située dans le sud-est de l'État de Floride. Elle est le siège du comté de Miami-Dade.
					</p>
				</div>
			  </div>
			
			</div>
			
			<br>
			
			<div class="row row-eq-height">
				<div class="col-md-9">
					<div class="container-fluid img-rounded" style="border: 1px solid #FFDEAD;">
						<h3 style="color: #E9967A;">Détails</h3>
						<div class="col-md-4">
							<p>Température : 25°C</p>
							<p>Précipitations (mm) : 70</p>
						</div>
						<div class="col-md-4">
							<p>Criminalité : N/A</p>
							<p>Niveau de richesse : N/A</p>
						</div>
						<div class="col-md-4">
							<p>Coût de vie : N/A</p>
							<p>Importance du tourisme : N/A</p>
						</div>
						<div class="col-md-12">
							<p><br></p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<img alt="Drapeau" class="img-rounded" src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Flag_of_Miami%2C_Florida.svg/1280px-Flag_of_Miami%2C_Florida.svg.png" width="100%" />
				</div>
			</div>
			
			<br>
			
			<!-- Carte Google Map -->
			<div id="cityMap"></div>
			
			<!-- Message si javascript n'est pas activé -->
			<noscript>
				<p>Attention : Il semble que JavaScript est désactivé ou qu'il ne soit pas supporté par votre navigateur.</p>
				<p>Pour afficher Google Maps, activez JavaScript en modifiant les options de votre navigateur, puis essayez à nouveau.</p>
			</noscript>
			
			<br>
	</div>
</body>
</html>