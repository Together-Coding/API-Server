package com.example.authserver.service;

import com.example.authserver.security.dto.JwtDTO;
import com.example.authserver.security.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class jwtUtilTests {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    void ex() throws Exception {
        String token = jwtUtil.generateToken("chasw326@naver.com");
        JwtDTO jwtDTO = jwtUtil.validateAndExtract(token + "salt");
//        System.out.println(jwtDTO.getIssuedAt());
//        System.out.println(jwtDTO.getEmail());
//        System.out.println(jwtDTO.isValid());
//        System.out.println(jwtDTO.getExpiredAt());
    }
}
