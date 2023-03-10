package org.jhey.nsa.api.utils;

import org.jboss.logging.Logger;

public class ThreadUtils {
   private ThreadUtils(){}
   public static void sleep(long milliseconds){
      try {
      Thread.sleep(milliseconds);
      }catch (InterruptedException e){
         Logger logger = Logger.getLogger(ThreadUtils.class.getName());
         logger.log(Logger.Level.ERROR, "Interrupted Thread exception has been thrown");
         Thread.currentThread().interrupt();
      }

   }
}
