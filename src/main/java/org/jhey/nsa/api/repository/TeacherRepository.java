package org.jhey.nsa.api.repository;

import org.jhey.nsa.api.model.Subject;
import org.jhey.nsa.api.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
   Optional<Teacher> findByName(String name);

}
