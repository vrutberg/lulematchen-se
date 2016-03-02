angular.module('app').factory('WebNotificationsService', ['$q', function($q) {
  var isSupported = function() {
    return 'Notification' in window;
  };

  var needsPermission = function() {
    if (!isSupported()) {
      return true;
    }

    return Notification.permission !== 'granted' && Notification.permission !== 'denied';
  };

  return {
    isSupported: isSupported,
    needsPermission: needsPermission,

    requestPermission: function() {
      if (!isSupported()) {
        return $q.reject();
      }

      var deferred = $q.defer();

      Notification.requestPermission(function(permission) {
        if (permission === 'granted') {
          deferred.resolve();
        } else {
          deferred.reject();
        }
      });

      return deferred.promise;
    },

    displayNotification: function(title, body, icon) {
      if (!isSupported() || needsPermission()) {
        return;
      }

      var options = {
        body: body,
        icon: icon
      };

      new Notification(title, options);
    }
  };
}]);