package com.example.authserver.service;

import com.example.authserver.domain.*;
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
    public Long register(Long userId, String name, String password, String description) {
        String enPw = passwordEncoder.encode(password);
        User user = userService.getUser(userId);
        Course course = Course.builder()
                .name(name)
                .user(user)
                .password(enPw)
                .description(description)
                .build();

        courseRepository.save(course);

        participantRepository.save(Participant.builder()
                .role(ParticipantRole.TEACHER)
                .course(course)
                .user(user)
                .build());

        return course.getId();
    }

    @Override
    @Transactional
    public void addUser(Long teacherId, List<String> emails, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));

        if (!course.getUser().getId().equals(teacherId)) {
            throw new ForbiddenException("권한이 없습니다.");
        }

        for (String email : emails) {
            User user = userService.getUserByEmail(email);
            Participant student = Participant.builder()
                    .course(course)
                    .user(user)
                    .role(ParticipantRole.STUDENT)
                    .build();
            participantRepository.save(student);
        }
    }

    @Override
    @Transactional
    public void updateTeacher(String teacherEmail, Long courseId) {
        User user = userService.getUserByEmail(teacherEmail);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));
        course.updateUser(user);
        Participant participant = participantRepository.findByCourse_IdAndRole(courseId, ParticipantRole.TEACHER);
        participant.updateUser(user);
    }

    @Override
    @Transactional
    public void changePw(Long userId, Long courseId, String newPw) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));
        if (!course.getUser().getId().equals(userId)) {
            throw new ForbiddenException("권한이 없습니다.");
        }
        String enPw = passwordEncoder.encode(newPw);
        course.updatePw(enPw);
        courseRepository.save(course);
    }

//    @Override
//    @Transactional
//    public void updateAccessible(Long userId, Long courseId, int status) {
//        Participant participant = participantRepository.getParticipantByCourse_IdAndUser_Id(courseId, userId);
//        if (!participant.getRole().equals(ParticipantRole.TEACHER)) {
//            throw new ForbiddenException("권한이 없습니다.");
//        }
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));
//        course.updateAccessible(status);
//    }

//    @Override
//    @Transactional
//    public void updateActive(Long userId, Long courseId, int status) {
//        Participant participant = participantRepository.getParticipantByCourse_IdAndUser_Id(courseId, userId);
//        if (!participant.getRole().equals(ParticipantRole.TEACHER)) {
//            throw new ForbiddenException("권한이 없습니다.");
//        }
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));
//        course.updateActive(status);
//    }


    @Override
    @Transactional
    public void delete(Long courseId, Long userId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));

        if (!course.getUser().getId().equals(userId)) {
            throw new ForbiddenException("삭제 권한이 없습니다.");
        }

        courseRepository.deleteById(courseId);
    }


    @Override
    @Transactional
    public List<CourseDTO.Response> getCoursesWhereIamStudent(Long userId) {
        List<Participant> participants = participantService.getCoursesThatIamStudentByUserId(userId);
        return getCourseResp(participants, "student");
    }

    @Override
    @Transactional
    public List<CourseDTO.Response> getCoursesWhereIamTeacher(Long userId) {
        List<Participant> participants = participantService.getCoursesThatIamTeacherByUserId(userId);
        return getCourseResp(participants, "teacher");
    }

    @Override
    @Transactional
    public void update(Long userId, Long courseId, String name, String description) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("can not find course. input courseId: " + courseId));
        if (!course.getUser().getId().equals(userId)) {
            throw new ForbiddenException("권한이 없습니다.");
        }
        String changedName;
        String changedDescription;
        if (name == null) {
            changedName = course.getName();
        } else {
            changedName = name;
        }
        if (description == null) {
            changedDescription = course.getDescription();
        } else {
            changedDescription = description;
        }
        course.update(changedName, changedDescription);
    }

    @Override
    @Transactional
    public CourseDTO.CourseWithParticipants getCourseData(Long courseId) {
        List<ParticipantDTO> participants = participantService.getParticipantList(courseId);
        Course course = courseRepository.findCourseById(courseId);
        return CourseDTO.CourseWithParticipants.builder()
                .teacherEmail(course.getUser().getEmail())
                .teacherName(course.getUser().getName())
                .courseId(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .participants(participants)
                .build();
    }

    private List<CourseDTO.Response> getCourseResp(List<Participant> participants, String role) {
        List<CourseDTO.Response> responses = new ArrayList<>();
        for (Participant participant : participants) {
            responses.add(CourseDTO.Response.builder()
                    .courseId(participant.getCourse().getId())
                    .name(participant.getCourse().getName())
                    .description(participant.getCourse().getDescription())
                    .role(role)
                    .build());
        }
        return responses;
    }

}
