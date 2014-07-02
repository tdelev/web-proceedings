<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
  <jsp:include page="fragments/head.jsp"/>

  <body>

	<jsp:include page="fragments/menu.jsp"/>
	
    <div class="container">
    	<h2>${conference.title}</h2>
    	<h3>Papers</h3>
      	 <c:forEach var="paper" items="${papers}">
      	 	<h4><a href="${pageContext.request.contextPath}/${paper.conference.titleSlug}/${paper.id}/${paper.titleSlug}">${paper.title}</a></h4>
      	 </c:forEach>

    </div> <!-- /container -->


    <jsp:include page="fragments/footer.jsp"/>
    
  </body>
</html>
