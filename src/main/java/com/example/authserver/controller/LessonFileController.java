package com.example.authserver.controller;


import com.example.authserver.dto.LessonFileDTO;
import com.example.authserver.security.dto.AuthUserDTO;
import com.example.authserver.service.LessonFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/file")
@RestController
@RequiredArgsConstructor
public class LessonFileController {

    private final LessonFileService lessonFileService;

    @PostMapping
    public void registerLessonFile(@AuthenticationPrincipal AuthUserDTO authUser,
                                   @RequestBody LessonFileDTO.File fileDTO) {

        lessonFileService.register(
                fileDTO.getUrl(),
                fileDTO.getLessonId(),
                authUser.getId());
    }
}
