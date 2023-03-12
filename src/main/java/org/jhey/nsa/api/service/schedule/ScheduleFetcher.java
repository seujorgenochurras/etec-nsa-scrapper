package org.jhey.nsa.api.service.schedule;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.request.Requester;
import org.jhey.nsa.request.Transcriber;
import org.jhey.nsa.selenium.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ScheduleFetcher {

   @Autowired
   private Transcriber transcriber;
   @Autowired
   private DailyScheduleService dailyScheduleService;

   private DailySchedule fetchSchedule() {
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get(Dotenv.load().get("SCHOOL_URL"));

      LoginPage loginPage = new LoginPage(chromeDriver);

      Requester requester = new Requester(loginPage.login());
      DailySchedule dailySchedule = transcriber.transcribe(requester.getSchedulePage());
      chromeDriver.quit();
      return dailySchedule;
   }

   @Async
   @Transactional
   public void fetch() {
      DailySchedule freshSchedule = fetchSchedule();
      if (dailyScheduleService.isLatestSchedule(freshSchedule)) {
         dailyScheduleService.save(freshSchedule);
      }
   }
}