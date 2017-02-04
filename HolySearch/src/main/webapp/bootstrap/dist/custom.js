var TimeString = "";
function setNewTime()
{
	if (Curmm<59) {Curmm++;}
	else {CurHH++; Curmm = 0;}
	if (CurHH>8 && CurHH<17)
		document.getElementById("officeIs").innerHTML = "sont ouverts";
	else
		document.getElementById("officeIs").innerHTML = "sont fermés";
	if (CurHH>23) {CurHH = 0;}
	if (CurHH<10) TimeString = "0" + CurHH + "h";
	else TimeString = CurHH + "h";
	if (Curmm<10) TimeString+="0";
	TimeString += Curmm;
	document.getElementById("LocalTime").innerHTML = TimeString;
	setTimeout("setNewTime()", 60000);
}

function loadPanel(type, id) {
	$.ajax(	{type:"GET"
			,url:'/'+(type=='H'?'hebergements':'activites')+'/responsive.cfm?id='+id
			,success:function(result){
				$('#panelHebAct').html(result);
				$('#panelHebActTop').scrollTop(0);
			}
	});
	return false;
}

function validateForm(version) {
	var b_submit_form = true;
	if (typeof version === 'undefined') {
		var dataFields = "travelling,when,flight_arrival,flight_departure,city_arrival,city_departure,arrival_date,nb_days,flex_arrival,departure_date,flex_stay,flight_reserved,nb_1824,nb_2535,nb_3654,nb_5565,nb_6500,nb_kids,kids_age,room_simple,room_double,room_twin,room_triple,room_quad,housing_notes,transportation_category,have_budget,budget_amount,budget_notes,coords_last_name,coords_first_name,coords_email,coords_email_confirm,coords_phone,coords_address,coords_skype,coords_newsletter";
		var parcoursID = document.getElementById('parcours_id').value; // '<?=$_GET["id"]?>';
	} else {
		var dataFields = "travelling,when,duration,housing_category,trip_type,project_stage,have_budget,budget_amount,budget_notes,coords_last_name,coords_first_name,coords_email,coords_email_confirm,coords_phone,coords_address,coords_skype,coords_newsletter";
		var parcoursID = '';
	}
	var dataArray = dataFields.split(",");
	var dataString = "";

	if (document.getElementById('coords_address').value=='') {
		b_submit_form = false;
		$("#coords_address").css("border","thin solid red");
		document.getElementById('coords_address').focus();
	}
	else $("#coords_address").css("border","none");

	if (document.getElementById('coords_email').value=='') {
		b_submit_form = false;
		$("#coords_email").css("border","thin solid red");
		document.getElementById('coords_email').focus();
	}
	else $("#coords_email").css("border","none");

	if (document.getElementById('coords_email').value!='' && document.getElementById('coords_email').value!=document.getElementById('coords_email_confirm').value) {
		b_submit_form = false;
		$("#coords_email_confirm").css("border","thin solid red");
		document.getElementById('coords_email_confirm').select();
		document.getElementById('coords_email_confirm').focus();
	}
	else if (document.getElementById('coords_email_confirm').value=='') {
		b_submit_form = false;
		$("#coords_email_confirm").css("border","thin solid red");
		document.getElementById('coords_email_confirm').focus();
	}
	else $("#coords_email_confirm").css("border","none");

	if (!isValidEmail(document.getElementById('coords_email').value)) {
		b_submit_form = false;
		$("#coords_email").css("border","thin solid red");
		document.getElementById('coords_email').select();
		document.getElementById('coords_email').focus();
	}
	else $("#coords_email").css("border","none");

	if (document.getElementById('coords_last_name').value=='') {
		b_submit_form = false;
		$("#coords_last_name").css("border","thin solid red");
		document.getElementById('coords_last_name').focus();
	}
	else $("#coords_last_name").css("border","none");

	if (document.getElementById('coords_first_name').value=='') {
		b_submit_form = false;
		$("#coords_first_name").css("border","thin solid red");
		document.getElementById('coords_first_name').focus();
	}
	else $("#coords_first_name").css("border","none");

	if (typeof version === 'undefined') {
		if	((document.getElementById('nb_1824').value=='') &&
			 (document.getElementById('nb_2535').value=='') &&
			 (document.getElementById('nb_3654').value=='') &&
			 (document.getElementById('nb_5565').value=='') &&
			 (document.getElementById('nb_6500').value=='')  )
		{
			b_submit_form = false;
			$("#nb_1824").css("border","thin solid red");
			$("#nb_2535").css("border","thin solid red");
			$("#nb_3654").css("border","thin solid red");
			$("#nb_5565").css("border","thin solid red");
			$("#nb_6500").css("border","thin solid red");
			document.getElementById('nb_1824').focus();
		} else {
			$("#nb_1824").css("border","none");
			$("#nb_2535").css("border","none");
			$("#nb_3654").css("border","none");
			$("#nb_5565").css("border","none");
			$("#nb_6500").css("border","none");
		}
	}

	if (b_submit_form) {
		for (var i=0; i<dataArray.length; i++) {
			elemArr = document.getElementsByName(dataArray[i]);
			elemStr = "";
			for(var j=0; j<elemArr.length; j++) {
				element = document.getElementById(elemArr[j].id);
//				if (element==null) alert (dataArray[i]+" not found.");
				if (	(element.type=="text" || element.type=="hidden" || element.type=="password" || element.type=="textarea" || element.type=="select-one")	||
						((element.type=="radio" || element.type=="checkbox") && element.checked)									)
					elemStr = elemStr + (elemStr==''?'':',') + element.value;
				else if (element.type!="radio" && element.type!="checkbox")
					elemStr = elemStr + (elemStr==''?'':',') + 'type ' + element.type;
			}
			dataString = dataString + "&" + dataArray[i] + "=" + encodeURIComponent(btoa(elemStr)); // $.base64.encode(elemStr));
		}

		if (typeof version === 'undefined') {
			$.ajax(	{type:"POST"
					,url:"/parcours/devis_request_2016.cfm"
					,contentType:"application/x-www-form-urlencoded; charset=iso-8859-1"
					,data:"parcours_id="+parcoursID+"&session_id="+dataString
					,success:function(result){
						$("#actualDevisButton").trigger("click");
						_gaq.push(['_trackPageview',location.pathname+location.search+'&DemandeDevis=1']);
						_gaq.push(['_trackEvent','Demande de devis',parcoursTitle]);
						fbq('track', 'DemandeDevis', {content_ids:[parcoursID]});
					}
			});
		} else {
			$.ajax(	{type:"POST"
					,url:"/parcours/devis_request_2016.cfm"
					,contentType:"application/x-www-form-urlencoded; charset=iso-8859-1"
					,data:"parcours_id="+parcoursID+"&session_id="+dataString+"&devis_type=mini"
					,success:function(result){
						$("#actualDevisButton").trigger("click");
						_gaq.push(['_trackPageview',location.pathname+location.search+'&DemandeDevis=1']);
						_gaq.push(['_trackEvent','Demande de mini-devis','Demande de mini-devis']);
						fbq('track', 'DemandeDevis', {content_ids:['Mini-devis']});
					}
			});
		}
	}

	return false;
}

