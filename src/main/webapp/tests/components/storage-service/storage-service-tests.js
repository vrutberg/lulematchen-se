describe('StorageService', function() {
  beforeEach(module('app'));

  var StorageService;

  beforeEach(inject(function (_StorageService_) {
    StorageService = _StorageService_;

    localStorage.clear();
  }));

  it('should return the default value when no value has been written', function() {
    var value = StorageService.readValue('key', 123);
    expect(value).toBe(123);
  });

  it('should return a stored number value correctly', function() {
    StorageService.writeValue('key', 123);
    var value = StorageService.readValue('key', 456);
    expect(value).toBe(123);
  });

  it('should return a stored boolean value correctly', function() {
    StorageService.writeValue('key', true);
    var value = StorageService.readValue('key', false);
    expect(value).toBe(true);
  });

  it('should return a stored complex value correctly', function() {
    StorageService.writeValue('key', { a: 1, b: true, c: 'a string' });
    var value = StorageService.readValue('key', false);
    expect(value).toEqual({ a: 1, b: true, c: 'a string' });
  });
});