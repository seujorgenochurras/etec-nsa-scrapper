package org.jhey.nsa.api.request.handler;

import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.NoSuchElementException;

public class LessonHandler {
   private LessonHandler() {
   }

   public static List<Lesson> handle(List<Element> tableElement){
      return tableElement.stream().map(element -> {
         System.out.println(element + "\n\n\n\n\n\n");
         LessonElement lessonElement = toLessonObject(element);
         Lesson result = new Lesson()
                 .setStart(1)
                 .setDayOfWeek("null");
        try {
           result.setSubject(lessonElement.getSubject());
           result.setTeacher(lessonElement.getTeacher());
        }catch (NullPointerException | NoSuchElementException e){
           result.setTeacher("Null").setSubject("null");
        }

        return result;
      }).toList();

   }
   private static LessonElement toLessonObject(Element element){
      return new LessonElement(element);
   }
}
