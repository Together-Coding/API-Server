package com.example.authserver.controller;

import com.example.authserver.dto.UserDTO;
import com.example.authserver.security.dto.JwtDTO;
import com.example.authserver.security.util.JWTUtil;
import com.example.authserver.service.UserService;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/user")
    public void registerUser(@RequestBody @Valid UserDTO.Signup request) {

        userService.signup(
                request.getEmail(),
                request.getPassword(),
                request.getName()
        );
    }

    @PostMapping("/token")
    public JwtDTO validateToken(@RequestBody Map<String, String> token) throws Exception {
        String jwt = token.get("token");
        JwtDTO jwtDTO = jwtUtil.validateAndExtract(jwt);
        if (jwtDTO != null) {
            String email = jwtDTO.getEmail();
            Long userId = userService.getUserByEmail(email).getId();
            jwtDTO.updateUserId(userId);
        } else{
            throw new SignatureException("토큰 에러");
        }
        return jwtDTO;
    }
}
