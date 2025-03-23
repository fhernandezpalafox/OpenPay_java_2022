package mx.openpay.android;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.UUID;

public class DeviceCollectorDefaultImpl {
   private String baseUrl;
   private String merchantId;
   private String errorMessage;
   private WebView webView;

   public DeviceCollectorDefaultImpl(final String baseUrl, final String merchantId) {
      this.baseUrl = baseUrl;
      this.merchantId = merchantId;
   }

   public String setup(final Activity activity) {
      try {
         String sessionId = UUID.randomUUID().toString();
         sessionId = sessionId.replace("-", "");
         char[] sessionIdChars = sessionId.toCharArray();
         sessionIdChars[12] = 'A';
         sessionId = String.valueOf(sessionIdChars);
         Log.i("OpenpaySDK", "buildedDeviceId: " + sessionId);
         this.webView = new WebView(activity);
         this.webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
               super.onPageFinished(view, url);
               Log.i("OpenpaySDK", "oc loaded");
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
               super.onReceivedError(view, request, error);
               Log.i("OpenpaySDK", "page error: " + error.toString());
            }
         });
         WebSettings webSettings = this.webView.getSettings();
         webSettings.setJavaScriptEnabled(true);
         String urlAsString = String.format("%s/oa/logo.htm?m=%s&s=%s", this.baseUrl, this.merchantId, sessionId);
         this.webView.loadUrl(urlAsString);
         return sessionId;
      } catch (Exception var6) {
         System.out.println(var6.getMessage());
         var6.printStackTrace();
         this.logError(this.getClass().getName(), var6.getMessage());
         this.errorMessage = var6.getMessage();
         throw new RuntimeException(var6);
      }
   }

   private void logError(final String tag, final String content) {
      if (content.length() > 4000) {
         Log.e(tag, content.substring(0, 4000));
         this.logError(tag, content.substring(4000));
      } else {
         Log.e(tag, content);
      }

   }

   public String getErrorMessage() {
      return this.errorMessage;
   }
}
