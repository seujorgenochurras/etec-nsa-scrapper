package org.jhey.nsa.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class ScheduleDTO {

   @NotNull
   private Long id;
   @NotNull
   private LocalDate lookupDate;

   @JsonProperty("Lessons")
   private List<LessonDTO> lessons;

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

   public List<LessonDTO> getLessons() {
      return lessons;
   }

   public ScheduleDTO setLessons(List<LessonDTO> lessons) {
      this.lessons = lessons;
      return this;
   }
}
