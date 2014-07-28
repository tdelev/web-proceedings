<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
  <jsp:include page="fragments/head.jsp"/>

  <body>


	<jsp:include page="fragments/menu.jsp"/>

	
    <div class="container">
   
   			<div class="list-group jumbotron">
   				<div class="list-group-item">
				<h1 class="list-group-item-heading">
					<a href="${pageContext.request.contextPath}/conference/${latestConf.id}/${latestConf.titleSlug}">${latestConf.title}</a>
				</h1>
				<div class="row">
					<div class="col-md-12">
						<label>Editors</label>
						<p class="list-group-item-text">${latestConf.editors}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<label>Topic</label>
						<p class="list-group-item-text">${latestConf.topic}</p>
					</div>
					<div class="col-md-6">
						<label>Venue</label>
						<p class="list-group-item-text">${latestConf.venue}</p>
					</div>
				</div>
				</div>
			</div>
      	 <c:forEach var="conf" items="${conferences}">
      	 	<div class="list-group">
      	 		<div class="list-group-item">
				<h2 class="list-group-item-heading">
				<i class="fa fa-fw fa-angle-double-right"></i>
				<a href="${pageContext.request.contextPath}/conference/${conf.id}/${conf.titleSlug}">${conf.title}</a></h2>
					<p class="list-group-item-text conference-item-text"><label>Editors:</label> ${conf.editors}</p>
					<p class="list-group-item-text conference-item-text"><label>Topic:</label> ${conf.topic}</p>
				</div>
			</div>
      	 </c:forEach>

    </div> <!-- /container -->


    <jsp:include page="fragments/footer.jsp"/>
    
  </body>
</html>
