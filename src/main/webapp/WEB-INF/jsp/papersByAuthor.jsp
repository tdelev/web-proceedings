<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
<jsp:include page="fragments/head.jsp" />

<body>
<div class="container">
	<div class="page-header">
  <h2>Papers by: ${author.firstName} ${author.lastName}</h2>
</div>
	<jsp:include page="fragments/menu.jsp" />
	
		<c:forEach var="paper" items="${papers}">
	<div class="list-group">
	<li class="list-group-item">
    <h4 class="list-group-item-heading"><a href="${pageContext.request.contextPath}/${paper.conference.titleSlug}/paper/${paper.id}/${paper.titleSlug}">${paper.title}</a></h4>
    <p class="list-group-item-text">${paper.citation}</p>
  </li>
</div>
      	 </c:forEach>

    </div> <!-- /container -->


    <jsp:include page="fragments/footer.jsp"/>
    
  </body>
</html>
