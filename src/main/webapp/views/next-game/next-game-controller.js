angular.module('app').controller('NextGameController', ['ApiService', '$interval',
  function(ApiService, $interval) {
    var vm = this;

    vm.game = {};
    vm.gameDetails = {};

    vm.countDownString = undefined;

    vm.getCountDown = function(dateTimeString) {
      var outputString = "";
      var now = moment().valueOf();
      var gameTime = moment(dateTimeString).valueOf();
      var difference = gameTime - now;

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

        if (duration.hours() > 0) {
          if (outputString.length > 0) {
            outputString += ", ";
          }

          tmpValue = duration.hours();
          outputString += tmpValue + (tmpValue == 1 ? " timme" : " timmar");
          duration.subtract(tmpValue, 'h');
        }

        if (duration.minutes() > 0) {
          if (outputString.length > 0) {
            outputString += ", ";
          }

          tmpValue = duration.minutes();
          outputString += tmpValue + (tmpValue == 1 ? " minut" : " minuter");
          duration.subtract(tmpValue, 'm');
        }

        if (duration.seconds() > 0) {
          if (outputString.length > 0) {
            outputString += ", ";
          }

          tmpValue = duration.seconds();
          outputString += tmpValue + (tmpValue == 1 ? " sekund" : " sekunder");
          duration.subtract(tmpValue, 's');
        }
      }

      return outputString;
    };

    vm.getGame = function() {
      ApiService.getFirstUnplayedGame().then(function(response) {
        vm.game = response.data;
      }).then(function() {
        return ApiService.getGameDetails(vm.game.gameId);
      }).then(function(response) {
        vm.gameDetails = response.data;
      });
    };

    $interval(function() {
      if (vm.game && vm.game.startDateTime) {
        vm.countDownString = vm.getCountDown(vm.game.startDateTime);
      }
    }, 1000);
  }]);