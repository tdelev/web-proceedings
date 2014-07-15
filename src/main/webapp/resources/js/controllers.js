'use strict';

/* Controllers */

WP.controller('HeaderController', [ '$scope', '$location',
		function($scope, $location) {
			$scope.isActive = function(viewLocation) {
				var re = new RegExp(viewLocation);
				return re.test($location.path());
			};
		} ]);

WP.controller('AuthorsController', [
		'$scope',
		'$filter',
		'ngTableParams',
		'Author',
		function($scope, $filter, ngTableParams, Author) {
			$scope.author = {};
			Author.query(function(data) {
				$scope.authors = data;
				var tableParams = {
					page : 1,
					count : 10,
					sorting : {
						firstName : 'asc' // initial sorting
					}
				};
				$scope.table = new ngTableParams(tableParams, {
					total : $scope.authors.length, // length of data
					getData : function($defer, params) {
						// filtering
						var filteredData = params.filter() ? $filter('filter')(
								$scope.authors, params.filter())
								: $scope.authors;
						// sorting
						var sortedData = params.sorting() ? $filter('orderBy')(
								filteredData, params.orderBy())
								: $scope.authors;
						params.total(sortedData.length);
						$defer.resolve(sortedData.slice((params.page() - 1)
								* params.count(), params.page()
								* params.count()));
					}
				});
			});
			$scope.saveAuthor = function() {
				Author.save($scope.author, function(author) {
					Author.query(function(data) {
						$scope.authors = data;
						$scope.table.reload();
					});
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
					Author.query(function(data) {
						$scope.authors = data;
						$scope.table.reload();
					});
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
WP.controller('PaperController', [
		'$scope',
		'$filter',
		'$upload',
		'Paper',
		'Conference',
		'PaperType',
		'ngTableParams',
		function($scope, $filter, $upload, Paper, Conference, PaperType, ngTableParams) {
			$scope.paper = {};
			$scope.conferences = Conference.query();
			$scope.types = PaperType.query();
			Paper.query(function(data) {
				$scope.papers = data;
				var tableParams = {
					page : 1,
					count : 10,
				};
				$scope.table = new ngTableParams(tableParams,
						{
							total : $scope.papers.length, // length of data
							getData : function($defer, params) {
								// filtering
								var filteredData = params.filter() ? $filter(
										'filter')($scope.papers,
										params.filter()) : $scope.papers;
								// sorting
								var sortedData = params.sorting() ? $filter(
										'orderBy')(filteredData,
										params.orderBy()) : $scope.papers;
								params.total(sortedData.length);
								$defer.resolve(sortedData.slice(
										(params.page() - 1) * params.count(),
										params.page() * params.count()));
							}
						});
			});
			$scope.savePaper = function() {
				Paper.save($scope.paper, function(paper) {
					Paper.query(function(data) {
						$scope.papers = data;
						$scope.table.reload();
					});
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
					Paper.query(function(data) {
						$scope.papers = data;
						$scope.table.reload();
					});
					$scope.paper = {};
				});
			};
			
			$scope.onFileSelect = function($files) {
        function onSuccess(data, status, headers, config) {
          console.log("success");
          $scope.paper = data;
        }
        function onError(data, status, headers, config) {
          console.log("error");
        }
        for (var i = 0; i < $files.length; i++) {
          var file = $files[i];
          $scope.upload = $upload.upload({
            url : WPUtil.ctx("/data/rest/papers/file/" + $scope.paper.id),
            data : {
              id: $scope.paper.id
            },
            file : file
          }).success(onSuccess).error(onError);
        }
      };

		} ]);
WP.controller('PaperAuthorsController', [ '$scope', '$routeParams',
		'PaperAuthor', 'Paper', 'Author',
		function($scope, $routeParams, PaperAuthor, Paper, Author) {
			$scope.paperAuthor = {};
			$scope.paperAuthors = PaperAuthor.getByPaperId({
				id : $routeParams.paper_id
			});
			$scope.paper = Paper.get({
				id : $routeParams.paper_id
			});
			$scope.authors = Author.query();
			$scope.savePaperAuthor = function() {
				$scope.paperAuthor.paper = $scope.paper;
				PaperAuthor.save($scope.paperAuthor, function(paperAuthor) {
					$scope.paperAuthors = PaperAuthor.getByPaperId({
						id : $routeParams.paper_id
					});
					$scope.paperAuthor = {};
					$scope.paperAuthorForm.$setPristine();
				});
			};

			$scope.getPaperAuthor = function(id) {
				$scope.paperAuthor = PaperAuthor.get({
					id : id
				});
			};
			$scope.deletePaperAuthor = function(id) {
				PaperAuthor.remove({
					id : id
				}, function() {
					$scope.paperAuthors = PaperAuthor.getByPaperId({
						id : $routeParams.paper_id
					});
					$scope.paperAuthor = {};
				});

			};

		} ]);

WP.controller('AuthorsImportController', [ '$scope', '$upload',
		function($scope, $upload) {

			$scope.onFileSelect = function($files) {
				function onSuccess(data, status, headers, config) {
					console.log("success");
					$scope.authors = data;
				}
				function onError(data, status, headers, config) {
					console.log("error");
				}
				for (var i = 0; i < $files.length; i++) {
					var file = $files[i];
					$scope.upload = $upload.upload({
						url : WPUtil.ctx("/data/rest/authors/import"),
						file : file
					}).success(onSuccess).error(onError);
				}
			};

		} ]);
