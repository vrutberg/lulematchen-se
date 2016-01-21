angular.module('app').controller('MainController', ['ApiService', '$interval',
  function(ApiService, $interval) {
    var vm = this;

    vm.game = {};
    vm.gameDetails = {};

    vm.countDownString = undefined;

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

    vm.updateCountdownString = function(dateTimeString) {
      vm.countDownString = getCountdownString(moment().valueOf(), moment(dateTimeString).valueOf());
    };

    vm.getGame = function() {
      ApiService.getTodaysGames().then(function(response) {
        if (response.data.length > 0) {
          vm.game = response.data[0];
        }

        return ApiService.getFirstUnplayedGame().then(function(response) {
          vm.game = response.data;
        });
      }).then(function() {
        vm.updateCountdownString(vm.game.startDateTime);
        return ApiService.getGameDetails(vm.game.gameId);
      }).then(function(response) {
        vm.gameDetails = response.data;
      });
    };

    $interval(function() {
      if (!vm.game.played) {
        vm.updateCountdownString(vm.game.startDateTime);
      }
    }, 1000);
  }]);