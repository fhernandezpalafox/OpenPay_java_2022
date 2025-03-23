package mx.openpay.android.validation;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressLint({"SimpleDateFormat"})
public class CardValidator {
   public static boolean validateCard(final String holderName, final String cardNumber, final Integer expMonth, final Integer expYear, final String cvv) {
      return validateHolderName(holderName) && validateCVV(cvv, cardNumber) && validateExpiryDate(expMonth, expYear) && validateNumber(cardNumber);
   }

   public static boolean validateHolderName(final String holderName) {
      return holderName != null && holderName.trim().length() > 0;
   }

   public static boolean validateCVV(final String cvv, final String cardNumber) {
      if (cvv != null && cvv.trim().length() != 0) {
         CardType type = getType(cardNumber);
         if (CardType.AMEX.equals(type) && cvv.trim().length() != 4) {
            return false;
         } else if ((CardType.MASTERCARD.equals(type) || CardType.VISA.equals(type)) && cvv.trim().length() != 3) {
            return false;
         } else {
            return !CardType.UNKNOWN.equals(type);
         }
      } else {
         return false;
      }
   }

   public static boolean validateExpiryDate(final Integer expirationMonth, final Integer expirationYear) {
      if (!validateMonth(expirationMonth)) {
         return false;
      } else if (expirationYear == null) {
         return false;
      } else {
         Calendar today = new GregorianCalendar();
         SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yy");

         try {
            Date expirationDate = dateFormat.parse(expirationMonth + "-" + expirationYear);
            Calendar cardExpiration = new GregorianCalendar();
            cardExpiration.setTime(expirationDate);
            if (cardExpiration.get(1) > today.get(1)) {
               return true;
            }

            if (cardExpiration.get(1) == today.get(1) && cardExpiration.get(2) >= today.get(2)) {
               return true;
            }
         } catch (ParseException var6) {
         }

         return false;
      }
   }

   public static boolean validateMonth(final Integer month) {
      if (month == null) {
         return false;
      } else {
         return month >= 1 && month <= 12;
      }
   }

   public static boolean validateNumber(final String number) {
      if (number != null && number.trim().length() != 0) {
         if (!LuhnValidator.passesLuhnTest(number)) {
            return false;
         } else {
            String trimNumber = number.trim();
            CardType type = getType(number);
            if (CardType.AMEX.equals(type) && trimNumber.length() != 15) {
               return false;
            } else if (CardType.MASTERCARD.equals(type) && trimNumber.length() != 16) {
               return false;
            } else if (CardType.VISA.equals(type) && trimNumber.length() != 16 && trimNumber.length() != 13) {
               return false;
            } else {
               return !CardType.UNKNOWN.equals(type);
            }
         }
      } else {
         return false;
      }
   }

   public static boolean startsWith(final String cardNumber, final String... prefixes) {
      String[] var2 = prefixes;
      int var3 = prefixes.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String prefix = var2[var4];
         if (cardNumber.startsWith(prefix)) {
            return true;
         }
      }

      return false;
   }

   public static CardType getType(final String cardNumber) {
      if (cardNumber != null && cardNumber.trim().length() > 0) {
         if (startsWith(cardNumber, "34", "37")) {
            return CardType.AMEX;
         }

         if (startsWith(cardNumber, "4")) {
            return CardType.VISA;
         }

         if (startsWith(cardNumber, "51", "52", "53", "54", "55")) {
            return CardType.MASTERCARD;
         }
      }

      return CardType.UNKNOWN;
   }
}
