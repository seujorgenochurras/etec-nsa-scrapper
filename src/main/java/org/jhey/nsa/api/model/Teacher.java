package org.jhey.nsa.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long uuid;

   @Column(name = "name")
   private String name;

   @Column(name = "email")
   private String email;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
