package org.jhey.nsa.api.model.schedule_classes;

import jakarta.persistence.*;
import org.jhey.nsa.api.model.Subject;
import org.jhey.nsa.api.model.Teacher;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "lesson")
public class Lesson {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   @OneToOne
   @JoinColumn(name = "teacher_fk")
   private Teacher teacher;

   @OneToOne
   @JoinColumn(name = "subject_fk")
   private Subject subject;

   @Column(name = "dayOfWeek")
   private DayOfWeek dayOfWeek;
   @Column(name = "start_time")
   private LocalTime startTime;
   public Lesson() {
   }

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

   public Lesson setTeacher(Teacher teacher) {
      this.teacher = teacher;
      return this;
   }

   public Lesson setSubject(Subject subject) {
      this.subject = subject;
      return this;
   }

   public DayOfWeek getDayOfWeek() {
      return dayOfWeek;
   }

   public LocalTime getStartTime() {
      return startTime;
   }

   public Lesson(long id, Teacher teacher, Subject subject, DayOfWeek dayOfWeek, LocalTime startTime, LocalDate lookupWeek, PositionMapping place) {
      this.id = id;
      this.teacher = teacher;
      this.subject = subject;
      this.dayOfWeek = dayOfWeek;
      this.startTime = startTime;
      this.place = place;
   }
}

