<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<style  type="text/css">
   .corps{
            display: flex;
            text-align: center;
            font-family: "Gill Sans", sans-serif;
            font-size: 14px;
        }

        img {
            
            width: 400px;
	        height: 300px;
        }

        .image{
            flex: 1 1 0em;   
        }

 </style>

 <t:layout>
 	 	<jsp:attribute name="body_area">


        <div id="conteneur">
        <div class="corps" >
            <span class="image" ><img src="<c:url value="/images/break_vacances.jpg"/>"/><p><a href="">Tous les services</a></p></span>
            <span class="image" ><img src="<c:url value="/images/hotel.jpg"/>"/><p><a href="">Hotels</a></p></span>

        </div>
        <div  class="corps">
         
            <span class="image" ><img src="<c:url value="/images/restaurant.jpg"/>"/><p><a href="">Restaurant</a></p></span>
            <span class="image" ><img src="<c:url value="/images/activite.jpg"/>"/><p><a href="">Activités</a></p></span>

        </div>
    </div>

	</jsp:attribute>
		</t:layout>


