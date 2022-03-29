package com.example.authserver.service;

import com.example.authserver.domain.Course;
import com.example.authserver.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;

    // TODO: 비밀번호 인코딩
    public void register(String name, String[] students, String password) {
        Course course = Course.builder()
                .name(name)
                .accessible(false) // 임시
                .active(false) // 임시
//                .user()
                .build();
    }
}
