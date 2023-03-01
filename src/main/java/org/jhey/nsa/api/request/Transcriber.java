package org.jhey.nsa.api.request;

import com.google.gson.Gson;
import org.jhey.nsa.api.model.Subject;
import org.jhey.nsa.api.model.Teacher;
import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jhey.nsa.api.model.schedule_classes.WeekSchedule;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.DayOfWeek;

public class Transcriber {

   public void transcribe(Document document) throws InterruptedException {
      Thread.sleep(3000);
      System.out.println(document.body());
      System.out.println(document.baseUri());
      Element scheduleTable = document.selectXpath("//*[@id=\"ctl00_ContentPlaceHolder1_gvHorario\"]/tbody").first();
      Element time = scheduleTable.child(0);
      Elements lessons = scheduleTable.after(time).getAllElements();
      WeekSchedule weekSchedule = new WeekSchedule();
      Subject subject = new Subject();
      subject.setName("Math");
      Teacher teacher = new Teacher();
      teacher.setName("Jhon Doe");
      teacher.setEmail("Jhon@doe.com");

      Elements actualLessons = lessons.select("//tr");

//      actualLessons.stream().forEach(lesson ->{
//
//         Element timeData = lesson.getElementById("ctl00_ContentPlaceHolder1_gvHorario_ctl02_lblHora");
//         Lesson lesson1 = new Lesson();
//      });
//      weekSchedule.setLessons(lessons.stream().map(lesson -> {
//         String subject = lesson.get
//         new Lesson()
//
//                 .setDayOfWeek(String.valueOf(DayOfWeek.FRIDAY))
//                 .setSubject(subject)
//                 .setStartTime(2121)
//                 .setTeacher(teacher)
//      }).toList());
//      Gson gson = new Gson();
//      System.out.println(gson.toJson(weekSchedule));
   }
}
