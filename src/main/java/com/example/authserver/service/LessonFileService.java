package com.example.authserver.service;

public interface LessonFileService {

    void register(String fileUrl, Long lessonId, Long userId);
}
