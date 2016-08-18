var exec = require('cordova/exec');
var SGMBarcode = function() {}

SGMBarcode.read = function (base64Image, successCallback, errorCallback) {
  exec(successCallback, errorCallback, "SGMBarcode", "read", [base64Image]);
}

module.exports = SGMBarcode;