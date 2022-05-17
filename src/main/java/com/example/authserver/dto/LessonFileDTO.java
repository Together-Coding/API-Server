package com.example.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class LessonFileDTO {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class File {

        @NotNull
        String url;

        @NotNull
        Long lessonId;
    }
}
