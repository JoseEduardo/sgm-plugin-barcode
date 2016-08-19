package sgm.plugin.barcode;

import com.google.zxing.NotFoundException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Plugin responsible for decode barcode data from base64 images.
 */
public class SGMBarcode extends CordovaPlugin {

  /**
   * Execute plugin action.
   *
   * @param action   Action to be executed
   * @param data     Array data passed as parameter
   * @param callback Context to notify plugin back
   * @return true when success, false otherwise
   * @throws JSONException If JSON was invalid
   */
  @Override
  public boolean execute(String action, JSONArray data, CallbackContext callback) throws JSONException {
    if ("decode".equals(action)) {
      return decode(data, callback);
    }

    return false;
  }

  private boolean decode(JSONArray data, CallbackContext callback) throws JSONException {
    try {
      String base64Image = data.getString(0);
      String decodedText = Base64Decoder.decode(base64Image);
      callback.success(decodedText);
    } catch (NotFoundException ex) {
      callback.error("Code not found in the base64 image");
    }

    return true;
  }
}