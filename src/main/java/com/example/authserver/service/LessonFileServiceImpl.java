package com.example.authserver.service;

import com.example.authserver.domain.Lesson;
import com.example.authserver.domain.LessonFile;
import com.example.authserver.exception.custom.ForbiddenException;
import com.example.authserver.exception.custom.NotFoundException;
import com.example.authserver.repository.LessonFileRepository;
import com.example.authserver.repository.LessonRepository;
import com.example.authserver.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class LessonFileServiceImpl implements LessonFileService {

    private final LessonRepository lessonRepository;
    private final LessonFileRepository lessonFileRepository;
    private final S3Uploader s3Uploader;

    @Override
    @Transactional
    public void register(String dirName, MultipartFile multipartFile, Long courseId, Long lessonId, Long userId) throws IOException {
        Lesson lesson = lessonRepository.findLessonById(lessonId);
        if (!lesson.getCourse().getUser().getId().equals(userId)) {
            throw new ForbiddenException("권한이 없습니다.");
        }
        String fileUrl = s3Uploader.upload(multipartFile, dirName, courseId, lessonId);
        LessonFile lessonFile = LessonFile.builder()
                .url(fileUrl)
                .build();
        lessonFileRepository.save(lessonFile);

        lesson.updateLessonFile(lessonFile);
    }

    @Override
    @Transactional
    public void delete(String fileUrl, Long lessonId, Long userId) {
        Lesson lesson = lessonRepository.findLessonById(lessonId);
        if (!lesson.getCourse().getUser().getId().equals(userId)) {
            throw new ForbiddenException("권한이 없습니다.");
        }
        Long fileId = lesson.getLessonFile().getId();
        lesson.updateLessonFile(null);
        lessonFileRepository.deleteById(fileId);
        s3Uploader.deleteFile(fileUrl);
    }

    @Override
    @Transactional
    public ResponseEntity<byte[]> download(String fileUrl) throws IOException {
        return s3Uploader.getObject(fileUrl);
    }


}
