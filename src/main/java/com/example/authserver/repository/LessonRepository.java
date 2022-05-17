package com.example.authserver.repository;

import com.example.authserver.domain.Lesson;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @EntityGraph(attributePaths = {"lessonFile"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Lesson> findAllByCourse_Id(Long courseId);

    @EntityGraph(attributePaths = {"course"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT L FROM Lesson l WHERE l.id =:lessonId")
    Lesson findLessonById(@Param("lessonId") Long lessonId);
}
