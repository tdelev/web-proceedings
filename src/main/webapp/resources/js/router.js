'use strict';

WP.config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
      $routeProvider.when('/authors', {
        templateUrl: 'views/authors.html',
        controller: 'AuthorsController'
      }).when('/authors/import', {
        templateUrl: 'views/authorsImport.html',
        controller: 'AuthorsImportController'
      }).when('/conferences', {
        templateUrl: 'views/conferences.html',
        controller: 'ConferencesController'
      }).when('/conferences/:id/meta', {
        templateUrl: 'views/conference_meta.html',
        controller: 'ConferenceMetaController'
      }).when('/papers', {
        templateUrl: 'views/papers.html',
        controller: 'PaperController'
      }).when('/paperTypes', {
        templateUrl: 'views/paperType.html',
        controller: 'PaperTypeController'
      }).when('/paper/:paper_id/authors', {
        templateUrl: 'views/paperAuthors.html',
        controller: 'PaperAuthorsController'
      }).otherwise({
        redirectTo: '/papers'
      });
      // $locationProvider.html5Mode(true);
    }]);
