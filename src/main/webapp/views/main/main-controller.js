angular.module('app').controller('MainController', ['ApiService', '$interval',
  function(ApiService, $interval) {
    var vm = this;

    vm.game = undefined;
    vm.hasGameStarted = false;

    vm.isBeforeNow = function(dateTimeString) {
      var nowMoment = moment();
      var gameTimeMoment = moment(dateTimeString);

      return gameTimeMoment.isBefore(nowMoment);
    };

    vm.getGame = function() {
      return ApiService.getTodaysGames().then(function(response) {
        if (response.data.length > 0) {
          // if-clause added for test
          if (window.location.href.indexOf("showNextGame") === -1) {
            return response.data[0];
          }
        }

        return ApiService.getFirstUnplayedGame().then(function(response) {
          return response.data;
        });
      });
    };

    vm.init = function() {
      vm.getGame().then(function(game) {
        vm.game = game;

        if (window.location.href.indexOf("forceLive") !== -1) {
          vm.game.played = false;
        }

        vm.hasGameStarted = vm.isBeforeNow(vm.game.startDateTime);
      });
    };
  }]);