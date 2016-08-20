package sgm.plugin.barcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Responsible for effectively decode data from base64 images.
 */
public class Base64Decoder {

  /**
   * Decode data from the base64 image. The image can be passed with
   * the data prefix <code>data:image/jpeg;base64,/9j/4AAQSkZJRgA...lft1f/2Q==</code>
   * or without <code>/9j/4AAQSkZJRgA...lft1f/2Q==</code>.
   *
   * @param base64Image The base64 image
   * @return Decoded data if the image is valid
   * @throws NotFoundException If the image don't have any valid code
   */
  public static String decode(String base64Image) throws NotFoundException {
    InputStream inputStream;

    if (base64Image.contains(",")) {
      int cutIndex = base64Image.indexOf(",") + 1;
      String plainImage = base64Image.substring(cutIndex);
      inputStream = createInputStream(plainImage);
    } else {
      inputStream = createInputStream(base64Image);
    }

    BinaryBitmap binaryBitmap = createBinaryBitmap(inputStream);
    Result result = new MultiFormatReader().decode(binaryBitmap);

    return result.getText();
  }

  private static InputStream createInputStream(String base64Image) {
    byte[] bytes = Base64.decode(base64Image, Base64.DEFAULT);
    return new ByteArrayInputStream(bytes);
  }

  private static BinaryBitmap createBinaryBitmap(InputStream inputStream) {
    RGBLuminanceSource luminanceSource = createLuminanceSource(inputStream);
    HybridBinarizer hybridBinarizer = new HybridBinarizer(luminanceSource);

    return new BinaryBitmap(hybridBinarizer);
  }

  private static RGBLuminanceSource createLuminanceSource(InputStream inputStream) {
    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
    int width = bitmap.getWidth();
    int height = bitmap.getHeight();
    int[] pixels = new int[width * height];

    bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
    bitmap.recycle();

    return new RGBLuminanceSource(width, height, pixels);
  }
}