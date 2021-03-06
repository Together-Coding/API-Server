package com.example.authserver.controller;

import com.example.authserver.dto.UserDTO;
import com.example.authserver.security.dto.AuthUserDTO;
import com.example.authserver.security.dto.JwtDTO;
import com.example.authserver.security.util.JWTUtil;
import com.example.authserver.service.UserService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

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
        JwtDTO jwtDTO = null;
        try {
            jwtDTO = jwtUtil.validateAndExtract(jwt);
        } catch (Exception e){
            throw new JwtException(e.getMessage());
        }
        if (jwtDTO.isValid()) {
            String email = jwtDTO.getEmail();
            Long userId = userService.getUserByEmail(email).getId();
            jwtDTO.updateUserId(userId);
        }
        return jwtDTO;
    }
}

