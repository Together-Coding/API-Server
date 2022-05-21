package com.example.authserver;


import com.example.authserver.repository.CourseRepository;
import com.example.authserver.repository.LessonRepository;
import com.example.authserver.repository.ParticipantRepository;
import com.example.authserver.repository.UserRepository;
import com.example.authserver.service.CourseService;
import com.example.authserver.service.ParticipantService;
import com.example.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiCommandLineRunner implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final CourseService courseService;
    private final UserService userService;
    private final ParticipantService participantService;

    @Override
    public void run(String... args) throws Exception {
        userService.signup("test@naver.com", "1234", "teacher1");
        courseService.register(
                1L,
                "코스1",
                "1234",
                "설명");
//        this.insertUser();
//        this.insertCourse();
//        this.addTeacher();
//        this.insertParticipants();
    }

    private void insertUser() {
        for (int i = 1; i <= 100; i++) {
            userService.signup("student" + i + "@naver.com",
                    "a123456789",
                    "student" + i);
        }
        for (int i = 1; i <= 10; i++) {
            userService.signup("teacher" + i + "@naver.com",
                    "a123456789",
                    "teacher" + i);
        }
    }


    private void insertCourse() {
        for (int i = 1; i <= 30; i++) {
            courseService.register(
                    (long) i,
                    "코스" + i,
                    "1234",
                    "설명" + i
            );
        }
    }


    private void addTeacher() {
        courseService.updateTeacher("teacher10@naver.com", 1L);
        courseService.updateTeacher("teacher10@naver.com", 30L);
        courseService.updateTeacher("teacher9@naver.com", 2L);
        courseService.updateTeacher("teacher9@naver.com", 29L);
        courseService.updateTeacher("teacher8@naver.com", 3L);
        courseService.updateTeacher("teacher8@naver.com", 28L);
        courseService.updateTeacher("teacher7@naver.com", 4L);
        courseService.updateTeacher("teacher7@naver.com", 27L);
        courseService.updateTeacher("teacher6@naver.com", 5L);
        courseService.updateTeacher("teacher6@naver.com", 26L);
        courseService.updateTeacher("teacher5@naver.com", 6L);
        courseService.updateTeacher("teacher5@naver.com", 25L);
        courseService.updateTeacher("teacher4@naver.com", 7L);
        courseService.updateTeacher("teacher4@naver.com", 24L);
        courseService.updateTeacher("teacher3@naver.com", 8L);
        courseService.updateTeacher("teacher3@naver.com", 23L);
        courseService.updateTeacher("teacher2@naver.com", 9L);
        courseService.updateTeacher("teacher2@naver.com", 22L);
        courseService.updateTeacher("teacher1@naver.com", 10L);
        courseService.updateTeacher("teacher1@naver.com", 21L);
    }

    private void insertParticipants() {
        courseService.addUser(101L,
                new ArrayList<>(List.of(
                        "student100@naver.com",
                        "student99@naver.com",
                        "student98@naver.com",
                        "student97@naver.com",
                        "student96@naver.com",
                        "student95@naver.com")
                ),21L);
        courseService.addUser(102L,
                new ArrayList<>(List.of(
                        "teacher1@naver.com",
                        "student92@naver.com",
                        "student90@naver.com",
                        "student97@naver.com",
                        "student81@naver.com",
                        "student83@naver.com")
                ),9L);
        courseService.addUser(106L,
                new ArrayList<>(List.of(
                        "student11@naver.com",
                        "student12@naver.com",
                        "student13@naver.com",
                        "teacher1@naver.com",
                        "student92@naver.com",
                        "student90@naver.com",
                        "student97@naver.com",
                        "student81@naver.com",
                        "student83@naver.com")
                ),26L);
        courseService.addUser(101L,
                new ArrayList<>(List.of(
                        "student10@naver.com",
                        "student9@naver.com",
                        "student8@naver.com",
                        "student7@naver.com",
                        "student6@naver.com",
                        "student5@naver.com")
                ),10L);


    }
}
