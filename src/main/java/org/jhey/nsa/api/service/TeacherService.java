package org.jhey.nsa.api.service;

import org.jhey.nsa.api.model.Teacher;
import org.jhey.nsa.api.repository.TeacherRepository;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

   @Autowired
   private TeacherRepository teacherRepository;

   public Teacher getTeacherByName(final String name) {
      return teacherRepository.findByName(name).orElseThrow(() -> new NotFoundException("Teacher " + name + " not found"));
   }
}
