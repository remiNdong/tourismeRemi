<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
  
  <link type="text/css" rel="stylesheet" href="<c:url value="/util/css/detailServiceCss.css"/>" />
 
 
     <t:layout>
 	 	<jsp:attribute name="body_area">
 	 	
 	 	<div id="formConteneur" >
 	 	
 	 	 <form class="monform" method="post" action="${pageContext.request.contextPath}/Offres">
 	 	
 	 		
		 		
				
 	 	
 	 	<h3>DETAIL DU SERVICE</h3>
 	 	
 	 	
 	 	
 	 	<fieldset>
 	 	<legend id="nomService"><c:out value="${ service.nom }"></c:out></legend>
 	 	
 	 	
 	 	<p>Référence: <c:out value="${ service.id }"></c:out></p>
 	 	<p><c:out value="${ service.description }"></c:out></p>
 	 	<p><c:out value="${ service.adresse } "></c:out></p>
 	 	
 	 	<c:choose>
 	 	
 	 	<c:when test="${typeService == 'hotel'}">
 	 	
 	 	<p>Etoiles : 
 			<c:forEach var="i" begin="1" end="${ service.classement }" step="1">
 			<img src="<c:url value="/images/etoile.jpg"/>"/>
			</c:forEach>
		</p>
		<p>Nombre de Chambres : <c:out value="${ service.nbChambres }"></c:out></p>
 		
		</c:when>
		
		
		<c:when test="${typeService == 'restaurant'}">
		<p>Capacité :  <c:out value="${ service.capacite }"></c:out></p>
 		<p>Terrasse :  <c:out value="${ service.terrasse }"></c:out></p>
 		<p>Réservation :  <c:out value="${ service.reservation }"></c:out></p>

		</c:when>
		
		 <c:otherwise>
		 
		 <p>Type :  <c:out value="${ service.type }"></c:out></p>
		 <p>Durée :  <c:out value="${ service.duree } minutes  Début : ${ service.heureDebut }   Fin : ${ service.heureFin }"></c:out></p>
		 
		

		 </c:otherwise>
		 
		 </c:choose>
 	 	
 	 	
 	 	
 	 	
 	 	
 	 	
 	 	
 	 	
 	 	</fieldset>
 	 	
 	 	
 	 	 
 	 	 <input type="hidden"  name="idService" value="${service.id}"/>
 	 	 
 	 	 <p class="offre"><input type="radio"  name="action" value="offreDuMoment">Voir l'offre du moment</p>
 	 	 <p class="offre"><input type="radio"  name="action" value="listeOffres" checked>Voir toutes les offres</p>
 	 	 
 	 	 <input style="background-image: url('images/palmier.jpg');" id="monFormSubmit" type="submit"   value="C'est parti"/> 
 	 	
 	 	  </form>
 	 	
 	 	 
 	 	  <form onsubmit="return verifForm()" class="monform" method="get" action="${pageContext.request.contextPath}/DetailService">
 	 	 
 	 	 <h3>Voir les Commentaires <img src="<c:url value="/images/fleche.png"/>"/>
 	 	 <input type="radio" name="visuCommentaire" value="Oui" ${newCommentaire ?  'checked':'' } />Oui
 	 	 <input type="radio" name="visuCommentaire" value="Non"  ${!newCommentaire ?  'checked':'' }/>Non
 	 	 
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
 	 	 
 	 	 
 	 	
 	 	 
 	 	
 	 	 
 	 	 <input type="hidden"  name="idService" value="${service.id}"/>
 	 	 
 	 	 <p class="offre"><input id="radioCommentaire" type="radio"  name="action" value="ajoutCommentaire" />Ajouter un commentaire</p>
 	 	 <div><textarea id="areaCommentaire" name="com" rows="7" cols="30" maxlength="210"></textarea></div>
 	 		<div> 
		 	 	<label for="nom">Prénom</label>
					<input type="text" id="prenomInternaute" name="prenomInternaute" />
					 <label for="nom">Nom</label>
					<input type="text" id="nomInternaute" name="nomInternaute" />
					 <label for="nom">Mail</label>
					<input type="text" id="mailInternaute" name="mailInternaute" />
				</div>
				
				<div class="error" id="erreurPrenom"></div>
				    <div class="error" id="erreurNom"></div>
				     <div class="error" id="erreurMail"></div>
				     <div class="error" id="erreurArea"></div>
				      <div class="error" id="erreurRadio"></div>
				
					  <select name="classement">
 						<option value="1">Notez le service...</option>
 						<option value="1">1</option>
 						<option value="2">2</option>
 						<option value="3">3</option>
 						<option value="4">4</option>
 						<option value="5">5</option>
 						 </select>
			
				 
				   
				     <c:choose>
 	 	
 	 					<c:when test="${typeService == 'hotel'}">
 	 	
		 	 	 				 <select name="propreteHotel">
		 						<option value="1">Notez la propreté...</option>
		 						<option value="1">1</option>
		 						<option value="2">2</option>
		 						<option value="3">3</option>
		 						<option value="4">4</option>
		 						<option value="5">5</option>
		 						 </select>
		 						 
		 						   <select name="calmeHotel">
		 						<option value="1">Notez le calme...</option>
		 						<option value="1">1</option>
		 						<option value="2">2</option>
		 						<option value="3">3</option>
		 						<option value="4">4</option>
		 						<option value="5">5</option>
		 						 </select>
 			 		
 		
						</c:when>
		
		
					<c:when test="${typeService == 'restaurant'}">
		
		 				 <select name="acceuilRestaurant">
 						<option value="1">Notez l'acceuil...</option>
 						<option value="1">1</option>
 						<option value="2">2</option>
 						<option value="3">3</option>
 						<option value="4">4</option>
 						<option value="5">5</option>
 						 </select>
 						 
 						   <select name="qualiteRestaurant">
 						<option value="1">Notez la qualite...</option>
 						<option value="1">1</option>
 						<option value="2">2</option>
 						<option value="3">3</option>
 						<option value="4">4</option>
 						<option value="5">5</option>
 						 </select>
 						 
 						 
		

					</c:when>
		
		
		
			 <c:otherwise>
		 
		 	   <select name="sensationsActivite">
 						<option value="1">Notez les sensations...</option>
 						<option value="1">1</option>
 						<option value="2">2</option>
 						<option value="3">3</option>
 						<option value="4">4</option>
 						<option value="5">5</option>
 						 </select>
 						 
 						   <select name="accompagnateursActivite">
 						<option value="1">Notez les accompagnateurs...</option>
 						<option value="1">1</option>
 						<option value="2">2</option>
 						<option value="3">3</option>
 						<option value="4">4</option>
 						<option value="5">5</option>
 						 </select>
		

				 </c:otherwise>
		 
				 </c:choose>
			
			 <input  type="submit"   value="Ajoutez votre commentaire"/> 
 	 	 
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
 <c:if test="${!newCommentaire }">
 $("div#commentaireOui").hide();
