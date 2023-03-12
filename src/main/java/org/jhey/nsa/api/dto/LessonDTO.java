package org.jhey.nsa.api.dto;

import java.time.LocalTime;

public class LessonDTO {
   String teacherName;
   String subjectName;
   LocalTime time;
   String position;

   public String getTeacherName() {
      return teacherName;
   }

   public LessonDTO setTeacherName(String teacherName) {
      this.teacherName = teacherName;
      return this;
   }

   public String getSubjectName() {
      return subjectName;
   }

   public LessonDTO setSubjectName(String subjectName) {
      this.subjectName = subjectName;
      return this;
   }

   public LocalTime getTime() {
      return time;
   }

   public LessonDTO setTime(LocalTime time) {
      this.time = time;
      return this;
   }

   public String getPosition() {
      return position;
   }

   public LessonDTO setPosition(String position) {
      this.position = position;
      return this;
   }
}
