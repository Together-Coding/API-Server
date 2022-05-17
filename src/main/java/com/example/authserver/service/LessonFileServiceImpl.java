package com.example.authserver.service;

import com.example.authserver.domain.Lesson;
import com.example.authserver.domain.LessonFile;
import com.example.authserver.exception.custom.ForbiddenException;
import com.example.authserver.exception.custom.NotFoundException;
import com.example.authserver.repository.LessonFileRepository;
import com.example.authserver.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class LessonFileServiceImpl implements LessonFileService {

    private final LessonRepository lessonRepository;
    private final LessonFileRepository lessonFileRepository;

    @Override
    @Transactional
    public void register(String fileUrl, Long lessonId, Long userId) {
        Lesson lesson = lessonRepository.findLessonById(lessonId);
        if (!lesson.getCourse().getUser().getId().equals(userId)) {
            throw new ForbiddenException("권한이 없습니다.");
        }
        LessonFile lessonFile = LessonFile.builder()
                .url(fileUrl)
                .build();
        lessonFileRepository.save(lessonFile);

        lesson.updateLessonFile(lessonFile);
    }

}
