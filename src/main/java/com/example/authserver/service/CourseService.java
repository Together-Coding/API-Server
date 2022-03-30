package com.example.authserver.service;

public interface CourseService {

    Long register(String name, String password, String teacherEmail ,String[] emails);
}