function isValidEmail(address) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(address);
}

function CallRequestSend() {
//				if (document.getElementById('call_request_submit').innerHTML=="Merci !") return;
	if (document.getElementById('call_request_email').value=="" ||
		document.getElementById('call_request_phone').value==""  ) return;
	if (document.getElementById('call_request_email').value=="Courriel" ||
		document.getElementById('call_request_phone').value=="Téléphone" ) return; // For IE
	if (!isValidEmail(document.getElementById('call_request_email').value)) {
		alert("L'adresse que vous avez entré ne semble pas valide.");
		document.getElementById('call_request_email').select();
		document.getElementById('call_request_email').focus();
		return false;
	}

	dataString  = "nom="+encodeURIComponent(btoa(document.getElementById('call_request_first_name').value+' '+document.getElementById('call_request_last_name').value));
	dataString += "&courriel="+encodeURIComponent(btoa(document.getElementById('call_request_email').value));
	dataString += "&telephone="+encodeURIComponent(btoa(document.getElementById('call_request_phone').value));
	dataString += "&jour="+encodeURIComponent(btoa(document.getElementById('call_request_day').options[document.getElementById('call_request_day').selectedIndex].value));
	dataString += "&heure="+encodeURIComponent(btoa(document.getElementById('call_request_time').options[document.getElementById('call_request_time').selectedIndex].value));
	$.ajax(	{type:"POST"
			,url:"/commun/call_request.cfm"
			,contentType:"application/x-www-form-urlencoded; charset=iso-8859-1"
			,data:dataString
			,success:function(result){
				$("#actualRDVButton").trigger("click");
				_gaq.push(['_trackPageview',location.pathname+location.search+'&DemandeRendezVous=1']);
				_gaq.push(['_trackEvent','Demande de rendez-vous',location.pathname+location.search]);
			}
	});
}

