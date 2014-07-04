<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
   
    <!-- <link rel="shortcut icon" href="../../assets/ico/favicon.ico"> -->

    <title>Web Proceedings</title>

    <!-- Bootstrap core CSS -->
    
    <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/font-awesome.min.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/ng-table/ng-table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/AngularJS-Toaster/toaster.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/select2/select2.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/select2/select2-bootstrap3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/angular-motion/dist/angular-motion.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body ng-app="WP">

	<!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation" ng-controller="HeaderController">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a ng-class="{ active: isActive('/')}" class="navbar-brand" href="${pageContext.request.contextPath}/">Web proceedings - Admin</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li ng-class="{ active: isActive('/admin')}"><a href="${pageContext.request.contextPath}/admin">Home</a></li>
              <li ng-class="{ active: isActive('/papers')}"><a href="#/papers">Papers</a></li>
            <li ng-class="{ active: isActive('/authors')}"><a href="#/authors">Authors</a></li>
             <li ng-class="{ active: isActive('/conferences')}"><a href="#/conferences">Conferences</a></li>
            <li ng-class="{ active: isActive('/paperTypes')}"><a href="#/paperTypes">Paper types</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#/login">Login</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
	
    <div class="container">
    	
      	<div ng-view></div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/jquery-cookie/jquery.cookie.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/momentjs/min/moment-with-langs.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/ng-file-upload/angular-file-upload-shim.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-resource/angular-resource.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-route/angular-route.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-animate/angular-animate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-sanitize/angular-sanitize.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-cookies/angular-cookies.js"></script>    
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-translate/angular-translate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/ng-file-upload/angular-file-upload.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/ng-table/ng-table.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-strap/dist/angular-strap.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-strap/dist/angular-strap.tpl.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/AngularJS-Toaster/toaster.js"></script>
  	
  	<script src="${pageContext.request.contextPath}/resources/bower_components/select2/select2.min.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-ui-select2/src/select2.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.js"></script>  	
    
    
  	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/util.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/router.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/services.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/filters.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/directives.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/controllers.js"></script>
  	
  	
  	
  	<toaster-container toaster-options="{'time-out': 3000}"></toaster-container>
  </body>
</html>
