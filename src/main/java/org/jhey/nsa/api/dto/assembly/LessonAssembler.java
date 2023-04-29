package org.jhey.nsa.api.dto.assembly;

import org.jhey.nsa.api.dto.LessonDTO;
import org.jhey.nsa.api.model.schedule_classes.Lesson;

import java.util.Objects;

public class LessonAssembler {
   private LessonAssembler() {
   }

   public static LessonDTO toModel(Lesson lesson) {
      String teacher;
      if (Objects.isNull(lesson.getTeacher())) {
         teacher = "null";
      } else teacher = lesson.getTeacher().getName();
      return new LessonDTO()
              .setPosition(lesson.getPlace())
              .setSubjectName(lesson.getSubject().getName())
              .setTeacherName(teacher)
              .setTime(lesson.getStartTime());
   }
}
