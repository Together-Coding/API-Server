package com.example.authserver.service;

public interface LessonService {

    void register(String name, String description, Long courseId);

    void update(Long id, String name, String description);

    void delete(Long id, Long userId);
}
