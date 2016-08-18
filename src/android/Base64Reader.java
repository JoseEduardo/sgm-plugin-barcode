package sgm.plugin.barcode;

import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Base64Reader {

  public String read(String base64Image) throws NotFoundException {
    InputStream inputStream;

    if (base64Image.contains(",")) {
      int substringIndex = base64Image.indexOf(",") + 1;
      String splittedImage = base64Image.substring(substringIndex);
      inputStream = createInputStream(splittedImage);
    } else {
      inputStream = createInputStream(base64Image);
    }

    BinaryBitmap binaryBitmap = createBinaryBitmap(inputStream);
    Result result = new MultiFormatReader().decode(binaryBitmap);

    return result.getText();
  }

  private InputStream createInputStream(String base64Image) {
    byte[] bytes = Base64.decode(base64Image, Base64.DEFAULT);
    return new ByteArrayInputStream(bytes);
  }

  private BinaryBitmap createBinaryBitmap(InputStream inputStream) {
    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
    int width = bitmap.getWidth();
    int height = bitmap.getHeight();
    int[] pixels = new int[width * height];
    bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
    bitmap.recycle();
    bitmap = null;

    RGBLuminanceSource luminanceSource = new RGBLuminanceSource(width, height, pixels);
    HybridBinarizer hybridBinarizer = new HybridBinarizer(luminanceSource);

    return new BinaryBitmap(hybridBinarizer);
  }
}