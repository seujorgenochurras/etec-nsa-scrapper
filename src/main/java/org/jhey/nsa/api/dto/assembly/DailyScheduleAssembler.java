package org.jhey.nsa.api.dto.assembly;


import org.jhey.nsa.api.dto.ScheduleDTO;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;

public final class DailyScheduleAssembler {
private DailyScheduleAssembler(){}
   public static ScheduleDTO toModel(DailySchedule dailySchedule){
      return new ScheduleDTO()
              .setId(dailySchedule.getId())
              .setLessons(
                      dailySchedule.getLessons()
                              .stream()
                              .map(LessonAssembler::toModel)
                              .toList())

              .setLookupDate(dailySchedule.getLookupDate());
   }
}
