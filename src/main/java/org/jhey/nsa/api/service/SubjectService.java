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
      String parsedName = new String(name.getBytes(), StandardCharsets.UTF_8);
      return subjectRepository.findByName(StringUtils.unaccent(parsedName)).orElseThrow(() -> new NotFoundException("Subject ".concat(StringUtils.unaccent(parsedName)).concat(" not found")));
   }
}
