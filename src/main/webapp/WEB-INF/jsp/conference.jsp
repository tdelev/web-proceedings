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
		<li  class="active">${conference.title}</li>
	</ol>
		<h2>${conference.title}</h2>
		<h3>Papers</h3>
		<c:forEach var="paper" items="${papers}">
			<div class="list-group">
			<li class="list-group-item">
				<a href="${pageContext.request.contextPath}/${paper.conference.titleSlug}/paper/${paper.id}/${paper.titleSlug}">
					<h4 class="list-group-item-heading">${paper.title}</h4>
				</a>
				<c:forEach var="pa" items="${paper.paperAuthors}">
				<h4 class="list-group-item-text">${pa.author.firstName} ${pa.author.lastName}</h4>
				</c:forEach>
				</li>
				
			</div>

		</c:forEach>

	</div>
	<!-- /container -->


	<jsp:include page="fragments/footer.jsp" />

</body>
</html>
