package org.jhey.nsa.api.controller;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.jhey.nsa.api.dto.ScheduleDTO;
import org.jhey.nsa.api.dto.assembly.DailyScheduleAssembler;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.api.service.DailyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check/schedule")
public class CheckScheduleController {

   @Autowired
   private DailyScheduleService dailyScheduleService;

   @PostMapping
   public ResponseEntity<ScheduleDTO> checkLatestSchedule(
          @Valid @RequestBody @Nullable DailySchedule dailySchedule){

      DailySchedule latestSchedule = dailyScheduleService.getLatestDailySchedule();

      if(latestSchedule.equals(dailySchedule)){
         return ResponseEntity.noContent().build();
      }
       return ResponseEntity.ok(DailyScheduleAssembler.toModel(latestSchedule));

   }
}
