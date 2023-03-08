package org.jhey.nsa.api.repository;

import org.jhey.nsa.api.model.schedule_classes.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
