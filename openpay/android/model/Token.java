package mx.openpay.android.model;

import com.google.api.client.util.Key;

public class Token {
   @Key
   private String id;
   @Key
   private Card card;

   public String toString() {
      return String.format("Token [id=%s, card=%s]", this.id, this.card);
   }

   public Token id(final String id) {
      this.id = id;
      return this;
   }

   public Token card(final Card card) {
      this.card = card;
      return this;
   }

   public String getId() {
      return this.id;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public Card getCard() {
      return this.card;
   }

   public void setCard(final Card card) {
      this.card = card;
   }
}
