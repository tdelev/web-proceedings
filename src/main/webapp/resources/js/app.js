'use strict';
var exampleAppConfig = {
  useAuthTokenHeader: true
};
// Declare app level module which depends on filters, and services
var WP = angular.module('WP', ['WP.filters', 'WP.services', 'WP.directives']);

WP.config([
    '$routeProvider',
    '$translateProvider',
    '$httpProvider',
    '$locationProvider',
    function($routeProvider, $translateProvider, $httpProvider,
            $locationProvider, toaster) {

      

    }]);

WP.run(function($rootScope, $location, $cookieStore) {

  
});
