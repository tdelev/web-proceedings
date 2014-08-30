<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
<jsp:include page="fragments/head.jsp" />

<body>
<jsp:include page="fragments/menu.jsp" />

<div class="container">
		<ol class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}/">Conferences</a></li>
			<li><a href="${pageContext.request.contextPath}/authors/">Authors index</a></li>
			<li class="active">${author.lastName}, ${author.firstName}</li>
		</ol>

		<div class="page-header">
	  		<h1>${author.firstName} ${author.lastName} <small>Papers (${total})</small></h1>
		</div>
	
		<c:forEach var="paper" items="${papers}">
			<%@include file="fragments/paper_item.jsp" %>
      	</c:forEach>

    </div> <!-- /container -->


    <jsp:include page="fragments/footer.jsp"/>
    
  </body>
</html>
