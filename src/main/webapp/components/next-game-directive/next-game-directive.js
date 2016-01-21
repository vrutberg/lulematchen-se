angular.module('app').directive('nextGame', function(ApiService, $interval) {
  return {
    templateUrl: 'components/next-game-directive/next-game-template.html',
    scope: {
      game: '='
    },

    controller: ['$scope', function($scope) {

      $scope.dateString = _.capitalize(moment($scope.game.startDateTime)
        .format('dddd D MMMM YYYY - HH:mm'));
      $scope.countDownString = "";

      var getCountdownString = function (from, to) {
        var outputString = "";
        var difference = to - from;

        if (difference > 0) {
          var duration = moment.duration(difference);
          var tmpValue;

          if (duration.days() > 0) {
            if (outputString.length > 0) {
              outputString += ", ";
            }

            tmpValue = duration.days();
            outputString += tmpValue + (tmpValue == 1 ? " dag" : " dagar");
            duration.subtract(tmpValue, 'd');
          }

          if (!(outputString.length == 0 && duration.hours() > 0)) {
            if (outputString.length > 0) {
              outputString += ", ";
            }

            tmpValue = duration.hours();
            outputString += tmpValue + (tmpValue == 1 ? " timme" : " timmar");
            duration.subtract(tmpValue, 'h');
          }

          if (!(outputString.length == 0 && duration.minutes() > 0)) {
            if (outputString.length > 0) {
              outputString += ", ";
            }

            tmpValue = duration.minutes();
            outputString += tmpValue + (tmpValue == 1 ? " minut" : " minuter");
            duration.subtract(tmpValue, 'm');
          }

          if (outputString.length > 0) {
            outputString += ", ";
          }

          tmpValue = duration.seconds();
          outputString += tmpValue + (tmpValue == 1 ? " sekund" : " sekunder");
          duration.subtract(tmpValue, 's');
        }

        return outputString;
      };

      var updateCountdownString = function(dateTimeString) {
        var now = moment().valueOf();
        var gameTime = moment(dateTimeString).valueOf();

        $scope.countDownString = getCountdownString(now, gameTime);
      };

      $interval(function() {
        updateCountdownString($scope.game.startDateTime);
      }, 1000);

      updateCountdownString($scope.game.startDateTime);
    }]
  };
});