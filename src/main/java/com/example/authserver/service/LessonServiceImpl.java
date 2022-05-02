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

@Log4j2
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    @Transactional
    public void register(String name, String description, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));

        Lesson lesson = Lesson.builder()
                .name(name)
                .description(description)
                .course(course)
                .build();

        lessonRepository.save(lesson);
    }

    @Transactional
    public void update(Long id, String name, String description) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("can not find lesson. input lessonId: " + id));

        lesson.update(name, description);
    }

    @Transactional
    public void delete(Long id, Long userId){
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("can not find lesson. input lessonId: " + id));

        Long ownerId = lesson.getCourse().getUser().getId();

        if (!ownerId.equals(userId)){
            throw new ForbiddenException("삭제할 권한이 없습니다.");
        }

        lessonRepository.delete(lesson);
    }


}
