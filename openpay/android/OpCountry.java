package mx.openpay.android;

public enum OpCountry {
   MX("https://sandbox-api.openpay.mx", "https://api.openpay.mx"),
   COL("https://sandbox-api.openpay.co", "https://api.openpay.co"),
   PE("https://sandbox-api.openpay.pe", "https://api.openpay.pe");

   private final String urlSandbox;
   private final String urlProduction;

   private OpCountry(String urlSandbox, String urlProduction) {
      this.urlSandbox = urlSandbox;
      this.urlProduction = urlProduction;
   }

   protected String getUrlProduction() {
      return this.urlProduction;
   }

   protected String getUrlSandbox() {
      return this.urlSandbox;
   }
}
