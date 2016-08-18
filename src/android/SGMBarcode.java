package sgm.plugin.barcode;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

public class SGMBarcode extends CordovaPlugin {

  private static final String READ_ACTION = "read";

  @Override
  public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
    if (READ_ACTION.equals(action)) {
      callbackContext.success(data.getString(0));
      return true;
    }

    return false;
  }
}