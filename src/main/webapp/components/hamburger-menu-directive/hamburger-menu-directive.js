angular.module('app').directive('hamburgerMenu', ['Modernizr', 'SettingsService', 'WebNotificationsService', function(Modernizr, SettingsService, WebNotificationsService) {
  return {
    templateUrl: 'components/hamburger-menu-directive/hamburger-menu-template.html',

    scope: {},

    controller: ['$scope', function($scope) {
      $scope.isAutoplaySupported = false;
      $scope.isWebNotificationsSupported = WebNotificationsService.isSupported();

      $scope.isWebNotificationsEnabled = !WebNotificationsService.needsPermission() && SettingsService.isWebNotificationsEnabled();
      $scope.isAudioEnabled = SettingsService.isAudioEnabled();
      $scope.isRetroModeEnabled = SettingsService.isRetroModeEnabled();

      $scope.$watch('isAudioEnabled', function(newValue, oldValue) {
        if (newValue !== oldValue) {
          SettingsService.setAudioEnabled(newValue);
        }
      });

      $scope.$watch('isRetroModeEnabled', function(newValue, oldValue) {
        if (newValue !== oldValue) {
          SettingsService.setRetroModeEnabled(newValue);
        }
      });

      $scope.$watch('isWebNotificationsEnabled', function(newValue, oldValue) {
        if (newValue !== oldValue) {
          if (newValue === true && WebNotificationsService.needsPermission()) {
            SettingsService.setWebNotificationsEnabled(false);

            WebNotificationsService.requestPermission().then(function() {
              SettingsService.setWebNotificationsEnabled(true);
            }, function() {
              $scope.isWebNotificationsEnabled = false;
            });
          } else {
            SettingsService.setWebNotificationsEnabled(newValue);
          }
        }
      });

      $scope.$watch(function() { return Modernizr.autoplay; }, function(newValue, oldValue) {
        $scope.isAutoplaySupported = newValue;
      });
    }],

    link: function() {
      $('body').addClass('drawer drawer--right');

      $('.drawer').drawer({
        iscroll: {
          // Configuring the iScroll
          // https://github.com/cubiq/iscroll#configuring-the-iscroll
          mouseWheel: true,
          preventDefault: false
        },
        showOverlay: true
      });
    }
  };
}]);