package org.jhey.nsa.api.model.schedule_classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
   @JsonInclude
   private Teacher teacher;

   @Valid
   @NotNull
   @OneToOne
   @JoinColumn(name = "subject_fk", referencedColumnName = "id")
   @JsonInclude
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
   @JsonIgnore
   private DailySchedule dailySchedule;

   /**
    * From A1 to I5
    * Where letter = startTime
    * and number = day of the week
    */
   @Column(name = "place")
   private String place;

   public String getPlace() {
      return place;
   }

   public Lesson setPlace(PositionMapping place) {
      this.place = place.toString();
      this.dayOfWeek = place.getDayOfWeek().name();
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

   public Teacher getTeacher() {
      return teacher;
   }

   public Subject getSubject() {
      return subject;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Lesson lesson)) return false;

      if (getTeacher() != null ? !getTeacher().equals(lesson.getTeacher()) : lesson.getTeacher() != null) return false;
      if (!getSubject().equals(lesson.getSubject())) return false;
      if (!getDayOfWeek().equals(lesson.getDayOfWeek())) return false;
      if (!getStartTime().equals(lesson.getStartTime())) return false;
      return getPlace().equals(lesson.getPlace());
   }

   @Override
   public int hashCode() {
      int result = getTeacher() != null ? getTeacher().hashCode() : 0;
      result = 31 * result + getSubject().hashCode();
      result = 31 * result + getDayOfWeek().hashCode();
      result = 31 * result + getStartTime().hashCode();
      result = 31 * result + getPlace().hashCode();
      return result;
   }
}

