package org.jhey.nsa.api.model;

import jakarta.persistence.*;

@Entity
@Table(name= "subject")
public class Subject {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
  private String name;

   public String getName() {
      return name;
   }

   public Subject setName(String name) {
      this.name = name;
      return this;
   }
}
