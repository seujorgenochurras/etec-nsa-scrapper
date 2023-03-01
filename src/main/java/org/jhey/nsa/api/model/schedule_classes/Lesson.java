package org.jhey.nsa.api.model.schedule_classes;

import org.jhey.nsa.api.model.Subject;
import org.jhey.nsa.api.model.Teacher;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lesson {
//   private Teacher teacher;
//   private Subject subject;
//   private DayOfWeek dayOfWeek;
//   private LocalTime startTime;
   int start;
   private String teacher;
   private String subject;
   private String dayOfWeek;
   private String startTime;

   public int getStart() {
      return start;
   }

   public Lesson setStart(int start) {
      this.start = start;
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

   public String getDayOfWeek() {
      return dayOfWeek;
   }

   public Lesson setDayOfWeek(String dayOfWeek) {
      this.dayOfWeek = dayOfWeek;
      return this;
   }

   public String getStartTime() {
      return startTime;
   }

   public Lesson setStartTime(String startTime) {
      this.startTime = startTime;
      return this;
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
////   public LocalTime getStartTime() {
////      return startTime;
////   }
//
//   public Lesson setStartTime(int startTime) {
//      this.start = startTime;
//      return this;
//   }
}
