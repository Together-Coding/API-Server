package com.example.authserver.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
public class LessonDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Register {

        @NotNull
        private Long courseId;

        @NotNull
        private String name;

        @NotNull
        private String description;

        @NotNull
        private Long lang_image_id;
    }

    @Getter
    @Builder
    public static class Resp {

        private Long lessonId;

        private Long courseId;

        private String name;

        private String description;

        private String fileUrl;
    }


}
