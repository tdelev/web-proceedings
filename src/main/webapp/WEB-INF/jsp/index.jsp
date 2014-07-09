<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
  <jsp:include page="fragments/head.jsp"/>

  <body>


	<jsp:include page="fragments/menu.jsp"/>

	
    <div class="container">
    <c:set var="maxY" value="0"/>
      	 <c:forEach var="conf" items="${conferences}">
      	 		<c:set var="max" value="${conf.year}"/>
      	 		<c:if test="${max > maxY}">
      	 		<c:set var="maxY" value="${max}"/>
      	 		</c:if>
      	  </c:forEach>
      	
      	 		 <c:forEach var="conf" items="${conferences}">
      	 		<div class="list-group">
				<a
					href="${pageContext.request.contextPath}/conference/${conf.id}/${conf.titleSlug}"
					class="list-group-item">
					<c:choose><c:when test="${maxY eq conf.year}"><h2 class="list-group-item-heading" style="color:blue">${conf.title}</h2></c:when>
					<c:otherwise><h2 class="list-group-item-heading">${conf.title}</h2></c:otherwise></c:choose>
					<p class="list-group-item-text">Editors: ${conf.editors}</p>
					<p class="list-group-item-text">Title: ${conf.topic}</p>
					<p class="list-group-item-text">Venue: ${conf.venue}</p>
				</a>
			</div>
      	 </c:forEach>

    </div> <!-- /container -->


    <jsp:include page="fragments/footer.jsp"/>
    
  </body>
</html>
