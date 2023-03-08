package org.jhey.nsa.api.model.schedule_classes;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.jhey.nsa.api.model.Subject;
import org.jhey.nsa.api.model.Teacher;

import java.time.LocalTime;


@Entity
@Table(name = "lesson")
public class Lesson {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   @Valid
   @Nullable
   @OneToOne
   @JoinColumn(name = "teacher_fk", referencedColumnName = "id")
   private Teacher teacher;

   @Valid
   @NotNull
   @OneToOne
   @JoinColumn(name = "subject_fk", referencedColumnName = "id")
   private Subject subject;

   @Column(name = "day_of_week")
   @NotNull
   private String dayOfWeek;
   @Column(name = "start_time")
   @NotNull
   private LocalTime startTime;

   @ManyToOne
   @JoinColumn(name = "week_fk", referencedColumnName = "id")
   @NotNull
   @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
   @Valid
   private DailySchedule dailySchedule;

   /**
    * From A1 to I5
    * Where letter = startTime
    * and number = day of the week
    * */
   @Column(name = "place")
   private String place;

   public String getPlace() {
      return place;
   }

   public Lesson setPlace(PositionMapping place) {
      this.place = place.toString();
      this.dayOfWeek = place.getDayOfWeek().name();
      this.startTime = place.getStartTime(); //For some reason the db
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

   public String getDayOfWeek() {
      return dayOfWeek;
   }

   public LocalTime getStartTime() {
      return startTime;
   }

   public DailySchedule getDailySchedule() {
      return dailySchedule;
   }

   public Lesson setDailySchedule(DailySchedule dailySchedule) {
      this.dailySchedule = dailySchedule;
      return this;
   }
}

