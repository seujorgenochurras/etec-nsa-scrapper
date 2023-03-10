package org.jhey.nsa.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.jhey.nsa.api.model.schedule_classes.Lesson;

import java.time.LocalDate;
import java.util.Set;

public class ScheduleDTO {

   @NotNull
   private Long id;
   @NotNull
   private LocalDate lookupDate;

   @JsonProperty("Lessons")
   private Set<Lesson> lessons;

   public ScheduleDTO(Long id, LocalDate lookupDate, Set<Lesson> lessons) {
      this.id = id;
      this.lookupDate = lookupDate;
      this.lessons = lessons;
   }

   public Long getId() {
      return id;
   }

   public ScheduleDTO setId(Long id) {
      this.id = id;
      return this;
   }

   public LocalDate getLookupDate() {
      return lookupDate;
   }

   public ScheduleDTO setLookupDate(LocalDate lookupDate) {
      this.lookupDate = lookupDate;
      return this;
   }

   public Set<Lesson> getLessons() {
      return lessons;
   }

   public ScheduleDTO setLessons(Set<Lesson> lessons) {
      this.lessons = lessons;
      return this;
   }
}
