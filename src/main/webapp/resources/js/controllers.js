'use strict';

/* Controllers */

function LoginController($scope, $rootScope, $location, $cookieStore,
		UserService) {

	$scope.rememberMe = false;

	$scope.login = function() {
		UserService.authenticate($.param({
			username : $scope.username,
			password : $scope.password
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

WP.controller('AuthorsController', [ '$scope', 'Author',
		function($scope, Author) {
			$scope.author = {};
			$scope.authors = Author.query();
			$scope.saveAuthor = function() {
				Author.save($scope.author, function(author) {
					$scope.authors = Author.query();
					$scope.author = {};
					$scope.authorForm.$setPristine();
				});

			};
			$scope.getAuthor = function(id) {
				$scope.author = Author.get({
					id : id
				});
			};
			$scope.deleteAuthor = function(id) {
				Author.remove({
					id : id
				}, function() {
					$scope.authors = Author.query();
					$scope.author = {};
				});
			};
		} ]);

WP.controller('ConferencesController', [ '$scope', 'Conference',
		function($scope, Conference) {
			$scope.conference = {};
			$scope.conferences = Conference.query();
			$scope.saveConference = function() {
				Conference.save($scope.conference, function(conference) {
					$scope.conferences = Conference.query();
					$scope.conference = {};
					$scope.conferenceForm.$setPristine();
				});
			};

			$scope.getConference = function(id) {
				$scope.conference = Conference.get({
					id : id
				});
			};
			$scope.deleteConference = function(id) {
				Conference.remove({
					id : id
				}, function() {
					$scope.conferences = Conference.query();
					$scope.conference = {};
				});

			};

		} ]);

WP.controller('PaperTypeController', [ '$scope', 'PaperType',
		function($scope, PaperType) {
			$scope.paperType = {};
			$scope.types = PaperType.query();
			$scope.savePaperType = function() {
				PaperType.save($scope.paperType, function(paperType) {
					$scope.types = PaperType.query();
					$scope.paperType = {};
					$scope.paperTypeForm.$setPristine();
				});
			};

			$scope.getType = function(id) {
				$scope.paperType = PaperType.get({
					id : id
				});
			};
			$scope.deleteType = function(id) {
				PaperType.remove({
					id : id
				}, function() {
					$scope.types = PaperType.query();
					$scope.paperType = {};
				});

			};

		} ]);
WP.controller('PaperController', [ '$scope', 'Paper','Conference','PaperType',
		function($scope, Paper,Conference,PaperType) {
			$scope.paper = {};
			$scope.papers = Paper.query();
			$scope.conferences=Conference.query();
			$scope.types=PaperType.query();
			$scope.savePaper = function() {
				Paper.save($scope.paper, function(paper) {
					$scope.papers = Paper.query();
					$scope.paper = {};
					$scope.papersForm.$setPristine();
				});
			};

			$scope.getPaper = function(id) {
				$scope.paper = Paper.get({
					id : id
				});
			};
			$scope.deletePaper = function(id) {
				Paper.remove({
					id : id
				}, function() {
					$scope.papers = Paper.query();
					$scope.paper = {};
				});

			};

		} ]);