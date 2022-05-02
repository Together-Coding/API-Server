package com.example.authserver.service;


import com.example.authserver.domain.Course;
import com.example.authserver.domain.Lesson;
import com.example.authserver.exception.custom.ForbiddenException;
import com.example.authserver.exception.custom.NotFoundException;
import com.example.authserver.repository.CourseRepository;
import com.example.authserver.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public void register(String name, String description, Long courseId, Long userId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));

        Long teacherId = course.getUser().getId();

        if (!teacherId.equals(userId)) {
            throw new ForbiddenException("생성권한이 없습니다.");
        }

        Lesson lesson = Lesson.builder()
                .name(name)
                .description(description)
                .course(course)
                .build();

        lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public void updateName(Long lessonId, Long userId, String name) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("can not find lesson. input lessonId: " + lessonId));

        Long ownerId = lesson.getCourse().getUser().getId();

        if (!ownerId.equals(userId)) {
            throw new ForbiddenException("수정권한이 없습니다.");
        }

        lesson.updateName(name);
    }

    @Override
    @Transactional
    public void updateDescription(Long lessonId, Long userId, String description) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("can not find lesson. input lessonId: " + lessonId));

        Long ownerId = lesson.getCourse().getUser().getId();

        if (!ownerId.equals(userId)) {
            throw new ForbiddenException("수정권한이 없습니다.");
        }

        lesson.updateDescription(description);
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("can not find lesson. input lessonId: " + id));

        Long ownerId = lesson.getCourse().getUser().getId();

        if (!ownerId.equals(userId)) {
            throw new ForbiddenException("삭제권한이 없습니다.");
        }

        lessonRepository.delete(lesson);
    }

    @Override
    @Transactional
    public List<Lesson> getLessons(Long courseId){
        return lessonRepository.findAllByCourse_Id(courseId);
    }
}
