package com.example.authserver.controller;

import com.example.authserver.dto.UserDTO;
import com.example.authserver.security.dto.AuthUserDTO;
import com.example.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserDTO.MyInfo getUserInfo(@AuthenticationPrincipal AuthUserDTO authUser){
        return userService.getUserInfo(authUser.getId());
    }

    @PutMapping
    public void updateUser(@AuthenticationPrincipal AuthUserDTO authUser,
                           @RequestBody @Valid UserDTO.Update updateDTO) {

        userService.updateUser(
                authUser.getId(),
                updateDTO.getName()
        );
    }

    @PutMapping("/password")
    public void changePassword(@AuthenticationPrincipal AuthUserDTO authUser,
                               @RequestBody @Valid UserDTO.Password passwordDTO) {

        userService.updatePassword(
                authUser.getId(),
                passwordDTO.getCurrentPassword(),
                passwordDTO.getNewPassword(),
                passwordDTO.getCheckPassword()
        );
    }
}
