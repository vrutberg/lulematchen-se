angular.module('app').factory('SettingsService', ['StorageService', function(StorageService) {
  var AUDIO_ENABLED_KEY = "se.lulematchen.settings.audio";
  var WEB_NOTIFICATIONS_ENABLED_KEY = "se.lulematchen.settings.web-notifications";
  var RETRO_MODE_ENABLED_KEY = "se.lulematchen.settings.retro-mode";

  return {
    isAudioEnabled: function() {
      return StorageService.readValue(AUDIO_ENABLED_KEY, true);
    },

    setAudioEnabled: function(value) {
      StorageService.writeValue(AUDIO_ENABLED_KEY, value);
    },

    isWebNotificationsEnabled: function() {
      return StorageService.readValue(WEB_NOTIFICATIONS_ENABLED_KEY, false);
    },

    setWebNotificationsEnabled: function(value) {
      return StorageService.writeValue(WEB_NOTIFICATIONS_ENABLED_KEY, value);
    },

    isRetroModeEnabled: function() {
      return StorageService.readValue(RETRO_MODE_ENABLED_KEY, false);
    },

    setRetroModeEnabled: function(value) {
      return StorageService.writeValue(RETRO_MODE_ENABLED_KEY, value);
    }
  };
}]);