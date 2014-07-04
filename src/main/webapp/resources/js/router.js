'use strict';

WP.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider.when('/', {
				templateUrl : 'views/index.html'
			}).when('/login', {
				templateUrl : 'views/login.html'
			}).when('/about', {
				templateUrl : 'views/about.html'
			}).when('/authors', {
				templateUrl : 'views/admin/authors.html',
				controller : 'AuthorsController'
			}).when('/authors/import', {
        templateUrl : 'views/admin/authorsImport.html',
        controller : 'AuthorsImportController'
      }).when('/conferences', {
				templateUrl : 'views/admin/conferences.html',
				controller : 'ConferencesController'
			}).when('/papers', {
				templateUrl : 'views/admin/papers.html',
				controller : 'PaperController'
			}).when('/paperTypes', {
				templateUrl : 'views/admin/paperType.html',
				controller : 'PaperTypeController'
			}).when('/paper/:paper_id/authors', {
				templateUrl: 'views/admin/paperAuthors.html',
				controller: 'PaperAuthorsController'
			}).otherwise({
				redirectTo : '/'
			});
			// $locationProvider.html5Mode(true);
		} ]);
