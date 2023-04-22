package org.jhey.nsa.api.model.schedule_classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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
   private final List<Lesson> lessons = new ArrayList<>();

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

      if(areLessonsTheSame(lessons, that.getLessons())) return true;
      return getLessons() == null && that.getLessons() == null;
   }
   private boolean areLessonsTheSame(List<Lesson> lessons1, List<Lesson> lessons2){
      if(lessons1.size() != lessons2.size()) return false;
      for(int i = 0; i < lessons1.size(); i++){
         if(!lessons1.get(i).equals(lessons2.get(i))) return false;
      }
      return true;
   }


   @Override
   public int hashCode() {
      return getLessons() != null ? getLessons().hashCode() : 0;
   }

}
