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
				<div class="page-header">
  					<h1>${paper.title}</h1>
				</div>
			<div class="blog-main">
				<div class="row">
					<div class="paper col-md-8">
						<p class="paper-meta">${paper.type.name}</p>
						<h3>Authors</h3>
						<c:forEach var="pa" items="${authors}" varStatus="loop">
						<span class="span-author">
							<a href="${pageContext.request.contextPath}/author/${pa.author.id}/${pa.author.slug}">${pa.author.firstName}
										${pa.author.lastName}</a>
						</span>
							<c:choose>
								<c:when test="${paper.authorsSize == 2}">
									<c:if test="${loop.first}"><span> and </span></c:if>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${loop.count < paper.authorsSize - 1}"><span class="span-coma">, </span></c:when>
										<c:otherwise>
											<c:if test="${paper.authorsSize != 1 && loop.count == paper.authorsSize - 1}"> and </c:if>
										</c:otherwise>	
									</c:choose>		
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<h3>Abstract</h3>
						<blockquote>${paper.paperAbstract}</blockquote>
						<h3>Keywords</h3>
						<p>${paper.keywords}</p>
					</div>
					<div class="col-md-3 col-md-offset-1">
						<div class="sidebar-module sidebar-module-inset">
							<h4>Download</h4>
							<c:if test="${paper.url != null}">
								<p><a href="${paper.url}"><i class="fa fa-fw fa-file-pdf-o"></i> Springer</a></p>
							</c:if>
							<c:if test="${hasAttachments}">
								<p><a href="${pageContext.request.contextPath}/attachment/paper/${paper.id}"><i class="fa fa-fw fa-file-pdf-o"></i> PDF</a></p>
							</c:if>
						</div>
						<div class="sidebar-module sidebar-module-inset">
							<h4>Export citation</h4>
							<ul class="list-inline">
								<li><a href="${pageContext.request.contextPath}/paper/${paper.id}/citation/bibtex"><i class="fa fa-fw fa-file-text"></i> BibTex</a></li>
							</ul>
						</div>
						<div class="sidebar-module">
							<h4>Conferences</h4>
							<ul class="list-unstyled">
								<c:forEach var="conf" items="${conferences}">
									<li><a href="${pageContext.request.contextPath}/conferences/${conf.year}/${conf.id}">${conf.title}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	

	<!-- /container -->


	<jsp:include page="fragments/footer.jsp" />
</body>
</html>
