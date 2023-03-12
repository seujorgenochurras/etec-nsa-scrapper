package org.jhey.nsa.api.commons;

import org.jhey.nsa.api.service.schedule.ScheduleFetcher;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class ApplicationContextUtils implements ApplicationContextAware {
   private static ApplicationContext applicationContext;

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      ApplicationContextUtils.applicationContext = applicationContext;
   }
   public static ApplicationContext getApplicationContext(){
      return applicationContext;
   }

   public <T> T getBean(Class<T> tClass ) {
      return getApplicationContext().getBean(tClass);
   }
}
