package org.jhey.nsa.api.model;

import jakarta.persistence.*;

@Entity
@Table(name= "subject")
public class Subject {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String name;

   public String getName() {
      return name;
   }

   public Subject setName(String name) {
      this.name = name;
      return this;
   }

   public Long getId() {
      return id;
   }

   public Subject setId(Long id) {
      this.id = id;
      return this;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Subject subject)) return false;

      return getName().equals(subject.getName());
   }

   @Override
   public int hashCode() {
      return getName().hashCode();
   }
}
