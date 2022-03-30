package com.example.authserver.service;

import com.example.authserver.domain.Participant;
import com.example.authserver.domain.User;
import com.example.authserver.dto.ParticipantDTO;
import com.example.authserver.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantRepository participantRepository;

    /**
     *
     * @param courseId 코스pk
     * @return 수업듣는 사람 목록
     */
    @Override
    @Transactional
    public List<ParticipantDTO> getParticipantList(Long courseId) {
        List<Participant> participants = participantRepository.getAllByCourse_Id(courseId);
        List<ParticipantDTO> dto = new ArrayList<>();
        for (Participant part : participants) {
            User user = part.getUser();
            dto.add(ParticipantDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .build());
        }
        return dto;
    }

}
