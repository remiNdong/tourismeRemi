<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

  <link type="text/css" rel="stylesheet" href="<c:url value="/util/css/indexCss.css"/>" />
	


 <t:layout>
 	 	<jsp:attribute name="body_area">

<form method="get" action="${pageContext.request.contextPath}/Routeur">
	<input type="hidden"  name="indexPage" value="${1}">
	
        
        <div class="corps" >
        
            <span  class="image" ><input id="monFormSubmit" style="background-image: url('images/break_vacances.jpg');" class="imageSubmit" type="submit"  name="action" value="Tous Les Services"></span>
              <span  class="image" ><input id="monFormSubmit2"  style="background-image: url('images/hotel.jpg');" class="imageSubmit" type="submit"  name="action" value="Hotels"></span>

        </div>
        <div  class="corps">
         
             <span  class="image" ><input  id="monFormSubmit3" style="background-image: url('images/restaurant.jpg');" class="imageSubmit" type="submit"  name="action" value="Restaurants"></span>
              <span  class="image" ><input id="monFormSubmit4"  style="background-image: url('images/activite.jpg');" class="imageSubmit" type="submit"  name="action" value="Activites"></span>

        </div>
        
        </form>
  

	</jsp:attribute>
		</t:layout>


