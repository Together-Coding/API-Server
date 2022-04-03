package com.example.authserver.controller;

import com.example.authserver.dto.CourseDTO;
import com.example.authserver.security.dto.AuthUserDTO;
import com.example.authserver.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/course")
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public void registerCourse(@RequestBody @Valid CourseDTO.Request courseDTO) {
        courseService.register(
                courseDTO.getName(),
                courseDTO.getPassword(),
                courseDTO.getTeacherName(),
                courseDTO.getDescription(),
                courseDTO.getParticipants()
        );
    }

    @GetMapping("/student")
    public List<CourseDTO.Response> getStudentCourse(@AuthenticationPrincipal AuthUserDTO authUser) {
        return courseService.getCoursesWhereIamStudent(authUser.getId());
    }

    @GetMapping("/teacher")
    public List<CourseDTO.Response> getTeacherCourse(@AuthenticationPrincipal AuthUserDTO authUser) {
        return courseService.getCoursesWhereIamTeacher(authUser.getId());
    }


}
