package org.jhey.nsa.api.service.schedule;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jhey.nsa.api.commons.ApplicationContextUtils;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.api.repository.DailyScheduleRepository;
import org.jhey.nsa.api.repository.LessonRepository;
import org.jhey.nsa.api.service.LessonService;
import org.jhey.nsa.request.Transcriber;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class DailyScheduleService {
   @Autowired
   private DailyScheduleRepository scheduleRepository;
   @Autowired
   private LessonService lessonService;
   @Autowired
   private Transcriber transcriber;
   @Autowired
   private LessonRepository lessonRepository;
   @Autowired
   private ApplicationContextUtils applicationContextUtils;

   public boolean isLatestSchedule(DailySchedule dailySchedule){
      if(Objects.isNull(scheduleRepository.findFirstByOrderByIdDesc())){
         System.out.println("Object is null");
         return true;
      }
      return !dailySchedule.equals(getLatestSchedule());
   }

   @Transactional
   public DailySchedule checkAndGetLatestSchedule(){
      new Thread(() -> applicationContextUtils.getBean(ScheduleFetcher.class).fetchAndSaveIfFoundFreshSchedule()).start();
      return getLatestSchedule();

   }
   public DailySchedule getLatestSchedule(){
      DailySchedule latestSchedule = scheduleRepository.findFirstByOrderByIdDesc();
      return Objects.isNull(latestSchedule) ? new DailySchedule() : latestSchedule;
   }

   @Transactional
   public DailySchedule save(@Valid DailySchedule dailySchedule){
      return scheduleRepository.save(dailySchedule);
   }

   public DailySchedule findById(long id) {
      return scheduleRepository.findById(id).orElseThrow(() -> new NotFoundException("Schedule not found"));
   }
   static{
      System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
   }
}
