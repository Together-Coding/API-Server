package com.example.authserver.service;

import com.example.authserver.domain.Participant;
import com.example.authserver.dto.ParticipantDTO;

import java.util.List;

public interface ParticipantService {

    List<ParticipantDTO> getParticipantList(Long courseId);

    List<Participant> getCoursesThatIamStudentByUserId(Long userId);

    List<Participant> getCoursesThatIamTeacherByUserId(Long userId);
}
