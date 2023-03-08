package org.jhey.nsa.request;

import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jhey.nsa.api.model.schedule_classes.PositionMapping;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.api.service.SubjectService;
import org.jhey.nsa.api.service.TeacherService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class Transcriber {
   @Autowired
   private TeacherService teacherService;
   @Autowired
   private SubjectService subjectService;

   public DailySchedule transcribe(Document document) {
      Element scheduleTable = document.selectXpath("//*[@id=\"ctl00_ContentPlaceHolder1_gvHorario\"]/tbody").first();
      Elements schedulesTableRows = scheduleTable.getElementsByTag("tr").next();

      DailySchedule dailySchedule = new DailySchedule().setLookupDate(LocalDate.now());
      AtomicInteger atomicInteger = new AtomicInteger(0);
      schedulesTableRows.forEach(row -> {

         //I need to check for numbers because some spans came out with the text as time.
         Elements tableData = row.select("span:not(:empty):not(:matches([0-9])), a:not(:empty)");

         AtomicReference<Lesson> lesson = new AtomicReference<>(new Lesson());
         tableData.forEach(data -> {
            if(data.tagName().equals("span") && data.text().startsWith("Sem aula no ")){
               lesson.getAndSet(new Lesson()
                       .setTeacher(null)
                       .setPlace(PositionMapping.getByIndex(atomicInteger.getAndIncrement()))
                       .setSubject(subjectService.getSubjectByName(data.text()))
               );
               lesson.get().setDailySchedule(dailySchedule);
               dailySchedule.addLesson(lesson.get());
              // lessonService.save(lesson.get());
            }
            //<a> is the subject
            else if(data.tagName().equals("a")){
               lesson.set(new Lesson()
                       .setSubject(subjectService.getSubjectByName(data.text()))
               );
             }
            else {
              // This is only the <span> and contains the Teacher data
                       lesson.set(lesson.get()
                               .setPlace(PositionMapping.getByIndex(atomicInteger.getAndIncrement()))
                               .setTeacher(teacherService.getTeacherByName(data.text())));
            //   lessonService.save(lesson.get());
               lesson.get().setDailySchedule(dailySchedule);
               dailySchedule.addLesson(lesson.get());
            }

         });
      });
      return dailySchedule;
//      Gson gson = new GsonBuilder()
//              .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
//              .create();
//      System.out.println(gson.toJson(dailySchedule));
   }
}