var exec = require('cordova/exec');
var SGMBarcode = function() {}

SGMBarcode.decode = function (base64Image, successCallback, errorCallback) {
  exec(successCallback, errorCallback, "SGMBarcode", "decode", [base64Image]);
}

module.exports = SGMBarcode;