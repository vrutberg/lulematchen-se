angular.module('app').directive('currentGame', ['ApiService', '$interval', function(ApiService, $interval) {
  return {
    templateUrl: 'components/current-game-directive/current-game-template.html',
    scope: {
      game: '='
    },

    controller: ['$scope', function($scope) {
      $scope.isLoading = false;
      $scope.dateString = _.capitalize(moment($scope.game.startDateTime)
        .format('dddd D MMMM YYYY - HH:mm'));

      $scope.homeScore = $scope.game.homeTeamResult;
      $scope.awayScore = $scope.game.awayTeamResult;

      var parseTimePeriod = function(timePeriod) {
        return {
          minutes: _.padStart(Math.floor(timePeriod / 60), 2, '0'),
          seconds: _.padStart(timePeriod % 60, 2, '0')
        };
      };

      var updateDetails = function(gameId, skipLoading) {
        return ApiService.getGameDetails(gameId).then(function (response) {
          $scope.gameDetails = response.data;
          $scope.isLoading = false;

          $scope.currentGameTime = parseTimePeriod($scope.gameDetails.live.timePeriod);
        });
      };

      if (!$scope.game.played) {
        $interval(function() {
          updateDetails($scope.game.gameId);
        }, 5000);

        $scope.isLoading = true;

        updateDetails($scope.game.gameId).then(function () {
          $scope.isLoading = false;
        });
      }
    }]
  };
}]);