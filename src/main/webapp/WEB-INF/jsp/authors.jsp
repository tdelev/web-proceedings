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
			<li class="active">Authors index</li>
		</ol>
		
		<div class="page-header">
	  		<h1>Authors index <small>(${total})</small></h1>
		</div>
		<div class="index-letters">
		<c:forEach var="key" items="${letters}">
			<h2><a href="#${key}">${key}</a></h2>
		</c:forEach>
		</div>
		<div class="index-authors">
			<c:forEach var="key" items="${letters}">
				<c:if test="${not empty index[key]}">
					<h2 class="index-title" id="${key}">${key}</h2>
					<ul class="index">
					<c:forEach var="author" items="${index[key]}">
						<li><a href="${pageContext.request.contextPath}/authors/${author.id}/${author.slug}">${author.lastName}, ${author.firstName}</a></li>
					</c:forEach>
					</ul>
				</c:if>
			</c:forEach>
		</div>
    </div> <!-- /container -->


    <jsp:include page="fragments/footer.jsp"/>
    
  </body>
</html>
