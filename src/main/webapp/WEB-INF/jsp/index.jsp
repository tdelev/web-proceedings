<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
  <jsp:include page="fragments/head.jsp"/>

  <body>


	<jsp:include page="fragments/menu.jsp"/>

	
    <div class="container">
      	 <c:forEach var="conf" items="${conferences}">
      	 	<h2><a href="${pageContext.request.contextPath}/conference/${conf.id}/${conf.titleSlug}">${conf.title}</a></h2>
      	 </c:forEach>

    </div> <!-- /container -->


    <jsp:include page="fragments/footer.jsp"/>
    
  </body>
</html>
