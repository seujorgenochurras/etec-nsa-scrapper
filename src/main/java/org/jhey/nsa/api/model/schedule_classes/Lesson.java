package org.jhey.nsa.api.model.schedule_classes;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lesson {
//   private Teacher teacher;
//   private Subject subject;

   private String teacher;
   private String subject;
   private DayOfWeek dayOfWeek;
   private LocalTime startTime;

   /**
    * From A1 to I5
    * Where letter = startTime
    * and number = day of the week
    * */
   private PositionMapping place;

   public PositionMapping getPlace() {
      return place;
   }

   public Lesson setPlace(PositionMapping place) {
      this.place = place;
      this.dayOfWeek = place.getDayOfWeek();
      this.startTime = place.getStartTime();
      return this;
   }

   public String getTeacher() {
      return teacher;
   }

   public Lesson setTeacher(String teacher) {
      this.teacher = teacher;
      return this;
   }

   public String getSubject() {
      return subject;
   }

   public Lesson setSubject(String subject) {
      this.subject = subject;
      return this;
   }

   public DayOfWeek getDayOfWeek() {
      return dayOfWeek;
   }

   public LocalTime getStartTime() {
      return startTime;
   }

//   public Teacher getTeacher() {
//      return teacher;
//   }
//
//   public Lesson setTeacher(Teacher teacher) {
//      this.teacher = teacher;
//      return this;
//   }
//
//   public Subject getSubject() {
//      return subject;
//   }
//
//   public Lesson setSubject(Subject subject) {
//      this.subject = subject;
//      return this;
//   }
//
//   public DayOfWeek getDayOfWeek() {
//      return dayOfWeek;
//   }
//
//   public Lesson setDayOfWeek(DayOfWeek dayOfWeek) {
//      this.dayOfWeek = dayOfWeek;
//      return this;
//   }
//
////   public LocalTimeAdapter getStartTime() {
////      return startTime;
////   }
//
//   public Lesson setStartTime(int startTime) {
//      this.start = startTime;
//      return this;
//   }
}
