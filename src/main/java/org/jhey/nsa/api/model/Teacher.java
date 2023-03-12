package org.jhey.nsa.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Teacher teacher)) return false;

      if (getName() != null ? !getName().equals(teacher.getName()) : teacher.getName() != null) return false;
      return getEmail() != null ? getEmail().equals(teacher.getEmail()) : teacher.getEmail() == null;
   }

   @Override
   public int hashCode() {
      int result = getName() != null ? getName().hashCode() : 0;
      result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
      return result;
   }
}
