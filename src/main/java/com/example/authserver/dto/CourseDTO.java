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

        @NotBlank
        private String name;

        @NotBlank
        private String password;

        @NotBlank
        private String description;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Password {

        @NotBlank
        String password;

        @NotNull
        Long courseId;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddUser {

        @NotNull
        private List<String> emails;

        @NotNull
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

        private String teacherEmail;

        private String teacherName;

        private Long courseId;

        private String name;

        private String description;

        private List<ParticipantDTO> participants;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {

        private String name = null;

        private String description = null;
    }
}
