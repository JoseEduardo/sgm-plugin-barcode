package sgm.plugin.barcode;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.zxing.NotFoundException;

public class SGMBarcode extends CordovaPlugin {

  private static final String READ_ACTION = "read";

  @Override
  public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
    if (READ_ACTION.equals(action)) {
      try {
        Base64Reader base64Reader = new Base64Reader();
        String decodedMessage = base64Reader.read(data.getString(0));
        callbackContext.success(decodedMessage);
        return true;
      } catch (NotFoundException ex) {
        callbackContext.error("Not found");
      }
    }

    return false;
  }
}