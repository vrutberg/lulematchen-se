angular.module('app').directive('currentGame', ['ApiService', 'SoundEffectsService', 'NotificationService', '$interval', '$timeout', function(ApiService, SoundEffectsService, NotificationService, $interval, $timeout) {
  return {
    templateUrl: 'components/current-game-directive/current-game-template.html',
    scope: {
      game: '='
    },

    controller: ['$scope', function($scope) {
      // TODO: do this in a more angular way
      $('html').removeClass('html-cover-up').addClass('html-game-is-on');

      $scope.GAME_STATES = {
        NOT_STARTED: 10000,
        PLAYING: 10001,
        INTERMISSION: 10002,
        OVER: 10003
      };

      $scope.isLoading = false;
      $scope.dateString = _.capitalize(moment($scope.game.startDateTime)
        .format('dddd, D MMMM YYYY - HH:mm'));

      $scope.homeScore = -1;
      $scope.awayScore = -1;

      $scope.currentGameState = $scope.GAME_STATES.NOT_STARTED;

      var getGameState = function(game) {
        if (game.played) {
          return $scope.GAME_STATES.OVER;
        }

        if (!game.live) {
          return $scope.GAME_STATES.NOT_STARTED;
        }

        if (parseTimePeriod(game.live.timePeriod)) {
          return $scope.GAME_STATES.INTERMISSION;
        }

        return $scope.GAME_STATES.PLAYING;
      };

      $scope.getGameStateString = function() {
        if ($scope.currentGameState === $scope.GAME_STATES.NOT_STARTED) {
          return 'Matchen har inte börjat';
        } else if ($scope.currentGameState === $scope.GAME_STATES.PLAYING) {
          return 'Matchen pågår';
        } else if ($scope.currentGameState === $scope.GAME_STATES.INTERMISSION) {
          return 'Paus';
        } else if ($scope.currentGameState === $scope.GAME_STATES.OVER) {
          return 'Matchen är slut';
        }
      };

      var parseTimePeriod = function(timePeriod) {
        return {
          minutes: _.padStart(Math.floor(timePeriod / 60), 2, '0'),
          seconds: _.padStart(timePeriod % 60, 2, '0')
        };
      };

      var updateDetails = function(gameId) {
        return ApiService.getGameDetails(gameId).then(function (response) {
          $scope.gameDetails = response.data;

          $scope.currentGameState = getGameState($scope.gameDetails);

          if ($scope.currentGameState == $scope.GAME_STATES.NOT_STARTED) {
            $scope.homeScore = 0;
            $scope.awayScore = 0;
          } else {
            $scope.homeScore = $scope.gameDetails.live.homeScore;
            $scope.awayScore = $scope.gameDetails.live.awayScore;

            $scope.currentGameTime = parseTimePeriod($scope.gameDetails.live.timePeriod);
          }
        });
      };

      $scope.$watch('homeScore', function(newValue, oldValue) {
        if (oldValue !== -1 && newValue !== oldValue) {
          var title = $scope.game.homeTeamCode + " vs. " + $scope.game.awayTeamCode;
          var body = $scope.game.homeTeamCode + " gjorde mål. Ställningen är " + $scope.homeScore + "-" + $scope.awayScore;

          NotificationService.displayNotification(title, body);

          if ($scope.game.homeTeamCode === 'LHF') {
            SoundEffectsService.playOurTeamScoredSound();
          } else {
            SoundEffectsService.playOtherTeamScoredSound();
          }
        }
      });

      $scope.$watch('awayScore', function(newValue, oldValue) {
        if (oldValue !== -1 && newValue !== oldValue) {
          var title = $scope.game.homeTeamCode + " vs. " + $scope.game.awayTeamCode;
          var body = $scope.game.awayTeamCode + " gjorde mål. Ställningen är " + $scope.homeScore + "-" + $scope.awayScore;

          NotificationService.displayNotification(title, body);

          if ($scope.game.awayTeamCode === 'LHF') {
            SoundEffectsService.playOurTeamScoredSound();
          } else {
            SoundEffectsService.playOtherTeamScoredSound();
          }
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
      } else {
        $scope.homeScore = $scope.game.homeTeamResult;
        $scope.awayScore = $scope.game.awayTeamResult;
      }

      if (window.location.href.indexOf("forceLive") !== -1) {
        $timeout(function() {
          $scope.homeScore = 4;
        }, 3000);
      }
    }]
  };
}]);