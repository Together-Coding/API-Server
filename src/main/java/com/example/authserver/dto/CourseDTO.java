package com.example.authserver.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


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

        private String role;
    }

    @Getter
    @Builder
    public static class CourseWithParticipants {

        private Long courseId;

        private String name;

        private String description;

        private List<ParticipantDTO> participants;
    }
}
