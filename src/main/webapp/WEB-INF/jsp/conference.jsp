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
  		<%@include file="fragments/conference_details.jsp" %>
	</div>
	<div class="paper-types">
		<ul class="nav nav-pills">
			<li><a href="#preface"><i class="fa fa-fw fa-envelope"></i> Preface</a></li>
			<li><a href="#committees"><i class="fa fa-fw fa-group"></i> Committees</a></li>
			<li><a href="${pageContext.request.contextPath}/conferences/${conference.year}/${conference.id}/papers"><i class="fa fa-fw fa-book"></i> Papers</a></li>
			<li><a href="${pageContext.request.contextPath}/attachment/conference/${conference.id}/${conference.pdf}"><i class="fa fa-fw fa-file-pdf-o"></i> PDF</a></li>
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
