package mx.openpay.android;

import android.app.Activity;
import android.provider.Settings.Secure;
import android.webkit.JavascriptInterface;

public class JavaScriptInterface {
   private Activity activity;

   JavaScriptInterface(final Activity activity) {
      this.activity = activity;
   }

   @JavascriptInterface
   public String getIdentifierForVendor() {
      return Secure.getString(this.activity.getContentResolver(), "android_id");
   }
}
