package com.example.authserver.controller;


import com.example.authserver.dto.UserDTO;
import com.example.authserver.security.dto.AuthUserDTO;
import com.example.authserver.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/participant")
@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @PutMapping
    public void updateNickname(@AuthenticationPrincipal AuthUserDTO authUser,
                               @RequestBody @Valid UserDTO.Nickname dto) {

        participantService.changeNickname(
                authUser.getId(),
                dto.getCourseId(),
                dto.getNickname()
        );
    }
}
