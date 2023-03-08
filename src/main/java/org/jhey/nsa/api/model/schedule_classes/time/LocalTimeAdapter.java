package org.jhey.nsa.api.model.schedule_classes.time;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter implements JsonSerializer<LocalTime> {
   private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

   @Override
   public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
      return new JsonPrimitive(localTime.format(formatter));
   }
}
