package org.jhey.nsa.api.service;

import org.jhey.nsa.api.model.Subject;
import org.jhey.nsa.api.repository.SubjectRepository;
import org.jhey.nsa.api.utils.StringUtils;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class SubjectService {

   @Autowired
   private SubjectRepository subjectRepository;

   public Subject getSubjectByName(String name) {
      name = new String(name.getBytes(), StandardCharsets.UTF_8);
      final String parsedName = StringUtils.unaccent(name);
      return subjectRepository.findByName(parsedName).orElseThrow(() -> new NotFoundException("Subject " + parsedName + " not found"));
   }
}
