package org.jhey.nsa.api.model.schedule_classes;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the schedule of the classes of the week
 * */
@Entity
public class DailySchedule {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(name = "lookup_date")
   private LocalDate lookupDate;
   @ManyToOne
   private List<Lesson> lessons = new ArrayList<>();

   public List<Lesson> getLessons() {
      return lessons;
   }
   public void addLesson(Lesson lesson){
      getLessons().add(lesson);
   }

   public DailySchedule setLessons(List<Lesson> lessons) {
      this.lessons = lessons;
      return this;
   }
   public LocalDate getLookupWeek() {
      return lookupDate;
   }

   public DailySchedule setLookupDate(LocalDate lookupDate) {
      this.lookupDate = lookupDate;
      return this;
   }
}
