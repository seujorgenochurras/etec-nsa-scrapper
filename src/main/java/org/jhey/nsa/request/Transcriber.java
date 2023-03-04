package org.jhey.nsa.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jhey.nsa.api.model.schedule_classes.PositionMapping;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.api.model.schedule_classes.time.LocalTimeAdapter;
import org.jhey.nsa.api.service.LessonService;
import org.jhey.nsa.api.service.SubjectService;
import org.jhey.nsa.api.service.TeacherService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


@Component
public class Transcriber {

   @Autowired
   private TeacherService teacherService;
   @Autowired
   private SubjectService subjectService;
   @Autowired
   private LessonService lessonService;

   public void transcribe(Document document) {
      Element scheduleTable = document.selectXpath("//*[@id=\"ctl00_ContentPlaceHolder1_gvHorario\"]/tbody").first();
      Elements schedulesTableRows = scheduleTable.getElementsByTag("tr").next();
      DailySchedule dailySchedule = new DailySchedule();

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
                       .setLookupWeek(LocalDate.now())
               );
               lessonService.save(lesson.get());
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
                               .setLookupWeek(LocalDate.now())
                               .setTeacher(teacherService.getTeacherByName(data.text())));
               lessonService.save(lesson.get());
            }

         });
      });

      Gson gson = new GsonBuilder()
              .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
              .create();
      System.out.println(gson.toJson(dailySchedule));
   }
}