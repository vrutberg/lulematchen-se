describe('SettingsService', function() {
  beforeEach(module('app'));

  var SettingsService, StorageService;

  beforeEach(inject(function (_SettingsService_, _StorageService_) {
    SettingsService = _SettingsService_;
    StorageService = _StorageService_;

    localStorage.clear();
  }));

  afterEach(function() {
    localStorage.clear();
  });

  describe('audio', function() {
    it('should be on by default', function() {
      var value = SettingsService.isAudioEnabled();
      expect(value).toBe(true);
    });

    it('should store the setting correctly', function() {
      SettingsService.setAudioEnabled(false);
      var value = SettingsService.isAudioEnabled();
      expect(value).toBe(false);
    });
  });
});