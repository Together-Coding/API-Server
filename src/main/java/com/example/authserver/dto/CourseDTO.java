package com.example.authserver.dto;

import lombok.Builder;
import lombok.Getter;


public class CourseDTO {

    @Getter
    public static class Request {

        private String name;

        private String password;

        private String teacherName;

        private String description;

        private String[] participants;
    }

    @Getter
    @Builder
    public static class Response {

        private Long courseId;

        private String name;

        private String description;
    }
}
