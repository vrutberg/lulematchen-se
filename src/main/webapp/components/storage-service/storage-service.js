angular.module('app').factory('StorageService', [function() {
  return {
    readValue: function(key, defaultValue) {
      var value = localStorage.getItem(key);

      if (value == null) {
        return defaultValue;
      }

      var data = JSON.parse(value);

      return data.value;
    },

    writeValue: function(key, value) {
      var data = {
        value: value
      };

      localStorage.setItem(key, JSON.stringify(data));
    }
  };
}]);