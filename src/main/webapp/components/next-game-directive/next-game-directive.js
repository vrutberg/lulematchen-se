angular.module('app').directive('nextGame', function(ApiService, $interval) {
  return {
    templateUrl: 'components/next-game-directive/next-game-template.html',
    scope: {
      game: '='
    },

    controller: ['$scope', function($scope) {
      // TODO: do this in a more angular way
      $('html').addClass('html-cover-up').removeClass('html-game-is-on');

      $scope.dateString = _.capitalize(moment($scope.game.startDateTime)
        .format('dddd, D MMMM YYYY - HH:mm'));

      $scope.gameTimeUnixEpoch = moment($scope.game.startDateTime).valueOf();
      $scope.nowAsEpoch = moment().valueOf();

      $interval(function() {
        $scope.nowAsEpoch = moment().valueOf();
      }, 1000);
    }]
  };
});