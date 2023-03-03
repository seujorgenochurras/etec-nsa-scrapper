package org.jhey.nsa.api.model.schedule_classes;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the schedule of the classes of the week
 * */
public class WeekSchedule {
   List<Lesson> lessons = new ArrayList<>();

   public List<Lesson> getLessons() {
      return lessons;
   }
   public void addLesson(Lesson lesson){
      getLessons().add(lesson);
   }

   public WeekSchedule setLessons(List<Lesson> lessons) {
      this.lessons = lessons;
      return this;
   }
}