angular.module('app').directive('currentGame', ['ApiService', '$interval', function(ApiService, $interval) {
  return {
    templateUrl: 'components/current-game-directive/current-game-template.html',
    scope: {
      game: '='
    },

    controller: ['$scope', function($scope) {
      $scope.isLoading = false;
      $scope.dateString = _.capitalize(moment($scope.game.startDateTime)
        .format('dddd, D MMMM YYYY - HH:mm'));

      $scope.homeScore = -1;
      $scope.awayScore = -1;

      var parseTimePeriod = function(timePeriod) {
        return {
          minutes: _.padStart(Math.floor(timePeriod / 60), 2, '0'),
          seconds: _.padStart(timePeriod % 60, 2, '0')
        };
      };

      var updateDetails = function(gameId) {
        return ApiService.getGameDetails(gameId).then(function (response) {
          $scope.gameDetails = response.data;

          $scope.homeScore = $scope.gameDetails.live.homeScore;
          $scope.awayScore = $scope.gameDetails.live.awayScore;

          $scope.currentGameTime = parseTimePeriod($scope.gameDetails.live.timePeriod);
        });
      };

      $scope.$watch('homeScore', function(newValue, oldValue) {
        if (oldValue !== -1 && newValue !== oldValue) {
          console.log('homeScore changed from', oldValue, 'to', newValue);
        }
      });

      $scope.$watch('awayScore', function(newValue, oldValue) {
        if (oldValue !== -1 && newValue !== oldValue) {
          console.log('awayScore changed from', oldValue, 'to', newValue);
        }
      });

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