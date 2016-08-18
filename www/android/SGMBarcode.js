function SGMBarcode() {
  this.read = function (base64Image, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "SGMBarcode", "read", [base64Image]);
  }
}

module.exports = SGMBarcode;