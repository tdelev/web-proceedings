'use strict';

/* Filters */

angular.module('WP.filters', ['ngSanitize']).filter('interpolate',
        ['version', function(version) {
          return function(text) {
            return String(text).replace(/\%VERSION\%/mg, version);
          };
        }]).filter('fileSize', function() {
  return function(fileSizeInBytes) {
    var i = -1;
    var byteUnits = [' kB', ' MB', ' GB', ' TB', 'PB', 'EB', 'ZB', 'YB'];
    do {
      fileSizeInBytes = fileSizeInBytes / 1024;
      i++;
    } while (fileSizeInBytes > 1024);

    return Math.max(fileSizeInBytes, 0.1).toFixed(1) + byteUnits[i];
  };
}).filter('shorten', function() {
  return function(value) {
    value = value || "";
    if (value.length && value.length > 50) {
      return value.substring(0, 50) + "...";
    } else {
      return value;
    }
  };
}).filter('fromNow', function() {
  return function(dateString) {
    return moment(dateString).fromNow()
  };
}).filter('mDate', function() {
  return function(dateString) {
    return moment(dateString).format('LL');
  };
});
