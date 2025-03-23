package mx.openpay.android;

public class OperationResult<T> {
   private T result;

   public OperationResult(final T result) {
      this.result = result;
   }

   public T getResult() {
      return this.result;
   }
}
