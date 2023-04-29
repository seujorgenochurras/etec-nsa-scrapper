package org.jhey.nsa.api.service;

import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jhey.nsa.api.repository.LessonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LessonService {
   @Autowired
   private LessonRepository lessonRepository;

   @Transactional
   public Lesson save(Lesson lesson) {
      return lessonRepository.save(lesson);
   }
}
