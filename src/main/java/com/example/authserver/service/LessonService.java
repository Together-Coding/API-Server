package com.example.authserver.service;

import com.example.authserver.domain.Lesson;

import java.util.List;

public interface LessonService {

    void register(String name, String description, Long courseId, Long userId);

    void updateName(Long lessonId, Long userId, String name);

    void updateDescription(Long lessonId, Long userId, String description);

    void delete(Long id, Long userId);

    List<Lesson> getLessons(Long courseId);
}