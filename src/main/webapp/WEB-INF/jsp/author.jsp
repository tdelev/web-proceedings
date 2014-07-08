<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
<jsp:include page="fragments/head.jsp" />

<body>

	<jsp:include page="fragments/menu.jsp" />

	<div class="container">
		<c:forEach var="author" items="${authors}">
			<h4><a href="${pageContext.request.contextPath}/author/${author.id}/${author.firstName}-${author.lastName}">${author.firstName} ${author.lastName}</a></h4>
      	 </c:forEach>

    </div> <!-- /container -->


    <jsp:include page="fragments/footer.jsp"/>
    
  </body>
</html>
