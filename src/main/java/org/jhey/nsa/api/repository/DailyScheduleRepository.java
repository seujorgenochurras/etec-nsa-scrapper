package org.jhey.nsa.api.repository;

import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
   DailySchedule findFirstByOrderByIdDesc();
}
