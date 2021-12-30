<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
     <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
  
     <link type="text/css" rel="stylesheet" href="<c:url value="/util/css/tousServicesCss.css"/>" />
     
     <t:layout>
 	 	<jsp:attribute name="body_area">
 	 	
 	 	
 	 	<h3>DETAIL DE L'OFFRE DE  <span id="nomService"><c:out  value="${ fn:toUpperCase(offre.prestataire.nom) }"></c:out></span></h3>
 	 	
 	 	<fieldset id="fieldSetOffres">
 	 		<p>${offre.prestataire.adresse.adresse}</p>
			<p>${offre.prestataire.adresse.ville}</p>
			<p>${offre.prestataire.adresse.pays}</p>
			<p><a href="<c:url value="DetailService?idService=${ offre.prestataire.id }"></c:url>">Retour au Service</a></p>
		 </fieldset>
 	 	
		

		<div id="detailOffre">
			<p>Nombre de personnes : <c:out  value="${ offre.nbPersonnes }"></c:out></p>
			<p>Descriptif : <c:out  value="${ offre.descriptif }"></c:out></p>
			<p>Prix de base : <c:out  value="${ offre.prix } €"></c:out></p>
			<p id="prixCnam">Prix négocié pour vous  : <c:out  value="${ prixOffre } €"></c:out></p>

		</div>
	 

 	 		
 	 	
	 
	 
	
 	 	
 	 	
	</jsp:attribute>
	</t:layout>
	
	