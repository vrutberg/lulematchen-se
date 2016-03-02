angular.module('app').factory('NotificationService', ['SettingsService', 'WebNotificationsService', function(SettingsService, WebNotificationsService) {
  var shouldDisplayNotifications = function() {
    return SettingsService.isWebNotificationsEnabled() && WebNotificationsService.isSupported();
  };

  return {
    displayNotification: function(title, body) {
      if (!shouldDisplayNotifications()) {
        return;
      }

      WebNotificationsService.displayNotification(title, body, 'img/general/icon-notification.png');
    }
  };
}]);