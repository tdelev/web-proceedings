'use strict';

WP.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {
    templateUrl: 'views/index.html'
  }).when('/login', {
    templateUrl: 'views/login.html',
    controller: LoginController
  }).when('/about', {
    templateUrl: 'views/about.html'
  }).otherwise({
    redirectTo: '/'
  });
}]);
