package com.example.authserver.service;

import com.example.authserver.dto.ParticipantDTO;

import java.util.List;

public interface ParticipantService {

    List<ParticipantDTO> getParticipantList(Long courseId);
}
