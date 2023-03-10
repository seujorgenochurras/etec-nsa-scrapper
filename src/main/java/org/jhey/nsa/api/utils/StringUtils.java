package org.jhey.nsa.api.utils;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public final class StringUtils {
   private static final HashMap<String, String> STRINGS_THAT_CONVERTER_DOESNT_CATCH = new HashMap<>();
   static{
      STRINGS_THAT_CONVERTER_DOESNT_CATCH.put("Lngua", "Lingua");
      STRINGS_THAT_CONVERTER_DOESNT_CATCH.put("Fsi", "Fisi");
      STRINGS_THAT_CONVERTER_DOESNT_CATCH.put("Qum", "Quim");
   }
      private StringUtils(){}

   /**
    * Removes all the accents (çãéì)
    * */
   public static String unaccent(String word) {
         word = Normalizer.normalize(word, Normalizer.Form.NFD);
         word = word.replaceAll("[^\\p{ASCII}]", "");
         for (Map.Entry<String, String> entry : STRINGS_THAT_CONVERTER_DOESNT_CATCH.entrySet()) {
         word = word.replace(entry.getKey(), entry.getValue());
        }
         return word;
   }
}