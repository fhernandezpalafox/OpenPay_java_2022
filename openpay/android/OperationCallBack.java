package mx.openpay.android;

import mx.openpay.android.exceptions.OpenpayServiceException;
import mx.openpay.android.exceptions.ServiceUnavailableException;

public interface OperationCallBack<T> {
   void onError(final OpenpayServiceException error);

   void onCommunicationError(final ServiceUnavailableException error);

   void onSuccess(final OperationResult<T> operationResult);
}
