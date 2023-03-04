package org.jhey.nsa.api.model.schedule_classes;

import java.time.DayOfWeek;
import java.time.LocalTime;

public enum PositionMapping {
   A1(DayOfWeek.MONDAY, LocalTime.of(7, 0)),
   A2(DayOfWeek.TUESDAY, LocalTime.of(7, 0)),
   A3(DayOfWeek.WEDNESDAY,  LocalTime.of(7, 0)),
   A4(DayOfWeek.THURSDAY,  LocalTime.of(7, 0)),
   A5(DayOfWeek.FRIDAY,  LocalTime.of(7, 0)),

   B1(DayOfWeek.MONDAY, LocalTime.of(7, 50)),
   B2(DayOfWeek.TUESDAY, LocalTime.of(7, 50)),
   B3(DayOfWeek.WEDNESDAY,  LocalTime.of(7,50)),
   B4(DayOfWeek.THURSDAY,  LocalTime.of(7,50)),
   B5(DayOfWeek.FRIDAY,  LocalTime.of(7, 50)),

   C1(DayOfWeek.MONDAY, LocalTime.of(8, 40)),
   C2(DayOfWeek.TUESDAY, LocalTime.of(8, 40)),
   C3(DayOfWeek.WEDNESDAY,  LocalTime.of(8,40)),
   C4(DayOfWeek.THURSDAY,  LocalTime.of(8,40)),
   C5(DayOfWeek.FRIDAY,  LocalTime.of(8, 40)),

   D1(DayOfWeek.MONDAY, LocalTime.of(9, 45)),
   D2(DayOfWeek.TUESDAY, LocalTime.of(9, 45)),
   D3(DayOfWeek.WEDNESDAY,  LocalTime.of(9,45)),
   D4(DayOfWeek.THURSDAY,  LocalTime.of(9,45)),
   D5(DayOfWeek.FRIDAY,  LocalTime.of(9, 45)),

   E1(DayOfWeek.MONDAY, LocalTime.of(10, 35)),
   E2(DayOfWeek.TUESDAY, LocalTime.of(10, 35)),
   E3(DayOfWeek.WEDNESDAY,  LocalTime.of(10,35)),
   E4(DayOfWeek.THURSDAY,  LocalTime.of(10,35)),
   E5(DayOfWeek.FRIDAY,  LocalTime.of(10, 35)),

   F1(DayOfWeek.MONDAY, LocalTime.of(11, 25)),
   F2(DayOfWeek.TUESDAY, LocalTime.of(11, 25)),
   F3(DayOfWeek.WEDNESDAY,  LocalTime.of(11,25)),
   F4(DayOfWeek.THURSDAY,  LocalTime.of(11,25)),
   F5(DayOfWeek.FRIDAY,  LocalTime.of(11, 25)),

   G1(DayOfWeek.MONDAY, LocalTime.of(13, 0)),
   G2(DayOfWeek.TUESDAY, LocalTime.of(13, 0)),
   G3(DayOfWeek.WEDNESDAY,  LocalTime.of(13,0)),
   G4(DayOfWeek.THURSDAY,  LocalTime.of(13,0)),
   G5(DayOfWeek.FRIDAY,  LocalTime.of(13, 0)),

   H1(DayOfWeek.MONDAY, LocalTime.of(13, 50)),
   H2(DayOfWeek.TUESDAY, LocalTime.of(13, 50)),
   H3(DayOfWeek.WEDNESDAY,  LocalTime.of(13,50)),
   H4(DayOfWeek.THURSDAY,  LocalTime.of(13,50)),
   H5(DayOfWeek.FRIDAY,  LocalTime.of(13, 50)),

   I1(DayOfWeek.MONDAY, LocalTime.of(14, 40)),
   I2(DayOfWeek.TUESDAY, LocalTime.of(14, 40)),
   I3(DayOfWeek.WEDNESDAY,  LocalTime.of(14,40)),
   I4(DayOfWeek.THURSDAY,  LocalTime.of(14,40)),
   I5(DayOfWeek.FRIDAY,  LocalTime.of(14, 40));

   private final DayOfWeek dayOfWeek;

   public DayOfWeek getDayOfWeek() {
      return dayOfWeek;
   }

   public LocalTime getStartTime() {
      return startTime;
   }

   private final LocalTime startTime;

   PositionMapping(DayOfWeek dayOfWeek, LocalTime startTime) {
       this.dayOfWeek = dayOfWeek;
       this.startTime = startTime;
   }

   public static PositionMapping getByIndex(int index){
      return PositionMapping.values()[index];
   }
}
