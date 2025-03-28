package mx.openpay.android.model;

import android.annotation.SuppressLint;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;

@SuppressLint({"DefaultLocale"})
public class Card {
   @Key("creation_date")
   private DateTime creationDate;
   @Key
   private String id;
   @Key("bank_name")
   private String bankName;
   @Key("allows_payouts")
   private Boolean allowsPayouts;
   @Key("holder_name")
   private String holderName;
   @Key("expiration_month")
   private String expirationMonth;
   @Key("expiration_year")
   private String expirationYear;
   @Key
   private Address address;
   @Key("card_number")
   private String cardNumber;
   @Key
   private String brand;
   @Key("allows_charges")
   private Boolean allowsCharges;
   @Key("bank_code")
   private String bankCode;
   @Key
   private String type;
   @Key
   private String cvv2;

   public Card cardNumber(final String cardNumber) {
      this.cardNumber = cardNumber;
      return this;
   }

   public Card cvv2(final String cvv2) {
      this.cvv2 = cvv2;
      return this;
   }

   public Card expirationMonth(final Integer expirationMonth) {
      this.expirationMonth = this.getTwoDigitString(expirationMonth);
      return this;
   }

   public Card expirationYear(final Integer expirationYear) {
      this.expirationYear = this.getTwoDigitString(expirationYear);
      return this;
   }

   private String getTwoDigitString(final Integer number) {
      return number == null ? null : String.format("%02d", number);
   }

   public Card holderName(final String holderName) {
      this.holderName = holderName;
      return this;
   }

   public Card address(final Address address) {
      this.address = address;
      return this;
   }

   public Card bankCode(final Integer bankCode) {
      if (bankCode == null) {
         this.bankCode = null;
      } else {
         this.bankCode = String.format("%03d", bankCode);
      }

      return this;
   }

   public Card bankCode(final String bankCode) {
      this.bankCode = bankCode;
      return this;
   }

   public String toString() {
      return String.format("Card [creationDate=%s, id=%s, bankName=%s, allowsPayouts=%s, holderName=%s, expirationMonth=%s, expirationYear=%s, address=%s, cardNumber=%s, brand=%s, allowsCharges=%s, bankCode=%s, type=%s, cvv2=%s]", this.creationDate, this.id, this.bankName, this.allowsPayouts, this.holderName, this.expirationMonth, this.expirationYear, this.address, this.cardNumber, this.brand, this.allowsCharges, this.bankCode, this.type, this.cvv2);
   }

   public DateTime getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(final DateTime creationDate) {
      this.creationDate = creationDate;
   }

   public String getId() {
      return this.id;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public String getBankName() {
      return this.bankName;
   }

   public void setBankName(final String bankName) {
      this.bankName = bankName;
   }

   public Boolean getAllowsPayouts() {
      return this.allowsPayouts;
   }

   public void setAllowsPayouts(final Boolean allowsPayouts) {
      this.allowsPayouts = allowsPayouts;
   }

   public String getHolderName() {
      return this.holderName;
   }

   public void setHolderName(final String holderName) {
      this.holderName = holderName;
   }

   public String getExpirationMonth() {
      return this.expirationMonth;
   }

   public void setExpirationMonth(final String expirationMonth) {
      this.expirationMonth = expirationMonth;
   }

   public String getExpirationYear() {
      return this.expirationYear;
   }

   public void setExpirationYear(final String expirationYear) {
      this.expirationYear = expirationYear;
   }

   public Address getAddress() {
      return this.address;
   }

   public void setAddress(final Address address) {
      this.address = address;
   }

   public String getCardNumber() {
      return this.cardNumber;
   }

   public void setCardNumber(final String cardNumber) {
      this.cardNumber = cardNumber;
   }

   public String getBrand() {
      return this.brand;
   }

   public void setBrand(final String brand) {
      this.brand = brand;
   }

   public Boolean getAllowsCharges() {
      return this.allowsCharges;
   }

   public void setAllowsCharges(final Boolean allowsCharges) {
      this.allowsCharges = allowsCharges;
   }

   public String getBankCode() {
      return this.bankCode;
   }

   public void setBankCode(final String bankCode) {
      this.bankCode = bankCode;
   }

   public String getType() {
      return this.type;
   }

   public void setType(final String type) {
      this.type = type;
   }

   public String getCvv2() {
      return this.cvv2;
   }

   public void setCvv2(final String cvv2) {
      this.cvv2 = cvv2;
   }
}
