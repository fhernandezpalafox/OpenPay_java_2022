package mx.openpay.android;

import android.os.AsyncTask;
import mx.openpay.android.exceptions.OpenpayServiceException;
import mx.openpay.android.exceptions.ServiceUnavailableException;
import mx.openpay.android.model.Card;
import mx.openpay.android.model.Token;
import mx.openpay.android.services.ServicesFactory;
import mx.openpay.android.services.TokenService;

public class Openpay {
   private TokenService tokenService;
   private DeviceCollectorDefaultImpl deviceCollectorDefaultImpl;

   public Openpay(final String merchantId, final String apiKey, final Boolean productionMode) {
      this(merchantId, apiKey, productionMode, OpCountry.MX);
   }

   public Openpay(final String merchantId, final String apiKey, final Boolean productionMode, final OpCountry opCountry) {
      String baseUrl = opCountry.getUrlSandbox();
      if (productionMode) {
         baseUrl = opCountry.getUrlProduction();
      }

      ServicesFactory servicesFactory = ServicesFactory.getInstance(baseUrl, merchantId, apiKey);
      this.tokenService = (TokenService)servicesFactory.getService(TokenService.class);
      this.deviceCollectorDefaultImpl = new DeviceCollectorDefaultImpl(baseUrl, merchantId);
   }

   public void createToken(final Card card, final OperationCallBack<Token> operationCallBack) {
      (new AsyncTask<Void, Void, OpenPayResult<Token>>() {
         protected OpenPayResult<Token> doInBackground(final Void... params) {
            OpenPayResult openPayResult = new OpenPayResult();

            try {
               Token newToken = Openpay.this.tokenService.create(card);
               openPayResult.setOperationResult(new OperationResult(newToken));
            } catch (OpenpayServiceException var4) {
               openPayResult.setOpenpayServiceException(var4);
            } catch (ServiceUnavailableException var5) {
               openPayResult.setServiceUnavailableException(var5);
            }

            return openPayResult;
         }

         protected void onPostExecute(final OpenPayResult<Token> result) {
            if (result.getOperationResult() != null) {
               operationCallBack.onSuccess(result.getOperationResult());
            } else if (result.getOpenpayServiceException() != null) {
               operationCallBack.onError(result.getOpenpayServiceException());
            } else if (result.getServiceUnavailableException() != null) {
               operationCallBack.onCommunicationError(result.getServiceUnavailableException());
            }

         }
      }).execute(new Void[0]);
   }

   public DeviceCollectorDefaultImpl getDeviceCollectorDefaultImpl() {
      return this.deviceCollectorDefaultImpl;
   }
}
