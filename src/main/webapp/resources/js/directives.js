'use strict';

/* Directives */

angular.module(
        'WP.directives',
        ['ngResource', 'ngRoute', 'ngAnimate', 'ngTable', 'ngCookies',
            'ui.bootstrap', 'ui.select2', 'mgcrea.ngStrap', 'toaster',
            'angularFileUpload', 'pascalprecht.translate'])

.directive('appVersion', ['version', function(version) {
  return function(scope, elm, attrs) {
    elm.text(version);
  };
}]);