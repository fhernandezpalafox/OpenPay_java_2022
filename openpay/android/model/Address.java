package mx.openpay.android.model;

import com.google.api.client.util.Key;

public class Address {
   @Key("postal_code")
   private String postalCode;
   @Key
   private String line1;
   @Key
   private String line2;
   @Key
   private String line3;
   @Key
   private String city;
   @Key
   private String state;
   @Key("country_code")
   private String countryCode;

   public String toString() {
      return String.format("Address [postalCode=%s, line1=%s, line2=%s, line3=%s, city=%s, state=%s, countryCode=%s]", this.postalCode, this.line1, this.line2, this.line3, this.city, this.state, this.countryCode);
   }

   public Address postalCode(final String postalCode) {
      this.postalCode = postalCode;
      return this;
   }

   public Address line1(final String line1) {
      this.line1 = line1;
      return this;
   }

   public Address line2(final String line2) {
      this.line2 = line2;
      return this;
   }

   public Address line3(final String line3) {
      this.line3 = line3;
      return this;
   }

   public Address city(final String city) {
      this.city = city;
      return this;
   }

   public Address state(final String state) {
      this.state = state;
      return this;
   }

   public Address countryCode(final String countryCode) {
      this.countryCode = countryCode;
      return this;
   }

   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(final String postalCode) {
      this.postalCode = postalCode;
   }

   public String getLine1() {
      return this.line1;
   }

   public void setLine1(final String line1) {
      this.line1 = line1;
   }

   public String getLine2() {
      return this.line2;
   }

   public void setLine2(final String line2) {
      this.line2 = line2;
   }

   public String getLine3() {
      return this.line3;
   }

   public void setLine3(final String line3) {
      this.line3 = line3;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(final String city) {
      this.city = city;
   }

   public String getState() {
      return this.state;
   }

   public void setState(final String state) {
      this.state = state;
   }

   public String getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(final String countryCode) {
      this.countryCode = countryCode;
   }
}
