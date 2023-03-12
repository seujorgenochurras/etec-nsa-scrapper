package org.jhey.nsa.api.model.schedule_classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the schedule of the classes of the week
 * */
@Entity
@Table(name = "week_schedule")
public class DailySchedule {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(name = "lookup_date")
   @NotNull
   private LocalDate lookupDate;
   public DailySchedule setLookupDate(LocalDate lookupDate) {
      this.lookupDate = lookupDate;
      return this;
   }

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "dailySchedule", orphanRemoval = true)
   @Valid
   @JsonIgnore
   private List<Lesson> lessons = new ArrayList<>();

   public List<Lesson> getLessons() {
      return lessons;
   }

   public void addLesson(Lesson lesson){
      lessons.add(lesson);
   }

   public long getId() {
      return id;
   }

   public LocalDate getLookupDate() {
      return lookupDate;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof DailySchedule that)) return false;

      if (getId() != that.getId()) return false;
      return getLessons().equals(that.getLessons());
   }

   @Override
   public int hashCode() {
      return getLessons() != null ? getLessons().hashCode() : 0;
   }

}
