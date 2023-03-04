package org.jhey.nsa.api.service;

import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jhey.nsa.api.repository.DailyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyScheduleService {
   @Autowired
   DailyScheduleRepository scheduleRepository;

   public boolean hasChangedSinceLastLessonSchedule(List<Lesson> schedule){
      return schedule.equals(scheduleRepository.findFirstByOrderByIdDesc().getLessons());
   }

}
