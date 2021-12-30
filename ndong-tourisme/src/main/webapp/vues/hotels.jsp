<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
  
     <link type="text/css" rel="stylesheet" href="<c:url value="/util/css/tousServicesCss.css"/>" />
     
     <t:layout>
 	 	<jsp:attribute name="body_area">
 	 	
 	 	
 	 	<h3>TOUS NOS HOTELS</h3>
 	 	
		
		
 	 	
 		<c:choose>
 	 	
 	 	 	<c:when test="${ empty listHotels }">
 				<p class="erreur">Aucun Hotel enregistré.</p>
 			</c:when>
 		<%-- Sinon, affichage du tableau. --%>
		    <c:otherwise>
		    
		     <form method="get" action="${pageContext.request.contextPath}/Routeur">
		     <input type="hidden"  name="action" value="Hotels">
		     
		     <div id="choixVille">
		     <select name="ville" id="listeVilles">
 			<option value="">Choisissez une ville...</option>
			 <c:forEach items="${ villes }" var="ville">
			 <option value="${ ville }">${ ville }</option>
			  </c:forEach>
			 </select>
			 
			 <select name="etoile" id="listeEtoiles">
			 <option value="0">Etoiles</option>
			 <c:forEach items="${ etoiles }" var="etoile">
			 <option value="${ etoile }">${ etoile }</option>
			 </c:forEach>
			 </select>
			 
			 <input id="boutonVille" type="submit" value="Lancer la recherche">
			 </div>
		     
 				<table>
						 <tr>
							 <th>Référence</th>
							 <th>Nom</th>
							 <th>Description</th>
							  <th>Etoiles</th>
							   <th>Nombre Chambres</th>
							 <th>Adresse</th>
							 <th>Ville</th>
							 <th>Pays</th>
							 <th>Lien</th>
 
 						</tr>
				 <%-- Parcours de la Map des Service, et utilisation de l'objet
				varStatus. --%>
					 <c:forEach items="${ listHotels }" var="hotel" varStatus="boucle">
						 <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de
						fond de chaque ligne du tableau. --%>
						 <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
						 <%-- Affichage des propriétés du Service --%>
						 <td><c:out value="${ hotel.id }"></c:out></td>
						 <td><c:out value="${ hotel.nom }"></c:out></td>
						 <td><c:out value="${ hotel.description }"></c:out></td>
						 <td><c:out value="${ hotel.classement }"></c:out></td>
						 <td><c:out value="${ hotel.nbChambres }"></c:out></td>
						 <td><c:out value="${ hotel.adresse.adresse }"></c:out></td>
						 <td><c:out value="${ hotel.adresse.ville }"></c:out></td>
						 <td><c:out value="${ hotel.adresse.pays }"></c:out></td>
						 <td class="choix"><a href="<c:url value="DetailService?idService=${ hotel.id }"></c:url>">Voir cet Hotel</a></td>
						 </tr>
					</c:forEach>
			 </table>
			 
			 
			
		
			 </form>
 		</c:otherwise>
	 </c:choose>
	 
	 
	 
 	 		
 	 	<div id="blocPageSuivante">
 	 	
 	 	 <c:forEach items="${ listePage }" var="key" >
 	 	 <span id="pageSuivante"><a href="<c:url value="Hotels?indexPage=${ key }"></c:url>">${ key }</a></span>
 	 	 
 	 	 </c:forEach>
 	 	
		</div>
	 
	 
	 
	
 	 	
 	 	
	</jsp:attribute>
	</t:layout>
	
	