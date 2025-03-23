package mx.openpay.android.services;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import mx.openpay.android.exceptions.OpenpayServiceException;
import mx.openpay.android.exceptions.ServiceUnavailableException;

public abstract class BaseService<V, T> {
   private static final JsonFactory JSON_FACTORY = new JacksonFactory();
   private static final String EMPTY_PASSWORD = "";
   private static final String API_VERSION = "v1";
   private static final String URL_SEPARATOR = "/";
   private static final String AGENT = "openpay-android/";
   private static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
   protected String baseUrl;
   protected String merchantId;
   protected String apiKey;
   protected Class<T> clazz;

   public BaseService(final String baseUrl, final String merchantId, final String apiKey, final Class<T> clazz) {
      this.merchantId = merchantId;
      this.apiKey = apiKey;
      this.clazz = clazz;
      this.baseUrl = baseUrl;
   }

   public HttpRequestFactory getRequestFactory() {
      HttpTransport netHttpTransport = new NetHttpTransport();
      return netHttpTransport.createRequestFactory(new BaseService.OpenpayHttpRequestInitializer());
   }

   public GenericUrl getGenericUrl(final String resourceUrl) {
      StringBuilder urlBuilder = (new StringBuilder(this.baseUrl)).append("/").append("v1").append("/").append(this.merchantId).append("/").append(resourceUrl);
      return new GenericUrl(urlBuilder.toString());
   }

   public HttpContent getContent(final V jsonData) {
      return new JsonHttpContent(JSON_FACTORY, jsonData);
   }

   public T post(final String resourceUrl, final V data) throws OpenpayServiceException, ServiceUnavailableException {
      try {
         HttpRequest request = this.getRequestFactory().buildPostRequest(this.getGenericUrl(resourceUrl), this.getContent(data));
         T newObject = request.execute().parseAs(this.clazz);
         return newObject;
      } catch (IOException var5) {
         if (var5 instanceof OpenpayServiceException) {
            throw (OpenpayServiceException)var5;
         } else {
            throw new ServiceUnavailableException(var5);
         }
      }
   }

   private class OpenpayHttpRequestInitializer implements HttpRequestInitializer {
      private OpenpayHttpRequestInitializer() {
      }

      public void initialize(final HttpRequest request) {
         request.setParser(new JsonObjectParser(BaseService.JSON_FACTORY));
         request.getHeaders().setBasicAuthentication(BaseService.this.apiKey, "");
         String version = this.getClass().getPackage().getImplementationVersion();
         if (version == null) {
            version = "1.0.1-UNKNOWN";
         }

         request.setSuppressUserAgentSuffix(true);
         request.getHeaders().setUserAgent("openpay-android/" + version);
         request.setUnsuccessfulResponseHandler(new BaseService.OpenpayHttpRequestInitializer.ServiceUnsuccessfulResponseHandler());
         request.setConnectTimeout(60000);
         request.setReadTimeout(60000);
      }

      // $FF: synthetic method
      OpenpayHttpRequestInitializer(Object x1) {
         this();
      }

      private class ServiceUnsuccessfulResponseHandler implements HttpUnsuccessfulResponseHandler {
         private ServiceUnsuccessfulResponseHandler() {
         }

         public boolean handleResponse(final HttpRequest request, final HttpResponse response, final boolean arg2) throws IOException {
            OpenpayServiceException exception = (OpenpayServiceException)(new JsonObjectParser(BaseService.JSON_FACTORY)).parseAndClose(response.getContent(), response.getContentCharset(), OpenpayServiceException.class);
            throw exception;
         }

         // $FF: synthetic method
         ServiceUnsuccessfulResponseHandler(Object x1) {
            this();
         }
      }
   }
}
