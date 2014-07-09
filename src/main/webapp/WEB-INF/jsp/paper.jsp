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
			<li><a
				href="${pageContext.request.contextPath}/conference/${paper.conference.id}/${paper.conference.titleSlug}">${paper.conference.title}</a></li>
			<li class="active">${paper.title}</li>
		</ol>
		<div class="blog-header">
		<h2 class="blog-title">${paper.title}</h2>
		<p class="lead blog-description">${paper.type.name}</p>
			
				
				<h3 >Authors</h3>
				<c:forEach var="author" items="${authors}">
				<p >${author.author.firstName} ${author.author.lastName} </p>
				</c:forEach >
				<h3 >Citation</h3>
				<p>${paper.citation}</p>
				<h3 >Abstract</h3>
				<p >${paper.paperAbstract}</p>
				<h3>Keywords</h3>
				<p >${paper.keywords}</p>
				<h3 >URL</h3>
				<p >${paper.url}</p>
		</div>
	</div>
	<!-- /container -->


	<jsp:include page="fragments/footer.jsp" />

</body>
</html>
