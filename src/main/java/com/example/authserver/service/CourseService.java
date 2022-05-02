package com.example.authserver.service;

import com.example.authserver.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    Long register(String name, String password, String teacherEmail, String description, String[] emails);

    Long addUser(String teacherEmail, String email, Long courseId);

    void delete(Long courseId);

    void changePw(Long courseId, String newPw);

    List<CourseDTO.Response> getCoursesWhereIamTeacher(Long userId);

    List<CourseDTO.Response> getCoursesWhereIamStudent(Long userId);

    CourseDTO.CourseWithParticipants getCourseData(Long courseId);
}
