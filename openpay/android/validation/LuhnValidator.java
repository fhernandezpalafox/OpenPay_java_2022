package mx.openpay.android.validation;

import java.util.ArrayList;
import java.util.List;

public class LuhnValidator {
   private static final int multiplicator = 2;

   public static boolean passesLuhnTest(String value) {
      char[] chars = value.toCharArray();
      List<Integer> digits = new ArrayList();
      char[] var3 = chars;
      int sum = chars.length;

      for(int var5 = 0; var5 < sum; ++var5) {
         char c = var3[var5];
         if (Character.isDigit(c)) {
            digits.add(c - 48);
         }
      }

      int length = digits.size();
      sum = 0;
      boolean even = false;

      for(int index = length - 1; index >= 0; --index) {
         int digit = (Integer)digits.get(index);
         if (even) {
            digit *= 2;
         }

         if (digit > 9) {
            digit = digit / 10 + digit % 10;
         }

         sum += digit;
         even = !even;
      }

      return sum % 10 == 0;
   }
}
