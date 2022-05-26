package com.example.authserver.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
public class FileDTO {


    @Getter
    public static class Upload {

        private Long courseId;

        private Long lessonId;
    }

    @Getter
    public static class Delete {

        private String fileUrl;

        private Long lessonId;
    }

    @Getter
    public static class Download {

        private String fileUrl;

    }
}
