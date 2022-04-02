package com.example.authserver.dto;

import lombok.Getter;

@Getter
public class CourseDTO {

    private String name;

    private String password;

    private String teacherName;

    private String[] participants;
}
