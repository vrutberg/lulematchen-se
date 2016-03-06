angular.module('app').factory('NotificationService', ['SettingsService', 'WebNotificationsService', function(SettingsService, WebNotificationsService) {
  return {
    displayNotification: function(title, body) {
      WebNotificationsService.displayNotification(title, body, 'img/general/icon-notification.png');
    }
  };
}]);