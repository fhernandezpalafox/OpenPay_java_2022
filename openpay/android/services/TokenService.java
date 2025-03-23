package mx.openpay.android.services;

import mx.openpay.android.exceptions.OpenpayServiceException;
import mx.openpay.android.exceptions.ServiceUnavailableException;
import mx.openpay.android.model.Card;
import mx.openpay.android.model.Token;

public class TokenService extends BaseService<Card, Token> {
   private static final String MERCHANT_TOKENS = "tokens";

   public TokenService(final String baseUrl, final String merchantId, final String apiKey) {
      super(baseUrl, merchantId, apiKey, Token.class);
   }

   public Token create(final Card card) throws OpenpayServiceException, ServiceUnavailableException {
      return (Token)this.post("tokens", card);
   }
}
