angular.module('app').controller('NextGameController', ['ApiService', '$interval',
  function(ApiService, $interval) {
    var vm = this;

    vm.game = {};
    vm.countDownString = undefined;

    vm.getCountDown = function(dateTimeString) {
      var outputString = "";
      var now = moment().valueOf();
      var gameTime = moment(dateTimeString).valueOf();
      var difference = gameTime - now;

      if (difference > 0) {
        var duration = moment.duration(difference);

        if (duration.days() > 0) {
          if (outputString.length > 0) {
            outputString += ", ";
          }

          outputString += duration.days() + " dagar";
          duration.subtract(duration.days(), 'd');
        }

        if (duration.hours() > 0) {
          if (outputString.length > 0) {
            outputString += ", ";
          }

          outputString += duration.hours() + " timmar";
          duration.subtract(duration.hours(), 'h');
        }

        if (duration.minutes() > 0) {
          if (outputString.length > 0) {
            outputString += ", ";
          }

          outputString += duration.minutes() + " minuter";
          duration.subtract(duration.minutes(), 'm');
        }

        if (duration.seconds() > 0) {
          if (outputString.length > 0) {
            outputString += ", ";
          }

          outputString += duration.seconds() + " sekunder";
          duration.subtract(duration.seconds(), 's');
        }
      }

      return outputString;
    };

    vm.getGame = function() {
      ApiService.getFirstUnplayedGame().then(function(response) {
        vm.game = response.data;
      });
    };

    $interval(function() {
      if (vm.game && vm.game.startDateTime) {
        vm.countDownString = vm.getCountDown("2016-01-14T22:00:00");
      }
    }, 1000);
  }]);