package com.example.authserver.controller;

import com.example.authserver.dto.UserDTO;
import com.example.authserver.security.dto.AuthUserDTO;
import com.example.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping
    public void updateUser(@AuthenticationPrincipal AuthUserDTO authUser,
                           @Valid UserDTO.Update updateDTO) {

        userService.updateUser(
                authUser.getId(),
                authUser.getName()
        );
    }

    @PutMapping("/api/user/password")
    public void changePassword(@AuthenticationPrincipal AuthUserDTO authUser,
                               @Valid UserDTO.Password passwordDTO) {

        userService.updatePassword(
                authUser.getId(),
                passwordDTO.getCurrentPassword(),
                passwordDTO.getNewPassword(),
                passwordDTO.getCheckPassword()
        );
    }
}
