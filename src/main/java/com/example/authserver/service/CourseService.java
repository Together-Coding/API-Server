package com.example.authserver.service;

public interface CourseService {

    Long register(String name, String password, String teacherEmail ,String[] emails);

    void addUser(String teacherEmail, String email, Long courseId);

    void delete(Long courseId);
}
