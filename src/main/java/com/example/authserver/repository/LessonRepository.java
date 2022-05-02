package com.example.authserver.repository;

import com.example.authserver.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByCourse_Id(Long courseId);
}
