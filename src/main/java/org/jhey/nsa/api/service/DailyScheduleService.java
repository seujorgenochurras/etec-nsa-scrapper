package org.jhey.nsa.api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.api.repository.DailyScheduleRepository;
import org.jhey.nsa.request.Requester;
import org.jhey.nsa.request.Transcriber;
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

   @Transactional
   public boolean hasChangedSinceLastSchedule(DailySchedule dailySchedule){
      return !dailySchedule.equals(scheduleRepository.findFirstByOrderByIdDesc());
   }

   @Transactional
   public DailySchedule getLatestDailySchedule(){
     return checkForScheduleChanges();
   }

   @Transactional
   private DailySchedule checkForScheduleChanges(){
//      ChromeDriver chromeDriver = new ChromeDriver();
//
//      chromeDriver.get(Dotenv.load().get("SCHOOL_URL"));
//      LoginPage loginPage = new LoginPage(chromeDriver);

     Requester requester = new Requester(null);
     DailySchedule latestFetchedSchedule = transcriber.transcribe(requester.getSchedulePage());

      boolean hasChanged = hasChangedSinceLastSchedule(latestFetchedSchedule);

      if(hasChanged){
         return save(latestFetchedSchedule);
      }
      return latestFetchedSchedule;
   }

   @Transactional
   public DailySchedule save(@Valid DailySchedule dailySchedule){
      return scheduleRepository.save(dailySchedule);
   }

   static{
      System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
   }
}
