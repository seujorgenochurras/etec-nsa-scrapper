package org.jhey.nsa.api.repository;

import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
