package org.jhey.nsa.api.service;

import org.jhey.nsa.api.model.Subject;
import org.jhey.nsa.api.repository.SubjectRepository;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

   @Autowired
  private SubjectRepository subjectRepository;

   public Subject getSubjectByName(String name) {
      return subjectRepository.findByName(name).orElseThrow(() -> new NotFoundException("Teacher not found"));
   }
}
