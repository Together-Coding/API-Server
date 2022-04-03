package com.example.authserver.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CourseServiceTests {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Test
    void insertDummies(){
        String[] emails = new String[5];
        emails[0] = "part1@naver.com";
        emails[1] = "part2@naver.com";
        emails[2] = "part3@naver.com";
        emails[3] = "part4@naver.com";
        emails[4] = "part5@naver.com";
        courseService.register(
                "컴퓨터 공학 종합 설계",
                "testPassword",
                "chasw326@naver.com",
                emails
        );
    }

    @Test
    void insertUsers(){
        userService.signup("part1@naver.com", "password1", "part1");
        userService.signup("part2@naver.com", "password2", "part2");
        userService.signup("part3@naver.com", "password3", "part3");
        userService.signup("part4@naver.com", "password4", "part4");
        userService.signup("part5@naver.com", "password5", "part5");
        userService.signup("chasw326@naver.com", "password326", "part326");
    }
}
