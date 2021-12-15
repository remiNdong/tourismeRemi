<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
     
     <style  type="text/css">
     
     
/* Tableaux -------------------------------------------------------------------------------------*/
table{
	border-collapse: collapse;
	font-family: "Gill Sans", sans-serif;
	font-size: 14px;
	text-align: center;
}
tr.pair{
	background-color:  rgb(192, 224, 240);
}
tr.impair{
	background-color: rgb(114, 193, 233);
}
th{
	color: #0568CD;
	border: 1px solid #0568CD;
	padding: 5px;
}

td{
	border: 1px solid #ddd;
	padding: 5px;
	width: 20em;
	
}
td{
	height: 5em;
}

h3{
	color : rgb(19, 40, 224);
	font-size: 18px;
}

     
     </style>
     
     <t:layout>
 	 	<jsp:attribute name="body_area">
 	 	
 	 	<h3>TOUS NOS SERVICES</h3>
 	 	
 		<c:choose>
 	 	
 	 	 	<c:when test="${ empty listServices }">
 				<p class="erreur">Aucun Service enregistré.</p>
 			</c:when>
 		<%-- Sinon, affichage du tableau. --%>
		    <c:otherwise>
 				<table>
						 <tr>
							 <th>Id</th>
							 <th>Nom</th>
							 <th>Description</th>
							 <th>Adresse</th>
							 <th>Ville</th>
							 <th>Pays</th>
							 <th>Lien</th>
 
 						</tr>
				 <%-- Parcours de la Map des Service, et utilisation de l'objet
				varStatus. --%>
					 <c:forEach items="${ listServices }" var="service" varStatus="boucle">
						 <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de
						fond de chaque ligne du tableau. --%>
						 <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
						 <%-- Affichage des propriétés du Service --%>
						 <td><c:out value="${ service.id }"></c:out></td>
						 <td><c:out value="${ service.nom }"></c:out></td>
						 <td><c:out value="${ service.description }"></c:out></td>
						 <td><c:out value="${ service.adresse.adresse }"></c:out></td>
						 <td><c:out value="${ service.adresse.ville }"></c:out></td>
						 <td><c:out value="${ service.adresse.pays }"></c:out></td>
						 <td><a href="<c:url value=""></c:url>">Voir ce Service</a></td>
						 </tr>
					</c:forEach>
			 </table>
 		</c:otherwise>
	 </c:choose>
 	 	
 	 	
	</jsp:attribute>
	</t:layout>