package mx.openpay.android.exceptions;

import android.annotation.TargetApi;
import java.io.IOException;

public class ServiceUnavailableException extends IOException {
   private static final long serialVersionUID = -7388627000694002585L;

   public ServiceUnavailableException(final String message) {
      super(message);
   }

   @TargetApi(26)
   public ServiceUnavailableException(final Throwable cause) {
      super(cause);
   }

   @TargetApi(26)
   public ServiceUnavailableException(final String message, final Throwable cause) {
      super(message, cause);
   }
}
