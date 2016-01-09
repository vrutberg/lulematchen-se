angular.module('app').factory('ApiService', ['$http', function($http) {
  return {
    getGames: function() {
      return $http.get('/_api/games');
    }
  };
}]);