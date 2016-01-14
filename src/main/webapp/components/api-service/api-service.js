angular.module('app').factory('ApiService', ['$http', function($http) {
  return {
    getGames: function() {
      return $http.get('_api/games');
    },

    getLastPlayedGame: function() {
      return $http.get('_api/games/lastPlayed');
    },

    getFirstUnplayedGame: function() {
      return $http.get('_api/games/firstUnplayed');
    },

    getGameDetails: function(gameId) {
      return $http.get('_api/games/' + gameId + '/details');
    }
  };
}]);