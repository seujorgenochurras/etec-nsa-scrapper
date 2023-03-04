package org.jhey.nsa.api.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jhey.nsa.api.model.schedule_classes.PositionMapping;
import org.jhey.nsa.api.model.schedule_classes.WeekSchedule;
import org.jhey.nsa.api.model.schedule_classes.time.LocalTimeAdapter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class Transcriber {

   public void transcribe(@NotNull Document document) {

      Element scheduleTable = document.selectXpath("//*[@id=\"ctl00_ContentPlaceHolder1_gvHorario\"]/tbody").first();
      Elements schedulesTableRows = scheduleTable.getElementsByTag("tr").next();
      WeekSchedule weekSchedule = new WeekSchedule();

      AtomicInteger atomicInteger = new AtomicInteger(0);
      schedulesTableRows.forEach(row -> {

         //I need to check for numbers because some spans came out with the text as time.
         Elements tableData = row.select("span:not(:empty):not(:matches([0-9])), a:not(:empty)");

         AtomicReference<Lesson> lesson = new AtomicReference<>(new Lesson());
         tableData.forEach(data -> {
            if(data.tagName().equals("span") && data.text().startsWith("Sem aula no ")){
               lesson.getAndSet(new Lesson()
                       .setTeacher("null")
                       .setPlace(PositionMapping.getByIndex(atomicInteger.getAndIncrement()))
                       .setSubject("Sem aula")
               );
               weekSchedule.addLesson(lesson.get());
            }
            else if(data.tagName().equals("a")){
               lesson.set(new Lesson()
                       .setSubject(data.text())
               );
            }else {
                       lesson.set(lesson.get()
                               .setPlace(PositionMapping.getByIndex(atomicInteger.getAndIncrement()))
                               .setTeacher(data.text()));
               weekSchedule.addLesson(lesson.get());
            }

         });
      });

      Gson gson = new GsonBuilder()
              .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
              .create();
      System.out.println(gson.toJson(weekSchedule));
   }
}
