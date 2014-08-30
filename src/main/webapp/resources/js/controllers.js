'use strict';

/* Controllers */

WP.controller('HeaderController', ['$scope', '$location',
    function($scope, $location) {
      $scope.isActive = function(viewLocation) {
        var re = new RegExp(viewLocation);
        return re.test($location.path());
      };
    }]);

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
          page: 1,
          count: 10,
          sorting: {
            firstName: 'asc' // initial sorting
          }
        };
        $scope.table = new ngTableParams(tableParams, {
          total: $scope.authors.length, // length of data
          getData: function($defer, params) {
            // filtering
            var filteredData = params.filter() ? $filter('filter')(
                    $scope.authors, params.filter()) : $scope.authors;
            // sorting
            var sortedData = params.sorting() ? $filter('orderBy')(
                    filteredData, params.orderBy()) : $scope.authors;
            params.total(sortedData.length);
            $defer.resolve(sortedData.slice((params.page() - 1)
                    * params.count(), params.page() * params.count()));
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
          id: id
        });
      };
      $scope.deleteAuthor = function(id) {
        Author.remove({
          id: id
        }, function() {
          Author.query(function(data) {
            $scope.authors = data;
            $scope.table.reload();
          });
          $scope.author = {};
        });
      };
    }]);

WP.controller('ConferencesController', [
    '$scope',
    '$filter',
    '$upload',
    '$modal',
    'Conference',
    'Attachment',
    'ngTableParams',
    'toaster',
    function($scope, $filter, $upload, $modal, Conference, Attachment,
            ngTableParams, toaster) {
      $scope.conference = {};

      Conference.query(function(data) {
        $scope.conferences = data;
        var tableParams = {
          page: 1,
          count: 10,
        };
        $scope.table = new ngTableParams(tableParams, {
          total: $scope.conferences.length, // length of data
          getData: function($defer, params) {
            // filtering
            var filteredData = params.filter() ? $filter('filter')(
                    $scope.conferences, params.filter()) : $scope.conferences;
            // sorting
            var sortedData = params.sorting() ? $filter('orderBy')(
                    filteredData, params.orderBy()) : $scope.conferences;
            params.total(sortedData.length);
            $defer.resolve(sortedData.slice((params.page() - 1)
                    * params.count(), params.page() * params.count()));
          }
        });
      });

      $scope.modalCreate = $modal({
        scope: $scope,
        title: 'Create conference',
        template: 'templates/modal-form.tpl.html',
        contentTemplate: 'forms/conference.html',
        show: false
      });

      $scope.createNew = function() {
        $scope.conference = {};
        $scope.modalCreate.show();
      };

      $scope.save = function() {
        Conference.save($scope.conference, function(conference) {
          $scope.conferences = Conference.query();
          $scope.conference = {};
          toaster.pop('success', 'Success', 'Conference saved');
        });
      };

      $scope.getConference = function(id) {
        $scope.conference = Conference.get({
          id: id
        }, function() {
          $scope.attachments = Attachment.getAttachmentsByObjectId({
            id: $scope.conference.id,
            bean: 'conference_attachment'
          });
          $scope.modalCreate.show();
        });
      };
      $scope.deleteConference = function(id) {
        Conference.remove({
          id: id
        }, function() {
          Conference.query(function(data) {
            $scope.conferences = data;
            $scope.table.reload();
          });
          $scope.conference = {};
        });
      };

      $scope.onFileSelect = function($files) {
        function onSuccess(data, status, headers, config) {
          $scope.attachments = Attachment.getAttachmentsByObjectId({
            id: $scope.conference.id,
            bean: 'conference_attachment'
          });
          toaster.pop('success', 'Success', 'Conference file uploaded');
        }
        function onError(data, status, headers, config) {
          toaster.pop('error', 'Error', 'Conference file upload failed');
        }
        for (var i = 0; i < $files.length; i++) {
          var file = $files[i];
          $scope.upload = $upload.upload(
                  {
                    url: WPUtil.ctx("/data/rest/attachments/conference/"
                            + $scope.conference.id),
                    data: {
                      id: $scope.conference.id
                    },
                    file: file
                  }).success(onSuccess).error(onError);
        }
      };

      $scope.basePath = WPUtil.basePath;

    }]);

WP.controller('PaperTypeController', ['$scope', 'PaperType',
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
          id: id
        });
      };
      $scope.deleteType = function(id) {
        PaperType.remove({
          id: id
        }, function() {
          $scope.types = PaperType.query();
          $scope.paperType = {};
        });

      };

    }]);