</c:if>
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
  <script>
 
 function verifPrenom(){
	var prenomVar= document.getElementById("prenomInternaute");
	var erreurPrenom=document.getElementById("erreurPrenom");
	var resultat=true;
	 if(prenomVar.value==""){
		 resultat=false;
		 erreurPrenom.textContent="Entrez votre prénom";
	 }else{
		 erreurPrenom.textContent="";
	 }
	 return resultat;
 }
 
 function verifNom(){
		var nomVar= document.getElementById("nomInternaute");
		var erreurNom=document.getElementById("erreurNom");
		var resultat=true;
		 if(nomVar.value==""){
			 resultat=false;
			 erreurNom.textContent="Entrez votre nom";
		 }else{
			 erreurNom.textContent="";
		 }
		 return resultat;
	 }
 
 function verifMail(){
		var mailVar= document.getElementById("mailInternaute");
		var erreurMail=document.getElementById("erreurMail");
		var resultat=true;
		 if(mailVar.value==""){
			 resultat=false;
			 erreurMail.textContent="Entrez votre mail";
		 }else{
			 erreurMail.textContent="";
		 }
		 return resultat;
	 }
 
 function verifArea(){
		var mailVar= document.getElementById("areaCommentaire");
		var erreurMail=document.getElementById("erreurArea");
		var resultat=true;
		 if(mailVar.value==""){
			 resultat=false;
			 erreurArea.textContent="Entrez votre commentaire";
		 }else{
			 erreurArea.textContent="";
		 }
		 return resultat;
	 }
 
 function verifRadio(){
		var radioVar= document.getElementById("radioCommentaire");
		var erreurRadio=document.getElementById("erreurRadio");
		var resultat=true;
		 if(!radioVar.checked){
			 resultat=false;
			 erreurRadio.textContent="Cochez la case";
		 }else{
			 erreurRadio.textContent="";
		 }
		 return resultat;
	 }

 
 
 function verifForm(){
	    var r1=verifNom();
	    var r2=verifPrenom();
	    var r3=verifMail();
	    var r4=verifArea();
	    var r5=verifRadio();
	    
	   
	return r1 && r2 && r3 && r4 && r5 ;

	}
 
 
 </script>