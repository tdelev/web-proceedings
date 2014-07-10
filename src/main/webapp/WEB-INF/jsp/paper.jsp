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
			<div class="col-sm-8 blog-main">
				<div class="blog-post">
					<h2 class="blog-post-title">${paper.type.name}</h2>
					<p class="blog-post-meta">${paper.type.name}</p>
					<h2>Authors</h2>
					<c:forEach var="author" items="${authors}">
						<p>
							<a
								href="${pageContext.request.contextPath}/author/${author.author.id}/${author.author.firstName}-${author.author.lastName}">${author.author.firstName}
								${author.author.lastName}</a>
						</p>
					</c:forEach>
					<h2>Citation</h2>
					<p>${paper.citation}</p>
					<h2>Abstract</h2>
					<blockquote>${paper.paperAbstract}</blockquote>
					<h2>Keywords</h2>
					<p>${paper.keywords}</p>
					<h2>URL</h2>
					<p>${paper.url}</p>
				</div>
			</div>
		</div>
	

	<!-- /container -->


	<jsp:include page="fragments/footer.jsp" />
</body>
</html>
