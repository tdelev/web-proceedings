<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
<jsp:include page="fragments/head.jsp" />

<body>

	<jsp:include page="fragments/menu.jsp" />

	<div class="container">
		<div class="page-header">
			<h1>Login</h1>
		</div>

		<div class="row">

			<div class="col-md-6">
				<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
					<div class="form-group">
						<label for="username" class="col-sm-3 control-label">Username</label>
						<div class="col-sm-9">
							<input id="username" name="j_username" type="text"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-3 control-label">Password</label>
						<div class="col-sm-9">
							<input id="password" name="j_password" type="password"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9 col-sm-offset-3">
							<input type="submit" value="Login"
								class="btn btn-primary" />
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-6">
				<p class="alert alert-info" ></p>
			</div>

		</div>

	</div>
	<!-- /container -->


	<jsp:include page="fragments/footer.jsp" />

</body>
</html>
