package org.jhey.nsa.api.request;

import com.google.gson.Gson;
import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jhey.nsa.api.model.schedule_classes.WeekSchedule;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class Transcriber {

   public void transcribe(Document document) {
      System.out.println(document.baseUri());

      Element scheduleTable = document.selectXpath("//*[@id=\"ctl00_ContentPlaceHolder1_gvHorario\"]/tbody").first();
      Elements schedulesTableRows = scheduleTable.getElementsByTag("tr").next();
      WeekSchedule weekSchedule = new WeekSchedule();

      AtomicReference<Character> rowLetter = new AtomicReference<>('`'); //Letter that comes before "a"
      AtomicInteger rowNumber = new AtomicInteger();
      schedulesTableRows.forEach(row -> {

         Elements tableData = row.select("span:not(:empty):not(:matches([0-9])), a:not(:empty)");

         //I need to check for numbers because some spans came out with the text as time.
         System.out.println("\n\n\n\n\n");

         rowLetter.getAndSet((char) (rowLetter.get() + 1));
         rowNumber.set(1);
         AtomicReference<Lesson> lesson = new AtomicReference<>(new Lesson());
         tableData.forEach(data -> {
            if(data.tagName().equals("span") && data.text().startsWith("Sem aula no ")){
               lesson.getAndSet(new Lesson()
                       .setTeacher("null")
                       .setPlace("" + rowLetter.get() + rowNumber.getAndIncrement())
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
                               .setPlace("" + rowLetter.get() + rowNumber.getAndIncrement())
                               .setTeacher(data.text()));
               weekSchedule.addLesson(lesson.get());
            }

//         rowNumber.getAndIncrement();
//            Lesson lesson = new Lesson()
//                    .setPlace("" + rowLetter.get() + rowNumber.get())
//                    .setSubject(data.text())
//                    .setTeacher("a");
//            weekSchedule.getLessons().add(lesson);
            //System.out.println(data.tagName() + " : " + data.text());
         });
      });

//      List<Element> lessons = scheduleTable.after(time).child(2).getAllElements()
//              .stream().filter(element -> element.tagName().equals("tr")).toList();
//      LessonHandler.handle(lessons);
//

      Gson gson = new Gson();
      System.out.println(gson.toJson(weekSchedule));
   }
}
