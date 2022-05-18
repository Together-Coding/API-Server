package com.example.authserver.service;

import com.example.authserver.domain.Lesson;
import com.example.authserver.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    Long register(Long userId, String name, String password, String description);

    void addUser(Long teacherId, List<String> email, Long courseId);

    void updateTeacher(String teacherEmail, Long courseId);

    void delete(Long courseId, Long userId);

    void changePw(Long userId, Long courseId, String newPw);

    List<CourseDTO.Response> getCoursesWhereIamTeacher(Long userId);

    List<CourseDTO.Response> getCoursesWhereIamStudent(Long userId);

    CourseDTO.CourseWithParticipants getCourseData(Long courseId);
}
