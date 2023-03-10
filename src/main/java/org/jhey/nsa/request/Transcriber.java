package org.jhey.nsa.request;

import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.jhey.nsa.api.model.schedule_classes.PositionMapping;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.api.service.SubjectService;
import org.jhey.nsa.api.service.TeacherService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class Transcriber {
   @Autowired
   private TeacherService teacherService;
   @Autowired
   private SubjectService subjectService;

   private Elements getSchedulesTableRows(Document document){
      Element scheduleTable = document.selectXpath("//*[@id=\"ctl00_ContentPlaceHolder1_gvHorario\"]/tbody").first();
      return scheduleTable.getElementsByTag("tr").next();
   }
   public DailySchedule transcribe(Document document) {
      Elements schedulesTableRows = getSchedulesTableRows(document);

      DailySchedule dailySchedule = new DailySchedule().setLookupDate(LocalDate.now());
      AtomicInteger atomicInteger = new AtomicInteger(0);
      schedulesTableRows.forEach(row -> {

         //I need to check for numbers because some spans came out with the text as time.
         Elements tableData = row.select("span:not(:empty):not(:matches([0-9])), a:not(:empty)");

         AtomicReference<Lesson> lesson = new AtomicReference<>(new Lesson());
         tableData.forEach(data -> {

            ElementIdentifiers elementIdentifiers = getWhatElementIsIdentifying(data);
            if(elementIdentifiers.equals(ElementIdentifiers.SUBJECT)
                    || elementIdentifiers.equals(ElementIdentifiers.CANCELLED_CLASS)){

               lesson.getAndSet(new Lesson()
                       .setPlace(PositionMapping.getByIndex(atomicInteger.getAndIncrement()))
                       .setSubject(subjectService.getSubjectByName(data.text())));

               if(elementIdentifiers.equals(ElementIdentifiers.CANCELLED_CLASS)){
                  lesson.get().setTeacher(null);

               }
               lesson.get().setDailySchedule(dailySchedule);
               dailySchedule.addLesson(lesson.get());
            }

            else {
               lesson.get().setTeacher(teacherService.getTeacherByName(data.text()));
             }
         });
      });
      return dailySchedule;
   }
   private ElementIdentifiers getWhatElementIsIdentifying(Element element){
      if (isElementIdentifyingCancelledClass(element)) return ElementIdentifiers.CANCELLED_CLASS;
      if (isElementIdentifyingASubject(element)) return ElementIdentifiers.SUBJECT;
      if (isElementIdentifyingATeacher(element)) return ElementIdentifiers.TEACHER;
      return null;
   }
   private boolean isElementIdentifyingCancelledClass(Element element){
      return element.tagName().equals("span") && element.text().startsWith("Sem aula no hor");
   }
   private boolean isElementIdentifyingATeacher(Element element){
      return !isElementIdentifyingASubject(element) && element.tagName().equals("span");
   }
   private boolean isElementIdentifyingASubject(Element element){
      return  element.tagName().equals("a");
   }
    private enum ElementIdentifiers {
       TEACHER,
       SUBJECT,
       CANCELLED_CLASS
   }
}