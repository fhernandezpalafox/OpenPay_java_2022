package mx.openpay.android.exceptions;

import android.annotation.TargetApi;
import com.google.api.client.util.Key;
import java.io.IOException;

public class OpenpayServiceException extends IOException {
   private static final long serialVersionUID = -7388627000694002585L;
   @Key
   public String category;
   @Key
   public String description;
   @Key("http_code")
   public Integer httpCode;
   @Key("error_code")
   public Integer errorCode;
   @Key("request_id")
   public String requestId;
   @Key
   public String body;

   public OpenpayServiceException() {
   }

   public OpenpayServiceException(final String message) {
      super(message);
   }

   @TargetApi(9)
   public OpenpayServiceException(final Throwable cause) {
      super(cause);
   }

   @TargetApi(9)
   public OpenpayServiceException(final String message, final Throwable cause) {
      super(message, cause);
   }

   public String getCategory() {
      return this.category;
   }

   public void setCategory(final String category) {
      this.category = category;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(final String description) {
      this.description = description;
   }

   public Integer getHttpCode() {
      return this.httpCode;
   }

   public void setHttpCode(final Integer httpCode) {
      this.httpCode = httpCode;
   }

   public Integer getErrorCode() {
      return this.errorCode;
   }

   public void setErrorCode(final Integer errorCode) {
      this.errorCode = errorCode;
   }

   public String getRequestId() {
      return this.requestId;
   }

   public void setRequestId(final String requestId) {
      this.requestId = requestId;
   }

   public String getBody() {
      return this.body;
   }

   public void setBody(final String body) {
      this.body = body;
   }
}
