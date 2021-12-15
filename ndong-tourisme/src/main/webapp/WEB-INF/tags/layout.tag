     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@tag description="Simple Template" pageEncoding="UTF-8"%>
 <%@attribute name="body_area" fragment="true" %>

 <html>
   <head>
        <meta charset="utf-8" />
        <title>Cnam Tourisme</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/util/style.css"/>" />
  
 </head>
    <div class="entete">
        <h1>Bienvenue chez Cnam Tourisme</h1> 
        <div id="navigation">
        <span class="entree"><a href="${pageContext.request.contextPath}/TousServices">Tous les services</a></span>
        <span class="entree"><a href="">Hotels</a></span>
        <span class="entree"><a href="">Restaurant</a></span>
        <span class="entree"><a href="">Activités</a></span>
    </div>
    </div>
 <body>
   <jsp:invoke fragment="body_area"/>
 </body>
  <div class="bas-de-page">
          
        <span class="entree"><a href="${pageContext.request.contextPath}/Acceuil">Accueil</a></span>
        <p>Contactez nous : cnam-tourisme@lecnam.net</p>
    </div>
 
</html>