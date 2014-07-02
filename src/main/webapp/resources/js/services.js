'use strict';

/* Services */

// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('WP.services', ['chieffancypants.loadingBar']).value('version',
        '0.1');

WP.factory('UserService', function($resource) {

  return $resource(WPUtil.ctx('/data/rest/user/:action'), {}, {
    authenticate: {
      method: 'POST',
      params: {
        'action': 'authenticate'
      },
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    },
  });
});

WP.factory('Paper', ['$resource', function($resource) {
  return $resource(WPUtil.ctx('/data/rest/papers/:id'), {});
}]);

WP.factory('Author', ['$resource', function($resource) {
  return $resource(WPUtil.ctx('/data/rest/authors/:id'), {});
}]);

WP.factory('Conference', ['$resource', function($resource) {
	  return $resource(WPUtil.ctx('/data/rest/conferences/:id'), {});
	}]);
WP.factory('PaperType', ['$resource', function($resource) {
	  return $resource(WPUtil.ctx('/data/rest/paperType/:id'), {});
	}]);

