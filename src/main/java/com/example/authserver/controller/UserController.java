package com.example.authserver.controller;

import com.example.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void registerUser(@RequestBody @Valid UserDTO.Signup request){

        userService.signup(
                request.getEmail(),
                request.getPassword(),
                request.getName()
        );
    }
}
