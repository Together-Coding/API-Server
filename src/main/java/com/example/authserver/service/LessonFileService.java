package com.example.authserver.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LessonFileService {

    void register(String dirName, MultipartFile multipartFile, Long courseId, Long lessonId, Long userId) throws IOException;

    void delete(String fileUrl, Long lessonId, Long userId);

    ResponseEntity<byte[]> download(String fileUrl) throws IOException;
}
