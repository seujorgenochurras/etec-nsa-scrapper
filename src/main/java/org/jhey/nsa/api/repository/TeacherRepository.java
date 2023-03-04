package org.jhey.nsa.api.repository;

import org.jhey.nsa.api.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
   Optional<Teacher> findByName(String name);
}
