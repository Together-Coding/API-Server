package com.example.authserver.controller;

import com.example.authserver.dto.CourseDTO;
import com.example.authserver.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/course")
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public void registerCourse(@RequestBody @Valid CourseDTO courseDTO) {
        courseService.register(
                courseDTO.getName(),
                courseDTO.getPassword(),
                courseDTO.getTeacherName(),
                courseDTO.getParticipants()
        );
    }
}
