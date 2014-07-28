<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
  <jsp:include page="fragments/head.jsp"/>

  <body>


	<jsp:include page="fragments/menu.jsp"/>

	
    <div class="container">
   
   			<div class="list-group jumbotron">
   				<div class="list-group-item">
				<h1 class="list-group-item-heading">
					No conferences
				</h1>
				<div class="row">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/admin/">Add conference in admin</a>
					</div>
				</div>
				</div>
			</div>
    </div> <!-- /container -->


    <jsp:include page="fragments/footer.jsp"/>
    
  </body>
</html>
