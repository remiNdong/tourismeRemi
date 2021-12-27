<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
  
  <link type="text/css" rel="stylesheet" href="<c:url value="/util/css/detailServiceCss.css"/>" />
 
 
     <t:layout>
 	 	<jsp:attribute name="body_area">
 	 	
 	 	<div id="formConteneur" >
 	 	
 	 	 <form id="monform" method="post" action="${pageContext.request.contextPath}/detailService">
 	 	
 	 		<c:choose>
 	 	
		 		<c:when test="${typeService == 'hotel'}">
		 		<c:set var="service" value="${ hotel }"></c:set>
				</c:when>
		
				<c:when test="${typeService == 'restaurant'}">
		 		<c:set var="service" value="${ restaurant }"></c:set>
				</c:when>
		
			 <c:otherwise>
				 <c:set var="service" value="${ activite }"></c:set>
			 </c:otherwise>
		
		
			</c:choose>
 
 	 	
 	 	<h3>DETAIL DU SERVICE</h3>
 	 	
 	 	
 	 	
 	 	<fieldset>
 	 	<legend id="nomService"><c:out value="${ service.nom }"></c:out></legend>
 	 	
 	 	
 	 	<p>Id : <c:out value="${ service.id }"></c:out></p>
 	 	<p><c:out value="${ service.description }"></c:out></p>
 	 	<p><c:out value="${ service.adresse } "></c:out></p>
 	 	
 	 	<c:choose>
 	 	
 	 	<c:when test="${typeService == 'hotel'}">
 	 	
 	 	<p>Etoiles : 
 			<c:forEach var="i" begin="1" end="${ hotel.classement }" step="1">
 			<img src="<c:url value="/images/etoile.jpg"/>"/>
			</c:forEach>
		</p>
		<p>Nombre de Chambres : <c:out value="${ hotel.nbChambres }"></c:out></p>
 		
		</c:when>
		
		
		<c:when test="${typeService == 'restaurant'}">
		<p>Capacité :  <c:out value="${ restaurant.capacite }"></c:out></p>
 		<p>Terrasse :  <c:out value="${ restaurant.terrasse }"></c:out></p>
 		<p>Réservation :  <c:out value="${ restaurant.reservation }"></c:out></p>

		</c:when>
		
		 <c:otherwise>
		 
		 <p>Type :  <c:out value="${ activite.type }"></c:out></p>
		 <p>Durée :  <c:out value="${ activite.duree } minutes  Début : ${ activite.heureDebut }   Fin : ${ activite.heureFin }"></c:out></p>
		 
		

		 </c:otherwise>
		 
		 </c:choose>
 	 	
 	 	
 	 	
 	 	
 	 	
 	 	
 	 	
 	 	
 	 	</fieldset>
 	 	
 	 	
 	 	 
 	 	 <input type="hidden"  name="idService" value="${service.id}"/>
 	 	 
 	 	 <p class="offre"><input type="radio"  name="action" value="offreDuMoment">Voir l'offre du moment</p>
 	 	 <p class="offre"><input type="radio"  name="action" value="listOffres">Voir toutes les offres</p>
 	 	 
 	 	 <input id="monFormInput" type="submit"   value="C'est parti"/>  
 	 	 
 	 	
 	 	 
 	 	 
 	 	 <h3>Voir les Commentaires <img src="<c:url value="/images/fleche.png"/>"/>
 	 	 <input type="radio" name="visuCommentaire" value="Oui"/>Oui
 	 	 <input type="radio" name="visuCommentaire" value="Non"/>Non
 	 	 
 	 	  </h3>
 	 	 
 	 	 
 	 	 
 	 	  <div id="commentaireNon">
 	 	  
 	 	  </div>
 	 	 
 	 	 <div id="commentaireOui">
 	 	 
 	 	 	
 	 	 	
 	 	 	<ol>
 	 	 		 <c:forEach items="${ service.notations }" var="notation" >
 	 	 		 
 	 	 		 <c:choose>
 	 	
 	 	<c:when test="${typeService == 'hotel'}">
 	 	
 			 <li><c:out value="${ notation.internaute.prenom } ${ notation.internaute.nom } :
 			  Note =${ notation.nbEtoiles } , Propreté = ${ notation.propreteHotel } , Calme 
 			    ${ notation.calmeHotel } ,  Commentaire = ${ notation.commentaire } "></c:out></li>
 		
		</c:when>
		
		
		<c:when test="${typeService == 'restaurant'}">
		
		 <li><c:out value="${ notation.internaute.prenom } ${ notation.internaute.nom } : Note =${ notation.nbEtoiles } ,
 			  Acceuil =${ notation.acceuilRestaurant } , Qualité = ${ notation.qualiteRestaurant }  
 			     ,  Commentaire = ${ notation.commentaire } "></c:out></li>
		

		</c:when>
		
		
		
		 <c:otherwise>
		 
		 	 <li><c:out value="${ notation.internaute.prenom } ${ notation.internaute.nom } : Note =${ notation.nbEtoiles } ,
 			  Sensations =${ notation.sensationsActivite } , Accompagnateur = ${ notation.qualiteAccompagnateur }  
 			     ,  Commentaire = ${ notation.commentaire } "></c:out></li>
		

		 </c:otherwise>
		 
		 </c:choose>
 	 	 		 
 	 	 		 
 	 	 		 
 	 	 		 
 	 	 		 </c:forEach>
 	 	 		 
 	 	 </ol>
 	 	 
 	 	 
 	 	 </div>
 	 	 
 	 	 </form>
 	 	 
 	 	 </div>
 	 	 

 	 	 
 	 	
 	 	
	</jsp:attribute>
	</t:layout>
	
	 	 	  <script src="<c:url value="/util/jquery.js"></c:url>"></script>
 
 <%-- Petite fonction jQuery permettant l'activation des commentaires --%>
 <script>
 jQuery(document).ready(function(){
 /* 1 - Au lancement de la page, on cache le bloc d'éléments du formulaire
correspondant aux commentaires */
 $("div#commentaireOui").hide();
 /* 2 - Au clic sur un des deux boutons radio "choixNouveauClient", on affiche le
bloc d'éléments correspondant (nouveau ou ancien client) */
 jQuery('input[name=visuCommentaire]:radio').click(function(){
 $("div#commentaireOui").hide();
 $("div#commentaireNon").hide();
 var divId = jQuery(this).val();
 $("div#commentaire"+divId).show();
 });
 });
 </script>