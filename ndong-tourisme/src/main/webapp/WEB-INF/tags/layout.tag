     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@tag description="Simple Template" pageEncoding="UTF-8"%>
 <%@attribute name="body_area" fragment="true" %>

 <html>
  <body>
   <head>
        <meta charset="utf-8" />
        <title>Cnam Tourisme</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/util/css/style.css"/>" />
  
 </head>
 

 

 
    <header>
        <h1>Bienvenue chez Cnam Tourisme</h1> 
        <div id="navigation">
        <span class="entree"><a href="<c:url value="Routeur?indexPage=1&action=Tous+Les+Services"/>">Tous les services</a></span>
        <span class="entree"><a href="<c:url value="Routeur?indexPage=1&action=Hotels"/>">Hotels</a></span>
        <span class="entree"><a href="">Restaurant</a></span>
        <span class="entree"><a href="">Activités</a></span>
    	</div>
    </header>
    
    
    
 	<div class="conteneur">
  		 <jsp:invoke fragment="body_area"/>
 	</div>
 
  
  	<footer>
          
        <span class="entree"><a href="${pageContext.request.contextPath}/Acceuil">Accueil</a></span>
        <p>Contactez nous : cnam-tourisme@lecnam.net</p>
   	 </footer>
    


 </body>
    
  
 
</html>