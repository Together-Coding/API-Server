package com.example.authserver.service;

import com.example.authserver.domain.Course;
import com.example.authserver.domain.Participant;
import com.example.authserver.domain.Role;
import com.example.authserver.domain.User;
import com.example.authserver.dto.CourseDTO;
import com.example.authserver.dto.ParticipantDTO;
import com.example.authserver.exception.custom.ForbiddenException;
import com.example.authserver.exception.custom.NotFoundException;
import com.example.authserver.repository.CourseRepository;
import com.example.authserver.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


@Log4j2
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;
    private final ParticipantRepository participantRepository;
    private final ParticipantService participantService;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public Long register(String name, String password, String teacherEmail, String description, String[] emails) {
        String enPw = passwordEncoder.encode(password);
        Course course = Course.builder()
                .name(name)
                .password(enPw)
                .description(description)
                .accessible(0)
                .active(0)
                .build();

        courseRepository.save(course);

        User owner = userService.getUserByEmail(teacherEmail);


        // 레슨 추가 해야함
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

    @Override
    @Transactional
    public Long addUser(String teacherEmail, String email, Long courseId) {
        List<Participant> teachers = participantRepository.getAllByCourse_IdAndRole(courseId, Role.TEACHER);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));
        User user = userService.getUserByEmail(email);
        // TODO : 여기 뭔가 이상한데...
        for (Participant teacher : teachers) {
            if (teacher.getUser().getEmail().equals(teacherEmail)) {
                Participant student = Participant.builder()
                        .course(course)
                        .user(user)
                        .role(Role.STUDENT)
                        .build();

                participantRepository.save(student);
                return student.getId();
            } else {
                throw new ForbiddenException("권한이 없습니다.");
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    @Transactional
    public List<CourseDTO.Response> getCoursesWhereIamStudent(Long userId) {
        List<Participant> participants = participantService.getCoursesThatIamStudentByUserId(userId);
        return getCourseResp(participants);
    }

    @Override
    @Transactional
    public List<CourseDTO.Response> getCoursesWhereIamTeacher(Long userId) {
        List<Participant> participants = participantService.getCoursesThatIamTeacherByUserId(userId);
        return getCourseResp(participants);
    }

    @Override
    @Transactional
    public CourseDTO.CourseWithParticipants getCourseData(Long courseId) {
        List<ParticipantDTO> participants = participantService.getParticipantList(courseId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));
        return CourseDTO.CourseWithParticipants.builder()
                .courseId(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .participants(participants)
                .build();
    }

    private List<CourseDTO.Response> getCourseResp(List<Participant> participants) {
        List<CourseDTO.Response> responses = new ArrayList<>();
        for (Participant participant : participants) {
            responses.add(CourseDTO.Response.builder()
                    .courseId(participant.getCourse().getId())
                    .name(participant.getCourse().getName())
                    .description(participant.getCourse().getDescription())
                    .build());
        }
        return responses;
    }

}
