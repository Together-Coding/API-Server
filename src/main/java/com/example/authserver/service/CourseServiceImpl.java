package com.example.authserver.service;

import com.example.authserver.domain.Course;
import com.example.authserver.domain.Participant;
import com.example.authserver.domain.Role;
import com.example.authserver.domain.User;
import com.example.authserver.repository.CourseRepository;
import com.example.authserver.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Log4j2
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;
    private final ParticipantRepository participantRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public Long register(String name, String password, String teacherEmail, String[] emails) {
        String enPw = passwordEncoder.encode(password);
        Course course = Course.builder()
                .name(name)
                .password(enPw)
                .accessible(false)
                .active(false)
                .build();

        courseRepository.save(course);

        User owner = userService.getUserByEmail(teacherEmail);

        Participant teacher = Participant.builder()
                .course(course)
                .user(owner)
                .role(Role.TEACHER)
                .build();

        participantRepository.save(teacher);

        for (String email : emails) {
            User user = userService.getUserByEmail(email);

            Participant student = Participant.builder()
                    .course(course)
                    .user(user)
                    .role(Role.STUDENT)
                    .build();

            participantRepository.save(student);
        }

        return course.getId();
    }

    @Transactional
    public void addUser(String teacherEmail, String email, Long courseId) {
        List<Participant> teachers = participantRepository.getAllByCourse_IdAndRole(courseId, Role.TEACHER);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("can not find course. input courseId: " + courseId));
        User user = userService.getUserByEmail(email);
        for (Participant teacher : teachers) {
            if (teacher.getUser().getEmail().equals(teacherEmail)) {
                Participant student = Participant.builder()
                        .course(course)
                        .user(user)
                        .role(Role.STUDENT)
                        .build();

                participantRepository.save(student);
                return;
            }
        }
    }

    @Transactional
    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }


}
