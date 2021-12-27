<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

  <link type="text/css" rel="stylesheet" href="<c:url value="/util/css/indexCss.css"/>" />
	


 <t:layout>
 	 	<jsp:attribute name="body_area">


        
        <div class="corps" >
            <span class="image" ><img src="<c:url value="/images/break_vacances.jpg"/>"/><p><a href="${pageContext.request.contextPath}/TousServices">Tous les services</a></p></span>
            <span class="image" ><img src="<c:url value="/images/hotel.jpg"/>"/><p><a href="">Hotels</a></p></span>

        </div>
        <div  class="corps">
         
            <span class="image" ><img src="<c:url value="/images/restaurant.jpg"/>"/><p><a href="">Restaurant</a></p></span>
            <span class="image" ><img src="<c:url value="/images/activite.jpg"/>"/><p><a href="">Activités</a></p></span>

        </div>
  

	</jsp:attribute>
		</t:layout>


