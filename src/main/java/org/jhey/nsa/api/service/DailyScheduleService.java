package org.jhey.nsa.api.service;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.api.repository.DailyScheduleRepository;
import org.jhey.nsa.request.Requester;
import org.jhey.nsa.request.Transcriber;
import org.jhey.nsa.selenium.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DailyScheduleService {
   @Autowired
   private DailyScheduleRepository scheduleRepository;

   @Autowired
   private LessonService lessonService;

   @Autowired
   private Transcriber transcriber;

   public boolean hasChangedSinceLastSchedule(DailySchedule dailySchedule){
      return !dailySchedule.equals(scheduleRepository.findFirstByOrderByIdDesc());
   }

   @Transactional
   public DailySchedule getLatestDailySchedule(){
     return checkForScheduleChanges();
   }

   private DailySchedule requestLatestSchedule(){
      ChromeDriver chromeDriver = new ChromeDriver();

      chromeDriver.get(Dotenv.load().get("SCHOOL_URL"));

      LoginPage loginPage = new LoginPage(chromeDriver);

      Requester requester = new Requester(loginPage.login());
      DailySchedule dailySchedule = transcriber.transcribe(requester.getSchedulePage());

      chromeDriver.close();
      return dailySchedule;
   }
   @Transactional
   private DailySchedule checkForScheduleChanges(){

      DailySchedule latestFetchedSchedule = requestLatestSchedule();

      boolean hasChanged = hasChangedSinceLastSchedule(latestFetchedSchedule);

      return hasChanged ? save(latestFetchedSchedule) : latestFetchedSchedule;
   }

   @Transactional
   public DailySchedule save(@Valid DailySchedule dailySchedule){
      return scheduleRepository.save(dailySchedule);
   }

   static{
      System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
   }
}
