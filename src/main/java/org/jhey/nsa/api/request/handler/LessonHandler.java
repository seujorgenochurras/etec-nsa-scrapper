package org.jhey.nsa.api.request.handler;

import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.NoSuchElementException;

public class LessonHandler {
   private LessonHandler() {
   }

   public static List<Lesson> handle(List<Element> tableElement){
      return tableElement.stream().map(element -> {
         LessonElement lessonElement = toLessonObject(element);
         Lesson result = new Lesson();
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
