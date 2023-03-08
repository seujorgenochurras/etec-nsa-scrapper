package org.jhey.nsa.api.utils;

import java.text.Normalizer;

public final class StringUtils {
      private StringUtils(){}

   public static String unaccent(String string) {
         string = Normalizer.normalize(string, Normalizer.Form.NFD);
         string = string.replaceAll("[^\\p{ASCII}]", "");
         if(string.contains("Lngua")) string = string.replace("Lngua", "Lingua");
         else if(string.contains("Fsi")) string = string.replace("Fsi", "Fisi");
         else if(string.contains("Qum")) string = string.replace("Qum", "Quim");
         return string;
   }
}