WP.controller('PaperController', [
    '$scope',
    '$filter',
    '$upload',
    '$modal',
    'Paper',
    'Conference',
    'Attachment',
    'PaperType',
    'ngTableParams',
    'toaster',
    function($scope, $filter, $upload, $modal, Paper, Conference, Attachment,
            PaperType, ngTableParams, toaster) {
      $scope.paper = {};
      $scope.conferences = Conference.query();
      $scope.types = PaperType.query();

      // Paper.query(function(data) {
      // $scope.papers = data;
      var tableParams = {
        page: 1,
        count: 10
      };
      $scope.table = new ngTableParams(tableParams, {
        total: 0, // length of data
        getData: function($defer, params) {
          Paper.paged(params.url(), function(data) {
            params.total(data.totalElements);
            $scope.totalElements = data.totalElements;
            $defer.resolve(data.content);
          });
          /*
           * // filtering var filteredData = params.filter() ?
           * $filter('filter')( $scope.papers, params.filter()) : $scope.papers; //
           * sorting var sortedData = params.sorting() ? $filter('orderBy')(
           * filteredData, params.orderBy()) : $scope.papers;
           * params.total(sortedData.length);
           * $defer.resolve(sortedData.slice((params.page() - 1) params.count(),
           * params.page() * params.count()));
           */
        }
      });

      // });
      $scope.modalCreate = $modal({
        scope: $scope,
        title: 'Create paper',
        template: 'templates/modal-form.tpl.html',
        contentTemplate: 'forms/paper.html',
        show: false
      });

      $scope.createNew = function() {
        $scope.paper = {};
        $scope.modalCreate.show();
      };

      $scope.save = function() {
        Paper.save($scope.paper, function(paper) {
          $scope.table.reload();
          $scope.paper = {};
          // $scope.form.$setPristine();
          $scope.modalCreate.hide();
          toaster.pop('success', 'Success', 'Paper saved');
        });
      };

      $scope.getPaper = function(id) {
        $scope.attachments = Attachment.getAttachmentsByObjectId({
          id: id,
          bean: 'paper_attachment'
        });
        $scope.paper = Paper.get({
          id: id
        }, function() {
          $scope.modalCreate.show();
        });
      };
      $scope.deletePaper = function(id) {
        Paper.remove({
          id: id
        }, function() {
          $scope.table.reload();
          $scope.paper = {};
        });
      };
      $scope.onFileSelect = function($files) {
        function onSuccess(data, status, headers, config) {
          $scope.attachments = Attachment.getAttachmentsByObjectId({
            id: $scope.paper.id,
            bean: 'paper_attachment'
          });
          toaster.pop('success', 'Success', 'Paper file uploaded');
        }
        function onError(data, status, headers, config) {
          toaster.pop('error', 'Error', 'Paper file upload failed');
        }
        for (var i = 0; i < $files.length; i++) {
          var file = $files[i];
          $scope.upload = $upload.upload({
            url: WPUtil.ctx("/data/rest/attachments/paper/" + $scope.paper.id),
            data: {
              id: $scope.paper.id
            },
            file: file
          }).success(onSuccess).error(onError);
        }
      };

      $scope.clearFilters = function() {
        delete $scope.table.$params.filter.conference;
        delete $scope.table.$params.filter.type;
        delete $scope.table.$params.filter.title;
      };

      $scope.basePath = WPUtil.basePath;

    }]);
WP.controller('PaperAuthorsController', ['$scope', '$routeParams', '$modal',
    'PaperAuthor', 'Paper', 'Author',
    function($scope, $routeParams, $modal, PaperAuthor, Paper, Author) {
      $scope.paperAuthor = {};
      $scope.paperAuthors = PaperAuthor.getByPaperId({
        id: $routeParams.paper_id
      });
      $scope.paper = Paper.get({
        id: $routeParams.paper_id
      });
      $scope.authors = Author.query();
      $scope.savePaperAuthor = function() {
        $scope.paperAuthor.paper = $scope.paper;
        PaperAuthor.save($scope.paperAuthor, function(paperAuthor) {
          $scope.paperAuthors = PaperAuthor.getByPaperId({
            id: $routeParams.paper_id
          });
          $scope.paperAuthor = {};
          $scope.paperAuthorForm.$setPristine();
        });
      };

      $scope.getPaperAuthor = function(id) {
        $scope.paperAuthor = PaperAuthor.get({
          id: id
        });
      };
      $scope.deletePaperAuthor = function(id) {
        PaperAuthor.remove({
          id: id
        }, function() {
          $scope.paperAuthors = PaperAuthor.getByPaperId({
            id: $routeParams.paper_id
          });
          $scope.paperAuthor = {};
        });
      };

      $scope.modalCreate = $modal({
        scope: $scope,
        title: 'Add new author',
        template: 'templates/modal-form.tpl.html',
        contentTemplate: 'forms/author.html',
        show: false
      });

      $scope.addAuthor = function() {
        $scope.author = {};
        $scope.modalCreate.show();
      };

      $scope.save = function() {
        Author.save($scope.author, function() {
          $scope.authors = Author.query();
          $scope.modalCreate.hide();
        });
      };

    }]);

WP.controller('AuthorsImportController', ['$scope', '$upload',
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
            url: WPUtil.ctx("/data/rest/authors/import"),
            file: file
          }).success(onSuccess).error(onError);
        }
      };

    }]);

WP.controller('ConferenceMetaController',
        [
            '$scope',
            '$routeParams',
            '$modal',
            'ConferenceMeta',
            'Conference',
            'toaster',
            function($scope, $routeParams, $modal, ConferenceMeta, Conference,
                    toaster) {
              $scope.conference = Conference.get({
                id: $routeParams.id
              });
              function initEditors() {
                $("#preface").markdown({
                  autofocus: true,
                  savable: true,
                  onSave: function(e) {
                    $scope.meta.preface = e.getContent();
                    $scope.saveMeta();
                  },
                  onShow: function(e) {
                    e.setContent($scope.meta.preface);
                  }
                });
                $("#committees").markdown({
                  savable: true,
                  onSave: function(e) {
                    $scope.meta.committees = e.getContent();
                    $scope.saveMeta();
                  },
                  onShow: function(e) {
                    e.setContent($scope.meta.committees);
                  }
                });
              }

              ConferenceMeta.getByConferenceId({
                id: $routeParams.id
              }, function(meta) {
                $scope.meta = meta;
                initEditors();
              }, function(error) {
                $scope.meta = {};
                initEditors();
              });

              $scope.saveMeta = function() {
                $scope.meta.conference = $scope.conference;
                ConferenceMeta.save($scope.meta, function(meta) {
                  toaster.pop('success', 'Success', 'Conference meta saved');
                });
              };

            }]);
