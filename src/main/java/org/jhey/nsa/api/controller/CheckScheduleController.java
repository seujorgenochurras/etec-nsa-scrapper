package org.jhey.nsa.api.controller;

import jakarta.annotation.Nullable;
import org.jhey.nsa.api.dto.ScheduleDTO;
import org.jhey.nsa.api.dto.assembly.DailyScheduleAssembler;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.api.service.schedule.DailyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/check/schedule")
public class CheckScheduleController {

   @Autowired
   private DailyScheduleService dailyScheduleService;

   @PostMapping
   public ResponseEntity<ScheduleDTO> checkLatestSchedule(
         @RequestBody @Nullable Long id){
      if(Objects.isNull(id)) id = -1L;
      DailySchedule latestSchedule = dailyScheduleService.checkAndGetLatestSchedule();

      if(latestSchedule.getId() == id){
         return ResponseEntity.noContent().build();
      }
       return ResponseEntity.ok(DailyScheduleAssembler.toModel(latestSchedule));

   }
}
