'use strict';

/* Controllers */

function LoginController($scope, $rootScope, $location, $cookieStore,
        UserService) {

  $scope.rememberMe = false;

  $scope.login = function() {
    UserService.authenticate($.param({
      username: $scope.username,
      password: $scope.password
    }), function(authenticationResult) {
      var authToken = authenticationResult.token;
      $rootScope.authToken = authToken;
      $cookieStore.put('authToken', authToken);
      UserService.get(function(user) {
        $rootScope.user = user;
        if ($rootScope.returnPath) {
          $location.path($rootScope.returnPath);
          delete $rootScope.returnPath;
        } else {
          $location.path("/");
        }
      });
    });
  };
};