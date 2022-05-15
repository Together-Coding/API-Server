package com.example.authserver.service;

import com.example.authserver.domain.Lesson;
import com.example.authserver.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    Long register(String name, String password, String description);

    void addUser(Long teacherId, String[] email, Long courseId);

    void updateAccessible(Long userId, Long courseId, int status);

    void updateActive(Long userId, Long courseId, int status);

    void addTeacher(String teacherEmail, Long courseId);

    void delete(Long courseId, Long userId);

    void changePw(Long courseId, String newPw);

    List<CourseDTO.Response> getCoursesWhereIamTeacher(Long userId);

    List<CourseDTO.Response> getCoursesWhereIamStudent(Long userId);

    CourseDTO.CourseWithParticipants getCourseData(Long courseId);
}
