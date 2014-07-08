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
		<li><a href="${pageContext.request.contextPath}/conference/${paper.conference.id}/${paper.conference.titleSlug}">${paper.conference.title}</a></li>
		<li class="active">${paper.title}</li>
	</ol>
		<div class="panel panel-info">
			<div class="panel-heading">${paper.title}</div>
			<div class="panel-body">Panel content</div>
		</div>
	</div>
	<!-- /container -->


	<jsp:include page="fragments/footer.jsp" />

</body>
</html>
