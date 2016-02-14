angular.module('app').factory('SoundEffectsService', ['AudioService', 'SettingsService', 'Modernizr', function(AudioService, SettingsService, Modernizr) {
  var shouldPlayAudioProxyFn = function(audioPlayingFn) {
    return function() {
      if (SettingsService.isAudioEnabled() && Modernizr.audio) {
        audioPlayingFn();
      }
    };
  };

  return {
    playOurTeamScoredSound: shouldPlayAudioProxyFn(function() {
      AudioService.playAudio('audio/lule-gor-mal.mp3');
    }),

    playOtherTeamScoredSound: shouldPlayAudioProxyFn(function() {
      AudioService.playAudio('audio/lule-slapper-in-mal.mp3');
    }),

    playGameOverSound: shouldPlayAudioProxyFn(function() {
      AudioService.playAudio('audio/match-slut.mp3');
    }),

    playPeriodStartsOrEndsSound: shouldPlayAudioProxyFn(function() {
      AudioService.playAudio('audio/period-slut-och-borjar.mp3');
    })
  };
}]);