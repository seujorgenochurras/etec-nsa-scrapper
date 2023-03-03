package org.jhey.nsa.api.request.handler;

import org.jsoup.nodes.Element;

/**
 * This element should be the table data
 * */
public class LessonElement {
   private final Element element;

   public LessonElement(Element element) {
      this.element = element;
   }

   public Element toElement(){return this.element;}

   String getTeacher() {
      return element.getAllElements().stream().filter(element1 -> element1.tagName().equals("span")).findFirst().get().text();
   }

   String getSubject() {
      return element.getAllElements().stream().filter(element1 -> element1.tagName().equals("a")).findFirst().get().text();
   }
}
