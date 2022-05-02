package com.example.authserver.service;

import com.example.authserver.domain.Participant;
import com.example.authserver.repository.ParticipantRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("dev")
public class CourseServiceTests {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipantRepository participantRepository;

//    @DisplayName("course에 수강생 추가")
//    @Test
//    void Should_AddUserToCourse() {
//        int rnd = (int) (Math.random() * 10000);
//        rnd += 10000;
//        userService.signup("student" + rnd + "@naver.com", "password1", "student" + rnd);
//        Long participantId = courseService.addUser(
//                "teacher1@naver.com",
//                "student" + rnd + "@naver.com",
//                1L
//        );
//        Participant participant = participantRepository.getParticipantById(participantId);
//        assertEquals("student" + rnd + "@naver.com", participant.getUser().getEmail());
//    }
//
//    @DisplayName("학생이 다른 학생 추가할 때 예외던짐")
//    @Test
//    void Should_ThrowException_When_StudentAddUser() {
//        int rnd = (int) (Math.random() * 10000);
//        rnd += 10000;
//        userService.signup("student" + rnd + "@naver.com", "password1", "student" + rnd);
//        int finalRnd = rnd;
//        Throwable ex = assertThrows(RuntimeException.class, () ->
//                courseService.addUser(
//                        "student1@naver.com",
//                        "student" + finalRnd + "@naver.com",
//                        1L));
//        assertEquals("권한이 없습니다.", ex.getMessage());
//    }

}
