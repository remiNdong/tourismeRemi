<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
     <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
  
     <link type="text/css" rel="stylesheet" href="<c:url value="/util/css/tousServicesCss.css"/>" />
     
     <t:layout>
 	 	<jsp:attribute name="body_area">
 	 	
 	 	
 	 	<h3>TOUTES LES OFFRES DE <span id="nomService"><c:out  value="${ fn:toUpperCase(service.nom) }"></c:out></span> </h3>
 	 	
 	 	<fieldset id="fieldSetOffres">
 	 		<p>${service.adresse.adresse}</p>
			<p>${service.adresse.ville}</p>
			<p>${service.adresse.pays}</p>
			<p><a href="<c:url value="DetailService?idService=${ service.id }"></c:url>">Retour au Service</a></td></p>
		 </fieldset>
 	 	
		
		
 	 	
 		<c:choose>
 	 	
 	 	 	<c:when test="${ empty listeOffres }">
 				<p class="erreur">Aucune Offre proposée.</p>
 			</c:when>
 		<%-- Sinon, affichage du tableau. --%>
		    <c:otherwise>
		    
		     
		 
		     
 				<table id="tabledesOffres">
						 <tr>
							 <th>Réfèrence</th>
							 <th>Nombre de Personnes</th>
							 <th>Prix</th>
							 <th>Descriptif</th>
							 <th>Lien</th>
							 
 
 						</tr>
				 <%-- Parcours de la Map des Service, et utilisation de l'objet
				varStatus. --%>
					 <c:forEach items="${listeOffres }" var="offre" varStatus="boucle">
						 <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de
						fond de chaque ligne du tableau. --%>
						 <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
						 <%-- Affichage des propriétés du Service --%>
						 <td><c:out value="${ offre.id }"></c:out></td>
						 <td><c:out value="${ offre.nbPersonnes }"></c:out></td>
						 <td><c:out value="${ offre.prix }"></c:out></td>
						 <td><c:out value="${ offre.descriptif }"></c:out></td>
						 <td class="choix"><a href="<c:url value="DetailOffre?idOffre=${ offre.id }"></c:url>">Découvrer le prix abonné CNAM Tourisme</a></td>
						 </tr>
					</c:forEach>
			 </table>
			 
			 
			
		
 		</c:otherwise>
	 </c:choose>
	 
	 
	 
 	 		
 	 	<div id="blocPageSuivante">
 	 	
 	 	 <c:forEach items="${ listePage }" var="key" >
 	 	 <span id="pageSuivante"><a href="<c:url value="OffresDuService?indexPage=${ key }"></c:url>">${ key }</a></span>
 	 	 
 	 	 </c:forEach>
 	 	
		</div>
	 
	 
	 
	
 	 	
 	 	
	</jsp:attribute>
	</t:layout>
	
	