package com.example.authserver.service;

import com.example.authserver.domain.Course;
import com.example.authserver.domain.Participant;
import com.example.authserver.domain.Role;
import com.example.authserver.domain.User;
import com.example.authserver.repository.CourseRepository;
import com.example.authserver.repository.ParticipantRepository;
import com.example.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Log4j2
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public Long register(String name, String password, String[] emails) {
        String enPw = passwordEncoder.encode(password);
        Course course = Course.builder()
                .name(name)
                .password(enPw)
                .accessible(false)
                .active(false)
                .build();

        courseRepository.save(course);

        for (String email : emails) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("can not find user. input email: " + email));

            Participant participant = Participant.builder()
                    .course(course)
                    .user(user)
                    .role(Role.STUDENT)
                    .build();

            participantRepository.save(participant);
        }

        return course.getId();
    }



    @Transactional
    public void delete(Long courseId){
        courseRepository.deleteById(courseId);
    }
}