function MessageSend() {
	if (document.getElementById('courriel').value=="" ||
		document.getElementById('prenom').value=="" ||
		document.getElementById('nom').value=="" ||
		document.getElementById('objet').value==""  ) return;
	if (!isValidEmail(document.getElementById('courriel').value)) {
		alert("L'adresse que vous avez entré ne semble pas valide.");
		document.getElementById('courriel').select();
		document.getElementById('courriel').focus();
		return false;
	}

	dataString  = "nom="+encodeURIComponent(btoa(document.getElementById('prenom').value+' '+document.getElementById('nom').value));
	dataString += "&courriel="+encodeURIComponent(btoa(document.getElementById('courriel').value));
	dataString += "&objet="+encodeURIComponent(btoa(document.getElementById('objet').value));
	dataString += "&message="+encodeURIComponent(btoa(document.getElementById('textarea').value));
	$.ajax(	{type:"POST"
			,url:"/commun/message.cfm"
			,contentType:"application/x-www-form-urlencoded; charset=iso-8859-1"
			,data:dataString
			,success:function(result){
				$("#actualSendButton").trigger("click");
				_gaq.push(['_trackPageview',location.pathname+location.search+'&MessageViaSite=1']);
				_gaq.push(['_trackEvent','MessageViaSite',location.pathname+location.search]);
			}
	});
}

function newsletterSubscribe() {
	if (isValidEmail(document.getElementById('newsletterEmail').value)) {
		$.ajax({type:"POST"
			   ,url:"/infolettre.cfm"
			   ,data:"newsletter_email="+escape(document.getElementById('newsletterEmail').value)
			   ,success:function(result){
				 $("#subscribeButton").html("Inscription OK<br/>Merci !");
				 $("#newsletterForm").hide("fast");
				 $("#subscribeButton").css("background-color","#2BB561");
				_gaq.push(['_trackPageview',location.pathname+location.search+'&InscriptionInfolettre=1']);
				_gaq.push(['_trackEvent','Inscription infolettre',location.pathname+location.search]);
			   }
			  });
	} else {
		alert("L'adresse que vous avez entré ne semble pas valide.");
		document.getElementById('newsletterEmail').select();
		document.getElementById('newsletterEmail').focus();
	}
	return false;
}

// <!--Start of Tawk.to Script-->
var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
(function(){
	var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
	s1.async=true;
	s1.src='https://embed.tawk.to/5729f79a6db93c444738a8ca/default';
	s1.charset='UTF-8';
	s1.setAttribute('crossorigin','*');
	s0.parentNode.insertBefore(s1,s0);
})();
// <!--End of Tawk.to Script-->