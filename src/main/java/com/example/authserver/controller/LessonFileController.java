package com.example.authserver.controller;

import com.example.authserver.dto.FileDTO;
import com.example.authserver.security.dto.AuthUserDTO;
import com.example.authserver.service.LessonFileService;
import com.example.authserver.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@RequestMapping("/api/file")
@RestController
@RequiredArgsConstructor
public class LessonFileController {

    private final LessonFileService lessonFileService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public void uploadFile(@AuthenticationPrincipal AuthUserDTO authUser,
                           @RequestPart("file") MultipartFile multipartFile,
                           @RequestPart("uploadDTO") FileDTO.Upload uploadDTO) throws IOException {

        final String dirName = "course";
        lessonFileService.register(
                dirName,
                multipartFile,
                uploadDTO.getCourseId(),
                uploadDTO.getLessonId(),
                authUser.getId());
    }

    @GetMapping
    public ResponseEntity<byte[]> download(@RequestBody FileDTO.Download download) throws IOException {
        return lessonFileService.download(download.getFileUrl());
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal AuthUserDTO authUser,
                       @RequestBody FileDTO.Delete deleteDTO) {

        lessonFileService.delete(
                deleteDTO.getFileUrl(),
                deleteDTO.getLessonId(),
                authUser.getId()
        );
    }
}
