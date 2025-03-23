package mx.openpay.android;

import mx.openpay.android.exceptions.OpenpayServiceException;
import mx.openpay.android.exceptions.ServiceUnavailableException;

public class OpenPayResult<T> {
   private OpenpayServiceException openpayServiceException;
   private OperationResult<T> operationResult;
   private ServiceUnavailableException serviceUnavailableException;

   public OpenpayServiceException getOpenpayServiceException() {
      return this.openpayServiceException;
   }

   public void setOpenpayServiceException(final OpenpayServiceException openpayServiceException) {
      this.openpayServiceException = openpayServiceException;
   }

   public OperationResult<T> getOperationResult() {
      return this.operationResult;
   }

   public void setOperationResult(final OperationResult<T> operationResult) {
      this.operationResult = operationResult;
   }

   public ServiceUnavailableException getServiceUnavailableException() {
      return this.serviceUnavailableException;
   }

   public void setServiceUnavailableException(final ServiceUnavailableException serviceUnavailableException) {
      this.serviceUnavailableException = serviceUnavailableException;
   }
}
