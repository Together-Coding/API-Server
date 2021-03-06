package com.example.authserver.service;

import com.example.authserver.domain.Lesson;
import com.example.authserver.dto.LessonDTO;

import java.util.List;

public interface LessonService {

    void register(String name, String description, Long courseId, Long userId, Long lang_image_id);

    void updateName(Long lessonId, Long userId, String name);

    void updateDescription(Long lessonId, Long userId, String description);

    void delete(Long lessonId, Long userId);

    List<LessonDTO.Resp> getLessons(Long courseId);
}
