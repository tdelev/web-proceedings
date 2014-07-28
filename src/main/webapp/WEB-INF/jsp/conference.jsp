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
			<c:forEach var="type" items="${types}">
  				<li><a href="#${type.id}">${type.name}</a></li>
  			</c:forEach>
		</ul>
	</div>
	<div class="papers">
		<c:forEach var="type" items="${types}">
			<h3 class="paper-type" id="${type.id}">${type.name}</h3>
			<c:forEach var="paper" items="${papersMap[type.id]}">
				<%@include file="fragments/paper_item.jsp" %>
			</c:forEach>
		</c:forEach>

	</div>
	</div>
	<!-- /container -->


	<jsp:include page="fragments/footer.jsp" />

</body>
</html>
