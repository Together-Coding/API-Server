package com.example.authserver.controller;

import com.example.authserver.dto.CourseDTO;
import com.example.authserver.dto.LessonDTO;
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


    // 테스트 완료
    @PostMapping
    public void registerCourse(@AuthenticationPrincipal AuthUserDTO authUser,
                               @RequestBody @Valid CourseDTO.Request courseDTO) {
        courseService.register(
                authUser.getId(),
                courseDTO.getName(),
                courseDTO.getPassword(),
                courseDTO.getDescription()
        );
    }

    // 테스트 완료
    @PutMapping("/teacher")
    public void addTeacher(@RequestBody @Valid CourseDTO.AddTeacher teacherDTO) {

        courseService.updateTeacher(
                teacherDTO.getTeacherEmail(),
                teacherDTO.getCourseId());
    }

    // 테스트 완료
    @GetMapping("/student")
    public List<CourseDTO.Response> getStudentCourse(@AuthenticationPrincipal AuthUserDTO authUser) {
        return courseService.getCoursesWhereIamStudent(authUser.getId());
    }

    // 테스트 완료
    @GetMapping("/teacher")
    public List<CourseDTO.Response> getTeacherCourse(@AuthenticationPrincipal AuthUserDTO authUser) {
        return courseService.getCoursesWhereIamTeacher(authUser.getId());
    }

    // 테스트 완료
    @GetMapping("/{courseId}")
    public CourseDTO.CourseWithParticipants getCourseWithParticipants(@PathVariable("courseId") Long courseId) {
        return courseService.getCourseData(courseId);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@AuthenticationPrincipal AuthUserDTO authUser,
                             @PathVariable Long courseId) {
        courseService.delete(
                courseId,
                authUser.getId());
    }

    // 테스트 완료
    @PostMapping("/student")
    public void registerNewStudent(@AuthenticationPrincipal AuthUserDTO authUser,
                                   @RequestBody @Valid CourseDTO.AddUser addUser) {
        courseService.addUser(
                authUser.getId(),
                addUser.getEmails(),
                addUser.getCourseId());
    }

    // 테스트 완료
    @PutMapping("/password")
    public void changePassword(@AuthenticationPrincipal AuthUserDTO authUser,
                               @RequestBody @Valid CourseDTO.Password passwordDTO){
        courseService.changePw(
                authUser.getId(),
                passwordDTO.getCourseId(),
                passwordDTO.getPassword()
        );
    }

    @PutMapping("/{courseId}")
    public void changeCourse(@AuthenticationPrincipal AuthUserDTO authUser,
                             @RequestBody @Valid CourseDTO.Update update,
                             @PathVariable Long courseId) {

        courseService.update(
                authUser.getId(),
                courseId,
                update.getName(),
                update.getDescription()
        );
    }
}
