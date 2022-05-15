package com.example.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Builder
@NoArgsConstructor
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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddUser {

        private List<String> emails;
        private Long courseId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddTeacher {

        @NotBlank
        private String teacherEmail;

        @NotNull
        private Long courseId;
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
