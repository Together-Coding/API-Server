package com.example.authserver.controller;


import com.example.authserver.domain.Lesson;
import com.example.authserver.dto.LessonDTO;
import com.example.authserver.security.dto.AuthUserDTO;
import com.example.authserver.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/lesson")
@RestController
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    // 테스트 완료
    @GetMapping("/{courseId}")
    public List<LessonDTO.Resp> getLessons(@PathVariable Long courseId) {
        return lessonService.getLessons(courseId);
    }

    // 테스트 완료
    @PostMapping
    public void registerLesson(@RequestBody LessonDTO.Register lessonDTO,
                               @AuthenticationPrincipal AuthUserDTO authUser) {

        lessonService.register(
                lessonDTO.getName(),
                lessonDTO.getDescription(),
                lessonDTO.getCourseId(),
                authUser.getId());
    }

    @PutMapping("/name/{lessonId}")
    public void updateName(@AuthenticationPrincipal AuthUserDTO authUser,
                           @PathVariable Long lessonId,
                           @RequestBody String name) {

        lessonService.updateName(
                lessonId,
                authUser.getId(),
                name
        );
    }

    @PutMapping("/description/{lessonId}")
    public void updateDescription(@AuthenticationPrincipal AuthUserDTO authUser,
                                  @PathVariable Long lessonId,
                                  @RequestBody String description) {

        lessonService.updateDescription(
                lessonId,
                authUser.getId(),
                description
        );
    }

    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@AuthenticationPrincipal AuthUserDTO authUser,
                             @PathVariable Long lessonId) {

        lessonService.delete(lessonId, authUser.getId());
    }

}
