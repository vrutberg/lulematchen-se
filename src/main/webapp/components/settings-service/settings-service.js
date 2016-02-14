angular.module('app').factory('SettingsService', ['StorageService', function(StorageService) {
  var AUDIO_ENABLED_KEY = "se.lulematchen.settings.audio";

  return {
    isAudioEnabled: function() {
      return StorageService.readValue(AUDIO_ENABLED_KEY, true);
    },

    setAudioEnabled: function(value) {
      StorageService.writeValue(AUDIO_ENABLED_KEY, value);
    }
  };
}]);