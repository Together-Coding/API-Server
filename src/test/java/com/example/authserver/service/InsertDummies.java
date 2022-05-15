package com.example.authserver.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("dev")
public class InsertDummies {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Test
    void insertDummies() {
        String[] emails = new String[10];
        emails[0] = "student1@naver.com";
        emails[1] = "student2@naver.com";
        emails[2] = "student3@naver.com";
        emails[3] = "student4@naver.com";
        emails[4] = "student5@naver.com";
        emails[5] = "student6@naver.com";
        emails[6] = "student7@naver.com";
        emails[7] = "student8@naver.com";
        emails[8] = "student9@naver.com";
        emails[9] = "student10@naver.com";

        courseService.register(
                "컴퓨터 공학 종합 설계",
                "testPassword",
                "팀플과목."
        );

        String[] emails2 = new String[5];
        emails2[0] = "student11@naver.com";
        emails2[1] = "student12@naver.com";
        emails2[2] = "student13@naver.com";
        emails2[3] = "student14@naver.com";
        emails2[4] = "student15@naver.com";

        courseService.register(
                "마이크로 프로세서 응용",
                "testPassword",
                "어려운 과목"
        );

        String[] emails3 = new String[3];
        emails3[0] = "student16@naver.com";
        emails3[1] = "student17@naver.com";
        emails3[2] = "student18@naver.com";

        courseService.register(
                "운영체제",
                "testPassword",
                "중요한 과목"
        );

        String[] emails4 = new String[4];
        emails4[0] = "student19@naver.com";
        emails4[1] = "student20@naver.com";
        emails4[2] = "student21@naver.com";
        emails4[3] = "student22@naver.com";

        courseService.register(
                "컴퓨터보안",
                "testPassword",
                "보안어려워"
        );
    }


    @Test
    void insertUsers() {
        for (int i = 1; i <= 100; i++) {
            userService.signup("student" + i + "@naver.com", "password1", "student" + i);
        }
        for (int i = 1; i <= 10; i++) {
            userService.signup("teacher" + i + "@naver.com", "password1", "teacher" + i);
        }
    }
}
