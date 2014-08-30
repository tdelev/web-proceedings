<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
<jsp:include page="fragments/head.jsp" />

<body>

	<jsp:include page="fragments/menu.jsp" />
	
	<div class="container">
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Conferences</a></li>
		<li  class="active">${conference.title}</li>
	</ol>
	<div class="page-header">
  		<h1>${conference.title} <small>Web proceedings ${conference.issn}</small></h1>
		<div class="conference-info">
			<div class="row">
				<div class="col-md-6">
					<label>Editors</label>
					<p class="paper-meta">${conference.editors}</p>
				</div>
				<div class="col-md-6">
					<label>Topic</label>
					<p class="paper-meta">${conference.topic}</p>
				</div>
			</div>
		</div>
	</div>
	<div class="paper-types">
		<ul class="nav nav-pills">
			<li><a href="#preface">Preface</a></li>
			<li><a href="#committees">Committees</a></li>
			<li><a href="${pageContext.request.contextPath}/conferences/${conference.year}/${conference.id}/papers">Papers</a></li>
			<li><a href="${pageContext.request.contextPath}/attachment/conference/${conference.id}"><i class="fa fa-fw fa-file-pdf-o"></i> PDF</a></li>
		</ul>
		</div>
		<div id="preface" class="preface">
			${preface}
		</div>
		<div id="committees" class="committees">
			${committees}
		</div>
	</div>
	<!-- /container -->


	<jsp:include page="fragments/footer.jsp" />

</body>
</html>
