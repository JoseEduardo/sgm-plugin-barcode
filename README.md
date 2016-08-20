SGMBarcode
==========

The `sgm.plugin.SGMBarcode` object provides functions to make decoding barcode data from base64 images easier.

    cordova plugin add https://github.com/uanderson/sgm-plugin-barcode.git

# API reference

SGMBarcode.decode
=================

Decode barcode data from the base64 image.

    sgm.plugin.SGMBarcode.decode(base64Image, successCallback, errorCallback);

Parameters
----------

base64Image: the base64 image data URL
successCallback: called with the decoded data (optional)
errorCallback: called when it is not possible to decode the image (optional)

Supported platforms
-------------------

- Android
- iOS (Coming)
- Windows Phone (Coming)
- Browser (Coming)

Example
-------
```javascript
sgm.plugin.SGMBarcode.decode('/9j/4AAQSkZJRgA...lft1f/2Q==', function (decodedData) {
  console.log(decodedData);
});
```