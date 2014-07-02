'use strict';

WP.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider.when('/', {
				templateUrl : 'views/index.html'
			}).when('/login', {
				templateUrl : 'views/login.html',
				controller : LoginController
			}).when('/about', {
				templateUrl : 'views/about.html'
			}).when('/admin/authors', {
				templateUrl : 'views/admin/authors.html',
				controller : 'AuthorsController'
			}).when('/admin/conferences', {
				templateUrl : 'views/admin/conferences.html',
				controller : 'ConferencesController'
			}).when('/admin/papers', {
				templateUrl: 'views/admin/papers.html'
			}).when('/admin/paperType', {
				templateUrl: 'views/admin/paperType.html',
				controller : 'PaperTypeController'
			}).otherwise({
				redirectTo : '/'
			});
			// $locationProvider.html5Mode(true);
		} ]);
