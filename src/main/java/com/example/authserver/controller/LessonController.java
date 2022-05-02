package com.example.authserver.controller;


import com.example.authserver.dto.LessonDTO;
import com.example.authserver.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/lesson")
@RestController
@RequiredArgsConstructor
public class LessonController {

    private LessonService lessonService;

    @PostMapping
    public void registerLesson(LessonDTO lessonDTO, Long courseId) {

        lessonService.register(
                lessonDTO.getName(),
                lessonDTO.getDescription(),
                courseId);
    }
}
